package com.natanmaia.veterinaria.data.vo;

import java.io.Serializable;

public class UploadFileResponseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private Long size;

    public UploadFileResponseVO() {

    }

    public UploadFileResponseVO(String fileName, String fileDownloadUri, String fileType, Long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UploadFileResponseVO)) return false;

        UploadFileResponseVO that = (UploadFileResponseVO) o;

        if (!getFileName().equals(that.getFileName())) return false;
        if (!getFileDownloadUri().equals(that.getFileDownloadUri())) return false;
        if (!getFileType().equals(that.getFileType())) return false;
        return getSize().equals(that.getSize());
    }

    @Override
    public int hashCode() {
        int result = getFileName().hashCode();
        result = 31 * result + getFileDownloadUri().hashCode();
        result = 31 * result + getFileType().hashCode();
        result = 31 * result + getSize().hashCode();
        return result;
    }
}
