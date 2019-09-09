package rts.domain;

import java.io.File;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Attachment  implements Serializable{
	
	private String fileName, MIMEType;
	private File file;
	private Long fileStorageId;
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the mIMEType
	 */
	public String getMIMEType() {
		return MIMEType;
	}
	/**
	 * @param mIMEType the mIMEType to set
	 */
	public void setMIMEType(String mIMEType) {
		MIMEType = mIMEType;
	}
	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}
	/**
	 * @return the fileStorageId
	 */
	public Long getFileStorageId() {
		return fileStorageId;
	}
	/**
	 * @param fileStorageId the fileStorageId to set
	 */
	public void setFileStorageId(Long fileStorageId) {
		this.fileStorageId = fileStorageId;
	}

}
