package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import ir.fathi.taskmanagement.enumType.TaskPriority;
import ir.fathi.taskmanagement.enumType.TaskType;
import ir.fathi.taskmanagement.model.User;

import java.time.LocalDateTime;
import java.util.List;

public record GetTaskDto(

        @JsonProperty("name") String name,

        @JsonProperty("type") List<TaskType> type,

        @JsonProperty("subject") String subject,

        @JsonProperty("priority") TaskPriority priority,

        @JsonFormat(pattern = "yyyy/mm/dd")
        @JsonProperty("createAt") LocalDateTime creatAt,

        @JsonProperty("description") String description

) {

        public GetTaskDto(String name, List<TaskType> type, String subject,
                          TaskPriority priority, LocalDateTime creatAt, String description) {
                this.name = name;
                this.type = type;
                this.subject = subject;
                this.priority = priority;
                this.creatAt = creatAt;
                this.description = description;

        }

}
