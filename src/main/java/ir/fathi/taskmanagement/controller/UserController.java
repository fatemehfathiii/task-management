package ir.fathi.taskmanagement.controller;

import ir.fathi.taskmanagement.dto.GetUserDto;
import ir.fathi.taskmanagement.dto.PostUserDto;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.User;
import ir.fathi.taskmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping
    public void save(@RequestBody @Valid PostUserDto postUserDto){
        service.save(User.fromDto(postUserDto));
    }

    @GetMapping
    @ResponseBody
    public List<GetUserDto> getAll(){
       return service.getAll().stream()
               .map(user -> new GetUserDto(user.getUsername()))
               .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    @ResponseBody
    public GetUserDto getById(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        return new GetUserDto(service.getById(id).getUsername());
    }


    @PatchMapping("/{id}/{newPassword}")
    public void updatePassword(@PathVariable @Positive Integer id, @RequestBody Map<String,String> newPassword)
            throws RecordNotFoundException, IllegalAccessException {
        var password=newPassword.get("password");
        if (password== null || password.isBlank()){
            throw new IllegalAccessException("password");
        }
        service.updatePassword(id,password);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        service.delete(id);
    }

}
