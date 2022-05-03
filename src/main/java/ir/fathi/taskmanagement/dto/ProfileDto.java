package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import ir.fathi.taskmanagement.Enum.Gender;
import ir.fathi.taskmanagement.customValidation.MobileNumber;
import ir.fathi.taskmanagement.model.Profile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

public record ProfileDto(

        @NotBlank
        @NotNull
        @JsonProperty("name")
        String name,
        @NotBlank
        @NotNull
        @JsonProperty("lastname") String lastname,

        @NotNull
        @NotBlank
        @JsonProperty("gender")
        Gender gender,

        @NotNull
        @PastOrPresent
        @JsonFormat(pattern = "yyyy/mm/yy")
        @JsonProperty("birthday")
        Date birthday,

        @MobileNumber
        @JsonProperty("mobilNumber")
        String mobileNumber,

        @Email
        @JsonProperty("email") String email
) {

        public ProfileDto(String name, String lastname, Gender gender, Date birthday, String mobileNumber, String email) {
                this.name = name;
                this.lastname = lastname;
                this.gender = gender;
                this.birthday = birthday;
                this.mobileNumber = mobileNumber;
                this.email = email;
        }

        public static ProfileDto customProfile(Profile profile){
                return new ProfileDto(profile.getName(), profile.getLastname(), profile.getGender(),profile.getBirthday()
                        ,profile.getMobileNumber(),profile.getEmail());
        }
}
