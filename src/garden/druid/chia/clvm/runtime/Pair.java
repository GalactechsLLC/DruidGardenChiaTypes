package garden.druid.chia.clvm.runtime;

public class Pair {
	
	private SExp first;
	private SExp rest;
	
	public Pair(SExp first, SExp rest) {
		this.first = first;
		this.rest = rest;
	}

	public SExp getFirst() {
		return this.first;
	}

	public void setFirst(SExp first) {
		this.first = first;
	}

	public SExp getRest() {
		return this.rest;
	}

	public void setRest(SExp rest) {
		this.rest = rest;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		if(first != null && !first.isNull()) {
			builder.append(first.toString());
		} 
		if(rest != null && !rest.isNull()) {
			builder.append(" ");
			builder.append(rest.toString());
		}
		builder.append(")");
		return builder.toString();
	}
}
