package com.datemap.dto;

public class MapDTO {
	private double latitude;
	private double longtitude;
	private String placeName;
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(double longtitude) {
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
