package ir.fathi.taskmanagement.controller;

import ir.fathi.taskmanagement.dto.GetUserDto;
import ir.fathi.taskmanagement.dto.PostUserDto;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.User;
import ir.fathi.taskmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid PostUserDto postUserDto){
        service.save(User.fromDto(postUserDto));
        return new ResponseEntity<>("save success",HttpStatus.CREATED);
    }


    @GetMapping("/getAll")
    @ResponseBody
    public List<GetUserDto> getAll(){
       return service.getAll().stream()
               .map(user -> new GetUserDto(user.getUsername()))
               .collect(Collectors.toList());
    }


    @GetMapping("/get/{id}")
    @ResponseBody
    @Validated
    public ResponseEntity<Object> getById(@PathVariable @Positive Integer id) {
        try {

            return ResponseEntity.ok().body(new GetUserDto(service.getById(id).getUsername()));

        }catch (RecordNotFoundException exception){
            return new ResponseEntity<>("user not found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/incomplete")
    @ResponseBody
    public List<String> getUserWhoHaveIncompleteTask(){
        return service.getUserWhoHaveIncompleteTask();
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<String> countOfActiveUser(){
        return new ResponseEntity<>(service.countOfActiveUser()+"users are active",HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    @Validated
    @ResponseBody
    public ResponseEntity<String> updatePassword(@PathVariable @Positive Integer id,
                                                 @RequestParam(name = "password") @NotBlank @Min(8) String newPassword) {
        return new ResponseEntity<>(service.updatePassword(id, newPassword)+"record updated.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Validated
    @ResponseBody
    public Map<String,Boolean> delete(@PathVariable @Positive Integer id){
        service.delete(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("delete",Boolean.TRUE);
        return response;
    }

}
