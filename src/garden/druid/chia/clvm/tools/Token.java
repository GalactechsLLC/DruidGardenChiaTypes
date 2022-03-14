package garden.druid.chia.clvm.tools;

public class Token {

	private String token;
	private int offset;
	
	public Token(String token, int offset) {
		super();
		this.token = token;
		this.offset = offset;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	@Override
	public String toString() {
		return this.token;
	}
}
