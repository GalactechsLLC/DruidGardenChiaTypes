package garden.druid.chia.types.ints;

import java.math.BigInteger;

public abstract class NativeUnsigned<T> extends Number implements Comparable<Number> {

	private static final long serialVersionUID = 1L;
	
	protected Number internal;
	
	public abstract T add(Number other);
	public abstract T sub(Number other);
	public abstract T div(Number other);
	public abstract T divFloor(Number other);
	public abstract T[] divMod(Number other);
	public abstract T mul(Number other);
	public abstract T min(Number other);
	public abstract T max(Number other);
	public abstract T pow(Number other);
	public abstract byte[] toByteArray();
	public abstract BigInteger toBigInteger();
	public abstract String toString();
	public abstract NativeUnsigned<T> fromString(String toParse);
}
