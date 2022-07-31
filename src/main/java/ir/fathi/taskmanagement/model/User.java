package ir.fathi.taskmanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
    @Column(nullable = false, unique = true, columnDefinition = "varchar(50)")
    private String username;
    @Column(nullable = false)
    private String password;
    private boolean locked;
    private boolean deleted;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<UserRole> userRoles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Profile profile;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> task;


    public static User fromDto(String username , String password) {
        User user = new User();
        Profile profile = new Profile();
        user.setUsername(username);
        user.setPassword(password);
        user.setProfile(profile);
        return user;
    }


    @Override
    public String toString() {

        return String.format(
                        """
                        User:{
                        id= %d ,
                        username= %s ,
                        password=%s ,
                        locked= %b ,
                        deleted= %b
                         }
                         """,
                id, username, password, locked, deleted);
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
            return this.getId().equals(user.getId());
        } else {
            return false;
        }
    }

    //********************************* custom user detail ************************************************
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = new HashSet<GrantedAuthority>();
        userRoles.stream().map(r -> "ROLE_" + r.getRole().getName()).forEach(r ->  authorities.add(new SimpleGrantedAuthority(r)));
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
