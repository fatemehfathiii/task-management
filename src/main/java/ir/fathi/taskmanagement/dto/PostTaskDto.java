package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.fathi.taskmanagement.Enum.TaskPriority;
import ir.fathi.taskmanagement.Enum.TaskType;
import ir.fathi.taskmanagement.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public record PostTaskDto(
        @NotNull
        @NotBlank
        @JsonProperty("name") String name,

        @NotNull
        @NotBlank
        @JsonProperty("subject") String subject,

        @NotEmpty
        @JsonProperty("type") List<TaskType> type,

        @NotBlank
        @NotNull
        @JsonProperty("priority") TaskPriority priority,

        @NotBlank
        @NotNull
        @JsonProperty("description") String description,

        @NotNull
        @NotBlank
        @JsonProperty("owner") User owner

) {
}
