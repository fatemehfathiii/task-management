package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public record PostUserRoleDto(
        @NotBlank
        @NotNull
        @Min(value = 6, message = "username must longer than 6 character ")
        @JsonProperty("username")
        String username,

        @NotEmpty
        @JsonProperty("roleNames")
        List<String> roleNames
) {
}
