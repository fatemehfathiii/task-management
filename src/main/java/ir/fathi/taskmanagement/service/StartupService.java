package ir.fathi.taskmanagement.service;

import ir.fathi.taskmanagement.config.StartupApplicationListener;
import ir.fathi.taskmanagement.model.Role;
import ir.fathi.taskmanagement.model.User;
import ir.fathi.taskmanagement.repository.RoleRepository;
import ir.fathi.taskmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class StartupService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;



    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void rollAndUserAdminProvider() {

        try{

            roleRepository.saveAll(generateRole());
            Logger.getLogger(StartupService.class.getName()).info("all the rolls are placed in role table.");

        }catch (Exception ignored){
            Logger.getLogger(StartupApplicationListener.class.getName()).info("all the rolls are placed in role table.");
        }


        try{
            makeUserAdmin();

        }catch (Exception exception){

            exception.getCause();
            exception.printStackTrace();
        }

    }


    private void makeUserAdmin(){
        if (!userService.isExistsUsername("administrator")) {
            var userAdmin = User.generateUserAdmin(passwordEncoder.encode("userAdminPassword"));
            userRepository.save(userAdmin);
            List<Role> allRoll = (List<Role>) roleRepository.findAll();
            userRoleService.saveRoleAndUserInUserRoleTable(userAdmin, allRoll);

            Logger.getLogger(StartupService.class.getName()).info("user_amin is placed in user table. ");
        }
    }


    private List<Role> generateRole() {
        return List.of(
                new Role(1, "ROLE_GET_USER", "GET", "USER"),
                new Role(2, "ROLE_UPDATE_USER", "UPDATE", "USER"),
                new Role(3, "ROLE_DELETE_USER", "DELETE", "USER"),
                new Role(4, "ROLE_GET_ROLE", "GET", "ROLE"),
                new Role(5, "ROLE_ASSIGN_ROLE", "CREATE", "USER_ROLE"),
                new Role(6, "ROLE_GET_PROFILE", "GET", "PROFILE"),
                new Role(7, "ROLE_UPDATE_PROFILE", "UPDATE", "PROFILE"),
                new Role(8, "ROLE_ADD_TASK", "CREATE", "TASK"),
                new Role(9, "ROLE_GET_TASK", "GET", "TASK"),
                new Role(10, "ROLE_UPDATE_TASK", "UPDATE", "TASK"),
                new Role(11, "ROLE_DELETE_TASK", "DELETE", "TASK")
        );
    }
}
