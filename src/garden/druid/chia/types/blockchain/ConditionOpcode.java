package garden.druid.chia.types.blockchain;

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

public enum ConditionOpcode {

	//UNKNOWN is ascii "0"
	UNKNOWN((byte) 48),

	// AGG_SIG is ascii "1"
	//the conditions below require bls12-381 signatures
	AGG_SIG_UNSAFE((byte) 49), AGG_SIG_ME((byte) 50),

	//the conditions below reserve coin amounts and have to be accounted for in output totals
	CREATE_COIN((byte) 51), RESERVE_FEE((byte) 52),

	//the conditions below deal with announcements, for inter-coin communication
	CREATE_COIN_ANNOUNCEMENT((byte) 60), ASSERT_COIN_ANNOUNCEMENT((byte) 61), CREATE_PUZZLE_ANNOUNCEMENT((byte) 62), ASSERT_PUZZLE_ANNOUNCEMENT((byte) 63),

	//the conditions below let coins inquire about themselves
	ASSERT_MY_COIN_ID((byte) 70), ASSERT_MY_PARENT_ID((byte) 71), ASSERT_MY_PUZZLEHASH((byte) 72), ASSERT_MY_AMOUNT((byte) 73),

	//the conditions below ensure that we're "far enough" in the future

	//wall-clock time
	ASSERT_SECONDS_RELATIVE((byte) 80), ASSERT_SECONDS_ABSOLUTE((byte) 81),

	//block index
	ASSERT_HEIGHT_RELATIVE((byte) 82), ASSERT_HEIGHT_ABSOLUTE((byte) 83);

	private byte value = 0;

	/**
	 * Constructor for Condition Opcode value
	 */
	ConditionOpcode(byte value) {
		this.setValue(value);
	}

	/**
	 * @return gets value
	 */
	public byte getValue() {
		return value;
	}

	/**
	 * @param sets value
	 */
	public void setValue(byte value) {
		this.value = value;
	}
}
