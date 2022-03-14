package garden.druid.chia.clvm.runtime;

import java.util.HashMap;

public class Operators {
	
	interface Operator{  
		Result run (SExp clvmObject);  
	}  
	
	//Core Opcodes
	public static final byte CORE_DOT = 0x00;
	public static final byte CORE_Q = 0x01; //Quote
	public static final byte CORE_A = 0x02; //Apply
	public static final byte CORE_I = 0x03; //Takes the form (i A B C) and acts as an if-statement that evaluates to B if A is True and C otherwise
	public static final byte CORE_C = 0x04; //Cons (Prepend To List)
	public static final byte CORE_F = 0x05; //Returns First
	public static final byte CORE_R = 0x06; //Returns Rest
	public static final byte CORE_L = 0x07; //listp
	public static final byte CORE_X = 0x08; //Raise Exception
	
	//Atom String Opcodes
	public static final byte ATOM_STRING_EQ = 0x09;
	public static final byte ATOM_STRING_GT = 0x0a;
	public static final byte ATOM_STRING_SHA = 0x0b;
	public static final byte ATOM_STRING_SUBSTR = 0x0c;
	public static final byte ATOM_STRING_STRLEN = 0x0d;
	public static final byte ATOM_STRING_CONCAT = 0x0e;
	
	//Atom byte Opcodes
	public static final byte ATOM_byte_ADD = 0x10;
	public static final byte ATOM_byte_SUB = 0x11;
	public static final byte ATOM_byte_MUL = 0x12;
	public static final byte ATOM_byte_DIV = 0x13;
	public static final byte ATOM_byte_DIVMOD = 0x14;
	public static final byte ATOM_byte_GT = 0x15;
	public static final byte ATOM_byte_ASH = 0x16;
	public static final byte ATOM_byte_LSH = 0x17;
	
	//Atom Vector of bool Opcodes
	public static final byte VECTOR_BOOL_LOGAND = 0x18;
	public static final byte VECTOR_BOOL_LOGIOR = 0x19;
	public static final byte VECTOR_BOOL_LOGXOR = 0x1a;
	public static final byte VECTOR_BOOL_LOGNOT = 0x1b;
	
	//Atom BLS Opcodes
	public static final byte BLS_POINT_ADD = 0x1d;
	public static final byte BLS_PUBKEY_FOR_EXP = 0x1e;
	
	//bool opcodes
	public static final byte BOOL_NOT = 0x20;
	public static final byte BOOL_ANY = 0x21;
	public static final byte BOOL_ALL = 0x22;
	
	//Misc Opcodes
	public static final byte SOFTFORK = 0x24;
	
	private static final HashMap<Byte, Operator> opMap = new HashMap<Byte, Operator>() {
		private static final long serialVersionUID = 1L;
		{
			//Core Ops
			put(CORE_I, Operations::opIf);
			put(CORE_C, Operations::opCons);
			put(CORE_F, Operations::opFirst);
			put(CORE_R, Operations::opRest);
			put(CORE_L, Operations::opListp);
			put(CORE_X, Operations::opRaise);
			
			//String Ops
			put(ATOM_STRING_EQ, Operations::opEq);
			put(ATOM_STRING_GT, Operations::opGrBytes);
			put(ATOM_STRING_SHA, Operations::opSha256);
			put(ATOM_STRING_SUBSTR, Operations::opSubstr);
			put(ATOM_STRING_STRLEN, Operations::opStrlen);
			put(ATOM_STRING_CONCAT, Operations::opConcat);
			
			//Atom Ops
			put(ATOM_byte_ADD, Operations::opAdd);
			put(ATOM_byte_SUB, Operations::opSubtract);
			put(ATOM_byte_MUL, Operations::opMultiply);
			put(ATOM_byte_DIV, Operations::opDiv);
			put(ATOM_byte_DIVMOD, Operations::opDivmod);
			put(ATOM_byte_GT, Operations::opGr);
			put(ATOM_byte_ASH, Operations::opAsh);
			put(ATOM_byte_LSH, Operations::opLsh);
			
			//Vector Ops
			put(VECTOR_BOOL_LOGAND, Operations::opLogand);
			put(VECTOR_BOOL_LOGIOR, Operations::opLogior);
			put(VECTOR_BOOL_LOGXOR, Operations::opLogxor);
			put(VECTOR_BOOL_LOGNOT, Operations::opLognot);
			
			//BLS Ops
			put(BLS_POINT_ADD, Operations::opPointAdd);
			put(BLS_PUBKEY_FOR_EXP, Operations::opPubkeyForExp);
			
			//Boolean Ops
			put(BOOL_NOT, Operations::opNot);
			put(BOOL_ANY, Operations::opAny);
			put(BOOL_ALL, Operations::opAll);
			
			//Softfork
			put(SOFTFORK, Operations::opSoftfork);
		}
	};
	
