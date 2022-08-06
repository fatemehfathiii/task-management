package ir.fathi.taskmanagement.controller;

import ir.fathi.taskmanagement.dto.GetRoleDto;
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
    public List<GetRoleDto> getAll() {
        return service.getAll().stream()
                .map(role -> new GetRoleDto( role.getName()))
                .collect(Collectors.toList());
    }


}
