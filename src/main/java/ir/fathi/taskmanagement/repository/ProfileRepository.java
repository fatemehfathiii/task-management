package ir.fathi.taskmanagement.repository;

import ir.fathi.taskmanagement.model.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer> {

    @Query(value = "from Profile p where p.user.username= :username")
    Optional<Profile> findProfileByUsername(@Param("username") String username);


    @Modifying
    @Query(value = "update Profile set name= :name ,lastname= :lastName where id= :id")
    Integer updateNameAndLastname(@Param("id") Integer id, @Param("name") String name, @Param("lastName") String lastname);

    @Modifying
    @Query(value = "update Profile set deleted=true where deleted=false and id= :id")
    Integer delete(@Param("id") Integer id);

}
