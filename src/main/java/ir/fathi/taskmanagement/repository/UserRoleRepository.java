package ir.fathi.taskmanagement.repository;

import ir.fathi.taskmanagement.model.Role;
import ir.fathi.taskmanagement.model.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {

    @Query(value = "select role from role r inner join userRole ur on r.id=ur.role_id where ur.user_id =: id "
            , nativeQuery = true)
    List<Role> rolesOfUser(@Param("userId") Integer id);

}
