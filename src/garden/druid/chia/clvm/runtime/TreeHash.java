package garden.druid.chia.clvm.runtime;

import garden.druid.chia.crypt.sha.SHA;
import garden.druid.chia.types.bytes.Bytes;
import garden.druid.chia.types.bytes.Bytes32;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class TreeHash {
	
	interface RuntimeOperator{  
		void run(LinkedList<RuntimeOperator> opStack, LinkedList<SExp> sexpStack, Set<Bytes32> precalculated);
	}  
	
	private static void roll(LinkedList<RuntimeOperator> opStack, LinkedList<SExp> sexpStack, Set<Bytes32> precalculated) {
		SExp p0 = sexpStack.removeLast();
		SExp p1 = sexpStack.removeLast();
        sexpStack.add(p0);
        sexpStack.add(p1);
	}
	
	private static void handlePair(LinkedList<RuntimeOperator> opStack, LinkedList<SExp> sexpStack, Set<Bytes32> precalculated) {
		SExp p0 = sexpStack.removeLast();
		SExp p1 = sexpStack.removeLast();
        sexpStack.add(new SExp(SHA.hash256(Bytes.add("\2".getBytes(), Bytes.add(p0.getAtom().getBytes(), p1.getAtom().getBytes())))));
	}
	
    private static void handleSexp(LinkedList<RuntimeOperator> opStack, LinkedList<SExp> sexpStack, Set<Bytes32> precalculated) {
    	SExp sexp = sexpStack.removeLast();
        if(sexp.getPair() != null) {
            sexpStack.add(sexp.first());
            sexpStack.add(sexp.rest());
            opStack.add(TreeHash::handlePair);
            opStack.add(TreeHash::handleSexp);
            opStack.add(TreeHash::roll);
            opStack.add(TreeHash::handleSexp);
    	} else {
    		SExp r;
            if(sexp.getAtom().length() == 32 && precalculated.contains(new Bytes32(sexp.getAtom().getBytes()))) {
                r = new SExp(sexp.getAtom());
            } else {
                r = new SExp(SHA.hash256(Bytes.add("\1".getBytes() , sexp.getAtom().getBytes())));
            }
            sexpStack.add(r);
    	}
    }
	
	public static Bytes32 getHash(SExp sexp, Set<Bytes32> _precalculated) {
		LinkedList<RuntimeOperator> opStack;
		LinkedList<SExp> sexpStack;
		Set<Bytes32> precalculated;
	    if(_precalculated == null) {
	    	precalculated = new HashSet<>();
	    } else {
	    	precalculated = _precalculated;
	    }
	    sexpStack = new LinkedList<>();
	    sexpStack.add(sexp);
	    opStack = new LinkedList<>();
	    opStack.add(TreeHash::handleSexp);
	    while(opStack.size() > 0){
	    	RuntimeOperator op = opStack.removeLast();
	        op.run(opStack, sexpStack, precalculated);
	    }
	    return new Bytes32(sexpStack.get(0).asBigInteger().toByteArray());
    }
}
