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

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService service;
    private final String username = SecurityContextHolder.getContext().getAuthentication().getName();

    @PostMapping
    @Secured("ROLE_ADD_TASK")
    public ResponseEntity<String> save(@RequestBody @Valid PostTaskDto taskDto) {
        service.save(Task.fromDto(taskDto));
        return new ResponseEntity<>("save success",HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    @Secured("ROLE_GET_TASK")
    @ResponseBody
    public List<GetTaskDto> getAllTask() {
        return service.getAll().stream()
                .map(task -> new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                        , task.getCreateAt(), task.getDescription()))
                .collect(Collectors.toList());
    }


    @GetMapping("/get/{id}")
    @Secured("ROLE_GET_TASK")
    @ResponseBody
    @Validated
    public GetTaskDto getTaskById(@PathVariable @Positive Integer id) throws RecordNotFoundException {
            var task = service.getTaskById(id);
            return new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                    , task.getCreateAt(), task.getDescription());
    }


    @GetMapping("/get/byUsername")
    @Secured("ROLE_GET_TASK")
    @ResponseBody
    public List<GetTaskDto> getTaskByUsername() {
        return service.getTaskByUsername(username).stream()
                .map(task -> new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                        , task.getCreateAt(), task.getDescription()))
                .collect(Collectors.toList());
    }

    @GetMapping("/get/incomplete")
    @Secured("ROLE_GET_TASK")
    @ResponseBody
    public List<GetTaskDto> getIncompleteTaskByUsername(){
      return service.getIncompleteTaskByUsername(username).stream()
              .map(task -> new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                      , task.getCreateAt(), task.getDescription()))
              .collect(Collectors.toList());
    }

    @GetMapping("/get/complete")
    @Secured("ROLE_GET_TASK")
    @ResponseBody
    public List<GetTaskDto> getCompleteTaskByUsername(){
        return service.getCompleteTaskByUsername(username).stream()
                .map(task -> new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                        , task.getCreateAt(), task.getDescription()))
                .collect(Collectors.toList());
    }


    @GetMapping("/get/today/complete")
    @Secured("ROLE_GET_TASK")
    @ResponseBody
    public List<GetTaskDto> getTodayCompleteTask(){
        return service.getTodayCompleteTask(username).stream()
                .map(task -> new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                        , task.getCreateAt(), task.getDescription()))
                .collect(Collectors.toList());
    }

    @GetMapping("/get/byPriority")
    @Secured("ROLE_GET_TASK")
    @ResponseBody
    @Validated
    @MethodDurationLog
    public List<GetTaskDto> getTaskByPriorityAndUsername(@RequestParam(name ="priority") @NotBlank @NotNull TaskPriority priority){
            return service.getTaskByPriorityAndUsername(username,priority).stream()
                    .map(task -> new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                            , task.getCreateAt(), task.getDescription()))
                    .collect(Collectors.toList());

    }

    @GetMapping("/get/byName")
    @Secured("ROLE_GET_TASK")
    @ResponseBody
    @Validated
    @MethodDurationLog
    public List<GetTaskDto> getTaskByNameOfPerson(@RequestBody @Valid Map< @NotBlank String, @NotBlank @NotNull String> items){
        return service.getTaskByNameOfPerson(items.get("name"),items.get("lastname")).stream()
                .map(task -> new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                        , task.getCreateAt(), task.getDescription()))
                .collect(Collectors.toList());
    }


    @PatchMapping("/update/{id}")
    @Secured("ROLE_UPDATE_TASK")
    @Validated
    @ResponseBody
    public ResponseEntity<String> updateTimeToDo(@PathVariable @Positive Integer id) {
        return new ResponseEntity<>(service.updateTimeToDo(id) + "record updated.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Secured("ROLE_DELETE_TASK")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable @Positive Integer id) {
        return new ResponseEntity<>(service.delete(id)+"record deleted.", HttpStatus.OK);
    }

}
