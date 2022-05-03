package ir.fathi.taskmanagement.model;

import ir.fathi.taskmanagement.dto.RoleDto;
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


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserRole> userRoles;


    public static Role fromDto(RoleDto roleDto) {
        Role role = new Role();
        role.setMain(roleDto.main());
        role.setCategory(roleDto.category());
        role.setName(roleDto.name());
        return role;
    }

    @Override
    public String toString() {
        return """
                Role:{
                main = main,
                category = category,
                name = name
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
            Role role = (Role) object;
            return this.getId().equals(role.getId());
        } else {
            return false;
        }
    }
}
