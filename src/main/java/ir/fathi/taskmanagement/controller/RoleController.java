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
        List<RoleDto> customRole = new ArrayList<>();
        service.getAll().forEach(role -> customRole.add(RoleDto.customRole(role)));
        return customRole;
    }

    @GetMapping("/{id}")
    @ResponseBody
    @Validated
    public RoleDto getById(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        return RoleDto.customRole(service.getById(id));
    }

    @PatchMapping("/{id}/{roleName}")
    @Validated
    public void updateRoleName(@PathVariable @Positive Integer id, @PathVariable @NotNull @NotBlank String roleName) throws RecordNotFoundException {
        service.updateRoleName(id, roleName);
    }
}
