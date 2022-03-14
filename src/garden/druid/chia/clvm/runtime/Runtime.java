package garden.druid.chia.clvm.runtime;

import garden.druid.chia.clvm.runtime.Operators.Operator;

import java.math.BigInteger;
import java.util.LinkedList;

public class Runtime {
	
	interface RuntimeOperator{  
		BigInteger run ();
	}  
	
	private final LinkedList<RuntimeOperator> opStack;
	private final LinkedList<SExp> valueStack;
	
	private final SExp program, args;
	private final BigInteger maxCost;
	
	public Runtime(SExp program, SExp args, BigInteger maxCost) {
		opStack = new LinkedList<>();
		valueStack = new LinkedList<>();
		this.program = program;
		this.args = args;
		this.maxCost = maxCost;
	}
	
	public Result run() {
		opStack.clear();
		valueStack.clear();
		opStack.push(this::evalOp);
		valueStack.push(this.program.cons(this.args));
		BigInteger cost = BigInteger.ZERO;
		boolean useMaxCost = maxCost.compareTo(BigInteger.ZERO) > 0;
		while(!opStack.isEmpty()) {
			RuntimeOperator opCode = opStack.removeLast();
			if(opCode == null) {
				break;
			}
	        cost = cost.add(opCode.run());
	        if(useMaxCost && cost.compareTo(maxCost) > 0) {
	        	throw new RuntimeException("cost exceeded: " + maxCost);
	        }
		}
		return new Result(cost, valueStack.getLast());
	}
	
	private BigInteger swapOp() {
		SExp v2 = valueStack.removeLast();
		SExp v1 = valueStack.removeLast();
		valueStack.add(v2);
		valueStack.add(v1);
        return BigInteger.ZERO;
	}
	
	private BigInteger consOp() {
		SExp v1 = valueStack.removeLast();
		SExp v2 = valueStack.removeLast();
		valueStack.add(v1.cons(v2));
        return BigInteger.ZERO;
	}
	
	private Result traversePath(SExp prog, SExp env) {
		SExp args = env;
		BigInteger cost = Costs.PATH_LOOKUP_BASE_COST;
        cost = cost.add(Costs.PATH_LOOKUP_COST_PER_LEG);
        if(prog.isNull()) {
            return new Result(cost, SExp.NULL);
        }

        byte[] atom = prog.getAtom() != null ? prog.getAtom().getBytes() : new byte[0];
        
        int endIndex = 0;
        int length = atom != null ? atom.length : 0;
        while(endIndex < length && atom[endIndex] == 0) {
            endIndex += 1;
        }

        cost = cost.add(BigInteger.valueOf(endIndex).multiply(Costs.PATH_LOOKUP_COST_PER_ZERO_BYTE));
        if(endIndex == length){
            return new Result(cost, SExp.NULL);
        }

        //create a bitmask for the most significant *set* bit
        //in the last non-zero byte
        byte endBitmask = msb_mask(atom[endIndex]);

        byte index = (byte) (length - 1);
        byte bitmask = 0x01;
        while( index > endIndex || bitmask < endBitmask) {
        	boolean bitSet = (atom[index] & bitmask) != 0;
        	if(args.getPair() == null) {
                throw new RuntimeException("path into atom" +  args);
            }
            if(bitSet) {
            	args = new SExp(args.rest());
            } else {
            	args = new SExp(args.first());
            }
            if(bitmask == 0x80){
                bitmask = 0x01;
                index -= 1;
            } else {
                bitmask <<= 1;
            }
            cost = cost.add(Costs.PATH_LOOKUP_COST_PER_LEG);
        }
        return new Result(cost, args);
	}
	
	private byte msb_mask(byte b) {
	    b |= b >> 1;
	    b |= b >> 2;
	    b |= b >> 4;
	    return (byte) ((b + 1) >> 1);
	}

	private BigInteger evalOp() {
		SExp value = valueStack.removeLast();
		SExp prog = value.first();
		SExp args = value.rest();
		//put a bunch of ops on op_stack
		if(prog.getPair() == null) {
		    //sexp is an atom
			Result traversed = traversePath(prog, args);
		    valueStack.add(traversed.getResult());
		    return traversed.getCost();
		}
		
		SExp operator = prog.first();
		if(operator.getPair() != null) {
			Pair opAsPair = operator.getPair();
		    if(opAsPair.getFirst().getPair() != null || !opAsPair.getRest().isNull()){
	    		throw new RuntimeException("in ((X)...) syntax X must be lone atom: " + prog);
		    }
		    valueStack.add(opAsPair.getFirst());
		    valueStack.add(args);
		    opStack.add(this::applyOp);
		    return Costs.APPLY_COST;
		}
		
		Atom op = operator.getAtom();
		SExp operandList = new SExp(prog.rest());
		if(op.length() == 1 && op.getBytes()[0] == Operators.CORE_Q){
			valueStack.add(operandList);
		    return Costs.QUOTE_COST;
		}
		
		opStack.add(this::applyOp);
		valueStack.add(operator);
		while(!operandList.isNull()){
		    SExp tmp = operandList.first();
		    valueStack.add(tmp.cons(args));
		    opStack.add(this::consOp);
		    opStack.add(this::evalOp);
		    opStack.add(this::swapOp);
		    operandList = operandList.rest();
		}
		valueStack.add(SExp.NULL);
		return BigInteger.ONE;
	}
	
	private BigInteger applyOp() {
    	SExp operandList = valueStack.removeLast();
    	SExp operator = valueStack.removeLast();
        if (operator.getPair() != null) {
        	throw new RuntimeException("internal error" + operator);
        }
        Atom op = operator.getAtom();
        if (op.length() == 1 && op.getBytes()[0] == Operators.CORE_A) {
            if (operandList.listLength() != 2) {
            	throw new RuntimeException("apply requires exactly 2 parameters" + operandList);
    		}
            SExp newProgram = operandList.first();
            SExp newAargs = operandList.rest().first();
            valueStack.add(newProgram.cons(newAargs));
            opStack.add(this::evalOp);
            return Costs.APPLY_COST;
        }
        byte opCode = op.getBytes()[0];
        Operator opr = Operators.get(opCode);
        Result result = opr.run(new SExp(operandList));
        valueStack.add(result.getResult());
        return result.getCost();
    }
	
}
