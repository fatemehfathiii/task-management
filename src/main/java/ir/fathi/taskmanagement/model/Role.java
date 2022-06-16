package ir.fathi.taskmanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String main;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<UserRole> userRoles;


    @Override
    public String toString() {
        return String.format("""
                        "Role:{
                        id= %d ,
                        name= %s ,
                        category= %s ,
                         main= %s
                         }"
                        """,
                id, name, category, main);
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
            Role role = (Role) object;
            return this.getId().equals(role.getId());
        } else {
            return false;
        }
    }
}
