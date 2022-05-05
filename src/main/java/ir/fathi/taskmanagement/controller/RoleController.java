package ir.fathi.taskmanagement.controller;

import ir.fathi.taskmanagement.dto.RoleDto;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.Role;
import ir.fathi.taskmanagement.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService service;

    @PostMapping
    public void save(@RequestBody @Valid RoleDto roleDto) {
        service.save(Role.fromDto(roleDto));
    }

    @GetMapping
    @ResponseBody
    public List<RoleDto> getAll() {

        // I've used stream and map function for convert role into rolDto in this case
        return service.getAll().stream()
                .map(role -> new RoleDto(role.getMain(), role.getCategory(), role.getName()))
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    @ResponseBody
    @Validated
    public RoleDto getById(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        //I've used constructor for conversion
        var role=service.getById(id);
        return new RoleDto(role.getMain(),role.getCategory(),role.getName());
    }

    @PatchMapping("/{id}/{roleName}")
    @Validated
    public void updateRoleName(@PathVariable @Positive Integer id, @PathVariable @NotNull @NotBlank String roleName) throws RecordNotFoundException {
        service.updateRoleName(id, roleName);
    }
}
