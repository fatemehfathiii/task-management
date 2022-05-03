package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record PostUserDto(

        @NotBlank
        @NotNull
        @Min(6)
        @JsonProperty("username")
        String username,


        @NotNull
        @NotBlank
        @Min(8)
        @JsonProperty("password")
        String password
) {
}
