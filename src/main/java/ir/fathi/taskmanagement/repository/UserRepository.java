package ir.fathi.taskmanagement.repository;
import ir.fathi.taskmanagement.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    boolean existsByUsernameAndDeletedFalse(String username);
    Optional<User> findUserByUsernameAndDeletedFalse(String username);
    List<User> findUserByDeletedIsFalseAndLockedIsFalse();

    @Query("select count(u.username) from User u where u.deleted=false")
    int countOfActiveUser();

    @Query("select distinct u.username from User u join u.task t where u.deleted = false and t.done is null")
    List<String> userWhoDidNotDoTask();

    @Modifying
    @Query(value = "update User set password= :newPassword where id= :id")
    Integer updatePassword(@Param("id") Integer id , @Param("newPassword") String password);


    @Modifying(clearAutomatically = true)
    @Query(value = "update User u set u.deleted = true  where u.deleted=false and u.id= :id")
    Integer delete(@Param("id") Integer id);

}
