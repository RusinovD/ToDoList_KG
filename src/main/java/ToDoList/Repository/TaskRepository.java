package ToDoList.Repository;

import ToDoList.Controller.UserInteraction;
import ToDoList.Enums.Status;
import ToDoList.Enums.TaskFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static ToDoList.Enums.TaskFields.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRepository {
    private Map<Integer, Task> taskMap = new HashMap<>();
    private int taskId = 1;

    UserInteraction userInteraction = new UserInteraction();

    private Task createTask() {
        return new Task();
    }

    public void addTask(String taskName, String taskDescription, LocalDate taskDeadline, Status taskStatus) {
        Task task = createTask();
        task.setName(taskName);
        task.setDescription(taskDescription);
        task.setDeadline(taskDeadline);
        task.setStatus(taskStatus);
        taskMap.put(taskId++, task);
        System.out.println("Задача \"" + taskName + "\" добавлена.");
    }

    public void listTasks() {
        Map<Integer, Task> tempMap = new HashMap<>(taskMap);
        if (!tempMap.isEmpty()) {
            System.out.println("Список задач:");
            tempMap.forEach((key, value) -> System.out.println("ID " + key + " - " + value));
        } else {
            System.out.println("В списке нет задач!");
        }
    }

    public void editTask(int id) {
        if (!taskMap.containsKey(id)) {
            System.out.println("Задача не найдена, повторите запрос.");
            return;
        }
        TaskFields cmd = userInteraction.scanForEditTask();
        if (cmd == EXIT) {
            System.out.println("Отмена.");
            return;
        }
        Task task = taskMap.get(id);
        if (cmd == NAME) {
            task.setName(userInteraction.scanTaskName());
        } else if (cmd == DESCRIPTION) {
            task.setDescription(userInteraction.scanTaskDescription());
        } else if (cmd == DEADLINE) {
            task.setDeadline(userInteraction.scanTaskDeadline());
        } else if (cmd == STATUS) {
            task.setStatus(userInteraction.scanTaskStatus());
        }
        System.out.println("В задаче \"" + task.getName() + "\" изменен параметр " + cmd + ".");
    }

    public void deleteTask(int id) {
        if (!taskMap.containsKey(id)) {
            System.out.println("Задача не найдена, повторите запрос.");
        } else {
            System.out.println("Задача \"" + taskMap.get(id).getName() + "\" удалена.");
            taskMap.remove(id);
        }
    }
}
