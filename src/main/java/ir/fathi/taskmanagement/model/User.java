package ir.fathi.taskmanagement.model;

import ir.fathi.taskmanagement.dto.PostUserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "app_user")
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(nullable = false, unique = true,updatable = false, columnDefinition = "varchar(50)")
    private String username;
    @Column(nullable = false)
    private String password;
    private boolean locked;
    private boolean deleted;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserRole> userRoles;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Profile profile;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Task> task;

    public static User fromDto(PostUserDto postUserDto , String encodePassword) {
        User user = new User();
        Profile profile = new Profile();
        user.setUsername(postUserDto.username());
        user.setPassword(encodePassword);
        profile.setName(postUserDto.name());
        profile.setLastname(postUserDto.lastname());
        profile.setNationalCode(postUserDto.nationalCode());
        user.setProfile(profile);
        return user;
    }

    public static User generateUserAdmin(String password){
        User user = new User();
        Profile profile = new Profile();
        user.setUsername("administrator");
        user.setPassword(password);
        profile.setName("admin");
        profile.setLastname("userAdmin");
        profile.setNationalCode("NATIONAL_CODE");
        user.setProfile(profile);
        return user;
    }




    @Override
    public String toString() {

        return String.format(
                """
                         User:{
                         id = %d ,
                         username = %s ,
                         password = %s ,
                         locked = %b ,
                         deleted = %b ,
                         profile_id = %d
                        }
                          """,
                id, username, password, locked, deleted ,profile.getId());
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
            User user = (User) object;
            return user.getUsername().equals(this.username) || user.getId().equals(this.id);
        }
        return false;
    }

    //********************************* custom user detail ************************************************
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = new HashSet<GrantedAuthority>();
        userRoles.forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getRole().getName())));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !deleted;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !deleted;
    }

    @Override
    public boolean isEnabled() {
        return !deleted;
    }
}
