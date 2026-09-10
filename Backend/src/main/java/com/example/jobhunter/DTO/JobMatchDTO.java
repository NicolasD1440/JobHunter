package com.example.jobhunter.DTO;

public class JobMatchDTO {
    private Integer id;
    private String title;
    private String company;
    private String location;
    private Double matchScore;

    public JobMatchDTO(Integer id, String title, String company, String location, Double mathScore) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.location = location;
        this.matchScore = mathScore;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(Double mathScore) {
        this.matchScore = mathScore;
    }
}