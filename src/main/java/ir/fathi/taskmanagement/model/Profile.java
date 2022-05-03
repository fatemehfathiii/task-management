package ir.fathi.taskmanagement.model;

import ir.fathi.taskmanagement.Enum.Gender;
import ir.fathi.taskmanagement.dto.ProfileDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(nullable = false, columnDefinition = "varchar(30)")
    private String name;

    @Column(nullable = false, columnDefinition = "varchar(30)")
    private String lastname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, columnDefinition = "date")
    private Date birthday;

    @Column(nullable = false, columnDefinition = "varchar(11)")
    private String mobileNumber;

    private String email;

    private boolean deleted;


    @OneToOne(optional = false)
    private User user;


    public static Profile fromDto(ProfileDto profileDto) {
        Profile profile = new Profile();
        profile.setName(profileDto.name());
        profile.setLastname(profileDto.lastname());
        profile.setGender(profileDto.gender());
        profile.setBirthday(profileDto.birthday());
        profile.setMobileNumber(profileDto.mobileNumber());
        profile.setEmail(profileDto.email());
        return profile;
    }


    @Override
    public String toString() {
        return """
                profile:{
                id = id ,
                name = name,
                lastname = lastname,
                gender = gender,
                birthday = birthday,
                mobileNumber = mobileNumber,
                email = email,
                deleted = deleted,
                user = user,
                }
                """;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (this.getClass() != object.getClass()) {
            return false;
        }

        if (this.getId() != null) {
            Profile profile = (Profile) object;
            return this.getId().equals(profile.getId());
        } else {
            return false;
        }

    }
}
