package com.mediamath.terminalone.models;

public class Segments {
	
	public enum restrictions {
		INCLUDE, EXCLUDE
	};
	
	public enum aud_seg_exc {
		AND, OR
	};

	public enum aud_seg_inc {
		AND, OR
	};
	
	private int id;
	private restrictions restriction;
	private aud_seg_exc exclude;
	private aud_seg_inc include;
	
	public Segments(int id, restrictions restriction, aud_seg_exc exclude, aud_seg_inc include) {
		this.id = id;
		this.restriction = restriction;
		this.exclude = exclude;
		this.include = include;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public restrictions getRestriction() {
		return restriction;
	}
	public void setRestriction(restrictions restriction) {
		this.restriction = restriction;
	}
	public aud_seg_exc getExclude() {
		return exclude;
	}
	public void setExclude(aud_seg_exc exclude) {
		this.exclude = exclude;
	}
	public aud_seg_inc getInclude() {
		return include;
	}
	public void setInclude(aud_seg_inc include) {
		this.include = include;
	}
	
	

}
