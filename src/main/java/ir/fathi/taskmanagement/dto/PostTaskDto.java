package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.fathi.taskmanagement.customValidation.Messenger;
import ir.fathi.taskmanagement.enumType.TaskPriority;
import ir.fathi.taskmanagement.enumType.TaskType;
import ir.fathi.taskmanagement.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

        @NotBlank
        @JsonProperty("description") String description,

        @NotNull
        @JsonProperty("owner") User owner

) {
}
