package in.co.hubapp.mobile.channel;

public class DocumentDetails {

	private Long docId;

	private String fileName;

	private String filePath;
	
	private String downloadUri;

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
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

	public String getDownloadUri() {
		return downloadUri;
	}

	public void setDownloadUri(String downloadUri) {
		this.downloadUri = downloadUri;
	}

	@Override
	public String toString() {
		return "DocumentDetails [docId=" + docId + ", fileName=" + fileName + ", filePath=" + filePath
				+ ", downloadUri=" + downloadUri + "]";
	}

	

}
