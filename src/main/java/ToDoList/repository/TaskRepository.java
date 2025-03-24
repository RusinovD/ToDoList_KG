package ToDoList.repository;

import ToDoList.enums.Status;
import ToDoList.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRepository {
    private Map<Integer, Task> taskMap = new HashMap<>();
    private int taskId = 1;

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

    public Task isTaskContains(int id) {
        if (!taskMap.containsKey(id)) {
            System.out.println("Такой задачи в списке нет.");
            return null;
        } else {
            return taskMap.get(id);
        }
    }

    public void editTaskName(Task task, String name) {
        task.setName(name);
        System.out.println("В задаче \"" + task.getName() + "\" изменено \"Название\".");
    }

    public void editTask(Task task, String description) {
        task.setDescription(description);
        System.out.println("В задаче \"" + task.getName() + "\" изменено \"Описание\".");
    }

    public void editTask(Task task, LocalDate deadline) {
        task.setDeadline(deadline);
        System.out.println("В задаче \"" + task.getName() + "\" изменен \"Срок\".");
    }

    public void editTask(Task task, Status status) {
        task.setStatus(status);
        System.out.println("В задаче \"" + task.getName() + "\" изменен \"Статус\".");
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
