package garden.druid.chia.rpc.responses;

import com.google.gson.annotations.SerializedName;

public class NetworkInfoResponse {

	private boolean success;
	@SerializedName(value = "network_name", alternate = "networkName")
	private String networkName;
	@SerializedName(value = "network_prefix", alternate = "networkPrefix")
	private String networkPrefix;

	public String getNetwork_name() {
		return networkName;
	}

	public void setNetwork_name(String network_name) {
		this.networkName = network_name;
	}

	public String getNetwork_prefix() {
		return networkPrefix;
	}

	public void setNetwork_prefix(String network_prefix) {
		this.networkPrefix = network_prefix;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
