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
        List<GetUserDto> customUser=new ArrayList<>();
        service.getAll().forEach(u->customUser.add(GetUserDto.customUser(u)));
        return customUser;
    }


    @GetMapping("/{id}")
    @ResponseBody
    public GetUserDto getById(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        return GetUserDto.customUser(service.getById(id));
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
