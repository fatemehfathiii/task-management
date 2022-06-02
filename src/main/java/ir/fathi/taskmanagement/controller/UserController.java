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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/post")
    public void save(@RequestBody @Valid PostUserDto postUserDto){
        service.save(User.fromDto(postUserDto));
    }

    @GetMapping
    @ResponseBody
    public List<GetUserDto> getAll(){
        //I've used stream and map function for convert user into GetUserDto in this case
       return service.getAll().stream()
               .map(user -> new GetUserDto(user.getUsername()))
               .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    @ResponseBody
    public GetUserDto getById(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        //I've used constructor for conversion
        return new GetUserDto(service.getById(id).getUsername());
    }


    @PatchMapping("/{id}/{newPassword}")
    public void updatePassword(@PathVariable @Positive Integer id, @PathVariable @NotBlank @NotNull @Min(8) String newPassword)
            throws RecordNotFoundException {
        service.updatePassword(id, newPassword);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        service.delete(id);
    }

}
