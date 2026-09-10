package com.example.jobhunter.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Profile {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String description;
    @ElementCollection
    @CollectionTable(name = "profile_education")
    private List<String> education = new ArrayList<>();
    private Integer yearsExperience = 0;
    @ElementCollection
    @CollectionTable(name = "profile_skills")
    private List<String> skills = new ArrayList<>();

    public Profile(String name, String phone, String email, String description,
                   List<String> education, Integer yearsExperience, List<String> skills) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.description = description;
        this.education = education;
        this.yearsExperience = yearsExperience;
        this.skills = skills;
    }

    public Profile() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
