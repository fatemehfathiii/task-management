package ir.fathi.taskmanagement.model;

import ir.fathi.taskmanagement.Enum.TaskPriority;
import ir.fathi.taskmanagement.Enum.TaskType;
import ir.fathi.taskmanagement.dto.PostTaskDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
    private LocalDateTime done;
    @Column(columnDefinition = "text")
    private String description;


    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;


    public static Task fromDto(PostTaskDto taskDto) {
        Task task = new Task();
        task.setName(taskDto.name());
        task.setType(taskDto.type());
        task.setSubject(taskDto.subject());
        task.setPriority(taskDto.priority());
        task.setCreateAt(LocalDateTime.now());
        task.setDescription(taskDto.description());
        task.setOwner(taskDto.owner());
        return task;
    }

    @PostUpdate
    private void update() {
        this.setUpdateAt(LocalDateTime.now());
    }


    @Override
    public String toString() {
        return """
                Task:{
                id = id,
                name = name,
                subject = subject,
                type = type,
                priority = priority,
                createAt = createAt,
                updateAt = updateAt,
                deleteAt = deleteAt,
                done = done,
                description = description,
                owner = owner,
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
            Task task = (Task) object;
            return this.getId().equals(task.getId());
        } else {
            return false;
        }

    }
}
