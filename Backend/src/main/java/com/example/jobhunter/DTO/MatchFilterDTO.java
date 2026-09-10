package com.example.jobhunter.DTO;

import java.util.List;

public class MatchFilterDTO {
    private Double minScore;
    private String workMode;
    private Integer salaryMin;
    private Integer salaryMax;
    private String location;
    private Integer yearsExperience;
    private List<String> skills;

    public MatchFilterDTO(Double minScore, String workMode, Integer salaryMin, Integer salaryMax, String location, Integer yearsExperience, List<String> skills) {
        this.minScore = minScore;
        this.workMode = workMode;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.location = location;
        this.yearsExperience = yearsExperience;
        this.skills = skills;
    }

    public Double getMinScore() {
        return minScore;
    }

    public void setMinScore(Double minScore) {
        this.minScore = minScore;
    }

    public String getWorkMode() {
        return workMode;
    }

    public void setWorkMode(String workMode) {
        this.workMode = workMode;
    }

    public Integer getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Integer salaryMax) {
        this.salaryMax = salaryMax;
    }

    public Integer getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Integer salaryMin) {
        this.salaryMin = salaryMin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(Integer yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
