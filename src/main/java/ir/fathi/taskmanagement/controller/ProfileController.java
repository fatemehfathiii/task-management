package ir.fathi.taskmanagement.controller;
import ir.fathi.taskmanagement.dto.ProfileDto;
import ir.fathi.taskmanagement.dto.UpdateProfileDto;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.Profile;
import ir.fathi.taskmanagement.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService service;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ProfileDto profileDto) {
        service.save(Profile.fromDto(profileDto));
        return new ResponseEntity<>("save successes",HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<ProfileDto> getAll() {
        return service.getAll().stream()
                .map(profile -> new ProfileDto(profile.getName(), profile.getLastname(), profile.getGender()
                        , profile.getBirthday(), profile.getMobileNumber(), profile.getEmail()))
                .collect(Collectors.toList());
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    @Validated
    public ProfileDto getById(@PathVariable @Positive Integer id) throws RecordNotFoundException {
            var profile=service.getById(id);
            return new ProfileDto(profile.getName(), profile.getLastname(), profile.getGender()
                    , profile.getBirthday(), profile.getMobileNumber(), profile.getEmail());
    }



    @GetMapping("/get/byUsername")
    @ResponseBody
    @Validated
    public ProfileDto etProfileByUsername(@RequestParam @NotBlank String username) throws RecordNotFoundException {
            var profile=service.getProfileByUsername(username);
            return new ProfileDto(profile.getName(), profile.getLastname(), profile.getGender()
                    , profile.getBirthday(), profile.getMobileNumber(), profile.getEmail());

    }



    @PatchMapping("/update/byName")
    @ResponseBody
    public ResponseEntity<String> updateNameAndLastname(@RequestBody @Valid UpdateProfileDto profileDto){
        return new ResponseEntity<>(
                service.updateNameAndLastname(profileDto.id(), profileDto.name(), profileDto.lastname())+"record updated.",
                HttpStatus.OK
        );
    }


    @DeleteMapping("/delete/{id}")
    @Validated
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable @Positive Integer id){
        return new ResponseEntity<>(service.delete(id)+"record deleted.",HttpStatus.OK);
    }


}
