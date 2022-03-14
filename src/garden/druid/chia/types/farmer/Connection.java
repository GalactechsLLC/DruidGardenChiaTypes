package garden.druid.chia.types.farmer;

import garden.druid.chia.types.bytes.Bytes32;

public class Connection {
	
	private String host;
	private Bytes32 node_id;
	private int port;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Bytes32 getNode_id() {
		return node_id;
	}
	public void setNode_id(Bytes32 node_id) {
		this.node_id = node_id;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
}
