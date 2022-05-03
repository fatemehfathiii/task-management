package ir.fathi.taskmanagement.repository;

import ir.fathi.taskmanagement.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer> {
}
