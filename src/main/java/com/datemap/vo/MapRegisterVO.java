package com.datemap.vo;

public class MapRegisterVO {
	private String lat;
	private String lng;
	private String title;
	private String content;
	private int mapIdx;
	private int memberId;
	private String placeName;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getMapIdx() {
		return mapIdx;
	}
	public void setMapIdx(int mapIdx) {
		this.mapIdx = mapIdx;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	@Override
	public String toString() {
		return "MapRegisterVO [lat=" + lat + ", lng=" + lng + ", title=" + title + ", content=" + content + ", mapIdx="
				+ mapIdx + ", memberId=" + memberId + ", placeName=" + placeName + "]";
	}
	
}
