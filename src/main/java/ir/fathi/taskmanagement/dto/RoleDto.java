package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RoleDto(

        @NotBlank
        @NotNull
        @JsonProperty("main")
        String main,

        @NotBlank
        @NotNull
        @JsonProperty("category")
        String category,

        @NotBlank
        @NotNull
        @JsonProperty("name")
        String name
) {

        public RoleDto(String main, String category, String name) {
                this.main = main;
                this.category = category;
                this.name = name;
        }
}
