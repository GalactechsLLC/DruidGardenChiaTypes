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
public class ChallengeChainSubSlot implements Streamable {

	@SerializedName(value = "challenge_chain_end_of_slot_vdf", alternate = "challengeChainEndOfSlotVdf")
	private VdfInfo challengeChainEndOfSlotVdf;
	
	@SerializedName(value = "new_sub_slot_iters", alternate = "newSubSlotIters")
	private NativeUInt64 newSubSlotIters;
	
	@SerializedName(value = "new_difficulty", alternate = "newDifficulty")
	private NativeUInt64 newDifficulty;
	
	@SerializedName(value = "infused_challenge_chain_sub_slot_hash", alternate = "infusedChallengeChainSubSlotHash")
	private Bytes32 infusedChallengeChainSubSlotHash;
	
	@SerializedName(value = "subepoch_summary_hash", alternate = "subepochSummaryHash")
	private Bytes32 subepochSummaryHash;

	/**
	 * @return gets challenge chain end of slot vdf
	 */
	public VdfInfo getChallengeChainEndOfSlotVdf() {
		return challengeChainEndOfSlotVdf;
	}

	/**
	 * @param sets challenge chain end of slot vdf 
	 */
	public void setChallengeChainEndOfSlotVdf(VdfInfo challengeChainEndOfSlotVdf) {
		this.challengeChainEndOfSlotVdf = challengeChainEndOfSlotVdf;
	}

	/**
	 * @return gets new sub slot iters
	 */
	public NativeUInt64 getNewSubSlotIters() {
		return newSubSlotIters;
	}

	/**
	 * @param sets new sub slot iters 
	 */
	public void setNewSubSlotIters(NativeUInt64 newSubSlotIters) {
		this.newSubSlotIters = newSubSlotIters;
	}

	/**
	 * @return gets new difficulty
	 */
	public NativeUInt64 getNewDifficulty() {
		return newDifficulty;
	}

	/**
	 * @param sets new difficulty 
	 */
	public void setNewDifficulty(NativeUInt64 newDifficulty) {
		this.newDifficulty = newDifficulty;
	}

	/**
	 * @return gets Infused challenge chain sub slot hash
	 */
	public Bytes32 getInfusedChallengeChainSubSlotHash() {
		return infusedChallengeChainSubSlotHash;
	}

	/**
	 * @param sets Infused challenge chain sub slot hash 
	 */
	public void setInfusedChallengeChainSubSlotHash(Bytes32 infusedChallengeChainSubSlotHash) {
		this.infusedChallengeChainSubSlotHash = infusedChallengeChainSubSlotHash;
	}

	/**
	 * @return gets Subepoch summary hash
	 */
	public Bytes32 getSubepochSummaryHash() {
		return subepochSummaryHash;
	}

	/**
	 * @param sets subepoch summary hash 
	 */
	public void setSubepochSummaryHash(Bytes32 subepochSummaryHash) {
		this.subepochSummaryHash = subepochSummaryHash;
	}

	/**
	 * @return hash for Challenge Chain Sub Slot
	 */
	@Override
	public Bytes32 hash() {
		ByteBuffer byteBuf = ByteBuffer.allocate(1024);
		byteBuf.put(this.getChallengeChainEndOfSlotVdf().getChallenge().getBytes());
		byteBuf.put(this.getChallengeChainEndOfSlotVdf().getNumberOfIterations().toByteArray());
		byteBuf.put(this.getChallengeChainEndOfSlotVdf().getOutput().getData().getBytes());
		if (this.getInfusedChallengeChainSubSlotHash() != null) {
			byteBuf.put((byte) 1);
			byteBuf.put(this.getInfusedChallengeChainSubSlotHash().getBytes());
		} else {
			byteBuf.put((byte) 0);
		}
		if (this.getSubepochSummaryHash() != null) {
			byteBuf.put((byte) 1);
			byteBuf.put(this.getSubepochSummaryHash().getBytes());
		} else {
			byteBuf.put((byte) 0);
		}
		if (this.getNewSubSlotIters() != null) {
			byteBuf.put((byte) 1);
			byteBuf.put(this.getNewSubSlotIters().toByteArray());
		} else {
			byteBuf.put((byte) 0);
		}
		if (this.getNewDifficulty() != null) {
			byteBuf.put((byte) 1);
			byteBuf.put(this.getNewDifficulty().toByteArray());
		} else {
			byteBuf.put((byte) 0);
		}
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
		if(!(other instanceof ChallengeChainSubSlot)) {
			return false;
		}
		ChallengeChainSubSlot cOther = (ChallengeChainSubSlot) other;
		return this.hash().equals(cOther.hash());
	}
}
