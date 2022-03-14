package garden.druid.chia.clvm.runtime;

import garden.druid.chia.crypt.bls_blst_bindings.BLS;
import garden.druid.chia.types.Tuple;
import garden.druid.chia.types.bytes.Bytes;
import garden.druid.chia.types.bytes.Bytes48;
import supranational.blst.SecretKey;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

public class Operations {
	
	public static Result opIf (SExp args) {
	    if(args.listLength() != 3){
	    	throw new RuntimeException("i takes exactly 3 arguments" + args);
	    }
	    SExp r = args.rest();
	    if (args.first().isNull()) {
	        return new Result(Costs.IF_COST, r.rest().first());
	    }
	    return new Result(Costs.IF_COST, r.first());
	}
	
	public static Result opCons(SExp args) {
	    if(args.listLength() != 2) {
	    	throw new RuntimeException("c takes exactly 2 arguments" + args);
	    }
	    return new Result(Costs.CONS_COST, args.first().cons(args.rest().first()));
	}
	
	public static Result opFirst(SExp args) {
	    if (args.listLength() != 1) {
	    	throw new RuntimeException("f takes exactly 1 argument" + args);
	    }
	    return new Result(Costs.FIRST_COST, args.first().first());
	}
	
	public static Result opRest(SExp args) {
	    if(args.listLength() != 1){
	    	throw new RuntimeException("r takes exactly 1 argument" + args);
	    }
	    return new Result(Costs.REST_COST, args.first().rest());
	}
	
	public static Result opListp(SExp args) {
	    if(args.listLength() != 1) {
	    	throw new RuntimeException("l takes exactly 1 argument" + args);
	    }
	    return new Result(Costs.LISTP_COST, args.first().isList() ? SExp.TRUE : SExp.FALSE);
	}
	
	public static Result opRaise(SExp args) {
	    throw new RuntimeException("clvm raise: " + args.toString());
	}
	
	public static Result opEq(SExp args){
	    if(args.listLength() != 2) {
	    	throw new RuntimeException("= takes exactly 2 arguments" + args);
	    }
	    SExp a0 = args.first();
	    SExp a1 = args.rest().first();
	    if(a0.getPair() != null || a1.getPair() != null){
	    	throw new RuntimeException("= on list" + (a0.getPair() !=null ? a0 : a1).toString());
	    }
	    Atom b0 = a0.getAtom();
	    Atom b1 = a1.getAtom();
	    BigInteger cost = Costs.EQ_BASE_COST;
	    cost = cost.add(BigInteger.valueOf(b0.length() + b1.length()).multiply(Costs.EQ_COST_PER_BYTE));
	    return new Result(cost, b0.equals(b1) ? SExp.TRUE : SExp.FALSE);
	}
	
