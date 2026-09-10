package com.example.jobhunter.Model;

public class RawJob {
    private String rawTitle;
    private String rawCompany;
    private String rawLocation;
    private String rawSalary;
    private String workMode;
    private String contractType;
    private String rawDescription;
    private String source;
    private String sourceUrl;

    public RawJob(String rawTitle, String rawCompany, String rawLocation, String workMode, String rawSalary, String contractType, String rawDescription, String source, String sourceUrl) {
        this.rawTitle = rawTitle;
        this.rawCompany = rawCompany;
        this.rawLocation = rawLocation;
        this.workMode = workMode;
        this.rawSalary = rawSalary;
        this.contractType = contractType;
        this.rawDescription = rawDescription;
        this.source = source;
        this.sourceUrl = sourceUrl;
    }

    public String getRawTitle() {
        return rawTitle;
    }

    public void setRawTitle(String rawTitle) {
        this.rawTitle = rawTitle;
    }

    public String getRawCompany() {
        return rawCompany;
    }

    public void setRawCompany(String rawCompany) {
        this.rawCompany = rawCompany;
    }

    public String getRawLocation() {
        return rawLocation;
    }

    public void setRawLocation(String rawLocation) {
        this.rawLocation = rawLocation;
    }

    public String getRawSalary() {
        return rawSalary;
    }

    public String getWorkMode() {
        return workMode;
    }

    public void setWorkMode(String workMode) {
        this.workMode = workMode;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public void setRawSalary(String rawSalary) {
        this.rawSalary = rawSalary;
    }

    public String getRawDescription() {
        return rawDescription;
    }

    public void setRawDescription(String rawDescription) {
        this.rawDescription = rawDescription;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
