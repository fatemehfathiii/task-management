package ir.fathi.taskmanagement.controller;
import ir.fathi.taskmanagement.dto.ProfileDto;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.Profile;
import ir.fathi.taskmanagement.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService service;

    @PostMapping
    public void save(@RequestBody @Valid ProfileDto profileDto) {
        service.save(Profile.fromDto(profileDto));
    }

    @GetMapping
    @ResponseBody
    public List<ProfileDto> getAll() {
        return service.getAll().stream()
                .map(profile -> new ProfileDto(profile.getName(), profile.getLastname(), profile.getGender()
                        , profile.getBirthday(), profile.getMobileNumber(), profile.getEmail()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    @Validated
    public ProfileDto getById(@PathVariable @Positive Integer id) throws RecordNotFoundException {
       var profile=service.getById(id);
       return new ProfileDto(profile.getName(), profile.getLastname(), profile.getGender()
                , profile.getBirthday(), profile.getMobileNumber(), profile.getEmail());
    }


    @PatchMapping("/{id}/{name}")
    @Validated
    public void updateName(@PathVariable @Positive Integer id, @PathVariable @NotNull @NotBlank String name)
            throws RecordNotFoundException {

        service.updateName(id, name);
    }

    @DeleteMapping("/{id}")
    @Validated
    public void delete(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        service.delete(id);
    }


}
