package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.ints.NativeUInt32;
import garden.druid.chia.types.ints.NativeUInt64;

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

public class CoinRecord {

	private Coin coin;
	
	@SerializedName(value = "confirmed_block_index", alternate = "confirmedBlockIndex")
	private NativeUInt32 confirmedBlockIndex;
	
	@SerializedName(value = "spent_block_index", alternate = "spentBlockIndex")
	private NativeUInt32 spentBlockIndex;
	private NativeUInt64 timestamp;
	private boolean coinbase, spent;

	/**
	 * @return gets coin
	 */
	public Coin getCoin() {
		return coin;
	}

	/**
	 * @param sets coin 
	 */
	public void setCoin(Coin coin) {
		this.coin = coin;
	}

	/**
	 * @return gets confirmed block index
	 */
	public NativeUInt32 getConfirmedBlockIndex() {
		return confirmedBlockIndex;
	}

	/**
	 * @param sets confirmed block index 
	 */
	public void setConfirmedBlockIndex(NativeUInt32 confirmedBlockIndex) {
		this.confirmedBlockIndex = confirmedBlockIndex;
	}

	/**
	 * @return gets spent block index
	 */
	public NativeUInt32 getSpentBlockIndex() {
		return spentBlockIndex;
	}

	/**
	 * @param sets spent block index 
	 */
	public void setSpentBlockIndex(NativeUInt32 spentBlockIndex) {
		this.spentBlockIndex = spentBlockIndex;
	}

	/**
	 * @return gets timestamp
	 */
	public NativeUInt64 getTimestamp() {
		return timestamp;
	}

	/**
	 * @param sets timestamp 
	 */
	public void setTimestamp(NativeUInt64 timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return true/false if coinbase
	 */
	public boolean isCoinbase() {
		return coinbase;
	}

	/**
	 * @param set coinbase = true/false 
	 */
	public void setCoinbase(boolean coinbase) {
		this.coinbase = coinbase;
	}

	/**
	 * @return true/false if spent
	 */
	public boolean isSpent() {
		return spent;
	}

	/**
	 * @param set spent = true/false 
	 */
	public void setSpent(boolean spent) {
		this.spent = spent;
	}

	/**
	 * @return true if objects are equal, else return false.
	 */
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof CoinRecord)) {
			return false;
		}
		CoinRecord cOther = (CoinRecord) other;
		return this.coin.hash().equals(cOther.getCoin().hash());
	}
}
