package ir.fathi.taskmanagement.controller;

import ir.fathi.taskmanagement.dto.GetTaskDto;
import ir.fathi.taskmanagement.dto.PostTaskDto;
import ir.fathi.taskmanagement.enumType.TaskPriority;
import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.model.Task;
import ir.fathi.taskmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService service;


    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid PostTaskDto taskDto) {
        service.save(Task.fromDto(taskDto));
        return new ResponseEntity<>("save success",HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<GetTaskDto> getAllTask() {
        return service.getAll().stream()
                .map(task -> new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                        , task.getCreateAt(), task.getDescription()))
                .collect(Collectors.toList());
    }


    @GetMapping("/get/{id}")
    @ResponseBody
    @Validated
    public GetTaskDto getTaskById(@PathVariable @Positive Integer id) throws RecordNotFoundException {

            var task = service.getTaskById(id);
            return new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                    , task.getCreateAt(), task.getDescription());
    }


    @GetMapping("/get/byUsername")
    @ResponseBody
    @Validated
    public List<GetTaskDto> getTaskByUsername(@RequestParam(name = "username") @NotBlank String username) {
        return service.getTaskByUsername(username).stream()
                .map(task -> new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                        , task.getCreateAt(), task.getDescription()))
                .collect(Collectors.toList());
    }

    @GetMapping("/get/incomplete")
    @ResponseBody
    @Validated
    public List<GetTaskDto> getIncompleteTaskByUsername(@RequestParam(name = "username") @NotBlank String username){
      return service.getIncompleteTaskByUsername(username).stream()
              .map(task -> new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                      , task.getCreateAt(), task.getDescription()))
              .collect(Collectors.toList());
    }

    @GetMapping("/get/complete")
    @ResponseBody
    @Validated
    public List<GetTaskDto> getCompleteTaskByUsername(@RequestParam(name = "username") @NotBlank String username){
        return service.getCompleteTaskByUsername(username).stream()
                .map(task -> new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                        , task.getCreateAt(), task.getDescription()))
                .collect(Collectors.toList());
    }

    @GetMapping("/get/today/complete")
    @ResponseBody
    @Validated
    public List<GetTaskDto> getTodayCompleteTask(@RequestParam(name = "username") @NotBlank String username){
        return service.getTodayCompleteTask(username).stream()
                .map(task -> new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                        , task.getCreateAt(), task.getDescription()))
                .collect(Collectors.toList());
    }

    @GetMapping("/get/byPriority")
    @ResponseBody
    @Validated
    public List<GetTaskDto> getTaskByPriorityAndUsername(@RequestBody  Map<@NotBlank @NotNull String,@NotNull Object> items){

            return service.getTaskByPriorityAndUsername((String) items.get("name"),(TaskPriority) items.get("priority")).stream()
                    .map(task -> new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                            , task.getCreateAt(), task.getDescription()))
                    .collect(Collectors.toList());

    }

    @GetMapping("/get/byName")
    @ResponseBody
    @Validated
    public List<GetTaskDto> getTaskByNameOfPerson(@RequestBody @Valid Map< @NotBlank String, @NotBlank @NotNull String> items){
        return service.getTaskByNameOfPerson(items.get("name"),items.get("lastname")).stream()
                .map(task -> new GetTaskDto(task.getName(), task.getType(), task.getSubject(), task.getPriority()
                        , task.getCreateAt(), task.getDescription()))
                .collect(Collectors.toList());
    }


    @PatchMapping("/update/{id}")
    @Validated
    @ResponseBody
    public ResponseEntity<String> updateTimeToDo(@PathVariable @Positive Integer id) {
        return new ResponseEntity<>(service.updateTimeToDo(id) + "record updated.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable @Positive Integer id) {
        service.delete(id);
        return new ResponseEntity<>("record deleted.", HttpStatus.OK);
    }


}
