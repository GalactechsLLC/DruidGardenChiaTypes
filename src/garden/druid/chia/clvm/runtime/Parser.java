package garden.druid.chia.clvm.runtime;

import garden.druid.chia.types.bytes.Bytes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

public class Parser {

	private final static int MAX_SINGLE_BYTE = 0x7F;
	private final static int NULL_BYTE = 0x80;

	public static SExp parse(byte[] serialData) {
		ByteArrayInputStream stream = new ByteArrayInputStream(serialData);
		try {
			return readObject(stream, stream.read());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static SExp readObject(ByteArrayInputStream stream, int cur) throws Exception {
		if(cur == 0x80) {//Null, 0, Empty ext
			return new SExp(new byte[0]);
		} else if(cur == 0xff) {//start cons box, read two items
			int read = stream.read();
			byte first = (byte)read;
			if(first == 0x01) {
				System.out.println("quote");
			}
			return new SExp(new Pair(readObject(stream, read), readObject(stream, stream.read())));
		} else {
			return new SExp(readAtom(stream, cur));
		}
	}
	
	private static byte[] readAtom(ByteArrayInputStream stream, int start) throws Exception {
		if (start == NULL_BYTE) {
	        return new byte[] {(byte)start};
		}
		if (start <= MAX_SINGLE_BYTE) {
	        return new byte[] {(byte)start};
		}
	    int bit_count = 0;
	    int bit_mask = NULL_BYTE;
	    int tmp = start;
	    while((tmp & bit_mask) > 0) {
	        bit_count += 1;
	        tmp &= 0xFF ^ bit_mask;
	        bit_mask >>= 1;
	    }
	    byte[] size_blob = new byte[] {(byte)tmp};
	    if(bit_count > 1) {
	        byte[] tmp2 = new byte[bit_count - 1];
	        for(int i = 0; i < tmp2.length; i++) {
	        	int read = stream.read();
	        	if(read == -1) throw new Exception("Bad Encoding");
	        	tmp2[i] = (byte) read;
	        }
	        size_blob = Bytes.add(size_blob, tmp2);
	    }
	    int size = new BigInteger(1, size_blob).intValueExact();
	    if (size >= Integer.MAX_VALUE) {
	    	throw new Exception("blob too large");
	    }
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    int read = 0;
	    while(read < size) {
	    	byte[] buf = new byte[1024];
	    	int tmpRead = stream.read(buf, 0, Math.min(buf.length, size-read));
	    	if(tmpRead > 0) {
	    		output.write(buf, 0, (byte)tmpRead);
		    	read += tmpRead;
	    	}
	    }
	    byte[] blob = output.toByteArray();
	    if(blob.length != size) {
	    	throw new Exception("Bad Encoding");
	    }
	    return blob;
	}
}
