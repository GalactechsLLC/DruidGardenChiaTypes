package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.ints.NativeUInt128;
import garden.druid.chia.types.ints.NativeUInt32;
import garden.druid.chia.types.ints.NativeUInt64;
import garden.druid.chia.types.ints.NativeUInt8;

import java.util.ArrayList;

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
public class BlockRecord {

	@SerializedName(value = "challenge_block_info_hash", alternate = "challengeBlockInfoHash")
	private Bytes32 challengeBlockInfoHash;	
	@SerializedName(value = "farmer_puzzle_hash", alternate = "farmerPuzzleHash")
	private Bytes32 farmerPuzzleHash;	
	@SerializedName(value = "header_hash", alternate = "headerHash")
	private Bytes32 headerHash;	
	@SerializedName(value = "pool_puzzle_hash", alternate = "poolPuzzleHash")
	private Bytes32 poolPuzzleHash;	
	@SerializedName(value = "prev_hash", alternate = "prevHash")
	private Bytes32 prevHash;	
	@SerializedName(value = "prev_transaction_block_hash", alternate = "prevTransactionBlockHash")
	private Bytes32 prevTransactionBlockHash;	
	@SerializedName(value = "reward_infusion_new_challenge", alternate = "rewardInfusionNewChallenge")
	private Bytes32 rewardInfusionNewChallenge;	
	@SerializedName(value = "finished_challenge_slot_hashes", alternate = "finishedChallengeSlotHashes")
	private ArrayList<Bytes32> finishedChallengeSlotHashes;	
	@SerializedName(value = "finished_infused_challenge_slot_hashes", alternate = "finishedInfusedChallengeSlotHashes")
	private ArrayList<Bytes32> finishedInfusedChallengeSlotHashes;	
	@SerializedName(value = "finished_reward_slot_hashes", alternate = "finishedRewardSlotHashes")
	private ArrayList<Bytes32> finishedRewardSlotHashes;	
	private NativeUInt32 height;	
	@SerializedName(value = "prev_transaction_block_height", alternate = "prevTransactionBlockHeight")
	private NativeUInt32 prevTransactionBlockHeight;	
	@SerializedName(value = "signage_point_index", alternate = "signagePointIndex")
	private NativeUInt8 signagePointIndex;
	private NativeUInt8 deficit;	
	private NativeUInt64 fees;
	@SerializedName(value = "sub_slot_iters", alternate = "subSlotIters")
	private NativeUInt64 subSlotIters;
	private NativeUInt64 timestamp;	
	@SerializedName(value = "required_iters", alternate = "requiredIters")
	private NativeUInt64 requiredIters;	
	@SerializedName(value = "total_iters", alternate = "totalIters")
	private NativeUInt128 totalIters;
	private NativeUInt128 weight;	
	@SerializedName(value = "challenge_vdf_output", alternate = "challengeVdfOutput")
	private VdfOutput challengeVdfOutput;	
	@SerializedName(value = "infused_challenge_vdf_output", alternate = "infusedChallengeVdfOutput")
	private VdfOutput infusedChallengeVdfOutput;
	private boolean overflow;	
	@SerializedName(value = "reward_claims_incorporated", alternate = "rewardClaimsIncorporated")
	private ArrayList<Coin> rewardClaimsIncorporated;

	/**
	 * @return gets Challenge Block Info Hash
	 */
	public Bytes32 getChallengeBlockInfoHash() {
		return challengeBlockInfoHash;
	}

	/**
	 * @param sets Challenge Block Info Hash
	 */
	public void setChallengeBlockInfoHash(Bytes32 challengeBlockInfoHash) {
		this.challengeBlockInfoHash = challengeBlockInfoHash;
	}

	/**
	 * @return gets Farmer puzzle hash
	 */
	public Bytes32 getFarmerPuzzleHash() {
		return farmerPuzzleHash;
	}

	/**
	 * @param sets Farmer puzzle hash
	 */
	public void setFarmerPuzzleHash(Bytes32 farmerPuzzleHash) {
		this.farmerPuzzleHash = farmerPuzzleHash;
	}

	/**
	 * @return gets header hash
	 */
	public Bytes32 getHeaderHash() {
		return headerHash;
	}

	/**
	 * @param sets header hash
	 */
	public void setHeaderHash(Bytes32 headerHash) {
		this.headerHash = headerHash;
	}

	/**
	 * @return gets pool puzzle hash
	 */
	public Bytes32 getPoolPuzzleHash() {
		return poolPuzzleHash;
	}

	/**
	 * @param sets pool puzzle hash
	 */
	public void setPoolPuzzleHash(Bytes32 poolPuzzleHash) {
		this.poolPuzzleHash = poolPuzzleHash;
	}

	/**
	 * @return gets prev hash
	 */
	public Bytes32 getPrevHash() {
		return prevHash;
	}

	/**
	 * @param sets prev hash
	 */
	public void setPrevHash(Bytes32 prevHash) {
		this.prevHash = prevHash;
	}

	/**
	 * @return gets prev transaction block hash
	 */
	public Bytes32 getPrevTransactionBlockHash() {
		return prevTransactionBlockHash;
	}

	/**
	 * @param sets prev transaction block hash
	 */
	public void setPrevTransactionBlockHash(Bytes32 prevTransactionBlockHash) {
		this.prevTransactionBlockHash = prevTransactionBlockHash;
	}

	/**
	 * @return gets reward infusion new challenge
	 */
	public Bytes32 getRewardInfusionNewChallenge() {
		return rewardInfusionNewChallenge;
	}

