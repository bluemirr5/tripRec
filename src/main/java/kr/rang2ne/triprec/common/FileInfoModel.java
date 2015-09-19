package kr.rang2ne.triprec.common;

public class FileInfoModel {

	private String fileName;
	private String filePath;
	private long fileSize;
	
	public FileInfoModel(){}
	
	public FileInfoModel(
			String fileName,
			String filePath,
			long fileSize) {
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "FileInfoModel [fileName=" + fileName + ", filePath=" + filePath
				+ ", fileSize=" + fileSize + "]";
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

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
}
