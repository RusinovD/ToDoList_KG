package ToDoList;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class TaskRepository {
    private List<Task> tasksList = new ArrayList<>();
}
