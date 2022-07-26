package ir.fathi.taskmanagement.service;

import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.Role;
import ir.fathi.taskmanagement.model.User;
import ir.fathi.taskmanagement.model.UserRole;
import ir.fathi.taskmanagement.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED)
public class UserRoleService {
    private final UserService userService;
    private final RoleService roleService;
    private final UserRoleRepository userRoleRepository;

    public void saveRoleAndUserInUserRoleTable(String username, List<String> roleNames) throws RecordNotFoundException {
        var user = userService.getUserByUsername(username);
        roleService.getRoleByListOfName(roleNames).forEach(
                role -> {
                    userRoleRepository.save(UserRole.assignUserWithRole(user, role));
                }
        );
    }

    public void saveRoleAndUserInUserRoleTable(User user, List<Role> roles) throws RecordNotFoundException {
        roles.forEach(r -> userRoleRepository.save(UserRole.assignUserWithRole(user, r)));
    }

    public void getRolesByUserId(Integer id) {

    }
}
