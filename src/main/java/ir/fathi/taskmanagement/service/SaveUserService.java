package ir.fathi.taskmanagement.service;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.User;
import ir.fathi.taskmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class SaveUserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void save(User user) throws RecordNotFoundException {
        userRepository.save(user);
        userRoleService.defaultRoleAssignmentToUser(user);
        Logger.getLogger(UserService.class.getName()).info("you can edit your profile.");
    }
}
