package com.example.jobhunter.DTO;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class CreateProfileDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    private String phone;
    private String email;
    private String description;
    private List<String> education;
    private Integer yearsExperience;
    @NotBlank(message = "Las habilidades son obligatorias")
    private List<String> skills;

    public CreateProfileDTO(String name, String phone, String email, String description, List<String> education, Integer yearsExperience, List<String> skills) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.description = description;
        this.education = education;
        this.yearsExperience = yearsExperience;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getEducation() {
        return education;
    }

    public void setEducation(List<String> education) {
        this.education = education;
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
