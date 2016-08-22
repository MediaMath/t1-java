package com.mediamath.terminalone.models.reporting.meta;

import java.util.HashMap;

public class MetaDimensions {
	
	HashMap<String, MetaDimensionData> dimensionsInfoMap = new HashMap<String, MetaDimensionData>();

	public HashMap<String, MetaDimensionData> getDimensionsInfoMap() {
		return dimensionsInfoMap;
	}

	public void setDimensionsInfoMap(HashMap<String, MetaDimensionData> dimensionsInfoMap) {
		this.dimensionsInfoMap = dimensionsInfoMap;
	}

}