	public static Result opSha256(SExp args) {
		BigInteger cost = Costs.SHA256_BASE_COST;
	    int arg_len = 0;
	    MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		    for(SExp CLVMObject : args.asList()) {
		        Atom atom = CLVMObject.getAtom();
		        if(atom == null) {
		        	throw new RuntimeException("sha256 on list" + CLVMObject);
		        }
		        arg_len += atom.length();
		        cost = cost.add(Costs.SHA256_COST_PER_ARG);
		        md.update(atom.getBytes());
		    }
		    cost = cost.add(BigInteger.valueOf(arg_len).multiply(Costs.SHA256_COST_PER_BYTE));
		    return createResult(cost, new SExp(md.digest()));
		} catch (NoSuchAlgorithmException e) { // Shouldn't Ever Happen
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Tuple<BigInteger, BigInteger>> asInts(String op_name, SExp args) {
		ArrayList<Tuple<BigInteger, BigInteger>> rtn = new ArrayList<>();
	    for (SExp arg : args.asList()) {
	        if(arg.getPair() != null) {
	        	throw new RuntimeException(op_name + " requires int args" + arg);
	        }
	        rtn.add(new Tuple<>(arg.asBigInteger(), BigInteger.valueOf(arg.getAtom().length())));
	    }
	    return rtn;
	}
	
	public static ArrayList<Tuple<BigInteger, BigInteger>> asInts(String op_name, SExp args, int count) {
		ArrayList<Tuple<BigInteger, BigInteger>> int_list = new ArrayList<Tuple<BigInteger, BigInteger>>() {
			private static final long serialVersionUID = 5050373669964210749L;
			{addAll(asInts(op_name, args));}
		};
	    if (int_list.size() != count) {
	    	String plural = count != 1 ? "s" : "";
	        throw new RuntimeException(op_name + " takes exactly " + count + " argument" +plural + " " + args.toString());
	    }
	    return int_list;
	}
	
	public static ArrayList<BigInteger> asInt32(String op_name, SExp args) {
		ArrayList<BigInteger> rtn = new ArrayList<>();
	    for(SExp arg : args.asList()) {
	        if(arg.getPair() != null) {
	        	throw new RuntimeException(op_name + " requires int32 args" + arg);
	        }
	        if (arg.getAtom().length() > 4) {
	        	throw new RuntimeException(op_name + " requires int32 args (with no leading zeros)" + arg);
	        }
	        rtn.add(arg.asBigInteger());
	    }
	    return rtn;
	}
	
	public static ArrayList<SExp> asBools(String op_name, SExp args) {
		ArrayList<SExp> rtn = new ArrayList<>();
		for(SExp arg : args.asList()) {
	        Atom v = arg.getAtom();
	        if (v.length() == 0){
	        	rtn.add(SExp.FALSE);
	        } else {
	        	rtn.add(SExp.TRUE);
	        }
		}
		return rtn;
	}
	
	public static ArrayList<SExp> asBools(String op_name, SExp args, int count) {
		ArrayList<SExp> bool_list = asBools(op_name, args);
	    if (bool_list.size() != count) {
	        String plural = count != 1 ? "s" : "";
	        throw new RuntimeException(op_name + " takes exactly " + count + " argument" +plural + " " + args);
	    }
	    return bool_list;
	}
	
	public static Result opAdd(SExp args) {
		BigInteger total = BigInteger.ZERO;
		BigInteger cost = Costs.ARITH_BASE_COST;
		BigInteger arg_size = BigInteger.ZERO;
	    for (Tuple<BigInteger, BigInteger> tup : asInts("+", args)) {
	        total = total.add(tup.getFirst());
	        arg_size = arg_size.add(tup.getSecond());
	        cost = cost.add(Costs.ARITH_COST_PER_ARG);
	    }
	    cost = arg_size.multiply(Costs.ARITH_COST_PER_BYTE);
	    return createResult(cost, new SExp(total));
	}
	
	public static Result opSubtract(SExp args) {
		BigInteger cost = Costs.ARITH_BASE_COST;
	    if(args.isNull()) {
	        return createResult(cost, new SExp(0));
	    }
	    BigInteger sign = BigInteger.ONE;
	    BigInteger total = BigInteger.ZERO;
	    BigInteger arg_size = BigInteger.ZERO;
	    for(Tuple<BigInteger, BigInteger> tup : asInts("-", args)) {
	        total = total.add(sign.multiply(tup.getFirst()));
	        sign = BigInteger.valueOf(-1);
	        arg_size = arg_size.add(BigInteger.ONE);
	        cost = cost.add(Costs.ARITH_COST_PER_ARG);
	    }
	    cost = cost.add(arg_size.multiply(Costs.ARITH_COST_PER_BYTE));
	    return createResult(cost, new SExp(total));
	}
	
	public static Result opMultiply(SExp args) {
		BigInteger cost = Costs.MUL_BASE_COST;
		ArrayList<Tuple<BigInteger, BigInteger>> operands = asInts("*", args);
	    try {
	    	Tuple<BigInteger, BigInteger> vop = operands.get(0);
	    	Tuple<BigInteger, BigInteger> rtn = vop;
		    for(int i = 1; i < operands.size(); i++ ) {
		    	Tuple<BigInteger, BigInteger> rop = operands.get(i);
		        cost = cost.add(Costs.MUL_COST_PER_OP);
		        cost = cost.add(rop.getSecond().add(rtn.getSecond()).multiply(Costs.MUL_LINEAR_COST_PER_BYTE));
		        cost = cost.add(new BigDecimal(rop.getSecond().multiply(rtn.getSecond())).divide(new BigDecimal(Costs.MUL_SQUARE_COST_PER_BYTE_DIVIDER), RoundingMode.FLOOR).toBigInteger());
		        rtn.setFirst(rtn.getFirst().multiply(rop.getFirst()));
		        rtn.setSecond(BigInteger.valueOf((rtn.getFirst().bitLength() + 7) >> 3));
		    }
		    return createResult(cost, new SExp(new SExp(new Atom(rtn.getFirst()))));
	    } catch (Exception e) {
	    	return createResult(cost, new SExp(new SExp(1)));
	    }
	}
	
	public static Result opDivmod(SExp args) {
		BigInteger cost = Costs.DIVMOD_BASE_COST;
		ArrayList<Tuple<BigInteger, BigInteger>> /*(i0, l0), (i1, l1)*/ list = asInts("divmod", args, 2);
	    if (list.get(1).getFirst().compareTo(BigInteger.ZERO) == 0) {
	    	throw new RuntimeException("divmod with 0: " + new SExp(new SExp(list.get(1).getFirst())));
	    }
	    cost = cost.add(list.get(0).getSecond().add(list.get(1).getSecond()).multiply(Costs.DIVMOD_COST_PER_BYTE));
	    BigInteger[] qr = list.get(0).getFirst().divideAndRemainder(list.get(1).getFirst());
	    //q, r = divmod(i0, i1);
	    SExp q1 = new SExp(new SExp(qr[0]));
	    SExp r1 = new SExp(new SExp(qr[1]));
	    cost = cost.add(BigInteger.valueOf(q1.getAtom().getBytes().length).add(BigInteger.valueOf(r1.getAtom().getBytes().length)).multiply(Costs.MALLOC_COST_PER_BYTE));
	    SExp obj = new SExp(new Atom(qr[0]), new Pair(new SExp(qr[1]), null));
	    return new Result(cost, new SExp(obj));
	}
	
	public static Result opDiv(SExp args) {
		BigInteger cost = Costs.DIV_BASE_COST;
		ArrayList<Tuple<BigInteger, BigInteger>> /*(i0, l0), (i1, l1)*/ list = asInts("/", args, 2);
	    if(list.get(1).getFirst().compareTo(BigInteger.ZERO) == 0) {
	        throw new RuntimeException("div with 0: " + new SExp(new SExp(list.get(0).getFirst())));
	    }
	    cost = cost.add(list.get(0).getSecond().add(list.get(1).getSecond()).multiply(Costs.DIV_COST_PER_BYTE));
	    BigInteger q = new BigDecimal(list.get(0).getFirst()).divide(new BigDecimal(list.get(1).getFirst()), RoundingMode.FLOOR).toBigInteger();
	    return createResult(cost, new SExp(new SExp(q)));
	}
	
	public static Result opGr(SExp args) {
		ArrayList<Tuple<BigInteger, BigInteger>> list = asInts(">", args, 2);
	    BigInteger cost = Costs.GR_BASE_COST;
	    cost = cost.add(list.get(0).getSecond().add(list.get(1).getSecond()).multiply(Costs.GR_COST_PER_BYTE));
	    return new Result(cost, list.get(0).getFirst().compareTo(list.get(1).getFirst()) > 0 ? SExp.TRUE : SExp.FALSE);
	}
	
	public static Result opGrBytes(SExp args) {
		ArrayList<SExp> arg_list = args.asList();
	    if(arg_list.size() != 2) {
	    	throw new RuntimeException(">s takes exactly 2 arguments: " + args);
	    }
	    SExp a0 = arg_list.get(0);
	    SExp a1 = arg_list.get(1);
	    if (a0.getPair() != null || a1.getPair() != null) {
	    	throw new RuntimeException(">s on list" + (a0.getPair() != null ? a0.toString() : a1.toString()));
	    }
	    BigInteger cost = Costs.GRS_BASE_COST;
	    cost = cost.add(BigInteger.valueOf(a0.getAtom().getBytes().length).add(BigInteger.valueOf(a1.getAtom().getBytes().length)).multiply(Costs.GRS_COST_PER_BYTE));
	    return new Result(cost, new BigInteger(a0.getAtom().getBytes()).compareTo(new BigInteger(a1.getAtom().getBytes())) > 0 ? SExp.TRUE : SExp.FALSE);
	}
	
	public static Result opPubkeyForExp(SExp args) {
		ArrayList<Tuple<BigInteger, BigInteger>> /*((i0, l0),)*/ list = asInts("pubkey_for_exp", args, 1);
		SecretKey exponent = BLS.loadKey(list.get(0).getFirst());
	    try {
	    	SExp r = new SExp(new SExp(BLS.getPublicKey(exponent).compress()));
	    	BigInteger cost = Costs.PUBKEY_BASE_COST;
	        cost = cost.add(list.get(0).getSecond().multiply(Costs.PUBKEY_COST_PER_BYTE));
	        return createResult(cost, r);
	    } catch(Exception ex) {
	    	ex.printStackTrace();
	    	throw new RuntimeException("problem in op_pubkey_for_exp: " + args);
	    }
	}
	
	public static Result opPointAdd(SExp items) {
	    BigInteger cost = Costs.POINT_ADD_BASE_COST;
	    byte[] p = new byte[0];//G1Element();
	    for (SExp sObj : items.asList()) {
	        if(sObj.getPair() != null) {
	        	throw new RuntimeException("point_add on list" + sObj);
	        }
	        try {
	            p = Bytes.add(p, new Bytes48(sObj.getAtom().getBytes()).getBytes());
	            cost = cost.add(Costs.POINT_ADD_COST_PER_ARG);
	        } catch (Exception e) {
	        	throw new RuntimeException("point_add expects blob, got " + sObj + ": " + items);
	        }
	    }
	    return createResult(cost, new SExp(new SExp(p)));
	}
	
	public static Result opStrlen(SExp args) {
	    if(args.listLength() != 1) {
	    	throw new RuntimeException("strlen takes exactly 1 argument" + args);
	    }
	    SExp a0 = args.first();
	    if(a0.getPair() != null) {
	    	throw new RuntimeException("strlen on list" + a0);
	    }
	    int size = a0.getAtom().length();
	    BigInteger cost = Costs.STRLEN_BASE_COST.add(BigInteger.valueOf(size).multiply(Costs.STRLEN_COST_PER_BYTE));
	    return createResult(cost, new SExp(new SExp(size)));
	}
	
	public static Result opSubstr(SExp args) {
	    int arg_count = args.listLength();
	    if (arg_count != 2 && arg_count != 3) { 
	    	throw new RuntimeException("substr takes exactly 2 or 3 arguments" + args);
	    }
	    SExp a0 = args.first();
	    if(a0.getPair() != null) {
	    	throw new RuntimeException("substr on list" + a0);
	    }
	
	    Atom s0 = a0.getAtom();
	    int i1, i2;
	    if(arg_count == 2) {
	        i1 = asInt32("substr", args.rest()).get(0).intValue();
	        i2 = s0.length();
	    } else {
	    	ArrayList<BigInteger> argList = asInt32("substr", args.rest());
	        i1 = argList.get(0).intValue();
	        i2 = argList.get(1).intValue();
	    }
	    if(i2 > s0.length() || i2 < i1 || i2 < 0 || i1 < 0) {
	    	throw new RuntimeException("invalid indices for substr" + args);
	    }
	    byte[] s = Arrays.copyOfRange(s0.getBytes(), i1, i2);
	    int cost = 1;
	    return new Result(BigInteger.valueOf(cost), new SExp(new SExp(s)));
	}
	
	public static Result opConcat(SExp args) {
	    BigInteger cost = Costs.CONCAT_BASE_COST;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    for(SExp arg : args.asList()) {
	        if(arg.getPair() != null) {
	        	throw new RuntimeException("concat on list" + arg);
	        }
	        try {
				baos.write(arg.getAtom().getBytes());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
	        cost = cost.add(Costs.CONCAT_COST_PER_ARG);
	    }
	    byte[] r = baos.toByteArray();
	    cost = cost.add(BigInteger.valueOf(r.length).multiply(Costs.CONCAT_COST_PER_BYTE));
	    return createResult(cost, new SExp(new SExp(r)));
	}
	
	public static Result opAsh(SExp args) {
		ArrayList<Tuple<BigInteger, BigInteger>> list = asInts("ash", args, 2);
		Tuple<BigInteger, BigInteger> first = list.get(0);
		Tuple<BigInteger, BigInteger> second = list.get(1);
	    if(second.getSecond().compareTo(BigInteger.valueOf(4)) > 0) {
	    	throw new RuntimeException("ash requires int32 args (with no leading zeros): " + args.rest().first());
	    }
	    if(second.getFirst().abs().compareTo(BigInteger.valueOf(65535)) > 0) {
	    	throw new RuntimeException("shift too large: " + new SExp(new SExp(second.getFirst())));
	    }
	    BigInteger r;
	    if(second.getFirst().compareTo(BigInteger.ZERO) >= 0) {
	        r = first.getFirst().shiftLeft(second.getFirst().intValue());
	    } else {
	        r = first.getFirst().shiftRight(second.getFirst().negate().intValue());
	    }
	    BigInteger cost = Costs.ASHIFT_BASE_COST;
	    cost = cost.add(first.getSecond().add(BigInteger.valueOf((r.bitLength() + 7) >> 3)).multiply(Costs.ASHIFT_COST_PER_BYTE));
	    return createResult(cost, new SExp(new SExp(r)));
	}
	
	public static Result opLsh(SExp args) {
		ArrayList<Tuple<BigInteger, BigInteger>> list = asInts("lsh", args, 2);
		Tuple<BigInteger, BigInteger> first = list.get(0);
		Tuple<BigInteger, BigInteger> second = list.get(1);
	    if(second.getSecond().compareTo(BigInteger.valueOf(4)) > 0) {
	        throw new RuntimeException("lsh requires int32 args (with no leading zeros): " + args.rest().first());
	    }
	    if(second.getFirst().abs().compareTo(BigInteger.valueOf(65535)) > 0) {
	    	throw new RuntimeException("shift too large: " + new SExp(new SExp(second.getFirst())));
	    }
	    BigInteger unsigned = new BigInteger(1, first.getFirst().toByteArray());
	    BigInteger r;
	    if(second.getFirst().compareTo(BigInteger.ZERO) >= 0) {
	        r = unsigned.shiftLeft(second.getFirst().intValue());
	    } else {
	        r = unsigned.shiftRight(second.getFirst().negate().intValue());
	    }
	    BigInteger cost = Costs.LSHIFT_BASE_COST;
	    cost = cost.add(first.getSecond().add(BigInteger.valueOf((r.bitLength() + 7) >> 3)).multiply(Costs.LSHIFT_COST_PER_BYTE));
	    return createResult(cost, new SExp(new SExp(r)));
	}
	
	public static Result opLogand(SExp args) {
		BigInteger total = BigInteger.ONE.negate();
		BigInteger arg_size = BigInteger.ZERO;
		BigInteger cost = Costs.LOG_BASE_COST;
	    for(Tuple<BigInteger, BigInteger> rl : asInts("logand", args)) {
	        total = total.and(rl.getFirst());
	        arg_size = arg_size.add(rl.getSecond());
	        cost = cost.add(Costs.LOG_COST_PER_ARG);
	    }
	    cost = cost.add(arg_size.multiply(Costs.LOG_COST_PER_BYTE));
	    return createResult(cost, new SExp(new SExp(total)));
	}
	
	public static Result opLogior(SExp args) {
		BigInteger total = BigInteger.ZERO;
		BigInteger arg_size = BigInteger.ZERO;
		BigInteger cost = Costs.LOG_BASE_COST;
	    for(Tuple<BigInteger, BigInteger> rl : asInts("logior", args)) {
	        total = total.or(rl.getFirst());
	        arg_size = arg_size.add(rl.getSecond());
	        cost = cost.add(Costs.LOG_COST_PER_ARG);
	    }
	    cost = cost.add(arg_size.multiply(Costs.LOG_COST_PER_BYTE));
	    return createResult(cost, new SExp(new SExp(total)));
	}
	
	public static Result opLogxor(SExp args) {
		BigInteger total = BigInteger.ZERO;
		BigInteger arg_size = BigInteger.ZERO;
		BigInteger cost = Costs.LOG_BASE_COST;
	    for(Tuple<BigInteger, BigInteger> rl : asInts("logxor", args)) {
	        total = total.xor(rl.getFirst());
	        arg_size = arg_size.add(rl.getSecond());
	        cost = cost.add(Costs.LOG_COST_PER_ARG);
	    }
	    cost = cost.add(arg_size.multiply(Costs.LOG_COST_PER_BYTE));
	    return createResult(cost, new SExp(new SExp(total)));
//	    def binop(a, b):
//	        a ^= b;
//	        return a;
//	
//	    return binop_reduction("logxor", 0, args, binop);
	}
	
	public static Result opLognot(SExp args) {
		Tuple<BigInteger, BigInteger> tup = asInts("lognot", args, 1).get(0);
	    BigInteger cost = Costs.LOGNOT_BASE_COST.add(tup.getSecond().multiply(Costs.LOGNOT_COST_PER_BYTE));
	    BigInteger not = tup.getFirst().not();
	    return createResult(cost, new SExp(new SExp(new Atom(not))));
	}
	
	public static Result opNot(SExp args) {
	    SExp i0 = asBools("not", args, 1).get(0);
	    SExp r;
	    if(i0.getAtom().length() == 0){
	        r = SExp.TRUE;
	    } else {
	        r = SExp.FALSE;
	    }
	    BigInteger cost = Costs.BOOL_BASE_COST;
	    return new Result(cost, new SExp(new SExp(r)));
	}
	
	public static Result opAny(SExp args) {
		ArrayList<SExp> items = asBools("any", args);
		BigInteger cost = Costs.BOOL_BASE_COST.add(BigInteger.valueOf(items.size()).multiply(Costs.BOOL_COST_PER_ARG));
		SExp r = SExp.FALSE;
	    for (SExp v : items) {
	        if(v.getAtom().length() != 0) {
	            r = SExp.TRUE;
	            break;
	        }
	    }
	    return new Result(cost, new SExp(new SExp(r)));
	}	
	
	public static Result opAll(SExp args) {
	    ArrayList<SExp> items = asBools("all", args);
	    BigInteger cost = Costs.BOOL_BASE_COST.add(BigInteger.valueOf(items.size()).multiply(Costs.BOOL_COST_PER_ARG));
	    SExp r = SExp.TRUE;
	    for (SExp v : items) {
	        if(v.getAtom().length() == 0) {
	            r = SExp.FALSE;
	            break;
	        }
	    }
	    return new Result(cost, new SExp(new SExp(r)));
	}
	
	public static Result opSoftfork(SExp args) {
	    if(args.listLength() < 1) {
	    	throw new RuntimeException("softfork takes at least 1 argument: " + args);
	    }
	    SExp a = args.first();
	    if(a.getPair() != null) {
	    	throw new RuntimeException("softfork requires int args: " + a);
	    }
	    BigInteger cost = a.asBigInteger();
	    if(cost.compareTo(BigInteger.ONE) < 0) {
	    	throw new RuntimeException("cost must be > 0: " + args);
	    }
	    return new Result(cost, SExp.FALSE);
	}
	
	public static Result createResult(BigInteger cost, SExp atom){
	    return new Result(cost.add(BigInteger.valueOf(atom.getAtom().length()).multiply(Costs.MALLOC_COST_PER_BYTE)), atom);
	}
}
