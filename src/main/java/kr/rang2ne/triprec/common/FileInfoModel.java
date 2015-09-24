package kr.rang2ne.triprec.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class FileInfoModel {
	private String fileName;
	private String filePath;
	private long fileSize;
}
