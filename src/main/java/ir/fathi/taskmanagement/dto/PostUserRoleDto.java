package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.util.List;

public record PostUserRoleDto(

        @Size(min = 6 , message = "username must longer than 6 character ")
        @JsonProperty("username")
        String username,

        @NotEmpty
        @JsonProperty("roleNames")
        List<String> roleNames

) {
}
