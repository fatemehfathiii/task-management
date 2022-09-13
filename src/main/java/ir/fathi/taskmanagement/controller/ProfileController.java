package ir.fathi.taskmanagement.controller;
import ir.fathi.taskmanagement.dto.ProfileDto;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService service;


    @GetMapping("/get/byUsername")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_GET_PROFILE")
    @ResponseBody
    public ProfileDto getProfileByUsername() throws RecordNotFoundException {
            var profile = service.getProfileByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            return ProfileDto.generateCustomProfileDto(profile);
    }

    @PutMapping("/update")
    @Secured("ROLE_UPDATE_PROFILE")
    @ResponseBody
    public ResponseEntity<String> updateProfile( @RequestBody @Valid ProfileDto profileDto){

        var updatedProfile=service.updateProfile(profileDto.name(), profileDto.lastname(), profileDto.nationalCode(),
                profileDto.sex(), profileDto.birthday() , profileDto.mobileNumber(), profileDto.email());
        return ResponseEntity.ok().body(updatedProfile + "record updated .");
    }

}
