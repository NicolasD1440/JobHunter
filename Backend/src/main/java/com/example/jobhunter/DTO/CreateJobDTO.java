package com.example.jobhunter.DTO;

import java.util.List;
import jakarta.validation.constraints.NotBlank;

public class CreateJobDTO {
    // Información general
    @NotBlank(message = "El titulo es obligatorio")
    private String title;
    @NotBlank(message = "La compañia es obligatoria")
    private String company;
    @NotBlank(message = "La ubicacion es obligatoria")
    private String location;

    // Condiciones
    private Integer salaryMin;
    private Integer salaryMax;
    private String workMode;
    private String contractType;

    // Requisitos
    private List<String> requiredEducation;
    private Integer requiredYearsExperience;
    private List<String> requiredSkills;

    // Información adicional
    private String description;

    // Origen
    @NotBlank(message = "La fuente es obligatoria")
    private String source;
    @NotBlank(message = "La Url es obligatoria")
    private String sourceUrl;

    public CreateJobDTO(String company, String location, String title, Integer salaryMin, Integer salaryMax, String workMode, List<String> requiredEducation, String contractType, Integer requiredYearsExperience, List<String> requiredSkills, String description, String sourceUrl, String source) {
        this.company = company;
        this.location = location;
        this.title = title;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.workMode = workMode;
        this.requiredEducation = requiredEducation;
        this.contractType = contractType;
        this.requiredYearsExperience = requiredYearsExperience;
        this.requiredSkills = requiredSkills;
        this.description = description;
        this.sourceUrl = sourceUrl;
        this.source = source;
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

    public Integer getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Integer salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getWorkMode() {
        return workMode;
    }

    public void setWorkMode(String workMode) {
        this.workMode = workMode;
    }

    public Integer getRequiredYearsExperience() {
        return requiredYearsExperience;
    }

    public void setRequiredYearsExperience(Integer requiredYearsExperience) {
        this.requiredYearsExperience = requiredYearsExperience;
    }

    public List<String> getRequiredEducation() {
        return requiredEducation;
    }

    public void setRequiredEducation(List<String> requiredEducation) {
        this.requiredEducation = requiredEducation;
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
