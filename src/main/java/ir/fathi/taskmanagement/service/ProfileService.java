package ir.fathi.taskmanagement.service;

import ir.fathi.taskmanagement.enumType.Sex;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.Profile;
import ir.fathi.taskmanagement.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true , isolation = Isolation.READ_COMMITTED)
public class ProfileService {
    private final ProfileRepository repository;


    public List<Profile> getAll(){
        return (List<Profile>) repository.findAll();
    }

    public Profile getProfileByUsername(String username) throws RecordNotFoundException {
        return repository.findProfileByUsername(username).orElseThrow(RecordNotFoundException::new);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Integer updateProfile(String name, String lastname, Sex sex, LocalDate birthday , String mobileNumber, String email){
        return repository.updateProfile(name, lastname, sex, birthday, mobileNumber, email);
    }


}
