package ToDoList;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Task {
    private String name;
    private String description;
    private LocalDate deadline;
    private Status status;
}
