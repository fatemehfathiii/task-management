package ir.fathi.taskmanagement.repository;

import ir.fathi.taskmanagement.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task,Integer> {

}