package com.mediamath.terminalone.models;

import java.util.ArrayList;

public class ThreePASCreativeBatchPlacements {

	ArrayList<ThreePASCreativePlacementInvalid> invalid;
	ArrayList<ThreePASCreativePlacementValid> valid;

	public ArrayList<ThreePASCreativePlacementInvalid> getInvalid() {
		return invalid;
	}

	public void setInvalid(ArrayList<ThreePASCreativePlacementInvalid> invalid) {
		this.invalid = invalid;
	}

	public ArrayList<ThreePASCreativePlacementValid> getValid() {
		return valid;
	}

	public void setValid(ArrayList<ThreePASCreativePlacementValid> valid) {
		this.valid = valid;
	}

}
