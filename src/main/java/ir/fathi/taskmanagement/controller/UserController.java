package ir.fathi.taskmanagement.controller;

import ir.fathi.taskmanagement.config.aspect.MethodDurationLog;
import ir.fathi.taskmanagement.dto.GetUserDto;
import ir.fathi.taskmanagement.dto.PostUserDto;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.User;
import ir.fathi.taskmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> save(@RequestBody @Valid PostUserDto postUserDto) {
        service.save(User.fromDto(postUserDto.username(), passwordEncoder.encode(postUserDto.password())));
        return new ResponseEntity<>("save success", HttpStatus.CREATED);
    }


    @GetMapping("/getAll")
    @Secured("ROLE_GET_USER")
    @MethodDurationLog
    @ResponseBody
    public List<GetUserDto> getAll() {
        return service.getAll().stream()
                .map(user -> new GetUserDto(user.getUsername()))
                .collect(Collectors.toList());
    }


    @GetMapping("/get/{id}")
    @Secured("ROLE_GET_USER")
    @ResponseBody
    @Validated
    public GetUserDto getById(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        return new GetUserDto(service.getById(id).getUsername());
    }

    @GetMapping("/get/incomplete")
    @Secured("ROLE_GET_USER")
    @ResponseBody
    public List<String> getUserWhoHaveIncompleteTask() {
        return service.getUserWhoHaveIncompleteTask();
    }

    @GetMapping("/get/active_user")
    @Secured("ROLE_GET_USER")
    @ResponseBody
    public List<GetUserDto> getActiveUser() {
        return service.getActiveUser().stream()
                .map(user -> new GetUserDto(user.getUsername()))
                .collect(Collectors.toList());
    }

    @GetMapping("/get/count")
    @Secured("ROLE_GET_USER")
    @ResponseBody
    public ResponseEntity<String> countOfActiveUser() {
        return new ResponseEntity<>(service.countOfActiveUser() + "users are active", HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    @Secured("ROLE_UPDATE_USER")
    @Validated
    @ResponseBody
    public ResponseEntity<String> updatePassword(@PathVariable @Positive Integer id,
                                                 @RequestParam(name = "password") @NotBlank @Min(8) String newPassword) {
        return new ResponseEntity<>(service.updatePassword(id, newPassword) + "record updated.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Secured("ROLE_DELETE_USER")
    @Validated
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable @Positive Integer id) {
        return new ResponseEntity<>(service.delete(id) + "record deleted", HttpStatus.OK);
    }

}