	private static final HashMap<Byte, String> keywordFromAtomMap = new HashMap<Byte, String>() {
		private static final long serialVersionUID = 1L;
		{
			//Core Ops
			put(CORE_Q, "q");
			put(CORE_A, "a");
			put(CORE_I, "i");
			put(CORE_C, "c");
			put(CORE_F, "f");
			put(CORE_R, "r");
			put(CORE_L, "l");
			put(CORE_X, "x");
			
			//String Ops
			put(ATOM_STRING_EQ, "=");
			put(ATOM_STRING_GT, ">s");
			put(ATOM_STRING_SHA, "sha256");
			put(ATOM_STRING_SUBSTR, "substr");
			put(ATOM_STRING_STRLEN, "strlen");
			put(ATOM_STRING_CONCAT, "concat");
			
			//Atom Ops
			put(ATOM_byte_ADD, "+");
			put(ATOM_byte_SUB, "-");
			put(ATOM_byte_MUL, "*");
			put(ATOM_byte_DIV, "/");
			put(ATOM_byte_DIVMOD, "divmod");
			put(ATOM_byte_GT, ">");
			put(ATOM_byte_ASH, "ash");
			put(ATOM_byte_LSH, "lsh");
			
			//Vector Ops
			put(VECTOR_BOOL_LOGAND, "logand");
			put(VECTOR_BOOL_LOGIOR, "logior");
			put(VECTOR_BOOL_LOGXOR, "logxor");
			put(VECTOR_BOOL_LOGNOT, "lognot");
			
			//BLS Ops
			put(BLS_POINT_ADD, "point_add");
			put(BLS_PUBKEY_FOR_EXP, "pubkey_for_exp");
			
			//Boolean Ops
			put(BOOL_NOT, "not");
			put(BOOL_ANY, "any");
			put(BOOL_ALL, "all");
			
			//Softfork
			put(SOFTFORK, "softfork");
		}
	};
	
	private static final HashMap<String, Byte> atomFromKeyWordMap = new HashMap<String, Byte>() {
		private static final long serialVersionUID = 1L;
		{
			for(Entry<Byte,String> entry: keywordFromAtomMap.entrySet()) {
				put(entry.getValue(), entry.getKey());
			}
		}
	};
	
	private static final HashMap<String, String> rewriteMap = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("add", "+");
			put("subtract", "-");
			put("multiply", "*");
			put("div", "/");
			put("if", "i");
			put("cons", "c");
			put("first", "f");
			put("rest", "r");
			put("listp", "l");
			put("raise", "x");
			put("eq", "=");
			put("gr", ">");
			put("gr_bytes", ">s");
		}
	};
	
	public static byte byteFromKeyword(String key) {
		return atomFromKeyWordMap.get(rewriteMap.getOrDefault(key, key));
	}
	
	public static String keywordFromByte(byte key) {
		return keywordFromAtomMap.get(key);
	}
	
	public static Operator get(Byte key) {
		return opMap.get(key);
	}
}
