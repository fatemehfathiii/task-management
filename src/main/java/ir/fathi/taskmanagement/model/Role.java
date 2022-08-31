package ir.fathi.taskmanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Role {
    @Id
    @Min(1)
    private Integer id;
    @Column(nullable = false , unique = true)
    private String name;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String main;

    public Role(Integer id, String name, String category, String main) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.main = main;
    }

    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
    private List<UserRole> userRoles;


    @Override
    public String toString() {
        return String.format("""
                        "Role:{
                        id = %d ,
                        name = %s ,
                        category = %s ,
                         main = %s
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
            return role.getName().equals(this.name);
        }
            return false;

    }
}
