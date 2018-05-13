package com.datemap.dto;

public class MapDTO {
	private String latitude;
	private String longtitude;
	private String placeName;
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	@Override
	public String toString() {
		return "MapDTO [latitude=" + latitude + ", longtitude=" + longtitude + ", place_name=" + placeName + "]";
	}
	
	
}
