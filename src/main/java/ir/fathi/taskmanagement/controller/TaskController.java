package ir.fathi.taskmanagement.controller;

import ir.fathi.taskmanagement.config.aspect.MethodDurationLog;
import ir.fathi.taskmanagement.dto.GetTaskDto;
import ir.fathi.taskmanagement.dto.PostTaskDto;
import ir.fathi.taskmanagement.enumType.TaskPriority;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.Task;
import ir.fathi.taskmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ROLE_ADD_TASK")
    public void save(@RequestBody @Valid PostTaskDto taskDto) throws RecordNotFoundException {
            service.save(Task.fromDto(taskDto) , taskDto.username());
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_GET_TASK")
    @ResponseBody
    public List<GetTaskDto> getAllTask() {
        return service.getAll().stream()
                .map(GetTaskDto::generateCustomGetTaskDto).collect(Collectors.toList());
    }


    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_GET_TASK")
    @ResponseBody
    @Validated
    public GetTaskDto getTaskById(@PathVariable @Positive Integer id) throws RecordNotFoundException {
         return GetTaskDto.generateCustomGetTaskDto(service.getTaskById(id));
    }


    @GetMapping("/get/byUsername")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_GET_TASK")
    @ResponseBody
    public List<GetTaskDto> getTaskByUsername() {
        return service.getTaskByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .stream().map(GetTaskDto::generateCustomGetTaskDto).collect(Collectors.toList());
    }


    @GetMapping("/get/incomplete")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_GET_TASK")
    @ResponseBody
    public List<GetTaskDto> getIncompleteTaskByUsername(){
      return service.getIncompleteTaskByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).stream()
              .map(GetTaskDto::generateCustomGetTaskDto).collect(Collectors.toList());
    }

    @GetMapping("/get/complete")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_GET_TASK")
    @ResponseBody
    public List<GetTaskDto> getCompleteTaskByUsername(){
        return service.getCompleteTaskByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).stream()
                .map(GetTaskDto::generateCustomGetTaskDto).collect(Collectors.toList());
    }


    @GetMapping("/get/today/complete")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_GET_TASK")
    @ResponseBody
    public List<GetTaskDto> getTodayCompleteTask(){
        return service.getTodayCompleteTask(SecurityContextHolder.getContext().getAuthentication().getName())
                .stream().map(GetTaskDto::generateCustomGetTaskDto).collect(Collectors.toList());
    }

    @GetMapping("/get/byPriority/{priority}")
    @Secured("ROLE_GET_TASK")
    @ResponseBody
    @Validated
    @MethodDurationLog
    public List<GetTaskDto> getTaskByPriorityAndUsername(@PathVariable @NotBlank @NotNull TaskPriority priority){
            return service.getTaskByPriorityAndUsername(SecurityContextHolder.getContext().getAuthentication().getName(),priority)
                    .stream().map(GetTaskDto::generateCustomGetTaskDto).collect(Collectors.toList());

    }

    @GetMapping("/get/byName")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_GET_TASK")
    @ResponseBody
    @Validated
    @MethodDurationLog
    public List<GetTaskDto> getTaskByNameOfPerson(@RequestBody @Valid Map< @NotBlank String, @NotBlank @NotNull String> items){
        return service.getTaskByNameOfPerson(items.get("name"),items.get("lastname")).stream()
                .map(GetTaskDto::generateCustomGetTaskDto).collect(Collectors.toList());
    }


    @PatchMapping("/update/{id}")
    @Secured("ROLE_UPDATE_TASK")
    @Validated
    @ResponseBody
    public ResponseEntity<String> updateTimeToDo(@PathVariable @Positive Integer id) {
        return ResponseEntity.ok().body(service.updateTimeToDo(id) + "record updated.");
    }


    @PatchMapping("/delete/{id}")
    @Secured("ROLE_DELETE_TASK")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable @Positive Integer id) {
        return ResponseEntity.ok().body(service.delete(id)+"record deleted.");
    }

}
