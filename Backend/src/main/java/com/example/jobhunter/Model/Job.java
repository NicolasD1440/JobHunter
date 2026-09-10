package com.example.jobhunter.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Job {
    @Id
    @GeneratedValue
    private Integer id;
    // Información general
    private String title;
    private String company;
    private String location;

    // Condiciones
    private Integer salaryMin;
    private Integer salaryMax;
    private String workMode;
    private String contractType;

    // Requisitos
    @ElementCollection
    @CollectionTable(name = "job_required_education")
    private List<String> requiredEducation = new ArrayList<>();
    private Integer requiredYearsExperience;
    @ElementCollection
    @CollectionTable(name = "job_required_skills")
    private List<String> requiredSkills = new ArrayList<>();

    // Información adicional
    @Lob
    private String description;

    // Origen
    private String source;
    private String sourceUrl;
    public Job(){

    }

    public Job(Integer id, String title, String company, String location, Integer salaryMin, Integer salaryMax, String contractType, String workMode, List<String> requiredEducation, Integer requiredYearsExperience, List<String> requiredSkills, String description, String source, String sourceUrl) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.location = location;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.contractType = contractType;
        this.workMode = workMode;
        this.requiredEducation = requiredEducation;
        this.requiredYearsExperience = requiredYearsExperience;
        this.requiredSkills = requiredSkills;
        this.description = description;
        this.source = source;
        this.sourceUrl = sourceUrl;
    }

    public Job(String title, String location, String company, Integer salaryMin, Integer salaryMax, String workMode, String contractType, List<String> requiredEducation, Integer requiredYearsExperience, List<String> requiredSkills, String source, String description, String sourceUrl) {
        this.title = title;
        this.location = location;
        this.company = company;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.workMode = workMode;
        this.contractType = contractType;
        this.requiredEducation = requiredEducation;
        this.requiredYearsExperience = requiredYearsExperience;
        this.requiredSkills = requiredSkills;
        this.source = source;
        this.description = description;
        this.sourceUrl = sourceUrl;
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

    public Integer getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Integer salaryMin) {
        this.salaryMin = salaryMin;
    }

    public Integer getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Integer salaryMax) {
        this.salaryMax = salaryMax;
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

    public List<String> getRequiredEducation() {
        return requiredEducation;
    }

    public void setRequiredEducation(List<String> requiredEducation) {
        this.requiredEducation = requiredEducation;
    }

    public Integer getRequiredYearsExperience() {
        return requiredYearsExperience;
    }

    public void setRequiredYearsExperience(Integer requiredYearsExperience) {
        this.requiredYearsExperience = requiredYearsExperience;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
