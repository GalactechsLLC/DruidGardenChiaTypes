package garden.druid.chia.rpc.responses;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.blockchain.SignagePoint;
import garden.druid.chia.types.blockchain.SubSlotBundle;

import java.math.BigDecimal;

public class SignagePointOrEOSResponse {
	@SerializedName(value = "signage_point", alternate = "signagePoint")
	private SignagePoint signagePoint;
	private SubSlotBundle eos;
	@SerializedName(value = "time_received", alternate = "timeReceived")
	private BigDecimal timeReceived;
	private boolean reverted;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public SignagePoint getSignage_point() {
		return signagePoint;
	}

	public void setSignage_point(SignagePoint signage_point) {
		this.signagePoint = signage_point;
	}

	public SubSlotBundle getEos() {
		return eos;
	}

	public void setEos(SubSlotBundle eos) {
		this.eos = eos;
	}

	public BigDecimal getTime_received() {
		return timeReceived;
	}

	public void setTime_received(BigDecimal time_received) {
		this.timeReceived = time_received;
	}

	public boolean isReverted() {
		return reverted;
	}

	public void setReverted(boolean reverted) {
		this.reverted = reverted;
	}
}
