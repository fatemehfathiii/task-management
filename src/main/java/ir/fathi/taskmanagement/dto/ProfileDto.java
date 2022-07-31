package ir.fathi.taskmanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import ir.fathi.taskmanagement.customValidation.Messenger;
import ir.fathi.taskmanagement.enumType.Sex;
import ir.fathi.taskmanagement.customValidation.MobileNumber;

import javax.validation.constraints.Email;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

public record ProfileDto(


        @JsonProperty("name")
        String name,

        @JsonProperty("lastname")
        String lastname,

        @JsonProperty("gender")
        @Messenger(message = "sex must be male or female or ")
        Sex sex,

        @PastOrPresent
        @Messenger(message = "birthday must be in (yyyy/mm/yy) format ")
        @JsonFormat(pattern = "yyyy/mm/yy")
        @JsonProperty("birthday")
        Date birthday,

        @MobileNumber(massage = "your mobile number is invalid")
        @Messenger(message = "mobile number must start whit 09 or 0098 or +98 ")
        @JsonProperty("mobilNumber")
        String mobileNumber,

        @Email(message = "your email is invalid")
        @JsonProperty("email") String email
) {

    public ProfileDto(String name, String lastname, Sex sex, Date birthday,
                      String mobileNumber, String email) {
        this.name = name;
        this.lastname = lastname;
        this.sex = sex;
        this.birthday = birthday;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }
}
