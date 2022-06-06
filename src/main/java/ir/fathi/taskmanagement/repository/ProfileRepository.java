package ir.fathi.taskmanagement.repository;

import ir.fathi.taskmanagement.model.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<Profile,Integer> {

    @Query(value = "select * from profile p inner join app_user u on u.id=p.user_id where u.username= :username",
    nativeQuery = true)
    Optional<Profile> findProfileByUsername(@Param("username") String username);


    @Modifying(flushAutomatically = true)
    @Query(value = "update Profile set name= :name ,lastname= :lastname where id= :id")
    Integer updateNameAndLastname(@Param("id") Integer id,@Param("name") String name,@Param("lastName") String lastname);

    @Modifying
    @Query(value = "update Profile set deleted=true where id= :id")
    Integer delete(@Param("id") Integer id);


}
