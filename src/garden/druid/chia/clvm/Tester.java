package garden.druid.chia.clvm;

import garden.druid.chia.clvm.runtime.Parser;
import garden.druid.chia.clvm.runtime.Result;
import garden.druid.chia.clvm.runtime.Runtime;
import garden.druid.chia.clvm.runtime.SExp;
import garden.druid.chia.clvm.tools.ArgsParser;
import garden.druid.chia.types.bytes.Bytes;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

public class Tester {

	private static final BigInteger NO_MAX = BigInteger.ONE.negate();

	public static void testCreateCoin(SExp cargs, String expectedResult) {
		System.out.println("Testing Create Coin");
		byte[] bytes = Bytes.parseHexBinary("ff02ffff01ff02ff02ffff04ff02ffff04ff0bffff04ff17ff8080808080ffff04ffff0133ff018080");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		String coin = result.getResult().toString();
		assert expectedResult.equals(coin);
		System.out.println("Create Coin Passed: " + coin + " = " + expectedResult);
	}
	
	public static void testComplicated(SExp cargs, String expectedResult) {
		System.out.println("Testing Complicated");
		byte[] bytes = Bytes.parseHexBinary("ff0effff0188526573756c743a20ffff0bffff10ff05ff02ffff12ff05ff02ffff13ff05ff028080808080");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		String hash = result.getResult().getAtom().getBytes().toString();
		assert expectedResult.equals(hash);
		System.out.println("Complicated Passed: " + hash + " = " + expectedResult);
	}
	
	public static void testEquals(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Equals");
		byte[] bytes = Bytes.parseHexBinary("ff09ff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		BigInteger asInt = result.getResult().getAtom().asInteger();
		assert Objects.equals(asInt, expectedResult);
		System.out.println("Equals Passed: " + asInt + " = " + expectedResult);
	}
	
	public static void testGts(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Gts");
		byte[] bytes = Bytes.parseHexBinary("ff0aff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("Gts Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
	
	public static void testSha(SExp cargs, String expectedResult) {
		System.out.println("Testing SHA");
		byte[] bytes = Bytes.parseHexBinary("ff0bff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Arrays.toString(result.getResult().getAtom().getBytes()).equals(expectedResult);
		System.out.println("SHA Passed: " + Arrays.toString(result.getResult().getAtom().getBytes()) + " = " + expectedResult);
	}
	
	public static void testSubstr(SExp cargs, String expectedResult) {
		System.out.println("Testing Substr");
		byte[] bytes = Bytes.parseHexBinary("ff0cff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert result.getResult().getAtom().toString().equals(expectedResult);
		System.out.println("Substr Passed: " + result.getResult().getAtom().toString() + " = " + expectedResult);
	}
	
	public static void testStrlen(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Strlen");
		byte[] bytes = Bytes.parseHexBinary("ff0dff0280");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("Strlen Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
	
	public static void testConcat(SExp cargs, String expectedResult) {
		System.out.println("Testing Concat");
		byte[] bytes = Bytes.parseHexBinary("ff0eff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert result.getResult().getAtom().toString().equals(expectedResult);
		System.out.println("Concat Passed: " + result.getResult().getAtom().toString() + " = " + expectedResult);
	}
	
	public static void testAddition(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Addition");
		byte[] bytes = Bytes.parseHexBinary("ff10ff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("Addition Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
	
	public static void testSubtraction(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Subtraction");
		byte[] bytes = Bytes.parseHexBinary("ff11ff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("Subtraction Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
	
	public static void testMultiplication(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Multiplication");
		byte[] bytes = Bytes.parseHexBinary("ff12ff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("Multiplication Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
	
	public static void testDiv(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Div");
		byte[] bytes = Bytes.parseHexBinary("ff13ff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("Div Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
	
	public static void testDivmod(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Divmod");
		byte[] bytes = Bytes.parseHexBinary("ff14ff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("Divmod Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
	
	public static void testGt(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Gt");
		byte[] bytes = Bytes.parseHexBinary("ff15ff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("Gt Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
	
	public static void testAsh(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Ash");
		byte[] bytes = Bytes.parseHexBinary("ff16ff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("Ash Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
	
	public static void testLsh(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Lsh");
		byte[] bytes = Bytes.parseHexBinary("ff17ff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("Lsh Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
	
	public static void testLogand(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Logand");
		byte[] bytes = Bytes.parseHexBinary("ff18ff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("Logand Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
	
	public static void testLogior(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Logior");
		byte[] bytes = Bytes.parseHexBinary("ff19ff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("Logior Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
	
	public static void testLogxor(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Logxor");
		byte[] bytes = Bytes.parseHexBinary("ff1aff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("Logxor Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
	
	public static void testLognot(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Lognot");
		byte[] bytes = Bytes.parseHexBinary("ff1bff0280");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("Lognot Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
	
	public static void testNot(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Not");
		byte[] bytes = Bytes.parseHexBinary("ff20ff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("Not Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
	
	public static void testAny(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Any");
		byte[] bytes = Bytes.parseHexBinary("ff21ff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("Any Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
	
	public static void testAll(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing All");
		byte[] bytes = Bytes.parseHexBinary("ff22ff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("All Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
	
	public static void testSoftfork(SExp cargs, BigInteger expectedResult) {
		System.out.println("Testing Softfork");
		byte[] bytes = Bytes.parseHexBinary("ff24ff02ff0580");
		SExp program = Parser.parse(bytes);
		Runtime runtime = new Runtime(program, cargs, NO_MAX);
		Result result = runtime.run();
		assert Objects.equals(result.getResult().getAtom().asInteger(), expectedResult);
		System.out.println("Softfork Passed: " + result.getResult().getAtom().asInteger() + " = " + expectedResult);
	}
}