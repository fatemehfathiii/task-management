package ir.fathi.taskmanagement.repository;

import ir.fathi.taskmanagement.model.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer> {

    @Modifying
    @Query(value = "update Role set name= :roleName where id= :id")
    Integer updateRoleName(@Param("id") Integer id , @Param("roleName") String name);
}
