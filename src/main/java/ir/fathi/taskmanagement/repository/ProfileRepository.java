package ir.fathi.taskmanagement.repository;

import ir.fathi.taskmanagement.enumType.Sex;
import ir.fathi.taskmanagement.model.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer> {

    @Query(value = "from Profile p where p.user.username= :username")
    Optional<Profile> findProfileByUsername(@Param("username") String username);

    @Modifying
    @Query(value = """
            update Profile
            set name =: name ,
             lastname =: lastname ,
             sex =: sex,
             birthday =: birthday ,
             mobileNumber =: mobileNumber,
             email =: email
                        """)
    Integer updateProfile(String name, String lastname, Sex sex, LocalDate birthday , String mobileNumber, String email);
}
