package com.uobia.gpes.bridge;

public interface ResourceManager {
	
	/**
	 * A resource manager provides the cost associated with each environment step. The cost
	 * can be determined by any means, but must vary according to some desired condition.  Step costs
	 * should be lowered as more desirable conditions are satisfied and increased as conditions become less desirable.
	 */
	
	/**
	 * Returns the cost to be imposed for the next environment step
	 * @return stepCost
	 */
	public int stepCost();
	
}
