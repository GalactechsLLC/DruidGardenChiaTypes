package garden.druid.chia.clvm.runtime;

import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.bytes.UnsizedBytes;

import java.math.BigInteger;
import java.util.ArrayList;

public class SExp {
	
	public static final SExp NULL = new SExp(new byte[0]);
	public static final SExp FALSE = new SExp(new byte[0]);
	public static final SExp TRUE = new SExp(new byte[]{1});
	
	private Atom atom;
	private Pair pair;

	public SExp(Object obj) {
		if(obj instanceof SExp) {
			this.atom = ((SExp)obj).getAtom();
			this.pair = ((SExp)obj).getPair();
		} else if(obj instanceof Pair) {
			this.atom = null;
			this.pair = (Pair)obj;
		} else if (obj instanceof byte[]){
			this.atom = new Atom(new UnsizedBytes((byte[])obj));
			this.pair = null;
		} else if (obj instanceof UnsizedBytes){
			this.atom = new Atom((UnsizedBytes)obj);
			this.pair = null;
		} else if (obj instanceof Bytes32){
			this.atom = new Atom(((Bytes32)obj).getBytes());
			this.pair = null;
		} else if (obj instanceof BigInteger){
			this.atom = new Atom((BigInteger)obj);
			this.pair = null;
		} else if (obj instanceof Integer){
			this.atom = new Atom(BigInteger.valueOf((Integer)obj));
			this.pair = null;
		} else {
			this.atom = (Atom)obj;
			this.pair = null;
        }
	}
	
	protected SExp(Atom atom, Pair pair) {
		this.atom = atom;
		this.pair = pair;
	}
	
	public Atom getAtom() {
		return atom;
	}

	public void setAtom(Atom atom) {
		this.atom = atom;
	}

	public Pair getPair() {
		return pair;
	}

	public void setPair(Pair pair) {
		this.pair = pair;
	}
    
	public BigInteger asBigInteger() {
		byte[] blob = this.atom.getBytes();
		if(blob == null || blob.length == 0) {
	        return BigInteger.ZERO;
	    }
	    return new BigInteger(blob);
    }

    public ArrayList<SExp> asList(){
    	SExp v = this;
        ArrayList<SExp> rtn = new ArrayList<>();
        while(!v.isNull()){
            rtn.add(v.first());
            v = v.rest();
        }
        return rtn;
	}

    public SExp first() {
        if(this.pair != null)
            return this.pair.getFirst();
        return SExp.NULL;
    }

    public SExp rest() {
        if(this.pair != null) {
            return this.pair.getRest();
        }
        return SExp.NULL;
    }
	
	public boolean isNull() {
        return this.atom != null && this.atom.length() == 0;
    }
	
	public boolean isList() {
		return this.pair != null;
	}
	
	public String toString() {
		StringBuilder rtn = new StringBuilder();
		if(isList()) {
			rtn.append("(");
			boolean first = true;
			ArrayList<SExp> list = this.asList();
			for(SExp exp : list) {
				if(!first) {
					rtn.append(" ");
					rtn.append(exp.toString());
				} else{
					first = false;
					if(exp.getAtom() != null) {
						byte[] ary = exp.getAtom().getBytes();
						if(ary.length == 1 && Operators.keywordFromByte(ary[0]) != null) {
							rtn.append(Operators.keywordFromByte(ary[0]));
						} else{
							rtn.append(exp);
						}
					} else{
						rtn.append(exp);
					}
				}
			}
			rtn.append(")");
		} else if(this.atom != null) {
			rtn.append(this.atom);
		}
		return rtn.toString();
	}
	
    public int listLength() {
        SExp v = this;
        int size = 0;
        while(v.isList()) {
            size++;
            v = v.rest();
        }
        return size;
    }
	
	public SExp cons(SExp other) {
		if(other == null) {
			return new SExp(new Pair(this, SExp.NULL));
		}
		return new SExp(new Pair(this, other));
	}
	
	public byte[] serialize() {
		return null;
	}
}
