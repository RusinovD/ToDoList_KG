package ToDoList.Repository;

import ToDoList.Enums.Status;
import lombok.*;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Task {
    private String name;
    private String description;
    private LocalDate deadline;
    private Status status;


}
