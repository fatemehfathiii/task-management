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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

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
                    Logger.getLogger(UserRoleService.class.getName())
                            .info("create"+roleNames.size()+" new records in userRole table.");
                }
        );
    }

    public void saveRoleAndUserInUserRoleTable(User user, List<Role> roles) {
        roles.forEach(r ->{
            userRoleRepository.save(UserRole.assignUserWithRole(user, r));
        });
        Logger.getLogger(UserRoleService.class.getName()).info("create"+roles.size()+" new records in userRole table.");
    }

    public void defaultRoleAssignmentToUser(User user) throws RecordNotFoundException {
        var role = roleService.getRoleByRoleName("ROLE_GET_PROFILE");
        userRoleRepository.save(UserRole.assignUserWithRole(user,role));
    }

    public List<Role> getRolesByUserId(Integer id){
        return userRoleRepository.rolesOfUser(id);

    }
}
