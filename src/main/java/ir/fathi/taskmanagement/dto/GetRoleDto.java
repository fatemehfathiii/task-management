package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.fathi.taskmanagement.model.Role;

public record GetRoleDto(

        @JsonProperty("roleName")
        String roleName
) {

    public GetRoleDto(String roleName) {
        this.roleName = roleName;
    }


    public static GetRoleDto generateCustomGetRoleDto(Role role){
        return new GetRoleDto( role.getName());
    }
}
