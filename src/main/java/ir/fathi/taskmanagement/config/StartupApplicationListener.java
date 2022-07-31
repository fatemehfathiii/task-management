package ir.fathi.taskmanagement.config;

import ir.fathi.taskmanagement.enumType.Sex;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.Profile;
import ir.fathi.taskmanagement.model.Role;
import ir.fathi.taskmanagement.model.User;
import ir.fathi.taskmanagement.repository.RoleRepository;
import ir.fathi.taskmanagement.service.UserRoleService;
import ir.fathi.taskmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //todo save all roles: exception: ignore

        if (!userService.isExistsUsername("administrator2")) {
            User user = new User();

            user.setUsername("administrator2");
            user.setPassword(passwordEncoder.encode("123456789"));

            Profile profile = new Profile();

            profile.setName("admin");
            profile.setLastname("admin");
            profile.setBirthday(new Date());
            profile.setSex(Sex.FEMALE);
            profile.setMobileNumber("09123456789");
            profile.setEmail("fake2@mail.com");

            user.setProfile(profile);
            profile.setUser(user);

            userService.save(user);

            Iterable<Role> roles = roleRepository.findAll();

            try {
                userRoleService.saveRoleAndUserInUserRoleTable(user, (List<Role>) roles);
            } catch (RecordNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
