package ir.fathi.taskmanagement.model;

import ir.fathi.taskmanagement.enumType.TaskPriority;
import ir.fathi.taskmanagement.enumType.TaskType;
import ir.fathi.taskmanagement.dto.PostTaskDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String name;
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String subject;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "type", joinColumns = @JoinColumn(name = "id", referencedColumnName = "task-id"))
    @Column(name = "type")
    private List<TaskType> type;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
    private LocalDateTime done;
    @Column(columnDefinition = "text")
    private String description;


    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;


    public static Task fromDto(PostTaskDto taskDto) {
        Task task = new Task();
        task.setName(taskDto.name());
        task.setType(taskDto.type());
        task.setSubject(taskDto.subject());
        task.setPriority(taskDto.priority());
        task.setDescription(taskDto.description());
        task.setOwner(taskDto.owner());
        return task;
    }

    @Override
    public String toString() {

        return String.format(
                """
                        Task:{
                        id= %d ,
                        name= %s ,
                        subject= %s ,
                        type= %s ,
                        priority= %s ,
                        createAt= %t% ,
                        updateAt= %t% ,
                        deleteAt= %t% ,
                        done= %t%n ,
                        description= %s }
                               """
                ,
                id, name, subject, type, priority, createAt, updateAt, deleteAt, done, description);
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
            Task task = (Task) object;
            return this.getId().equals(task.getId());
        } else {
            return false;
        }

    }
}
