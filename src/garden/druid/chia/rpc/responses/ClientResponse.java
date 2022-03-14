package garden.druid.chia.rpc.responses;

import garden.druid.chia.rpc.ClientError;

public class ClientResponse<T> extends ClientError {

	private T data;

	public T getData() {
		return this.data;
	}

	public void setData(T t) {
		this.data = t;
	}
}
