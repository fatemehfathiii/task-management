package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.fathi.taskmanagement.customValidation.ValidateEnum;
import ir.fathi.taskmanagement.enumType.TaskPriority;
import ir.fathi.taskmanagement.enumType.TaskType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public record PostTaskDto(
        @NotBlank
        @JsonProperty("name") String name,

        @NotBlank
        @JsonProperty("subject") String subject,

        @ValidateEnum(targetClassType =TaskType.class , message = " type must be undefined or fixedWork or fixedDuration or fixedUnits")
        @JsonProperty("type") List<TaskType> type,

        @NotBlank
        @ValidateEnum(targetClassType = TaskPriority.class,message = "priority must be low or medium or high or highest")
        @JsonProperty("priority") TaskPriority priority,

        @Size(max = 255 , message = "you must write description with 255 character.")
        @JsonProperty("description") String description,

        @NotBlank
        @Size(min = 6,message = "username of owner must longer than 6 character. ")
        @JsonProperty("owner") String username
) {
}
