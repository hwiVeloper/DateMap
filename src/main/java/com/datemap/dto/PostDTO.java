package com.datemap.dto;

public class PostDTO {
	private String title;
	private String content;
	private String mapId;
	private String memberId;
	
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
	public String getMapId() {
		return mapId;
	}
	public void setMapId(String mapId) {
		this.mapId = mapId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Override
	public String toString() {
		return "PostDTO [title=" + title + ", content=" + content + ", mapId=" + mapId + ", memberId=" + memberId + "]";
	}
	
	
}
