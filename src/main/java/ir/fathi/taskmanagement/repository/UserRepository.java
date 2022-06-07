package ir.fathi.taskmanagement.repository;
import ir.fathi.taskmanagement.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@ResponseBody

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    Optional<User> findUserByUsernameAndDeletedFalse(String username);



    @Query(value = "select count(u.username) from app_user u where u.deleted=false",nativeQuery = true)
    int countOfActiveUser();


    @Query(value = "select distinct u.username from user u inner join task t on u.id=t.user_id where u.deleted=false and t.done IS  NULL" ,
            nativeQuery = true)
    List<String> userWhoDidNotDoTask();


    @Modifying
    @Query(value = "update User set password= :newPassword where id= :id")
    Integer updatePassword(@Param("id") Integer id , @Param("newPassword") String password);


    @Modifying(clearAutomatically = true)
    @Query(value = "update User u set u.deleted = true  where u.deleted=false and u.id= :id")
    Integer delete(@Param("id") Integer id);

}
