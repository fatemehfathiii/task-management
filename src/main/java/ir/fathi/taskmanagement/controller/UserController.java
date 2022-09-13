package ir.fathi.taskmanagement.controller;

import ir.fathi.taskmanagement.config.aspect.MethodDurationLog;
import ir.fathi.taskmanagement.dto.ChangePasswordDto;
import ir.fathi.taskmanagement.dto.GetUserDto;
import ir.fathi.taskmanagement.dto.PostUserDto;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.User;
import ir.fathi.taskmanagement.service.SaveUserService;
import ir.fathi.taskmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SaveUserService saveUserService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @MethodDurationLog
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid PostUserDto postUserDto) throws RecordNotFoundException {
        saveUserService.save(User.fromDto(postUserDto, passwordEncoder.encode(postUserDto.password())));
    }


    @GetMapping("/getAll")
    @Secured("ROLE_GET_USER")
    @ResponseStatus(HttpStatus.OK)
    @MethodDurationLog
    @ResponseBody
    public List<GetUserDto> getAll() {
        return userService.getAll().stream()
                .map(GetUserDto::generateCustomGetUserDto).collect(Collectors.toList());
    }


    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_GET_USER")
    @ResponseBody
    @Validated
    public GetUserDto getById(@PathVariable @Positive Integer id) throws RecordNotFoundException {
            return GetUserDto.generateCustomGetUserDto(userService.getById(id));
    }

    @GetMapping("/get/incomplete")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_GET_USER")
    @ResponseBody
    public List<String> getUsersWhoHaveIncompleteTask() {
        return userService.getUserWhoHasIncompleteTask();
    }

    @GetMapping("/get/active_user")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_GET_USER")
    @ResponseBody
    public List<GetUserDto> getActiveUser() {
        return userService.getActiveUser().stream().map(GetUserDto::generateCustomGetUserDto).collect(Collectors.toList());
    }

    @GetMapping("/get/count")
    @Secured("ROLE_GET_USER")
    @ResponseBody
    public ResponseEntity<String> countOfActiveUser() {
        return ResponseEntity.ok().body(userService.countOfActiveUser() + "users are active");
    }

    @PatchMapping("/update")
    @Secured("ROLE_UPDATE_USER")
    @ResponseBody
    public ResponseEntity<String> updatePassword(@RequestBody @Validated ChangePasswordDto newPassword) {
        var updated=userService.updatePassword(newPassword.id(), passwordEncoder.encode(newPassword.NewPassword()));
        return ResponseEntity.ok().body(updated + "record updated.");
    }


    @DeleteMapping("/delete/{id}")
    @Secured("ROLE_DELETE_USER")
    @Validated
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable @Positive Integer id) {
        return ResponseEntity.ok().body(userService.delete(id) + "record deleted");
    }

}
