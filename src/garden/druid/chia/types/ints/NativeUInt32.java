package garden.druid.chia.types.ints;

import java.math.BigInteger;
import java.nio.ByteBuffer;

public class NativeUInt32 extends NativeUnsigned<NativeUInt32> {

	public static final NativeUInt32 ZERO = new NativeUInt32(BigInteger.ZERO);
	public static final NativeUInt32 ONE = new NativeUInt32(BigInteger.ONE);
	public static final NativeUInt32 TEN = new NativeUInt32(BigInteger.TEN);

	private static final long serialVersionUID = 1L;

	private Integer internal = 0;

	public NativeUInt32() {}
	
	public NativeUInt32(Number other) {
		this.internal = other.intValue();
	}

	@Override
	public NativeUInt32 add(Number other) {
		return new NativeUInt32(this.internal + other.intValue());
	}

	@Override
	public NativeUInt32 sub(Number other) {
		return new NativeUInt32(this.internal - other.intValue());
	}

	@Override
	public NativeUInt32 mul(Number other) {
		return new NativeUInt32(this.internal * other.intValue());
	}

	@Override
	public NativeUInt32 div(Number other) {
		return new NativeUInt32(Integer.divideUnsigned(this.internal, other.intValue()));
	}

	@Override
	public NativeUInt32[] divMod(Number other) {
		NativeUInt32[] rtn = new NativeUInt32[2];
		rtn[0] = new NativeUInt32(Integer.divideUnsigned(this.internal, other.intValue()));
		rtn[1] = new NativeUInt32(Integer.remainderUnsigned(this.internal, other.intValue()));
		return rtn;
	}

	@Override
	public NativeUInt32 divFloor(Number other) {
		return div(other);
	}

	@Override
	public NativeUInt32 pow(Number other) {
		return new NativeUInt32(this.internal ^ other.intValue());
	}

	@Override
	public NativeUInt32 min(Number other) {
		if(this.compareTo(other) <= 0){
			return this;
		} else {
			return new NativeUInt32(other);
		}
	}

	@Override
	public NativeUInt32 max(Number other) {
		if(this.compareTo(other) >= 0){
			return this;
		} else {
			return new NativeUInt32(other);
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
		byte[] rtn = new byte[Integer.BYTES];
		ByteBuffer buf = ByteBuffer.wrap(rtn);
		buf.putInt(this.internal);
		return rtn;
	}

	@Override
	public String toString() {
		return Integer.toUnsignedString(this.internal);
	}

	@Override
	public NativeUInt32 fromString(String toParse) {
		return new NativeUInt32(Integer.parseUnsignedInt(toParse));
	}

	@Override
	public BigInteger toBigInteger() {
		return new BigInteger(1, this.toByteArray());
	}
}

