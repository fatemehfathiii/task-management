package ir.fathi.taskmanagement.service;

import ir.fathi.taskmanagement.dto.PostTaskDto;
import ir.fathi.taskmanagement.enumType.TaskPriority;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.Task;
import ir.fathi.taskmanagement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true , isolation = Isolation.READ_COMMITTED)
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;


    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void save(PostTaskDto postTaskDto) throws RecordNotFoundException {
        var owner =userService.getUserByUsername(postTaskDto.username());
        taskRepository.save(Task.fromDto(owner,postTaskDto));
    }

    public List<Task> getAll() {
        return (List<Task>) taskRepository.findAll();
    }

    public Task getTaskById(Integer id) throws RecordNotFoundException {
        return taskRepository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    public List<Task> getTaskByUsername(String username){
        return taskRepository.findTaskByOwner_Username(username);
    }

    public List<Task> getIncompleteTaskByUsername(String username){
        return taskRepository.findTaskByOwner_UsernameAndDoneIsNull(username);
    }

    public List<Task> getCompleteTaskByUsername(String username){
        return taskRepository.findTaskByOwner_UsernameAndDoneIsNotnull(username);
    }

    public List<Task> getTodayCompleteTask(String username){
        return  taskRepository.findTaskByOwner_UsernameAndDoneToday(username);
    }

    public List<Task> getTaskByPriorityAndUsername(String username , TaskPriority priority){
        return taskRepository.findTaskByOwner_UsernameAndPriority(username,priority);
    }


    public List<Task> getTaskByNameOfPerson(String name ,String lastname){
        return taskRepository.findTaskByNameOfPerson(name, lastname);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Integer updateTimeToDo(Integer id){
      return taskRepository.updateTimeToDo(id);
    }


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Integer delete (Integer id){
       return taskRepository.delete(id);
    }

}
