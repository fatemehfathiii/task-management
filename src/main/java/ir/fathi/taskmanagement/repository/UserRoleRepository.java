package ir.fathi.taskmanagement.repository;

import ir.fathi.taskmanagement.model.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole,Integer> {
}
