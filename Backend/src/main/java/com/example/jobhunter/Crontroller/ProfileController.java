package com.example.jobhunter.Crontroller;


import com.example.jobhunter.DTO.CreateProfileDTO;
import com.example.jobhunter.Model.Profile;
import com.example.jobhunter.Service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private ProfileService service;
    public ProfileController(ProfileService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getProfiles(){
        List<Profile> profiles =  service.getProfiles();
        if (!profiles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(profiles);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Profile> getOneProfile(@PathVariable Integer id){
        Profile profile = service.getOneProfile(id);
        if (profile != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(profile);

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .build();
    }

    @PostMapping
    public ResponseEntity<Profile> createNewProfile(@RequestBody CreateProfileDTO dto){
        Profile profile = service.crateNewProfile(dto);
           return ResponseEntity.status(HttpStatus.CREATED)
                    .body(profile);


    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@RequestBody CreateProfileDTO dto, @PathVariable Integer id){
        Profile profile = service.updateProfile(dto, id);
        if (profile != null) {
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(profile);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Profile> deleteProfile(@PathVariable Integer id){
        Profile profile = service.deleteProfile(id);
        if (profile != null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .build();
    }

}
