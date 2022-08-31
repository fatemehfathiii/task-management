package ir.fathi.taskmanagement.service;

import ir.fathi.taskmanagement.customValidation.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.User;
import ir.fathi.taskmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class UserService implements UserDetailsService {
    private final UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findUserByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credential"));
    }
    //*****************************************************************************************

    public List<User> getAll() {
        return (List<User>) repository.findAll();
    }

    public User getById(Integer id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
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


    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public boolean isExistsUsername(String username) {
        return repository.existsByUsernameAndDeletedFalse(username);
    }
}
