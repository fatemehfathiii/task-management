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

    List<Task> findTaskByOwner_UsernameAndPriority(String username , TaskPriority priority);
    boolean existsByTaskCode(int taskCode);


    @Query("from Task t where t.owner.username= :username and t.done is not null")
    List<Task> findTaskByOwner_UsernameAndDoneIsNotnull(@Param("username") String username);

    @Query("from Task t where t.owner.username= :username and t.done=current_date")
    List<Task> findTaskByOwner_UsernameAndDoneToday(@Param("username") String username);

    @Query("from Task t where t.owner.profile.name= :name and t.owner.profile.lastname= :lastname")
    List<Task> findTaskByNameOfPerson(@Param("name") String name ,@Param("lastname") String lastname);


    @Modifying
    @Query(value = "update Task set done = current_timestamp where done is  null and id= :id")
    Integer updateTimeToDo(@Param("id") Integer id);


    @Modifying
    @Query(value = "update Task set deleteAt = current_timestamp where deleteAt is null and id= :id")
    Integer delete(@Param("id") Integer id);

}