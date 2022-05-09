package ir.fathi.taskmanagement.dto;

import ir.fathi.taskmanagement.model.Profile;
import ir.fathi.taskmanagement.model.Role;
import ir.fathi.taskmanagement.model.Task;
import ir.fathi.taskmanagement.model.User;

public class ConvertToDto {

    public static Object convertDto(Object obj){

        if (obj instanceof User){
            return new GetUserDto(((User) obj).getUsername());
        }

        if (obj instanceof Task){
            return new GetTaskDto(((Task) obj).getName(),((Task) obj).getType(),((Task) obj).getSubject(),
                    ((Task) obj).getPriority(),((Task) obj).getCreateAt(),
                    ((Task) obj).getDescription(),((Task) obj).getOwner());
        }

        if (obj instanceof Role){
            return new RoleDto(((Role) obj).getMain(), ((Role) obj).getCategory(), ((Role) obj).getName());
        }


        if (obj instanceof Profile){

            return new ProfileDto(((Profile) obj).getName(), ((Profile) obj).getLastname(), ((Profile) obj).getGender(),
                    ((Profile) obj).getBirthday(), ((Profile) obj).getMobileNumber(), ((Profile) obj).getEmail());
        }


    }
}
