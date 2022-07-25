package ir.fathi.taskmanagement.service;

import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.Profile;
import ir.fathi.taskmanagement.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true , isolation = Isolation.READ_COMMITTED)
public class ProfileService {
    private final ProfileRepository repository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void save(Profile profile){
        repository.save(profile);
    }

    public List<Profile> getAll(){
        return (List<Profile>) repository.findAll();
    }

    public Profile getById(Integer id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    public Profile getProfileByUsername(String username) throws RecordNotFoundException {
        return repository.findProfileByUsername(username).orElseThrow(RecordNotFoundException::new);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Integer updateNameAndLastname(Integer id,String name,String lastname) {
     return repository.updateNameAndLastname(id, name, lastname);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Integer delete(Integer id){
      return repository.delete(id);
    }
}
