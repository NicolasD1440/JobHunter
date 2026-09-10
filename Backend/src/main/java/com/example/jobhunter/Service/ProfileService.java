package com.example.jobhunter.Service;

import com.example.jobhunter.DTO.CreateProfileDTO;
import com.example.jobhunter.Model.Profile;
import com.example.jobhunter.Repository.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private ProfileRepository repository;
    public ProfileService(ProfileRepository repository){
        this.repository = repository;
    }

    public List<Profile> getProfiles(){
        return repository.findAll();
    }

    public Profile getOneProfile(Integer id){
        Optional<Profile> profile = repository.findById(id);
        return profile.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil no encontrado"));
    }

    public Profile crateNewProfile(CreateProfileDTO dto){
        String name = dto.getName();
        String phone = dto.getPhone();
        String email = dto.getEmail();
        String description =dto.getDescription();
        List<String> education = dto.getEducation();
        Integer yearsExperience = dto.getYearsExperience();
        List<String> skills = dto.getSkills();
        Profile profile = new Profile(name, phone, email, description, education, yearsExperience, skills);
        return repository.save(profile);
    }

    public Profile updateProfile(CreateProfileDTO dto, Integer id){
        Profile profile = getOneProfile(id);
        if (profile != null) {
            profile.setName(dto.getName());
            profile.setPhone(dto.getPhone());
            profile.setEmail(dto.getEmail());
            profile.setDescription(dto.getDescription());
            profile.setEducation(dto.getEducation());
            profile.setYearsExperience(dto.getYearsExperience());
            profile.setSkills(dto.getSkills());

            return repository.save(profile);
        }
        return null;
    }

        public Profile deleteProfile(Integer id){
            Profile profile = getOneProfile(id);
            if (profile != null) {
                repository.delete(profile);
                return profile;
            }

            return null;
        }

}
