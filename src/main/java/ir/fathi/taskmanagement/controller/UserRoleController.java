package ir.fathi.taskmanagement.controller;

import ir.fathi.taskmanagement.dto.PostUserRoleDto;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/assign")
@RequiredArgsConstructor
public class UserRoleController {
    private final UserRoleService service;

    @PostMapping("/Role/user")
    @Secured("ROLE_ASSIGN_ROLE")
    public ResponseEntity<String> matchUserWithRole(@RequestBody @Valid PostUserRoleDto userRoleDto) throws RecordNotFoundException {
        service.saveRoleAndUserInUserRoleTable(userRoleDto.username(), userRoleDto.roleNames());

        return new ResponseEntity<>("roles are assign to the user ", HttpStatus.CREATED);

    }
}
