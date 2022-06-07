package ir.fathi.taskmanagement.service;

import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.Role;
import ir.fathi.taskmanagement.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleService {
    private final RoleRepository repository;

    public List<Role> getAll(){
        return (List<Role>) repository.findAll();
    }


    public Role getById(Integer id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Integer updateRoleName(Integer id,String roleName){
     return repository.updateRoleName(id, roleName);
    }
}
