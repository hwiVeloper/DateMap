package com.datemap.dto;

public class PostDTO {
	private String title;
	private String content;
	private int mapIdx;
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
	public int getMapIdx() {
		return mapIdx;
	}
	public void setMapIdx(int mapIdx) {
		this.mapIdx = mapIdx;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Override
	public String toString() {
		return "PostDTO [title=" + title + ", content=" + content + ", map_idx=" + mapIdx + ", member_id=" + memberId
				+ "]";
	}
	
	
}
