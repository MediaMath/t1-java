package com.mediamath.terminalone.models;

public class ThreePASCreativeBatch {
	private String filename;
	private String user_id;
	private String id;
	private String total;
	private ThreePASCreativeBatchPlacements placements;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public ThreePASCreativeBatchPlacements getPlacements() {
		return placements;
	}

	public void setPlacements(ThreePASCreativeBatchPlacements placements) {
		this.placements = placements;
	}
}
