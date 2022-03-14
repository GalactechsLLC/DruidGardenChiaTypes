package garden.druid.chia.types.ints;

import org.bouncycastle.util.Arrays;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class NativeUInt128 extends NativeUnsigned<NativeUInt128> {

	public static final NativeUInt128 ZERO = new NativeUInt128(BigInteger.ZERO);
	public static final NativeUInt128 ONE = new NativeUInt128(BigInteger.ONE);
	public static final NativeUInt128 TEN = new NativeUInt128(BigInteger.TEN);
	private static final long serialVersionUID = 1L;

	private BigInteger internal = BigInteger.ZERO;
	private static final byte[] MAX_ARY = new byte[]{
			-1, -1, -1, -1,
			-1, -1, -1, -1,
			-1, -1, -1, -1,
			-1, -1, -1, -1};
	private static final BigInteger MAX_BINT = new BigInteger(1, MAX_ARY);
	public static final NativeUInt128 MAX_VALUE = new NativeUInt128(MAX_BINT);

	public NativeUInt128() {}
	
	public NativeUInt128(Number other) {
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
	public NativeUInt128 add(Number other) {
		if(other instanceof BigInteger) {
			BigInteger oInt = (BigInteger) other;
			if(oInt.compareTo(BigInteger.ZERO) < 0) {
				oInt = new BigInteger(1, oInt.toByteArray());
			}
			return new NativeUInt128(this.internal.add(oInt));
		} else if(other instanceof NativeUnsigned) {
			NativeUnsigned<?> nativeUn = (NativeUnsigned<?>) other;
			return new NativeUInt128(this.internal.add(new BigInteger(1, nativeUn.toByteArray())));
		} else {
			return new NativeUInt128(this.internal.add(BigInteger.valueOf(other.longValue())));
		}
	}

	@Override
	public NativeUInt128 sub(Number other) {
		if(other instanceof BigInteger) {
			BigInteger oInt = (BigInteger) other;
			if(oInt.compareTo(BigInteger.ZERO) < 0) {
				oInt = new BigInteger(1, oInt.toByteArray());
			}
			return new NativeUInt128(this.internal.subtract(oInt));
		} else if(other instanceof NativeUnsigned) {
			NativeUnsigned<?> nativeUn = (NativeUnsigned<?>) other;
			return new NativeUInt128(this.internal.subtract(new BigInteger(1, nativeUn.toByteArray())));
		} else {
			return new NativeUInt128(this.internal.subtract(BigInteger.valueOf(other.longValue())));
		}
	}

	@Override
	public NativeUInt128 mul(Number other) {
		if(other instanceof BigInteger) {
			BigInteger oInt = (BigInteger) other;
			if(oInt.compareTo(BigInteger.ZERO) < 0) {
				oInt = new BigInteger(1, oInt.toByteArray());
			}
			return new NativeUInt128(this.internal.multiply(oInt));
		} else if(other instanceof NativeUnsigned) {
			NativeUnsigned<?> nativeUn = (NativeUnsigned<?>) other;
			return new NativeUInt128(this.internal.multiply(new BigInteger(1, nativeUn.toByteArray())));
		} else {
			return new NativeUInt128(this.internal.multiply(BigInteger.valueOf(other.longValue())));
		}
	}

	@Override
	public NativeUInt128 div(Number other) {
		if(other instanceof BigInteger) {
			BigInteger oInt = (BigInteger) other;
			if(oInt.compareTo(BigInteger.ZERO) < 0) {
				oInt = new BigInteger(1, oInt.toByteArray());
			}
			return new NativeUInt128(this.internal.divide(oInt));
		} else if(other instanceof NativeUnsigned) {
			NativeUnsigned<?> nativeUn = (NativeUnsigned<?>) other;
			return new NativeUInt128(this.internal.divide(new BigInteger(1, nativeUn.toByteArray())));
		} else {
			return new NativeUInt128(this.internal.divide(BigInteger.valueOf(other.longValue())));
		}
	}

	@Override
	public NativeUInt128 divFloor(Number other) {
		if(other instanceof BigInteger) {
			return new NativeUInt128(new BigDecimal(this.internal).divide(new BigDecimal((BigInteger)other), RoundingMode.FLOOR).toBigInteger());
		} else if(other instanceof NativeUnsigned) {
			return new NativeUInt128(new BigDecimal(this.internal).divide(new BigDecimal(((NativeUnsigned<?>)other).toBigInteger()), RoundingMode.FLOOR).toBigInteger());
		} else if(other instanceof Long) {
			return new NativeUInt128(new BigDecimal(this.internal).divide(new BigDecimal(other.longValue()), RoundingMode.FLOOR).toBigInteger());
		} else if(other instanceof Integer) {
			return new NativeUInt128(new BigDecimal(this.internal).divide(new BigDecimal(other.intValue()), RoundingMode.FLOOR).toBigInteger());
		} else if(other instanceof Short) {
			return new NativeUInt128(new BigDecimal(this.internal).divide(new BigDecimal(other.shortValue()), RoundingMode.FLOOR).toBigInteger());
		} else if(other instanceof Byte) {
			return new NativeUInt128(new BigDecimal(this.internal).divide(new BigDecimal(other.byteValue()), RoundingMode.FLOOR).toBigInteger());
		}
		return new NativeUInt128(new BigDecimal(this.internal).divide(BigDecimal.valueOf(other.doubleValue()), RoundingMode.FLOOR).toBigInteger());
	}

	@Override
	public NativeUInt128[] divMod(Number other) {
		if(other instanceof BigInteger) {
			BigInteger oInt = (BigInteger) other;
			if(oInt.compareTo(BigInteger.ZERO) < 0) {
				oInt = new BigInteger(1, oInt.toByteArray());
			}
			BigInteger[] ints = this.internal.divideAndRemainder(oInt);
			NativeUInt128[] rtn = new NativeUInt128[2];
			rtn[0] = new NativeUInt128(ints[0]);
			rtn[1] = new NativeUInt128(ints[1]);
			return rtn;
		}  else if(other instanceof NativeUnsigned) {
			NativeUnsigned<?> nativeUn = (NativeUnsigned<?>) other;
			BigInteger[] ints =this.internal.divideAndRemainder(new BigInteger(1, nativeUn.toByteArray()));
			NativeUInt128[] rtn = new NativeUInt128[2];
			rtn[0] = new NativeUInt128(ints[0]);
			rtn[1] = new NativeUInt128(ints[1]);
			return rtn;
		} else {
			BigInteger[] ints = this.internal.divideAndRemainder(new BigInteger(1, BigInteger.valueOf(other.longValue()).toByteArray()));
			NativeUInt128[] rtn = new NativeUInt128[2];
			rtn[0] = new NativeUInt128(ints[0]);
			rtn[1] = new NativeUInt128(ints[1]);
			return rtn;
		}
	}

	@Override
	public NativeUInt128 pow(Number other) {
		return new NativeUInt128(this.longValue() ^ other.longValue());
	}

	@Override
	public NativeUInt128 min(Number other) {
		if(this.compareTo(other) <= 0){
			return this;
		} else {
			return new NativeUInt128(other);
		}
	}

	@Override
	public NativeUInt128 max(Number other) {
		if(this.compareTo(other) >= 0){
			return this;
		} else {
			return new NativeUInt128(other);
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
	public NativeUInt128 fromString(String toParse) {
		return new NativeUInt128(new BigInteger(toParse));
	}
}
