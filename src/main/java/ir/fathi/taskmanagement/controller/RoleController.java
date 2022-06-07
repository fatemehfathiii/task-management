package ir.fathi.taskmanagement.controller;

import ir.fathi.taskmanagement.dto.RoleDto;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService service;

    @GetMapping
    @Secured("ROLE_GET_ROLE")
    @ResponseBody
    public List<RoleDto> getAll() {
        return service.getAll().stream()
                .map(role -> new RoleDto(role.getMain(), role.getCategory(), role.getName()))
                .collect(Collectors.toList());
    }


    @GetMapping("/get/{id}")
    @Secured("ROLE_GET_ROLE")
    @ResponseBody
    @Validated
    public RoleDto getById(@PathVariable @Positive Integer id) throws RecordNotFoundException {
            var role=service.getById(id);
            return new RoleDto(role.getMain(),role.getCategory(),role.getName());
    }


    @PatchMapping("/update/{id}")
    @Secured("ROLE_UPDATE_ROLE")
    @Validated
    @ResponseBody
    public ResponseEntity<String> updateRoleName(@PathVariable @Positive Integer id, @RequestParam(name = "name") @NotBlank String newRoleName){
       return new ResponseEntity<>(service.updateRoleName(id,newRoleName)+"record updated.", HttpStatus.OK);
    }

}
