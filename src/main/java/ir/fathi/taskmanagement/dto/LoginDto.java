package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Size;

public record LoginDto(
        @Size(min = 6 , max = 50, message = "username must be between 6 and 50 character ")
        @JsonProperty("username")
        String username,

        @Size(min = 8, message = "password must longer than 8 character ")
        @JsonProperty("password")
        String password
) {
}
