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
    @Secured("ROLE_GET_PROFILE")
    @ResponseBody
    public ProfileDto getProfileByUsername(){
        try{
            var profile = service.getProfileByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            return ProfileDto.generateCustomProfileDto(profile);
        }catch (RecordNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "there is any profile whit this username" , exception);
        }
    }

    @PutMapping("/update")
    @Secured("ROLE_UPDATE_PROFILE")
    @ResponseBody
    public ResponseEntity<String> updateProfile( @RequestBody @Valid ProfileDto profileDto){

        var number=service.updateProfile(profileDto.name(), profileDto.lastname(), profileDto.nationalCode(),
                profileDto.sex(), profileDto.birthday() , profileDto.mobileNumber(), profileDto.email());
        return new  ResponseEntity<>(number + "record updated ." ,HttpStatus.OK);
    }

}
