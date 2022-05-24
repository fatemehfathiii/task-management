package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record PostUserDto(

        @NotBlank
        @NotNull
        @Min(value = 6,message = "username must longer than 6 character ")
        @JsonProperty("username")
        String username,


        @NotNull
        @NotBlank
        @Min(value = 8 ,message = "password must longer than 8 character ")
        @JsonProperty("password")
        String password
) {
}
