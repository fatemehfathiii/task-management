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
import java.util.ArrayList;
import java.util.List;

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
        //I've used a static method in dto and for each function for convert profile into profileDto
        List<ProfileDto> profileDto = new ArrayList<>();
        service.getAll().forEach(p -> profileDto.add(ProfileDto.customProfile(p)));
        return profileDto;
    }

    @GetMapping("/{id}")
    @ResponseBody
    @Validated
    public ProfileDto getById(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        //I've used static method in this case
        return ProfileDto.customProfile(service.getById(id));
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
