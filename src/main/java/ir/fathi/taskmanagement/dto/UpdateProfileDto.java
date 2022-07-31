package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record UpdateProfileDto(
        @Positive
        @NotNull
        @JsonProperty("id")
        Integer id,

        @NotBlank
        @JsonProperty("name")
        String name,

        @NotBlank
        @JsonProperty("lastname")
        String lastname
) {
}
