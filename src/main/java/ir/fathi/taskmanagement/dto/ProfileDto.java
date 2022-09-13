package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import ir.fathi.taskmanagement.customValidation.ValidateEnum;
import ir.fathi.taskmanagement.enumType.Sex;
import ir.fathi.taskmanagement.customValidation.MobileNumber;
import ir.fathi.taskmanagement.model.Profile;

import javax.validation.constraints.Email;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public record ProfileDto(

        @JsonProperty("name")
        String name,

        @JsonProperty("lastname")
        String lastname,

        @JsonProperty("nationalCode")
        String nationalCode ,

        @ValidateEnum(targetClassType = Sex.class , message = "sex must be female or male")
        @JsonProperty("sex")
        Sex sex,

        @PastOrPresent
        @JsonFormat(pattern = "yyyy/mm/dd")
        @JsonProperty("birthday")
        LocalDate birthday,

        @MobileNumber(massage = "your mobile number is invalid")
        @JsonProperty("mobilNumber")
        String mobileNumber,

        @Email(message = "your email is invalid")
        @JsonProperty("email") String email
) {

    public ProfileDto(String name, String lastname,String nationalCode, Sex sex, LocalDate birthday,
                      String mobileNumber, String email) {
        this.name = name;
        this.lastname = lastname;
        this.nationalCode=nationalCode;
        this.sex = sex;
        this.birthday = birthday;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }


    public static ProfileDto generateCustomProfileDto(Profile profile){

        return new ProfileDto(profile.getName(), profile.getLastname() , profile.getNationalCode() , profile.getSex()
                , profile.getBirthday(), profile.getMobileNumber(), profile.getEmail());
    }
}
