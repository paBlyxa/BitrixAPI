package com.fpa.bitrixapi.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class BitrixFile extends BitrixEntity {

    private String code;
    private Integer storageId;
    private String type;
    private Integer parentId;
    private String deletedType;
    private Integer createdBy;
    private Integer updatedBy;
    private Integer deletedBy;
    private String detailUrl;
    private String downloadUrl;

    @JsonGetter("CODE")
    public String getCode() {
        return code;
    }

    @JsonSetter("CODE")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonGetter("STORAGE_ID")
    public Integer getStorageId() {
        return storageId;
    }

    @JsonSetter("STORAGE_ID")
    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    @JsonGetter("TYPE")
    public String getType() {
        return type;
    }

    @JsonSetter("TYPE")
    public void setType(String type) {
        this.type = type;
    }

    @JsonGetter("PARENT_ID")
    public Integer getParentId() {
        return parentId;
    }

    @JsonSetter("PARENT_ID")
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @JsonGetter("DELETED_TYPE")
    public String getDeletedType() {
        return deletedType;
    }

    @JsonSetter("DELETED_TYPE")
    public void setDeletedType(String deletedType) {
        this.deletedType = deletedType;
    }

    @JsonGetter("CREATED_BY")
    public Integer getCreatedBy() {
        return createdBy;
    }

    @JsonSetter("CREATED_BY")
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @JsonGetter("UPDATED_BY")
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    @JsonSetter("UPDATED_BY")
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    @JsonGetter("DELETED_BY")
    public Integer getDeletedBy() {
        return deletedBy;
    }

    @JsonSetter("DELETED_BY")
    public void setDeletedBy(Integer deletedBy) {
        this.deletedBy = deletedBy;
    }

    @JsonGetter("DETAIL_URL")
    public String getDetailUrl() {
        return detailUrl;
    }

    @JsonSetter("DETAIL_URL")
    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    @JsonGetter("DOWNLOAD_URL")
    public String getDownloadUrl() {
        return downloadUrl;
    }

    @JsonSetter("DOWNLOAD_URL")
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public boolean isFolder() {
        return "folder".equals(type);
    }

    @Override
    public String toString() {
        return "BitrixFile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", storageId=" + storageId +
                ", type='" + type + '\'' +
                ", parentId=" + parentId +
                ", deletedType='" + deletedType + '\'' +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", deletedBy=" + deletedBy +
                ", detailUrl='" + detailUrl + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }
}
