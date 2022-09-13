package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record PostUserDto(
        @Size(min = 6 , max = 50, message = "username must be between 6 and 50 character ")
        @JsonProperty("username")
        String username,

        @Size(min = 8, message = "password must longer than 8 character ")
        @JsonProperty("password")
        String password,

        @NotBlank(message = "you must enter your name")
        String name,

        @NotBlank(message = "you must enter your lastname")
        String lastname,

        @NotBlank(message = "you must enter your nationalCode")
        @Size(min = 10)
        String nationalCode
) {

        public PostUserDto(String username, String password, String name, String lastname, String nationalCode) {
                this.username = username;
                this.password = password;
                this.name = name;
                this.lastname = lastname;
                this.nationalCode = nationalCode;
        }
}
