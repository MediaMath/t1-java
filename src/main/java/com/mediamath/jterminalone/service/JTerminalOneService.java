package com.mediamath.jterminalone.service;

import java.util.HashMap;
import java.util.List;

import com.mediamath.jterminalone.utils.ConditionQuery;
import com.mediamath.jterminalone.utils.Constants;

public class JTerminalOneService {

	/**
	 * Construct Child Path based on consumer request
	 * @param child
	 * @return
	 */
	public String constructChildPath(String child){
		String childPath="";
		HashMap<String, Integer> childMap = Constants.childPaths.get(child);
		for(String s : childMap.keySet()){
			if(s.equalsIgnoreCase("target_dimensions")){
				childPath += "?target_dimensions="+String.valueOf(childMap.get("target_dimensions"));
			}
			else{
				childPath +="?"+child;
			}
		}

		return childPath;
	}
	
	public StringBuffer constructIncludePath(List<ConditionQuery> includeConditionList){
		StringBuffer includePath = new StringBuffer("");
		for(ConditionQuery conditionquery : includeConditionList) {
			if(includePath.toString().equalsIgnoreCase("")) {
				if(conditionquery.getInclude() != null) {
					includePath.append("?with=" + conditionquery.getInclude());
					if(conditionquery.getWith() != null) {
						includePath.append("," + conditionquery.getWith());
					}
				}
			} else {
				if(conditionquery.getInclude() != null) {
					includePath.append("&with=" + conditionquery.getInclude());
					if(conditionquery.getWith() != null) {
						includePath.append("," + conditionquery.getInclude());
					}
				}
			}
		}
		
		return includePath;
	}
	
	public String constructPaginationPath(int pageLimit, int pageOffset){
		String pagePath = "";
		if(pageLimit>0 && pageLimit<=100){
			pagePath += "page_limit="+String.valueOf(pageLimit);
		}
		if(pageOffset> 0){
			if(!pagePath.toString().equalsIgnoreCase("")){
				pagePath +="&page_offset="+String.valueOf(pageOffset);
			}
			else{
				pagePath +="page_offset="+String.valueOf(pageOffset);
			}
		}
		
		return pagePath;
	}
	
}
