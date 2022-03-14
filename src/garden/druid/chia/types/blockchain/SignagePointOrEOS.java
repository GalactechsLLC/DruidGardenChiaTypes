package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class SignagePointOrEOS {
	@SerializedName(value = "signage_point", alternate = "signagePoint")
	private SignagePoint signagePoint;
	private SubSlotBundle eos;
	@SerializedName(value = "time_received", alternate = "timeReceived")
	private BigDecimal timeReceived;
	private boolean reverted;

	public SignagePoint getSignagePoint() {
		return signagePoint;
	}

	public void setSignagePoint(SignagePoint signagePoint) {
		this.signagePoint = signagePoint;
	}

	public SubSlotBundle getEos() {
		return eos;
	}

	public void setEos(SubSlotBundle eos) {
		this.eos = eos;
	}

	public BigDecimal getTimeReceived() {
		return timeReceived;
	}

	public void setTimeReceived(BigDecimal timeReceived) {
		this.timeReceived = timeReceived;
	}

	public boolean isReverted() {
		return reverted;
	}

	public void setReverted(boolean reverted) {
		this.reverted = reverted;
	}
}
