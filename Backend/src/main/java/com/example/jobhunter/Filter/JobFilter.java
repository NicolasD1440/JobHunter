package com.example.jobhunter.Filter;

import com.example.jobhunter.DTO.MatchFilterDTO;
import com.example.jobhunter.Model.Job;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JobFilter {



    public boolean isWorkModeValid(MatchFilterDTO filter, Job job){
        String workMode = filter.getWorkMode();
        String normal =  normalize(workMode);
        if ( (normal == null || normal.isEmpty()) || normal.equals(normalize(job.getWorkMode()))) {
            return true;
        }
        return false;
    }

    public boolean isSalaryValid(MatchFilterDTO filter, Job job) {
        Integer salaryMin = filter.getSalaryMin();
        Integer salaryMax = filter.getSalaryMax();

        Integer jobSalaryMin = job.getSalaryMin();
        Integer jobSalaryMax = job.getSalaryMax();

            if (jobSalaryMin != null && jobSalaryMax == null) {
                jobSalaryMax = jobSalaryMin;
            }

            if(jobSalaryMin == null && jobSalaryMax != null){
                 jobSalaryMin = jobSalaryMax;
            }

            if (salaryMax == null && salaryMin == null) {
                return true;
            } else if (jobSalaryMin == null && jobSalaryMax == null) {
                return false;
            } else if (salaryMax == null && salaryMin <= jobSalaryMax) {
                return true;
            } else if (salaryMin == null && salaryMax >= jobSalaryMin){
                return true;
            }else if(salaryMin <= jobSalaryMax && salaryMax >= jobSalaryMin){
                return true;
            }
            return false;

    }

    public boolean isLocationValid(MatchFilterDTO filter, Job job){
        if (filter.getLocation() == null ) {
            return true;
        }
        if (job.getLocation() == null) {
            return false;
        }
        String locationFilter = normalize(filter.getLocation());
        String locationJob = normalize(job.getLocation());
        if (locationFilter.isEmpty()) {
            return true;
        }

        String[] jobWords = locationJob.split(" ");
        return Arrays.asList(jobWords).contains(locationFilter);
    }

    public boolean isExperienceValid(MatchFilterDTO filter, Job job){
        Integer yearFilter = filter.getYearsExperience();
        Integer yearJob = job.getRequiredYearsExperience();
        if (yearFilter == null) return true;
        if (yearJob == null) return true;

        if (yearJob <= yearFilter) {
                return true;
            }
            return false;

    }


        public boolean isSkillsValid(MatchFilterDTO filter, Job job) {
            List<String> listFilter = filter.getSkills();
            List<String> listJob = job.getRequiredSkills();

            if (listFilter == null || listFilter.isEmpty()) {
                return true;
            }
            if (listJob == null || listJob.isEmpty()) {
                return false;
            }

            Set<String> normalizedJobSkills = listJob.stream()
                    .map(JobFilter::normalize)
                    .collect(Collectors.toSet());


            for (String skill : listFilter) {
                String normalizedSkill = normalize(skill);
                if (!normalizedSkill.isEmpty() && !normalizedJobSkills.contains(normalizedSkill)) {
                    return false;
                }
            }
            return true;
        }


    public boolean isJobValid(MatchFilterDTO filter, Job job){
        boolean workMode = isWorkModeValid(filter, job);
        boolean salary = isSalaryValid(filter, job);
        boolean location = isLocationValid(filter, job);
        boolean experience = isExperienceValid(filter, job);
        boolean skills = isSkillsValid(filter, job);

        List<Boolean> conditions = List.of(workMode,salary,location,experience,skills);
        boolean allTrue = conditions.stream().allMatch(Boolean::booleanValue);
        return allTrue;

    }
    public static String normalize(String input) {
        if (input == null) return "";
        String normalized = input.toLowerCase().trim();
        normalized = Normalizer.normalize(normalized, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        normalized = normalized.replaceAll("[^a-z0-9\\s]", "");
        return normalized.replaceAll("\\s+", " ");
    }
}
