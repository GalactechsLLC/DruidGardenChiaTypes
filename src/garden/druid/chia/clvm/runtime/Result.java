package garden.druid.chia.clvm.runtime;

import java.math.BigInteger;

public class Result {

	private BigInteger cost;
	private SExp result;
	
	public Result(BigInteger cost, SExp result) {
		super();
		this.cost = cost;
		this.result = result;
	}
	
	public BigInteger getCost() {
		return this.cost;
	}
	public void setCost(BigInteger cost) {
		this.cost = cost;
	}
	public SExp getResult() {
		return this.result;
	}
	public void setResult(SExp result) {
		this.result = result;
	}
}