	/**
	 * @param sets reward infusion new challenge
	 */
	public void setRewardInfusionNewChallenge(Bytes32 rewardInfusionNewChallenge) {
		this.rewardInfusionNewChallenge = rewardInfusionNewChallenge;
	}

	/**
	 * @return gets finished challenge slot hashes
	 */
	public ArrayList<Bytes32> getFinishedChallengeSlotHashes() {
		return finishedChallengeSlotHashes;
	}

	/**
	 * @param sets finished challenge slot hashes
	 */
	public void setFinishedChallengeSlotHashes(ArrayList<Bytes32> finishedChallengeSlotHashes) {
		this.finishedChallengeSlotHashes = finishedChallengeSlotHashes;
	}

	/**
	 * @return gets finished infused challenge slot hashes
	 */
	public ArrayList<Bytes32> getFinishedInfusedChallengeSlotHashes() {
		return finishedInfusedChallengeSlotHashes;
	}

	/**
	 * @param sets finished infused challenge slot hashes
	 */
	public void setFinishedInfusedChallengeSlotHashes(ArrayList<Bytes32> finishedInfusedChallengeSlotHashes) {
		this.finishedInfusedChallengeSlotHashes = finishedInfusedChallengeSlotHashes;
	}

	/**
	 * @return gets finished reward slot hashes
	 */
	public ArrayList<Bytes32> getFinishedRewardSlotHashes() {
		return finishedRewardSlotHashes;
	}

	/**
	 * @param sets finished reward slot hashes
	 */
	public void setFinishedRewardSlotHashes(ArrayList<Bytes32> finishedRewardSlotHashes) {
		this.finishedRewardSlotHashes = finishedRewardSlotHashes;
	}

	/**
	 * @return gets height
	 */
	public NativeUInt32 getHeight() {
		return height;
	}

	/**
	 * @param sets height
	 */
	public void setHeight(NativeUInt32 height) {
		this.height = height;
	}

	/**
	 * @return gets prev transaction block height
	 */
	public NativeUInt32 getPrevTransactionBlockHeight() {
		return prevTransactionBlockHeight;
	}

	/**
	 * @param sets prev transaction block height
	 */
	public void setPrevTransactionBlockHeight(NativeUInt32 prevTransactionBlockHeight) {
		this.prevTransactionBlockHeight = prevTransactionBlockHeight;
	}

	/**
	 * @return gets challenge vdf output
	 */
	public VdfOutput getChallengeVdfOutput() {
		return challengeVdfOutput;
	}

	/**
	 * @param sets challenge vdf output
	 */
	public void setChallengeVdfOutput(VdfOutput challengeVdfOutput) {
		this.challengeVdfOutput = challengeVdfOutput;
	}

	/**
	 * @return gets infused challenge vdf output
	 */
	public VdfOutput getInfusedChallengeVdfOutput() {
		return infusedChallengeVdfOutput;
	}

	/**
	 * @param sets infused challenge vdf output 
	 */
	public void setInfusedChallengeVdfOutput(VdfOutput infusedChallengeVdfOutput) {
		this.infusedChallengeVdfOutput = infusedChallengeVdfOutput;
	}

	/**
	 * @return true/false if overflow
	 */
	public boolean isOverflow() {
		return overflow;
	}

	/**
	 * @param sets overflow 
	 */
	public void setOverflow(boolean overflow) {
		this.overflow = overflow;
	}

	/**
	 * @return gets reward claims incorporated
	 */
	public ArrayList<Coin> getRewardClaimsIncorporated() {
		return rewardClaimsIncorporated;
	}

	/**
	 * @param sets reward claims incorporated 
	 */
	public void setRewardClaimsIncorporated(ArrayList<Coin> rewardClaimsIncorporated) {
		this.rewardClaimsIncorporated = rewardClaimsIncorporated;
	}

	/**
	 * @return gets signage point index
	 */
	public NativeUInt8 getSignagePointIndex() {
		return signagePointIndex;
	}

	/**
	 * @param sets signage point index 
	 */
	public void setSignagePointIndex(NativeUInt8 signagePointIndex) {
		this.signagePointIndex = signagePointIndex;
	}

	/**
	 * @return gets deficit
	 */
	public NativeUInt8 getDeficit() {
		return deficit;
	}

	/**
	 * @param sets deficit 
	 */
	public void setDeficit(NativeUInt8 deficit) {
		this.deficit = deficit;
	}

	/**
	 * @return gets fees
	 */
	public NativeUInt64 getFees() {
		return fees;
	}

	/**
	 * @param sets fees 
	 */
	public void setFees(NativeUInt64 fees) {
		this.fees = fees;
	}

	/**
	 * @return gets sub slot iters
	 */
	public NativeUInt64 getSubSlotIters() {
		return subSlotIters;
	}

	/**
	 * @param sets sub slot iters 
	 */
	public void setSubSlotIters(NativeUInt64 subSlotIters) {
		this.subSlotIters = subSlotIters;
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
	 * @return gets requires iters
	 */
	public NativeUInt64 getRequiredIters() {
		return requiredIters;
	}

	/**
	 * @param sets required iters 
	 */
	public void setRequiredIters(NativeUInt64 requiredIters) {
		this.requiredIters = requiredIters;
	}

	/**
	 * @return gets total iters
	 */
	public NativeUInt128 getTotalIters() {
		return totalIters;
	}

	/**
	 * @param sets total iters 
	 */
	public void setTotalIters(NativeUInt128 totalIters) {
		this.totalIters = totalIters;
	}

	/**
	 * @return gets weight
	 */
	public NativeUInt128 getWeight() {
		return weight;
	}

	/**
	 * @param sets weight 
	 */
	public void setWeight(NativeUInt128 weight) {
		this.weight = weight;
	}

}
