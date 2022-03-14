package garden.druid.chia.types.ints;

import org.bouncycastle.util.Arrays;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class NativeUInt256  extends NativeUnsigned<NativeUInt256> {

	public static final NativeUInt256 ZERO = new NativeUInt256(BigInteger.ZERO);
	public static final NativeUInt256 ONE = new NativeUInt256(BigInteger.ONE);
	public static final NativeUInt256 TEN = new NativeUInt256(BigInteger.TEN);
	private static final long serialVersionUID = 1L;

	private BigInteger internal = BigInteger.ZERO;
	private static final byte[] MAX_ARY = new byte[]{
			-1, -1, -1, -1,
			-1, -1, -1, -1,
			-1, -1, -1, -1,
			-1, -1, -1, -1,
			-1, -1, -1, -1,
			-1, -1, -1, -1,
			-1, -1, -1, -1,
			-1, -1, -1, -1};
	private static final BigInteger MAX_BINT = new BigInteger(1, MAX_ARY);
	public static final NativeUInt256 MAX_VALUE = new NativeUInt256(MAX_BINT);

	public NativeUInt256() {}
	
	public NativeUInt256(Number other) {
		BigInteger asInt;
		if(other instanceof BigInteger) {
			BigInteger oInt = (BigInteger) other;
			if(oInt.compareTo(BigInteger.ZERO) < 0) {
				oInt = new BigInteger(1, oInt.toByteArray());
			}
			asInt = oInt;
		} else {
			asInt = (BigInteger.valueOf(other.longValue()));
		}
		byte[] asAry = asInt.toByteArray();
		if(asAry.length == 17 && asAry[0] == 1) {
			asInt = new BigInteger(1, Arrays.copyOfRange(asAry, 1, asAry.length));
		}
		if(MAX_BINT.compareTo(asInt) < 0) {
			throw new ArithmeticException("Invalid UInt128, too Large: " + asInt);
		}
		this.internal = asInt;	
	}

	@Override
	public NativeUInt256 add(Number other) {
		if(other instanceof BigInteger) {
			BigInteger oInt = (BigInteger) other;
			if(oInt.compareTo(BigInteger.ZERO) < 0) {
				oInt = new BigInteger(1, oInt.toByteArray());
			}
			return new NativeUInt256(this.internal.add(oInt));
		} else if(other instanceof NativeUnsigned) {
			NativeUnsigned<?> nativeUn = (NativeUnsigned<?>) other;
			return new NativeUInt256(this.internal.add(new BigInteger(1, nativeUn.toByteArray())));
		} else {
			return new NativeUInt256(this.internal.add(BigInteger.valueOf(other.longValue())));
		}
	}

	@Override
	public NativeUInt256 sub(Number other) {
		if(other instanceof BigInteger) {
			BigInteger oInt = (BigInteger) other;
			if(oInt.compareTo(BigInteger.ZERO) < 0) {
				oInt = new BigInteger(1, oInt.toByteArray());
			}
			return new NativeUInt256(this.internal.add(oInt));
		} else if(other instanceof NativeUnsigned) {
			NativeUnsigned<?> nativeUn = (NativeUnsigned<?>) other;
			return new NativeUInt256(this.internal.subtract(new BigInteger(1, nativeUn.toByteArray())));
		} else {
			return new NativeUInt256(this.internal.subtract(BigInteger.valueOf(other.longValue())));
		}
	}

	@Override
	public NativeUInt256 mul(Number other) {
		if(other instanceof BigInteger) {
			BigInteger oInt = (BigInteger) other;
			if(oInt.compareTo(BigInteger.ZERO) < 0) {
				oInt = new BigInteger(1, oInt.toByteArray());
			}
			return new NativeUInt256(this.internal.add(oInt));
		} else if(other instanceof NativeUnsigned) {
			NativeUnsigned<?> nativeUn = (NativeUnsigned<?>) other;
			return new NativeUInt256(this.internal.multiply(new BigInteger(1, nativeUn.toByteArray())));
		} else {
			return new NativeUInt256(this.internal.multiply(BigInteger.valueOf(other.longValue())));
		}
	}

	@Override
	public NativeUInt256 div(Number other) {
		if(other instanceof BigInteger) {
			BigInteger oInt = (BigInteger) other;
			if(oInt.compareTo(BigInteger.ZERO) < 0) {
				oInt = new BigInteger(1, oInt.toByteArray());
			}
			return new NativeUInt256(this.internal.add(oInt));
		} else if(other instanceof NativeUnsigned) {
			NativeUnsigned<?> nativeUn = (NativeUnsigned<?>) other;
			return new NativeUInt256(this.internal.divide(new BigInteger(1, nativeUn.toByteArray())));
		} else {
			return new NativeUInt256(this.internal.divide(BigInteger.valueOf(other.longValue())));
		}
	}

	@Override
	public NativeUInt256[] divMod(Number other) {
		if(other instanceof BigInteger) {
			BigInteger oInt = (BigInteger) other;
			if(oInt.compareTo(BigInteger.ZERO) < 0) {
				oInt = new BigInteger(1, oInt.toByteArray());
			}
			BigInteger[] ints = this.internal.divideAndRemainder(oInt);
			NativeUInt256[] rtn = new NativeUInt256[2];
			rtn[0] = new NativeUInt256(ints[0]);
			rtn[1] = new NativeUInt256(ints[1]);
			return rtn;
		}  else if(other instanceof NativeUnsigned) {
			NativeUnsigned<?> nativeUn = (NativeUnsigned<?>) other;
			BigInteger[] ints =this.internal.divideAndRemainder(new BigInteger(1, nativeUn.toByteArray()));
			NativeUInt256[] rtn = new NativeUInt256[2];
			rtn[0] = new NativeUInt256(ints[0]);
			rtn[1] = new NativeUInt256(ints[1]);
			return rtn;
		} else {
			BigInteger[] ints = this.internal.divideAndRemainder(new BigInteger(1, BigInteger.valueOf(other.longValue()).toByteArray()));
			NativeUInt256[] rtn = new NativeUInt256[2];
			rtn[0] = new NativeUInt256(ints[0]);
			rtn[1] = new NativeUInt256(ints[1]);
			return rtn;
		}
	}

	@Override
	public NativeUInt256 divFloor(Number other) {
		if(other instanceof BigInteger) {
			return new NativeUInt256(new BigDecimal(this.internal).divide(new BigDecimal((BigInteger)other), RoundingMode.FLOOR).toBigInteger());
		} else if(other instanceof NativeUnsigned) {
			return new NativeUInt256(new BigDecimal(this.internal).divide(new BigDecimal(((NativeUnsigned<?>)other).toBigInteger()), RoundingMode.FLOOR).toBigInteger());
		} else if(other instanceof Long) {
			return new NativeUInt256(new BigDecimal(this.internal).divide(new BigDecimal(other.longValue()), RoundingMode.FLOOR).toBigInteger());
		} else if(other instanceof Integer) {
			return new NativeUInt256(new BigDecimal(this.internal).divide(new BigDecimal(other.intValue()), RoundingMode.FLOOR).toBigInteger());
		} else if(other instanceof Short) {
			return new NativeUInt256(new BigDecimal(this.internal).divide(new BigDecimal(other.shortValue()), RoundingMode.FLOOR).toBigInteger());
		} else if(other instanceof Byte) {
			return new NativeUInt256(new BigDecimal(this.internal).divide(new BigDecimal(other.byteValue()), RoundingMode.FLOOR).toBigInteger());
		}
		return new NativeUInt256(new BigDecimal(this.internal).divide(BigDecimal.valueOf(other.doubleValue()), RoundingMode.FLOOR).toBigInteger());
	}

	@Override
	public NativeUInt256 pow(Number other) {
		return new NativeUInt256(this.longValue() ^ other.longValue());
	}

	@Override
	public NativeUInt256 min(Number other) {
		if(this.compareTo(other) <= 0){
			return this;
		} else {
			return new NativeUInt256(other);
		}
	}

	@Override
	public NativeUInt256 max(Number other) {
		if(this.compareTo(other) >= 0){
			return this;
		} else {
			return new NativeUInt256(other);
		}
	}

	@Override
	public int compareTo(Number arg0) {
		BigInteger toCompare = null;
		if(arg0 instanceof BigInteger) {
			BigInteger oInt = (BigInteger) arg0;
			if(oInt.compareTo(BigInteger.ZERO) < 0) {
				oInt = new BigInteger(1, oInt.toByteArray());
			}
			toCompare = oInt;
		} else {
			toCompare = (BigInteger.valueOf(arg0.longValue()));
		}
		return this.internal.compareTo(toCompare);
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
		byte[] ary = this.internal.toByteArray();
		if(ary.length%2!=0 && (ary[0] & 0) == 0) {
			ary = Arrays.copyOfRange(ary, 1, ary.length);
		}
		if(ary.length < 16) {
			//pad it
			
		}
		return ary;
	}

	@Override
	public BigInteger toBigInteger() {
		return this.internal;
	}

	@Override
	public String toString() {
		return this.internal.toString();
	}

	@Override  
	public NativeUInt256 fromString(String toParse) {
		return new NativeUInt256(new BigInteger(toParse));
	}
}