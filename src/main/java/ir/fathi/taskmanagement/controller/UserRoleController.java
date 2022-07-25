package ir.fathi.taskmanagement.controller;

import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/assign")
@RequiredArgsConstructor
public class UserRoleController {
    private final UserRoleService service;

    @PostMapping("/Role/{username}")
    @Validated
    @Secured("ROLE_ASSIGN_ROLE")
    public ResponseEntity<String> matchUserWithRole
            (@PathVariable @NotNull @NotBlank String username, @RequestBody @NotEmpty List<String> roleName) throws RecordNotFoundException {
        service.saveRoleAndUserInUserRoleTable(username, roleName);

        return new ResponseEntity<>("roles are assign to the user " , HttpStatus.CREATED);

    }
}
