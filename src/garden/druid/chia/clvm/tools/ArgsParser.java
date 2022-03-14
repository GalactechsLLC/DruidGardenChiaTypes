package garden.druid.chia.clvm.tools;

import garden.druid.chia.clvm.runtime.SExp;

public class ArgsParser {
	
	public static SExp createArgs(SExp... args) {
		SExp rtn = args[args.length-1].cons(SExp.NULL);
		for(int i = args.length-2; i >= 0 ; i--) {
			rtn = args[i].cons(rtn);
		}
		return rtn;
	}

}
