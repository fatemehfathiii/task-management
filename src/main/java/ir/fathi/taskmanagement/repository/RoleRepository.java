package ir.fathi.taskmanagement.repository;

import ir.fathi.taskmanagement.model.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer> {
    List<Role> findRoleByNameIn(List<String> roleName);
    Optional<Role> findRoleByName(String name);


}
