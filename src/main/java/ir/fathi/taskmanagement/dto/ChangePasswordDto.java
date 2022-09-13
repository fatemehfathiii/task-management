package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public record ChangePasswordDto(
        @NotNull
        @Positive
        @JsonProperty("id") Integer id,

        @NotBlank
        @Size(min = 8 , message = "password must longer than 8 character ")
        @JsonProperty("NewPassword")  String NewPassword
) {
}
