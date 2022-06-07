package ir.fathi.taskmanagement.service;

import ir.fathi.taskmanagement.model.User;
import ir.fathi.taskmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findUserByUsernameAndDeletedFalse(username)
                .orElseThrow(()->new UsernameNotFoundException("Invalid credential"));
    }

    public User findUserByUserName(String username){
        return repository.findUserByUsernameAndDeletedFalse(username)
                .orElseThrow(()->new UsernameNotFoundException("Invalid credential"));
    }


}
