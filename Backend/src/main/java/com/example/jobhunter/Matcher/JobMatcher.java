package com.example.jobhunter.Matcher;

import com.example.jobhunter.Model.Job;
import com.example.jobhunter.Model.Profile;

import java.text.Normalizer;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JobMatcher {

    public double calculateMatch(Profile profile, Job job){
        double skills = calculateSkillsMatch(profile,job);
        double education = calculateEducationMatch(profile,job);
        double experience = calculateExperienceMatch(profile,job);

        double total = (skills * 0.5) + (education * 0.2) + (experience * 0.3);
        return total;
    }

    public double calculateSkillsMatch(Profile profile, Job job){
        List<String> skillsProfile = profile.getSkills();
        List<String> skillsJob = job.getRequiredSkills();
        if (!skillsJob.isEmpty()) {
        if (!skillsProfile.isEmpty()) {

            Set<String> setSkillsJob = normalizeText(skillsJob);

            List<String> skillsMatch = normalizeText(skillsProfile).stream()
                .filter(setSkillsJob::contains)
                .distinct()
                .toList();

                Double match = ((double) skillsMatch.size() ) / setSkillsJob.size();
                return match;
        }
        return 0.0;
        }
        return 1.0;
    }

    public double calculateExperienceMatch(Profile profile, Job job){
        Integer yearProfile = profile.getYearsExperience();
        Integer yearJob = job.getRequiredYearsExperience();

        if (yearJob != null) {
            if (yearJob <= yearProfile) {
                return 1.0;
            }
            return 0.0;
        }
        return 1.0;

    }

    public double calculateEducationMatch(Profile profile, Job job){
        List<String> educationProfile = profile.getEducation();
        List<String> educationJob = job.getRequiredEducation();

        if (educationJob.isEmpty()) return 1.0;

        Set<String> normalizedJobEducation = normalizeText(educationJob);

        boolean hasMatch = normalizeText(educationProfile).stream()
                .anyMatch(normalizedJobEducation::contains);
        return educationProfile.isEmpty() ? 0.0 : hasMatch ? 1.0 : 0.0;
    }

    public Set<String> normalizeText(List<String> lista){
        if(lista.isEmpty()) return Set.of();
        return  lista.stream()
                .map(s -> Normalizer.normalize(s, Normalizer.Form.NFD)
                        .replaceAll("\\p{M}", "")
                        .toLowerCase())
                        .collect(Collectors.toSet());

    }
}
