package garden.druid.chia.types.ints;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.BitSet;

public class NativeUInt64 extends NativeUnsigned<NativeUInt64> {

	public static final NativeUInt64 ZERO = new NativeUInt64(BigInteger.ZERO);
	public static final NativeUInt64 ONE = new NativeUInt64(BigInteger.ONE);
	public static final NativeUInt64 TEN = new NativeUInt64(BigInteger.TEN);

	private static final long serialVersionUID = 1L;

	private Long internal = 0l;

	public NativeUInt64() {}
	
	public NativeUInt64(Number other) {
		this.internal = other.longValue();
	}

	@Override
	public NativeUInt64 add(Number other) {
		return new NativeUInt64(this.longValue() + other.longValue());
	}

	@Override
	public NativeUInt64 sub(Number other) {
		return new NativeUInt64(this.longValue() - other.longValue());
	}

	@Override
	public NativeUInt64 mul(Number other) {
		return new NativeUInt64(this.longValue() * other.longValue());
	}

	@Override
	public NativeUInt64 div(Number other) {
		return new NativeUInt64(Long.divideUnsigned(this.longValue(), other.longValue()));
	}

	@Override
	public NativeUInt64[] divMod(Number other) {
		NativeUInt64[] rtn = new NativeUInt64[2];
		rtn[0] = new NativeUInt64(Long.divideUnsigned(this.longValue(), other.longValue()));
		rtn[1] = new NativeUInt64(Long.remainderUnsigned(this.longValue(), other.longValue()));
		return rtn;
	}

	@Override
	public NativeUInt64 divFloor(Number other) {
		return div(other);
	}

	@Override
	public NativeUInt64 pow(Number other) {
		return new NativeUInt64(this.longValue() ^ other.longValue());
	}

	@Override
	public NativeUInt64 min(Number other) {
		if(this.compareTo(other) <= 0){
			return this;
		} else {
			return new NativeUInt64(other);
		}
	}

	@Override
	public NativeUInt64 max(Number other) {
		if(this.compareTo(other) >= 0){
			return this;
		} else {
			return new NativeUInt64(other);
		}
	}

	@Override
	public int compareTo(Number arg0) {
		return Long.compareUnsigned(this.longValue(), arg0.longValue());
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
		byte[] rtn = new byte[Long.BYTES];
		ByteBuffer buf = ByteBuffer.wrap(rtn);
		buf.putLong(this.internal);
		return rtn;
	}
	
	public byte[] chiaIntToBytes() {
		byte[] ary = toByteArray();
		boolean firstByte = BitSet.valueOf(new byte[] { ary[0] }).get(0);
		ByteBuffer byteBuf = ByteBuffer.allocate(Long.BYTES + 1);
		if (firstByte) {
			byteBuf.put((byte) 0);
		}
		boolean trim = true;
		for (int i = 0; i < ary.length; i++) {
			if (trim) {
				if (ary[i] == (byte) 0) {
					continue;
				} else {
					byteBuf.put(ary[i]);
					trim = false;
				}
			} else {
				byteBuf.put(ary[i]);
			}
		}
		byteBuf.flip();
		byte[] toRtn = new byte[byteBuf.limit()];
		byteBuf.get(toRtn, 0, byteBuf.limit());
		return toRtn;
	}

	@Override
	public String toString() {
		return new BigInteger(1, new byte[] { (byte) (this.internal >> 56),
		        (byte) (this.internal >> 48), (byte) (this.internal >> 40), (byte) (this.internal >> 32),
		        (byte) (this.internal >> 24), (byte) (this.internal >> 16), (byte) (this.internal >> 8),
		        (byte) (this.internal >> 0) }).toString();
	}

	@Override
	public NativeUInt64 fromString(String toParse) {
		return new NativeUInt64(Long.parseUnsignedLong(toParse));
	}

	@Override
	public BigInteger toBigInteger() {
		return new BigInteger(1, this.toByteArray());
	}
}
