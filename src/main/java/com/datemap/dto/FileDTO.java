package com.datemap.dto;

public class FileDTO {
	private int postIdx;
	private long fileSize;
	private String fileName;
	private String filePath;
	private String fileType;
	
	public int getPostIdx() {
		return postIdx;
	}
	public void setPostIdx(int postIdx) {
		this.postIdx = postIdx;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	@Override
	public String toString() {
		return "FileDTO [postIdx=" + postIdx + ", fileSize=" + fileSize + ", fileName=" + fileName + ", filePath="
				+ filePath + ", fileType=" + fileType + "]";
	}
	
	
}
