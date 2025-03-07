package ToDoList;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class TaskRepository {
    private List<Task> taskList = new ArrayList<>();
    static TaskRepository tr = new TaskRepository();
}
