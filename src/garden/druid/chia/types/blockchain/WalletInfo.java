package garden.druid.chia.types.blockchain;

import garden.druid.chia.types.ints.NativeUInt32;
import garden.druid.chia.types.ints.NativeUInt8;

public class WalletInfo {
	private String data, name;
	private NativeUInt32 id;
	private NativeUInt8 type;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NativeUInt32 getId() {
		return id;
	}

	public void setId(NativeUInt32 id) {
		this.id = id;
	}

	public NativeUInt8 getType() {
		return type;
	}

	public void setType(NativeUInt8 type) {
		this.type = type;
	}
}
