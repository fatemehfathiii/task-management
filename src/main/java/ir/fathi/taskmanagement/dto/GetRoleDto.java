package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetRoleDto(

        @JsonProperty("roleName")
        String roleName
) {

    public GetRoleDto(String roleName) {
        this.roleName = roleName;
    }
}
