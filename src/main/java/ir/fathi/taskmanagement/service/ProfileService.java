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
@Transactional(readOnly = true)
public class ProfileService {
    private final ProfileRepository repository;

    @Transactional
    public void save(Profile profile){
        repository.save(profile);
    }

    public List<Profile> getAll(){
        return (List<Profile>) repository.findAll();
    }

    public Profile getById(Integer id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void updateName(Integer id,String name) throws RecordNotFoundException {
        var profile=repository.findById(id).orElseThrow((RecordNotFoundException::new));
        profile.setName(name);
        repository.save(profile);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ  )
    public void delete(Integer id) throws RecordNotFoundException {
        var profile=repository.findById(id).orElseThrow((RecordNotFoundException::new));
        profile.setDeleted(true);
        repository.save(profile);
    }
}
