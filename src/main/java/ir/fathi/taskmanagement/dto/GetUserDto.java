package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.fathi.taskmanagement.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record GetUserDto(

        @JsonProperty("username")
        String username
) {
        public GetUserDto(String username) {
                this.username = username;
        }

        public static GetUserDto generateCustomGetUserDto(User user){
                return new GetUserDto(user.getUsername());
        }
}
