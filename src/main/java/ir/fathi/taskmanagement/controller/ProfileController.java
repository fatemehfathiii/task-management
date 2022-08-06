package ir.fathi.taskmanagement.controller;
import ir.fathi.taskmanagement.config.aspect.MethodDurationLog;
import ir.fathi.taskmanagement.dto.ProfileDto;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService service;

    @GetMapping("/getAll")
    @Secured("ROLE_GET_PROFILE")
    @ResponseBody
    @MethodDurationLog
    public List<ProfileDto> getAll() {
        return service.getAll().stream()
                .map(profile -> new ProfileDto(profile.getName(), profile.getLastname(), profile.getSex()
                        , profile.getBirthday(), profile.getMobileNumber(), profile.getEmail()))
                .collect(Collectors.toList());
    }


    @GetMapping("/get/byUsername")
    @Secured("ROLE_GET_PROFILE")
    @ResponseBody
    public ProfileDto getProfileByUsername() throws RecordNotFoundException {
        var profile = service.getProfileByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return new ProfileDto(profile.getName(), profile.getLastname(), profile.getSex()
                , profile.getBirthday(), profile.getMobileNumber(), profile.getEmail());
    }

    @PutMapping("/update")
    @Secured("ROLE_UPDATE_PROFILE")
    @ResponseBody
    public ResponseEntity<String> updateProfile( @RequestBody @Valid ProfileDto profileDto){

        var number=service.updateProfile(profileDto.name(), profileDto.lastname(), profileDto.sex(),
                profileDto.birthday() , profileDto.mobileNumber(), profileDto.email());
        return new  ResponseEntity<>(number + "record updated ." ,HttpStatus.OK);
    }


}
