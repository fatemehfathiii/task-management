package ir.fathi.taskmanagement.controller;

import ir.fathi.taskmanagement.dto.ConvertToDto;
import ir.fathi.taskmanagement.dto.GetTaskDto;
import ir.fathi.taskmanagement.dto.PostTaskDto;
import ir.fathi.taskmanagement.exception.ConvertToDtoException;
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
        //I've used a static method in dto and for each function for convert task into GetTaskDto
        List<GetTaskDto> taskDto=
        return service.getAll()
                })
    }

    @GetMapping("/{id}")
    @Validated
    public GetTaskDto getTaskById(@PathVariable @Positive Integer id) throws RecordNotFoundException {
        //I've used static method in this case
        return GetTaskDto.customTask(service.getTaskById(id));
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
