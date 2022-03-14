package garden.druid.chia.types.ints;

import com.google.gson.annotations.JsonAdapter;
import garden.druid.chia.types.jsonAdapters.NativeUInt8Adapter;

import java.math.BigInteger;
import java.nio.ByteBuffer;

@JsonAdapter(NativeUInt8Adapter.class)
public class NativeUInt8 extends NativeUnsigned<NativeUInt8>{

	public static final NativeUInt8 ZERO = new NativeUInt8(BigInteger.ZERO);
	public static final NativeUInt8 ONE = new NativeUInt8(BigInteger.ONE);
	public static final NativeUInt8 TEN = new NativeUInt8(BigInteger.TEN);

	private static final long serialVersionUID = 1L;

	private Byte internal = 0;

	public NativeUInt8() {}
	
	public NativeUInt8(Number other) {
		this.internal = other.byteValue();
	}

	@Override
	public NativeUInt8 add(Number other) {
		return new NativeUInt8(this.internal + other.byteValue());
	}

	@Override
	public NativeUInt8 sub(Number other) {
		return new NativeUInt8(this.internal - other.byteValue());
	}

	@Override
	public NativeUInt8 mul(Number other) {
		return new NativeUInt8(this.internal * other.byteValue());
	}

	@Override
	public NativeUInt8 div(Number other) {
		return new NativeUInt8(Integer.divideUnsigned(this.internal, other.byteValue()));
	}

	@Override
	public NativeUInt8[] divMod(Number other) {
		NativeUInt8[] rtn = new NativeUInt8[2];
		rtn[0] = new NativeUInt8(Integer.divideUnsigned(this.internal, other.byteValue()));
		rtn[1] = new NativeUInt8(Integer.remainderUnsigned(this.internal, other.byteValue()));
		return rtn;
	}

	@Override
	public NativeUInt8 divFloor(Number other) {
		return div(other);
	}

	@Override
	public NativeUInt8 pow(Number other) {
		return new NativeUInt8(this.internal ^ other.byteValue());
	}

	@Override
	public NativeUInt8 min(Number other) {
		if(this.compareTo(other) <= 0){
			return this;
		} else {
			return new NativeUInt8(other);
		}
	}

	@Override
	public NativeUInt8 max(Number other) {
		if(this.compareTo(other) >= 0){
			return this;
		} else {
			return new NativeUInt8(other);
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

	public byte[] toByteArray() {
		byte[] rtn = new byte[Byte.BYTES];
		ByteBuffer buf = ByteBuffer.wrap(rtn);
		buf.put(this.internal);
		return rtn;
	}

	@Override
	public String toString() {
		return Integer.toUnsignedString(this.internal);
	}

	@Override
	public NativeUInt8 fromString(String toParse) {
		return new NativeUInt8(Integer.parseUnsignedInt(toParse));
	}

	@Override
	public BigInteger toBigInteger() {
		return new BigInteger(1, this.toByteArray());
	}
}

