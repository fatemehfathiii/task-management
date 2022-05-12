package ir.fathi.taskmanagement.service;

import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.User;
import ir.fathi.taskmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

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
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void updatePassword(Integer id, String newPassword) throws RecordNotFoundException {
        var user = repository.findById(id).orElseThrow(RecordNotFoundException::new);
        user.setPassword(newPassword);
        repository.save(user);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void delete(Integer id) throws RecordNotFoundException {
        var user = repository.findById(id).orElseThrow(RecordNotFoundException::new);
        user.setDeleted(true);
        repository.save(user);
    }

}
