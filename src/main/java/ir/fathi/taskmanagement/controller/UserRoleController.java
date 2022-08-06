package ir.fathi.taskmanagement.controller;

import ir.fathi.taskmanagement.dto.GetRoleDto;
import ir.fathi.taskmanagement.dto.PostUserRoleDto;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/userRole")
@RequiredArgsConstructor
public class UserRoleController {
    private final UserRoleService service;

    @PostMapping("/assign")
    @Secured("ROLE_ASSIGN_ROLE")
    public ResponseEntity<String> assignUserWithRole(@RequestBody @Valid PostUserRoleDto userRoleDto) throws RecordNotFoundException {
        service.saveRoleAndUserInUserRoleTable(userRoleDto.username(), userRoleDto.roleNames());

        return new ResponseEntity<>("roles are assign to the user ", HttpStatus.CREATED);
    }

    @GetMapping("/get/RoleName/{userId}")
    @ResponseBody
    @Secured("ROLE_GET_ROLE")
    public List<GetRoleDto> getRolesOfUser(@PathVariable Integer userId) {
        var roleNames = service.getRolesByUserId(userId);

        if (roleNames.isEmpty()) {
            Logger.getLogger(UserRoleController.class.getName())
                    .info(" user have any role ! ");
        }

        return roleNames.stream().map(role -> new GetRoleDto(role.getName())).collect(Collectors.toList());

    }
}
