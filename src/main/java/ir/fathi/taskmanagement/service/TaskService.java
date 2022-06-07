package ir.fathi.taskmanagement.service;

import ir.fathi.taskmanagement.dto.GetTaskDto;
import ir.fathi.taskmanagement.dto.PostTaskDto;
import ir.fathi.taskmanagement.enumType.TaskPriority;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.Task;
import ir.fathi.taskmanagement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Priority;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository repository;

    @Transactional
    public void save(Task task) {
        repository.save(task);
    }

    public List<Task> getAll() {
        return (List<Task>) repository.findAll();
    }

    public Task getTaskById(Integer id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    public List<Task> getTaskByUsername(String username){
        return repository.findTaskByOwner_Username(username);
    }

    public List<Task> getIncompleteTaskByUsername(String username){
        return repository.findTaskByOwner_UsernameAndDoneIsNull(username);
    }

    public List<Task> getCompleteTaskByUsername(String username){
        return repository.findTaskByOwner_UsernameAndDoneIsNotnull(username);
    }

    public List<Task> getTodayCompleteTask(String username){
        return  repository.findTaskByOwner_UsernameAndDoneToday(username);
    }

    public List<Task> getTaskByPriorityAndUsername(String username , TaskPriority priority){
        return repository.findTaskByOwner_UsernameAndPriority(username,priority);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,readOnly = true)
    public List<Task> getTaskByNameOfPerson(String name ,String lastname){
        return repository.findTaskByNameOfPerson(name, lastname);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Integer updateTimeToDo(Integer id){
      return repository.updateTimeToDo(id);
    }


    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Integer delete (Integer id){
       return repository.delete(id);
    }

}
