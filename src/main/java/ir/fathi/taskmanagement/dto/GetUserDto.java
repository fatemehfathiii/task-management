package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record GetUserDto(

        @NotNull
        @NotBlank
        @JsonProperty("username")
        String username
) {

        public GetUserDto(String username) {
                this.username = username;
        }
}
