package ir.fathi.taskmanagement.service;

import ir.fathi.taskmanagement.dto.RoleDto;
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
public class RoleService {

    private final RoleRepository repository;

    @Transactional
    public void save(RoleDto roleDto){
        repository.save(Role.fromDto(roleDto));
    }


    public List<Role> getAll(){
        return (List<Role>) repository.findAll();
    }


    public Role getById(Integer id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void updateRoleName(Integer id,String roleName) throws RecordNotFoundException {
        var role=repository.findById(id).orElseThrow(RecordNotFoundException::new);
        role.setName(roleName);
        repository.save(role);
    }
}
