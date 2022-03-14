package garden.druid.chia.clvmwrapper;

import com.google.gson.Gson;
import garden.druid.base.logging.Logger;
import garden.druid.chia.types.blockchain.Coin;
import garden.druid.chia.types.blockchain.CoinSpend;
import garden.druid.chia.types.blockchain.DelayedPuzInfo;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.bytes.UnsizedBytes;
import garden.druid.chia.types.ints.NativeUInt64;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class Puzzles {
	
	private static final Gson gson = new Gson();

	private static final String SCRIPT_PATH = "/home/tomcat/";
	private static final String SCRIPT_NAME = "chia_utils.py";

	public static Bytes32 validateProof(Bytes32 plot_id, int size, Bytes32 challenge, UnsizedBytes proof) {
		if (plot_id == null || challenge == null || proof == null) {
			return null;
		}
		String rtn = null;
		Process p = null;
		BufferedReader br = null;
		try {
			ProcessBuilder pb = new ProcessBuilder("python3", SCRIPT_NAME, "validate_proof", plot_id.toString(), "" + size, challenge.toString(), proof.toString());
			pb.directory(new File(SCRIPT_PATH));
			p = pb.start();
			p.waitFor();
			String tmp;
			if (p.exitValue() > 0) {
				br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
				while ((tmp = br.readLine()) != null) {
					rtn = tmp;
				}
				rtn = null;
			} else {
				br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((tmp = br.readLine()) != null) {
					rtn = tmp;
				}
			}
			return new Bytes32(rtn);
		} catch (Exception e) {
			rtn = null;
			e.printStackTrace();
		} finally {
			if (p != null && p.isAlive()) {
				p.destroy();
			}
			if (br != null) {
				try {
					br.close();
				} catch (Exception ignored) {
				}
			}
		}
		return null;
	}

	public static DelayedPuzInfo getDelayedPuzInfoFromLauncherSpend(CoinSpend launcher_solution) {
		try {
			if (launcher_solution == null) {
				return null;
			}
			DelayedPuzInfo rtn;
			StringBuilder rtnValue = new StringBuilder();
			Process p = null;
			BufferedReader br = null;
			try {
				ProcessBuilder pb = new ProcessBuilder("python3", SCRIPT_NAME, "get_delayed_puz_info_from_launcher_spend", gson.toJson(launcher_solution));
				pb.directory(new File(SCRIPT_PATH));
				p = pb.start();
				p.waitFor();
				String tmp;
				if (p.exitValue() > 0) {
					br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
					while ((tmp = br.readLine()) != null) {
						Logger.getInstance().log(Level.WARNING, tmp);
					}
				} else {
					br = new BufferedReader(new InputStreamReader(p.getInputStream()));
					while ((tmp = br.readLine()) != null) {
						rtnValue.append(tmp);
						rtnValue.append(System.lineSeparator());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (p != null && p.isAlive()) {
					p.destroy();
				}
				if (br != null) {
					try {
						br.close();
					} catch (Exception ignored) {
					}
				}
			}
			rtn = gson.fromJson(rtnValue.toString(), DelayedPuzInfo.class);
			return rtn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Bytes32 launcherIdToP2PuzzleHash(Bytes32 launcher_id, NativeUInt64 delay_time, Bytes32 delay_puzzle_hash) {
		if (launcher_id == null || delay_time.compareTo(new NativeUInt64(3600)) < 0 || delay_puzzle_hash == null) {
			return null;
		}
		String rtn = null;
		Process p = null;
		BufferedReader br = null;
		try {
			ProcessBuilder pb = new ProcessBuilder("python3", SCRIPT_NAME, "launcher_id_to_p2_puzzle_hash", launcher_id.toString(), delay_time.toString(), delay_puzzle_hash.toString());
			pb.directory(new File(SCRIPT_PATH));
			p = pb.start();
			p.waitFor();
			String tmp = null;
			if (p.exitValue() > 0) {
				br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
				while ((tmp = br.readLine()) != null) {
					rtn = tmp;
				}
				rtn = null;
			} else {
				br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((tmp = br.readLine()) != null) {
					rtn = tmp;
				}
			}
		} catch (Exception e) {
			rtn = null;
			e.printStackTrace();
		} finally {
			if (p != null && p.isAlive()) {
				p.destroy();
			}
			if (br != null) {
				try {
					br.close();
				} catch (Exception ignored) {
				}
			}
		}
		return new Bytes32(rtn);
	}

	public static Coin getMostRecentSingletonCoinFromCoinSolution(CoinSpend last_solution) {
		try {
			if (last_solution == null) {
				return null;
			}
			Coin rtn;
			StringBuilder rtnValue = new StringBuilder();
			Process p = null;
			BufferedReader br = null;
			try {
				ProcessBuilder pb = new ProcessBuilder("python3", SCRIPT_NAME, "get_most_recent_singleton_coin_from_coin_spend", gson.toJson(last_solution));
				pb.directory(new File(SCRIPT_PATH));
				p = pb.start();
				p.waitFor();
				String tmp;
				if (p.exitValue() > 0) {
					br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
					while ((tmp = br.readLine()) != null) {
						Logger.getInstance().log(Level.WARNING, tmp);
					}
				} else {
					br = new BufferedReader(new InputStreamReader(p.getInputStream()));
					while ((tmp = br.readLine()) != null) {
						rtnValue.append(tmp);
						rtnValue.append(System.lineSeparator());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (p != null && p.isAlive()) {
					p.destroy();
				}
				if (br != null) {
					try {
						br.close();
					} catch (Exception ignored) {
					}
				}
			}
			rtn = gson.fromJson(rtnValue.toString(), Coin.class);
			return rtn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
