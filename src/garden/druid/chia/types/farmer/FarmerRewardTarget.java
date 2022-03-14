package garden.druid.chia.types.farmer;

public class FarmerRewardTarget {
	
	private String farmer_target, pool_target;
	private boolean have_farmer_sk, have_pool_sk;
	
	public String getFarmer_target() {
		return farmer_target;
	}
	public void setFarmer_target(String farmer_target) {
		this.farmer_target = farmer_target;
	}
	public String getPool_target() {
		return pool_target;
	}
	public void setPool_target(String pool_target) {
		this.pool_target = pool_target;
	}
	public boolean isHave_farmner_sk() {
		return have_farmer_sk;
	}
	public void setHave_farmner_sk(boolean have_farmer_sk) {
		this.have_farmer_sk = have_farmer_sk;
	}
	public boolean isHave_pool_sk() {
		return have_pool_sk;
	}
	public void setHave_pool_sk(boolean have_pool_sk) {
		this.have_pool_sk = have_pool_sk;
	}
}
