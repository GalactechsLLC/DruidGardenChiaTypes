package garden.druid.chia.clvm.runtime;

import java.math.BigInteger;

public class Program {
	
	private final SExp program;
	private final byte[] serializedData;
	
	public Program(byte[] serializedData) {
		this.serializedData = serializedData;
		this.program = Parser.parse(serializedData);
	}
	
	public Result run(SExp args, BigInteger maxCost) {
		return new Runtime(this.program, args, maxCost).run();
	}
	
	public byte[] toByteArray() {
		return this.serializedData;
	}
}
