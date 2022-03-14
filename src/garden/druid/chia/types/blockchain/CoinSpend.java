package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.bytes.UnsizedBytes;


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

public class CoinSpend {
	private Coin coin;
	@SerializedName(value = "puzzle_reveal", alternate = "puzzleReveal")
	private UnsizedBytes puzzleReveal;
	private UnsizedBytes solution;

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
	 * @return gets puzzle reveal
	 */
	public UnsizedBytes getPuzzleReveal() {
		return puzzleReveal;
	}

	/**
	 * @param sets puzzle reveal 
	 */
	public void setPuzzleReveal(UnsizedBytes puzzleReveal) {
		this.puzzleReveal = puzzleReveal;
	}

	/**
	 * @return gets solution
	 */
	public UnsizedBytes getSolution() {
		return solution;
	}

	/**
	 * @param sets solution 
	 */
	public void setSolution(UnsizedBytes solution) {
		this.solution = solution;
	}

	
	/**
	 * @return true if objects are equal, else return false.
	 */
	@Override
	public boolean equals(Object o) {
		CoinSpend other;
		if (o instanceof CoinSpend) {
			other = (CoinSpend) o;
		} else {
			return false;
		}
		if (!this.getPuzzleReveal().toString().equals(other.getPuzzleReveal().toString())) {
			return false;
		}
		if (!this.getSolution().toString().equals(other.getSolution().toString())) {
			return false;
		}
		if (!this.getCoin().name().equals(other.getCoin().name())) {
			return false;
		}
		return true;
	}
}
