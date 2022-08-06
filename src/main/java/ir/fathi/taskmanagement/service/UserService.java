package ir.fathi.taskmanagement.service;

import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.User;
import ir.fathi.taskmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true , isolation = Isolation.READ_COMMITTED)

public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findUserByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credential"));
    }
    //*****************************************************************************************

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void save(User user) throws RecordNotFoundException {
        repository.save(user);
        userRoleService.defaultRoleAssignmentToUser(user);
        Logger.getLogger(UserService.class.getName()).info("you can edit your profile.");
    }

    public List<User> getAll() {
        return (List<User>) repository.findAll();
    }

    public User getById(Integer id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    public User getUserByUsername(String username) throws RecordNotFoundException {
        return repository.findUserByUsernameAndDeletedFalse(username).orElseThrow(RecordNotFoundException::new);
    }

    public List<String> getUserWhoHaveIncompleteTask() {
        return repository.userWhoDidNotDoTask();
    }

    public List<User> getActiveUser() {
        return repository.findUserByDeletedIsFalseAndLockedIsFalse();
    }

    public int countOfActiveUser() {
        return repository.countOfActiveUser();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Integer updatePassword(Integer id, String newPassword) {
        return repository.updatePassword(id, newPassword);
    }


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Integer delete(Integer id) {
        return repository.delete(id);
    }


    public boolean isExistsUsername(String username) {
        return repository.existsByUsernameAndDeletedFalse(username);
    }
}
