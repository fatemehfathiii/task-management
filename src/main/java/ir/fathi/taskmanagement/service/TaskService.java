package ir.fathi.taskmanagement.service;

import ir.fathi.taskmanagement.dto.PostTaskDto;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.Task;
import ir.fathi.taskmanagement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;

    @Transactional
    public void save(PostTaskDto taskDto) {
        repository.save(Task.fromDto(taskDto));
    }

    @Transactional(readOnly = true)
    public List<Task> getAll() {
        return (List<Task>) repository.findAll();
    }

    @Transactional(readOnly = true)
    public Task getTaskById(Integer id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void updateTimeToDo(Integer id) throws RecordNotFoundException {
        var task=repository.findById(id).orElseThrow(RecordNotFoundException::new);
        task.setDone(LocalDateTime.now());
        repository.save(task);
    }


    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void delete (Integer id) throws RecordNotFoundException {
        var task = repository.findById(id).orElseThrow(RecordNotFoundException::new);
        task.setDeleteAt(LocalDateTime.now());
        repository.save(task);

    }
}
