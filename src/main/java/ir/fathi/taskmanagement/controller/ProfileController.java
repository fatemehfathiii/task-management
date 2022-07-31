package ir.fathi.taskmanagement.controller;
import ir.fathi.taskmanagement.config.aspect.MethodDurationLog;
import ir.fathi.taskmanagement.dto.ProfileDto;
import ir.fathi.taskmanagement.dto.UpdateProfileDto;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.Profile;
import ir.fathi.taskmanagement.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService service;

    @PostMapping
    @Secured("ROLE_ADD_PROFILE")
    public ResponseEntity save(@RequestBody @Valid ProfileDto profileDto) {
        service.save(Profile.fromDto(profileDto));
        return new ResponseEntity<>("save successes", HttpStatus.CREATED);
    }

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

    @GetMapping("/get/{id}")
    @Secured("ROLE_GET_PROFILE")
    @ResponseBody
    @Validated
    public ProfileDto getById(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        var profile = service.getById(id);
        return new ProfileDto(profile.getName(), profile.getLastname(), profile.getSex()
                , profile.getBirthday(), profile.getMobileNumber(), profile.getEmail());
    }


    @GetMapping("/get/byUsername")
    @Secured("ROLE_GET_PROFILE")
    @ResponseBody
    public ProfileDto getProfileByUsername() throws RecordNotFoundException {
        var profile = service.getProfileByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return new ProfileDto(profile.getName(), profile.getLastname(), profile.getSex()
                , profile.getBirthday(), profile.getMobileNumber(), profile.getEmail());
    }


    @PatchMapping("/update/byName")
    @Secured(("ROLE_UPDATE_PROFILE"))
    @ResponseBody
    public ResponseEntity<String> updateNameAndLastname(@RequestBody @Valid UpdateProfileDto profileDto) {
        return new ResponseEntity<>(
                service.updateNameAndLastname(profileDto.id(), profileDto.name(), profileDto.lastname()) + "record updated.",
                HttpStatus.OK
        );
    }


    @DeleteMapping("/delete/{id}")
    @Secured("ROLE_DELETE_PROFILE")
    @Validated
    @ResponseBody
    @MethodDurationLog
    public ResponseEntity<String> delete(@PathVariable @Positive Integer id) {
        return new ResponseEntity<>(service.delete(id) + "record deleted.", HttpStatus.OK);
    }

}
