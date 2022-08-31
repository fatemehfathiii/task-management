package ir.fathi.taskmanagement.service;

import ir.fathi.taskmanagement.customValidation.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.Role;
import ir.fathi.taskmanagement.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true,isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
public class RoleService {
    private final RoleRepository repository;

    public List<Role> getAll(){
        return (List<Role>) repository.findAll();
    }

    public List<Role> getRoleByListOfName(List<String> roleNames){
        return repository.findRoleByNameIn(roleNames);
    }

    public Role getRoleByRoleName(String name) throws RecordNotFoundException {
        return repository.findRoleByName(name).orElseThrow(RecordNotFoundException::new);
    }


}
