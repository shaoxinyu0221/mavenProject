package com.paper.entity;

public class Paper {

    private Integer id;
    private String title;
    private Integer typeId;
    private String paperSummary;
    private String paperPath;
    private String creationDate;
    private String modifyDate;
    private String status;
    private String pername;

    public String getPername() {
        return pername;
    }

    public void setPername(String pername) {
        this.pername = pername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getPaperSummary() {
        return paperSummary;
    }

    public void setPaperSummary(String paperSummary) {
        this.paperSummary = paperSummary;
    }

    public String getPaperPath() {
        return paperPath;
    }

    public void setPaperPath(String paperPath) {
        this.paperPath = paperPath;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
