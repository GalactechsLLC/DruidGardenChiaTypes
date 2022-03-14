package garden.druid.chia.types.farmer;

import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.bytes.Bytes48;
import garden.druid.chia.types.ints.NativeUInt64;

public class Plot {

	private NativeUInt64 file_size, time_modified;
	private String filename;
	private Bytes32 plot_id, pool_contract_puzzle_hash;
	private Bytes48 plot_public_key, pool_public_key;
	private short size;
	
	public NativeUInt64 getFile_size() {
		return file_size;
	}
	public void setFile_size(NativeUInt64 file_size) {
		this.file_size = file_size;
	}
	public NativeUInt64 getTime_modified() {
		return time_modified;
	}
	public void setTime_modified(NativeUInt64 time_modified) {
		this.time_modified = time_modified;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Bytes32 getPlot_id() {
		return plot_id;
	}
	public void setPlot_id(Bytes32 plot_id) {
		this.plot_id = plot_id;
	}
	public Bytes32 getPool_contract_puzzle_hash() {
		return pool_contract_puzzle_hash;
	}
	public void setPool_contract_puzzle_hash(Bytes32 pool_contract_puzzle_hash) {
		this.pool_contract_puzzle_hash = pool_contract_puzzle_hash;
	}
	public Bytes48 getPlot_public_key() {
		return plot_public_key;
	}
	public void setPlot_public_key(Bytes48 plot_public_key) {
		this.plot_public_key = plot_public_key;
	}
	public Bytes48 getPool_public_key() {
		return pool_public_key;
	}
	public void setPool_public_key(Bytes48 pool_public_key) {
		this.pool_public_key = pool_public_key;
	}
	public short getSize() {
		return size;
	}
	public void setSize(short size) {
		this.size = size;
	}
	
}
