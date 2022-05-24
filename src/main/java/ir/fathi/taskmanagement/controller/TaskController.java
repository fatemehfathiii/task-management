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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return service.getAll().stream()
                .map(task -> new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                        , task.getCreateAt(), task.getDescription(), task.getOwner()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Validated
    public GetTaskDto getTaskById(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        var task = service.getTaskById(id);
        return new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                , task.getCreateAt(), task.getDescription(), task.getOwner());
    }

    @PatchMapping("/{id}")
    @Validated
    public void updateTimeToDo(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        service.updateTimeToDo(id);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }


}
