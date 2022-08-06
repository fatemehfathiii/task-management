package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.fathi.taskmanagement.customValidation.Messenger;
import ir.fathi.taskmanagement.enumType.TaskPriority;
import ir.fathi.taskmanagement.enumType.TaskType;
import ir.fathi.taskmanagement.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public record PostTaskDto(
        @NotBlank
        @JsonProperty("name") String name,

        @NotBlank
        @JsonProperty("subject") String subject,

        @NotEmpty
        @Messenger(message = " type must be undefined or FixedWork or FixedDuration or FixedUnits ")
        @JsonProperty("type") List<TaskType> type,

        @NotBlank
        @Messenger(message = "priority must be  LOW or MEDIUM or HIGH or HIGHEST ")
        @JsonProperty("priority") TaskPriority priority,

        @Messenger(message = "you can write description for this task.")
        @JsonProperty("description") String description,

        @NotBlank
        @Size(min = 6,message = "username of owner must longer than 6 character ")
        @JsonProperty("owner") String username
) {
}
