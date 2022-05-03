package ir.fathi.taskmanagement.controller;

import ir.fathi.taskmanagement.dto.GetTaskDto;
import ir.fathi.taskmanagement.dto.PostTaskDto;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.Task;
import ir.fathi.taskmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService service;


    @PostMapping
    public void save(@RequestBody @Valid PostTaskDto taskDto) {
        service.save(Task.fromDto(taskDto));
    }

    @GetMapping
    @ResponseBody
    public List<GetTaskDto> getAllTask() {
        List<GetTaskDto> customTask = new ArrayList<>();
        service.getAll().forEach(task -> customTask.add(GetTaskDto.customTask(task)));
        return customTask;
    }

    @GetMapping("/{id}")
    @Validated
    public Task getTaskById(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        return service.getTaskById(id);
    }

    @PatchMapping("/{id}")
    @Validated
    public void updateTimeToDo(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        service.updateTimeToDo(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        service.delete(id);
    }

}
