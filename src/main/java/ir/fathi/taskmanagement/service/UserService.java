package ir.fathi.taskmanagement.service;

import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.User;
import ir.fathi.taskmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findUserByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credential"));
    }


    @Transactional
    public void save(User user) {
        repository.save(user);
    }

    public List<User> getAll() {
        return (List<User>) repository.findAll();
    }

    public User getById(Integer id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(RecordNotFoundException::new);
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

    @Transactional
    public Integer updatePassword(Integer id, String newPassword) {
        return repository.updatePassword(id, newPassword);
    }


    @Transactional
    public Integer delete(Integer id) {
        return repository.delete(id);
    }


}
