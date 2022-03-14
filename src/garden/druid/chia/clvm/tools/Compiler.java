package garden.druid.chia.clvm.tools;

import garden.druid.chia.clvm.runtime.Operators;
import garden.druid.chia.clvm.runtime.SExp;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

public class Compiler {
	
	public static SExp compile(String source) {
		SExp exp = null;
		try {
			ArrayList<Token> tokens = tokenize(source);
			ArrayList<SExp> expressions = new ArrayList<>();
			Iterator<Token> iter = tokens.iterator();
			Token token;
			do {
				if(iter.hasNext()) {
					token = iter.next();
					SExp parsed = parseToken(token, iter);
					expressions.add(parsed);
				} else {
					token = null;
				}
			} while (token != null);
			exp = expressions.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exp;
	}
	
	private static SExp parseToken(Token token, Iterator<Token> tokens) throws Exception {
		if("(".equals(token.getToken())) {
			return parseCons(tokens.next(), tokens);
		}
		SExp rtn;
		rtn = parseInt(token);
		if(rtn != null) {
			return rtn;
		}
		rtn = parseHex(token);
		if(rtn != null) {
			return rtn;
		}
		rtn = parseQuotes(token);
		if(rtn != null) {
			return rtn;
		}
		rtn = parseSymbol(token);
		return rtn;
	}
	
	private static SExp parseInt(Token token) {
		try {
			BigInteger val = new BigInteger(token.getToken());
			if(val.equals(BigInteger.ZERO)) 
				return SExp.NULL;
			return new SExp(val);
		} catch(Exception ignored) {
			//ignored
		}
		return null;
	}
	
	private static SExp parseHex(Token token) throws Exception {
		if(token.getToken().toUpperCase().startsWith("0X")) {
			 try {
	            String tkn = token.getToken().substring(2);
	            if(tkn.length() % 2 == 1) {
	            	tkn = "0" + tkn;
	            }
	            return new SExp(tkn.getBytes());
			 } catch (Exception e){
	            throw new Exception("invalid hex at "+ token.getOffset() +": " + token.getToken());
	        }
		}
		return null;
	}
	
	private static SExp parseQuotes(Token token) throws Exception {
		if(token.getToken().length() < 2){
	        return null;
		}
	    String c = token.getToken().substring(0, 1);
	    if(!"'\"".contains(c)) {
	        return null;
	    }

	    if(!c.equals(token.getToken().substring(token.getToken().length() -1))){
	    	 throw new Exception("unterminated string starting at " + token.getOffset() +": " + token.getToken());
	    }
	    return new SExp(token.getToken().substring(1, token.getToken().length() -1).getBytes(StandardCharsets.UTF_8));
	}
	
	private static SExp parseSymbol(Token token) {
		try {
			byte b;
			if(token.getToken().startsWith("#")) {
				b = Operators.byteFromKeyword(token.getToken().substring(1));
			} else {
				b = Operators.byteFromKeyword(token.getToken());
			}
			return new SExp(new byte[] {b});
		} catch(Exception e) {
			System.out.println("Failed Symbol: " + token);
			return new SExp(token.getToken().getBytes(StandardCharsets.UTF_8));
		}
	}
	
	private static SExp parseCons(Token token, Iterator<Token> tokens) throws Exception {
		if(")".equals(token.getToken())){
	        return SExp.NULL;
		}
	    SExp first_sexp = parseToken(token, tokens);
	    token = tokens.next();
	    SExp rest_sexp;
	    if(".".equals(token.getToken())){
	        int dot_offset = token.getOffset();
	        //grab the last item;
	        token = tokens.next();
	        rest_sexp = parseToken(token, tokens).cons(SExp.NULL);
	        token = tokens.next();
	        if(!")".equals(token.getToken())){
	            throw new Exception("illegal dot expression at " + dot_offset);
	        }
	    } else {
	    	rest_sexp = parseCons(token, tokens);
	    }
	    return first_sexp.cons(rest_sexp);
	}
	
	private static ArrayList<Token> tokenize(String source) throws Exception{
		int offset = 0;
		ArrayList<Token> tokens = new ArrayList<>();
	    while(offset < source.length()) {
	        offset = consume_whitespace(source, offset);
	        if(offset >= source.length()) {
	            break;
	        }
	        char c = source.charAt(offset);
	        if("(.)".indexOf(c) > -1) {
	            tokens.add(new Token(""+c, offset));
	            offset += 1;
	            continue;
	        }
	        if("\"'".indexOf(c) > -1) {
	            int start = offset;
	            char initial_c = source.charAt(start);
	            offset += 1;
	            while(offset < source.length() && source.charAt(offset) != initial_c) {
	                offset += 1;
	            }
	            if(offset < source.length()) {
	            	tokens.add(new Token(source.substring(start,offset + 1), start));
	                offset += 1;
	                continue;
	            } else {
	                throw new Exception("unterminated string starting at " + start + ": " + source.substring(start));
	            }
	        }
	        Token token = consume_until_whitespace(source, offset);
	        tokens.add(token);
	        offset = token.getOffset();
	    }
	    return tokens;
	}
	
	private static int consume_whitespace(String s, int offset) {
	    while(true){
	        while(offset < s.length() && (s.charAt(offset)+"").trim().isEmpty()){
	            offset += 1;
	        }
	        if(offset >= s.length() || s.charAt(offset) != ';') {
	            break;
	        }
	        while(offset < s.length() && !"\n\r".contains(""+s.charAt(offset))){
	            offset += 1;
	        }
	    }
	    return offset;
	}

	private static Token consume_until_whitespace(String s, int offset) {
		int start = offset;
	    while(offset < s.length() && !(s.charAt(offset)+"").trim().isEmpty() && s.charAt(offset) != ')') {
	        offset += 1;
	    }
	    return new Token(s.substring(start,offset), offset);
	}
}
