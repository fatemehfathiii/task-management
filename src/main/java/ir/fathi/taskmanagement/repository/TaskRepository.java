package ir.fathi.taskmanagement.repository;

import ir.fathi.taskmanagement.enumType.TaskPriority;
import ir.fathi.taskmanagement.model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task,Integer> {

    List<Task> findTaskByOwner_Username(String username);

    List<Task> findTaskByOwner_UsernameAndDoneIsNull(String username);

    List<Task> findTaskByOwner_UsernameAndDoneIsNotnull(String username);

    List<Task> findTaskByOwner_UsernameAndPriority(String username , TaskPriority priority);


    @Query(value = "select * from user u inner join task t on u.id=t.user_id where u.username= :username and t.done=current_date",
            nativeQuery = true)
    List<Task> findTaskByOwner_UsernameAndDoneToday(@Param("username") String username);

    @Query(value = "select * from Task t inner join user u on u.id=t.user_id where u.username=" +
            "(select u.username from user u inner join profile p on u.id=p.user_id where p.name= : name and p.lastname= :lastname)",
            nativeQuery = true)
    List<Task> findTaskByNameOfPerson(@Param("name") String name ,@Param("lastname") String lastname);


    @Modifying(clearAutomatically = true)
    @Query(value = "update Task set done = current_timestamp where done is  null and id= :id")
    Integer updateTimeToDo(@Param("id") Integer id);


    @Modifying(clearAutomatically = true)
    @Query(value = "update Task set deleteAt = current_timestamp where deleteAt is null and id= :id")
    Integer delete(@Param("id") Integer id);

}