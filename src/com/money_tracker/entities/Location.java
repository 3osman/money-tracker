package com.money_tracker.entities;

public class Location {
	private long id;
	private long entryId;
	private String lat;
	private String lng;
	

	public long getEntryId() {
		return entryId;
	}

	public void setEntryId(long entryId) {
		this.entryId = entryId;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
