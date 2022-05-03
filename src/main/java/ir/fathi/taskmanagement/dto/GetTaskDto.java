package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import ir.fathi.taskmanagement.Enum.TaskPriority;
import ir.fathi.taskmanagement.Enum.TaskType;
import ir.fathi.taskmanagement.model.Task;
import ir.fathi.taskmanagement.model.User;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public record GetTaskDto(

        @NotNull
        @NotBlank
        @JsonProperty("name") String name,

        @NotEmpty
        @JsonProperty("type") List<TaskType> type,

        @NotNull
        @NotBlank
        @JsonProperty("subject") String subject,

        @NotNull
        @NotBlank
        @JsonProperty("priority") TaskPriority priority,

        @FutureOrPresent
        @JsonFormat(pattern = "yyyy/mm/dd")
        @JsonProperty("createAt") LocalDateTime creatAt,

        @NotNull
        @NotBlank
        @JsonProperty("description") String description,


        @NotNull
        @NotBlank
        @JsonProperty("owner") User owner
) {

        public GetTaskDto(String name, List<TaskType> type, String subject, TaskPriority priority, LocalDateTime creatAt, String description, User owner) {
                this.name = name;
                this.type = type;
                this.subject = subject;
                this.priority = priority;
                this.creatAt = creatAt;
                this.description = description;
                this.owner = owner;
        }

        public static GetTaskDto customTask(Task task){

                return new GetTaskDto(task.getName(),task.getType(), task.getSubject(), task.getPriority(),
                        task.getCreateAt(), task.getDescription(), task.getOwner()) ;

        }
}
