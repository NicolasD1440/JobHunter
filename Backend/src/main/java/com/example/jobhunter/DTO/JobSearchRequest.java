package com.example.jobhunter.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public class JobSearchRequest {
    @NotEmpty(message = "debe agregar minimo una palabra clave")
    private List<String> keywords;
    private String location;
    private String workMode;
    @PositiveOrZero(message = "El salario no puede ser negativo")
    private Integer salaryMin;

    public JobSearchRequest(List<String> keywords, String location, String workMode, Integer salaryMin) {
        this.keywords = keywords;
        this.location = location;
        this.workMode = workMode;
        this.salaryMin = salaryMin;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWorkMode() {
        return workMode;
    }

    public void setWorkMode(String workMode) {
        this.workMode = workMode;
    }

    public Integer getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Integer salaryMin) {
        this.salaryMin = salaryMin;
    }
}
