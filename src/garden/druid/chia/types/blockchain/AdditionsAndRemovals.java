package garden.druid.chia.types.blockchain;

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
public class AdditionsAndRemovals {

	
	private ArrayList<CoinRecord> additions, removals;

	/**
	 * @return gets additions
	 */
	public ArrayList<CoinRecord> getAdditions() {
		return additions;
	}

	/**
	 * @param sets additions
	 */
	public void setAdditions(ArrayList<CoinRecord> additions) {
		this.additions = additions;
	}

	/**
	 * @return gets removals
	 */
	public ArrayList<CoinRecord> getRemovals() {
		return removals;
	}

	/**
	 * @param Sets removals
	 */
	public void setRemovals(ArrayList<CoinRecord> removals) {
		this.removals = removals;
	}
	
	/**
	 * @return true if objects are equal, else return false.
	 */
	@Override
	public boolean equals(Object other) {
		if( other == null || !(other instanceof AdditionsAndRemovals)) {
			return false;
		}
		AdditionsAndRemovals aorOther = (AdditionsAndRemovals) other;
		if(aorOther.getAdditions().size() != this.getAdditions().size() || aorOther.getRemovals().size() != this.getRemovals().size()) {
			return false;
		}
		for(int i = 0; i < this.getAdditions().size(); i++) {
			if(!this.getAdditions().get(i).equals(aorOther.getAdditions().get(i))) {
				return false;
			}
		}
		for(int i = 0; i < this.getRemovals().size(); i++) {
			if(!this.getRemovals().get(i).equals(aorOther.getRemovals().get(i))) {
				return false;
			}
		}
		return true;
	}
}
