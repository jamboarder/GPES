package com.uobia.gpes.bridge;

public class ResourceManager {
	double costScale = 1.0;
	
	public int stepCost() {
		//TODO: Finish implementation
		return 0;
	}
	
	public void setCostScale(double scale) {
		//TODO: Finish implementation
		if (scale >= 0 || scale <= 1.0)  {
			costScale = scale;
		}
		return;
	}
}
