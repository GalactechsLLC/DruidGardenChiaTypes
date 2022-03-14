package garden.druid.chia.crypt.bech32;

import garden.druid.base.logging.Logger;
import garden.druid.chia.types.Tuple;
import garden.druid.chia.types.bytes.Bytes32;

import java.util.ArrayList;
import java.util.logging.Level;

public class Bech32 {

	private static final String CHARSET = "qpzry9x8gf2tvdw0s3jn54khce6mua7l";

    private static final int[] generator = new int[] {0x3B6A57B2, 0x26508E6D, 0x1EA119FA, 0x3D4233DD, 0x2A1462B3};
    
    private static final int M = 0x2BC830A3;

    private static int polymod(final int[] values) {
    	int chk = 1;
	    for(int value : values) {
	    	int top = chk >> 25;
	        chk = (chk & 0x1FFFFFF) << 5 ^ value;
	        for(int i=0; i < 5; i++){
	        	if( ((top >> i) & 1) > 0) {
	        		chk ^= generator[i];
	        	} else {
	        		chk ^= 0;
	        	}
	        }
	    }
	    return chk;
    }

    private static int[] expand(final String hrp) {
    	int[] rtn = new int[hrp.length()*2+1];
    	char[] hrp_ary = hrp.toCharArray();
    	int index = 0;
    	for(char chr : hrp_ary) {
    		rtn[index] = chr >> 5;
    		index++;
    	}
    	rtn[index] = 0;
    	index++;
		for(char chr : hrp_ary) {
    		rtn[index] = chr & 31;
			index++;
    	}
    	return rtn;
    }

    private static boolean verifyChecksum(final String hrp, final int[] data) {
    	int[] hrp_ary = expand(hrp);
    	int[] rtn = new int[hrp_ary.length + data.length];
    	int index = 0;
    	for(int i : hrp_ary) {
    		rtn[index] = i;
    		index++;
    	}
    	for(int i : data) {
    		rtn[index] = i;
    		index++;
    	}
    	return polymod(rtn) == M;
    }

    private static int[] createChecksum(final String hrp, final int[] data)  {
    	int[] hrp_ary = expand(hrp);
    	int[] values = new int[hrp_ary.length + data.length + 6];
    	int index = 0;
    	for(int i : hrp_ary) {
    		values[index] = i;
    		index++;
    	}
    	for(int i : data) {
    		values[index] = i;
    		index++;
    	}
    	for(int i = 0; i < 6; i ++) {
    		values[index] = 0;
    		index++;
    	}
        int p_mod = polymod(values) ^ M;
        int[] rtn = new int[6];
    	for(int i = 0; i < 6; i ++) {
    		rtn[i] = (p_mod >> 5 * (5 - i)) & 31;
    	}
        return rtn;
    }

    public static String encode(String hrp, final int[] data) {
    	int[] chk_sum = createChecksum(hrp, data);
    	int[] combined = new int[chk_sum.length + data.length];
    	int index = 0;
    	for(int i : data) {
    		combined[index] = i;
    		index++;
    	}
    	for(int i : chk_sum) {
    		combined[index] = i;
    		index++;
    	}
    	StringBuilder builder = new StringBuilder();
    	builder.append(hrp.toLowerCase());
    	builder.append("1");
    	for(int i : combined) {
    		builder.append(CHARSET.charAt(i));
    	}
        return builder.toString();
    }

    public static int[] convertbits(int[] data, int frombits, int tobits, boolean pad) throws Exception {
    	int acc = 0;
    	int bits = 0;
    	ArrayList<Integer> ret = new ArrayList<Integer>();
    	int maxv = (1 << tobits) - 1;
    	int max_acc = (1 << (frombits + tobits - 1)) - 1;
        for(int value : data) {
            if(value < 0 || (value >> frombits) > 0 ) {
                throw new Exception("Invalid Value");
            }
            acc = ((acc << frombits) | value) & max_acc;
            bits += frombits;
            while (bits >= tobits) {
                bits -= tobits;
                ret.add((acc >> bits) & maxv);
            }
        }
        if(pad) {
            if(bits > 0) {
                ret.add((acc << (tobits - bits)) & maxv);
            }
        } else if(bits >= frombits || ((acc << (tobits - bits)) & maxv) > 0) {
        	throw new Exception("Invalid bits");
        }
        int[] rtn = new int[ret.size()];
        for(int i =0; i < ret.size(); i++) {
        	rtn[i] = ret.get(i);
        }
        return rtn;
    }
    
    public static Tuple<String, int[]> decode(final String bech) {
    	for (int i = 0; i < bech.length(); i++) {
    		char c = bech.charAt(i);
    		if (c < 33 || c > 126) {
    			Logger.getInstance().log(Level.SEVERE, "Invalid Bech chracter");
    			return null;
    		}
    	}
        if (!bech.toLowerCase().equals(bech) && !bech.toUpperCase().equals(bech)){
        	Logger.getInstance().log(Level.SEVERE, "Invalid Bech casing");
            return null;
        }
        String lbech = bech.toLowerCase();
        final int pos = lbech.lastIndexOf('1');
        if (pos < 1 || pos + 7 > bech.length() || bech.length() > 90) {
        	Logger.getInstance().log(Level.SEVERE, "Invalid Bech Length");
            return null;
        }
        int[] rtn = new int[bech.length() - 1 - pos];
        int index = 0;
        for(int i = pos +1; i < bech.length(); i++) {
        	char chr = lbech.charAt(i);
        	if(CHARSET.indexOf(chr) == -1) {
        		Logger.getInstance().log(Level.SEVERE, "Invalid Bech chracter2: " + chr);
        		return null;
        	} else {
        		rtn[index] = CHARSET.indexOf(chr);
        		index++;
        	}
        }
        String hrp = lbech.substring(0, pos);
        if(!verifyChecksum(hrp, rtn)) {
        	Logger.getInstance().log(Level.SEVERE, "Invalid Bech checksum");
            return null;
        }
        int[] ary = new int[rtn.length - 6];
        for(int i = 0; i < ary.length; i ++) {
        	ary[i] = (byte) rtn[i];
        }
        return new Tuple<>(hrp, ary);
    }
    
    public static String encodePuzzleHash(Bytes32 puzzle_hash, String prefix) {
    	try {
    		int[] bytes = new int[puzzle_hash.getBytes().length];
			for(int i = 0; i < bytes.length; i ++) {
				bytes[i] = puzzle_hash.getBytes()[i] >= 0 ? puzzle_hash.getBytes()[i] : puzzle_hash.getBytes()[i] + 256;
			}
			return Bech32.encode(prefix, convertbits(bytes, 8, 5, true));
		} catch (Exception e) {
			Logger.getInstance().log(Level.SEVERE, "Error in Bech32.encodePuzzleHash", e);
		}
    	return null;
    }
    
    public static Bytes32 decodePuzzleHash(String address) {
    	Tuple<String, int[]> data = Bech32.decode(address);
    	int[] decoded;
		try {
			decoded = convertbits(data.getSecond(), 5, 8, false);
			byte[] bytes = new byte[decoded.length];
			for(int i = 0; i < bytes.length; i ++) {
				bytes[i] = (byte)decoded[i];
			}
			return new Bytes32(bytes);
		} catch (Exception e) {
			Logger.getInstance().log(Level.SEVERE, "Error in Bech32.decodePuzzleHash", e);
		}
		return null;
    }    
}
