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
@Transactional(readOnly = true)
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

    public List<String> getUserWhoHaveIncompleteTask(){
        return repository.userWhoDidNotDoTask();
    }


    @Transactional(readOnly = true,isolation = Isolation.SERIALIZABLE)
    public int countOfActiveUser(){
        return repository.countOfActiveUser();
    }


    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Integer updatePassword(Integer id, String newPassword){
       return repository.updatePassword(id, newPassword);
    }


    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Integer delete(Integer id){
        return repository.delete(id);
    }

}
