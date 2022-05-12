package ir.fathi.taskmanagement.model;

import ir.fathi.taskmanagement.dto.PostUserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "app_user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(nullable = false, unique = true, columnDefinition = "varchar(50)")
    private String username;
    @Column(nullable = false, columnDefinition = "varchar(30)")
    private String password;
    private boolean locked;
    private boolean deleted;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserRole> userRoles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Profile profile;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> task;


    public static User fromDto(PostUserDto userDto) {
        User user = new User();
        user.setUsername(userDto.username());
        user.setPassword(userDto.password());
        return user;
    }


    @Override
    public String toString() {
        return String.format("User:{ %nid= %d%n username= %s%n password=%s%n locked= %b%n deleted= %b%n }",
                id,username,password,locked,deleted);
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


}
