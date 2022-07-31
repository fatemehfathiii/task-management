package ir.fathi.taskmanagement.model;

import ir.fathi.taskmanagement.enumType.Sex;
import ir.fathi.taskmanagement.dto.ProfileDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private String lastname;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private LocalDate birthday;
    private String mobileNumber;

    @Column(unique = true)
    private String email;

    private boolean deleted;


    @OneToOne(optional = false)
    private User user;


    @Override
    public String toString() {

        return String.format(
                """
                        Profile:{
                        id= %d ,
                        name= %s ,
                        lastname= %s ,
                        sex= %s ,
                        birthday= %tF ,
                        mobileNumber= %s ,
                        email= %s ,
                        deleted=%b
                        }
                        """
                ,
                id, name, lastname, sex, birthday, mobileNumber, email, deleted);
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
