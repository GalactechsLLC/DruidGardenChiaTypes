package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.crypt.sha.SHA;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.interfaces.Streamable;
import garden.druid.chia.types.ints.NativeUInt64;

import java.nio.ByteBuffer;

/**
 * @author Galactechs LLC.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.     
 */
public class Coin implements Streamable {

	private NativeUInt64 amount;
	
	@SerializedName(value = "parent_coin_info", alternate = "parentCoinInfo")
	private Bytes32 parentCoinInfo;
	
	@SerializedName(value = "puzzle_hash", alternate = "puzzleHash")
	private Bytes32 puzzleHash;

	/**
	 * @return gets amount
	 */
	public NativeUInt64 getAmount() {
		return amount;
	}

	/**
	 * @param sets amount 
	 */
	public void setAmount(NativeUInt64 amount) {
		this.amount = amount;
	}

	/**
	 * @return gets parent coin info
	 */
	public Bytes32 getParentCoinInfo() {
		return parentCoinInfo;
	}

	/**
	 * @param sets parent coin info 
	 */
	public void setParentCoinInfo(Bytes32 parentCoinInfo) {
		this.parentCoinInfo = parentCoinInfo;
	}

	/**
	 * @return gets puzzle hash
	 */
	public Bytes32 getPuzzleHash() {
		return puzzleHash;
	}

	/**
	 * @param sets puzzle hash 
	 */
	public void setPuzzleHash(Bytes32 puzzleHash) {
		this.puzzleHash = puzzleHash;
	}

	/**
	 * @return name of hash
	 */
	public String name() {
		return hash().toString();
	}

	/**
	 * method to convert object (?) to hash
	 */
	@Override
	public Bytes32 hash() {
		ByteBuffer byteBuf = ByteBuffer.allocate(1024);
		byteBuf.put(this.getParentCoinInfo().getBytes());
		byteBuf.put(this.getPuzzleHash().getBytes());
		byteBuf.put(this.getAmount().chiaIntToBytes());
		byteBuf.flip();
		byte[] objAry = new byte[byteBuf.limit()];
		byteBuf.get(objAry, 0, byteBuf.limit());
		return new Bytes32(SHA.hash256(objAry));
	}

	/**
	 * @return true if objects are equal, else return false.
	 */
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof Coin)) {
			return false;
		}
		Coin cOther = (Coin) other;
		return this.hash().equals(cOther.hash());
	}
}
