package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;

public class NetworkInfo {
	@SerializedName(value = "network_name", alternate = "networkName")
	private String networkName;
	@SerializedName(value = "network_prefix", alternate = "networkPrefix")
	private String networkPrefix;

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public String getNetworkPrefix() {
		return networkPrefix;
	}

	public void setNetworkPrefix(String networkPrefix) {
		this.networkPrefix = networkPrefix;
	}
}
