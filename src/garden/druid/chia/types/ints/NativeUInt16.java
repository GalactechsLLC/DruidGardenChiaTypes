package garden.druid.chia.types.ints;

import java.math.BigInteger;
import java.nio.ByteBuffer;

public class NativeUInt16 extends NativeUnsigned <NativeUInt16>{

	public static final NativeUInt16 ZERO = new NativeUInt16(BigInteger.ZERO);
	public static final NativeUInt16 ONE = new NativeUInt16(BigInteger.ONE);
	public static final NativeUInt16 TEN = new NativeUInt16(BigInteger.TEN);

	private static final long serialVersionUID = 1L;

	private Short internal = 0;

	public NativeUInt16() {}
	
	public NativeUInt16(Number other) {
		this.internal = other.shortValue();
	}

	@Override
	public NativeUInt16 add(Number other) {
		return new NativeUInt16(this.internal + other.shortValue());
	}

	@Override
	public NativeUInt16 sub(Number other) {
		return new NativeUInt16(this.internal - other.shortValue());
	}

	@Override
	public NativeUInt16 mul(Number other) {
		return new NativeUInt16(this.internal * other.shortValue());
	}

	@Override
	public NativeUInt16 div(Number other) {
		return new NativeUInt16(Integer.divideUnsigned(this.internal, other.shortValue()));
	}

	@Override
	public NativeUInt16[] divMod(Number other) {
		NativeUInt16[] rtn = new NativeUInt16[2];
		rtn[0] = new NativeUInt16(Integer.divideUnsigned(this.internal, other.shortValue()));
		rtn[1] = new NativeUInt16(Integer.remainderUnsigned(this.internal, other.shortValue()));
		return rtn;
	}

	@Override
	public NativeUInt16 divFloor(Number other) {
		return div(other);
	}

	@Override
	public NativeUInt16 pow(Number other) {
		return new NativeUInt16(this.internal ^ other.shortValue());
	}

	@Override
	public NativeUInt16 min(Number other) {
		if(this.compareTo(other) <= 0){
			return this;
		} else {
			return new NativeUInt16(other);
		}
	}

	@Override
	public NativeUInt16 max(Number other) {
		if(this.compareTo(other) >= 0){
			return this;
		} else {
			return new NativeUInt16(other);
		}
	}

	@Override
	public int compareTo(Number arg0) {
		return Integer.compareUnsigned(this.intValue(), arg0.intValue());
	}
	
	@Override
	public double doubleValue() {
		return this.internal.doubleValue();
	}

	@Override
	public float floatValue() {
		return this.internal.floatValue();
	}

	@Override
	public int intValue() {
		return this.internal.intValue();
	}

	@Override
	public long longValue() {
		return this.internal.longValue();
	}

	@Override
	public byte[] toByteArray() {
		byte[] rtn = new byte[Short.BYTES];
		ByteBuffer buf = ByteBuffer.wrap(rtn);
		buf.putShort(this.internal);
		return rtn;
	}

	@Override
	public String toString() {
		return Integer.toUnsignedString(this.internal);
	}

	@Override
	public NativeUInt16 fromString(String toParse) {
		return new NativeUInt16(Integer.parseUnsignedInt(toParse));
	}

	@Override
	public BigInteger toBigInteger() {
		return new BigInteger(1, this.toByteArray());
	}
}
