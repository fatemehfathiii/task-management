package ir.fathi.taskmanagement.controller;

import ir.fathi.taskmanagement.dto.GetRoleDto;
import ir.fathi.taskmanagement.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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
    public List<GetRoleDto> getAll() {
        return service.getAll().stream()
                .map(GetRoleDto::generateCustomGetRoleDto).collect(Collectors.toList());
    }


}
