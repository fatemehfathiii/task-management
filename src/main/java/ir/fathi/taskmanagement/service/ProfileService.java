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
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true , isolation = Isolation.READ_COMMITTED)
public class ProfileService {
    private final ProfileRepository repository;

    public Profile getProfileByUsername(String username) throws RecordNotFoundException {
        return repository.findProfileByUsername(username).orElseThrow(()-> new RecordNotFoundException(LocalDateTime.now()));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Integer updateProfile(String name, String lastname , String nationalCode, Sex sex, LocalDate birthday , String mobileNumber, String email){
        return repository.updateProfile(name, lastname , nationalCode, sex, birthday, mobileNumber, email);
    }


}
