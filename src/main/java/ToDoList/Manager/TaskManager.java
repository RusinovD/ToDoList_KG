package ToDoList.Manager;

import ToDoList.Enums.Status;
import ToDoList.Enums.TaskFields;
import ToDoList.Repository.Task;
import ToDoList.Repository.TaskRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static ToDoList.Enums.TaskFields.*;

public class TaskManager implements TaskManagerInterface {
    TaskRepository taskRepository;

    public TaskManager(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void filterTasksByStatus(Status status) {
        Map<Integer, Task> tempMap = new HashMap<>();
        for (int id : taskRepository.getTaskMap().keySet()) {
            if (taskRepository.getTaskMap().get(id).getStatus().equals(status)) {
                tempMap.put(id, taskRepository.getTaskMap().get(id));
            }
        }
        if (tempMap.isEmpty()) {
            System.out.print("В списке задач со статусом " + status + " нет.");
        } else {
            System.out.println("Задачи со статусом " + status + ":");
            tempMap.forEach((key, value) -> System.out.println("ID " + key + " - " + value));
        }
    }

    @Override
    public void sortTasks(TaskFields cmd) {
        if (cmd == EXIT) {
            System.out.println("Отмена.");
            return;
        }
        Map<Integer, Task> tempMap = taskRepository.getTaskMap();
        if (tempMap.isEmpty()) {
            System.out.println("Список задач пуст");
            return;
        }
        if (cmd == NAME) {
            tempMap.entrySet().stream().sorted(Map.Entry.comparingByValue(
                            Comparator.comparing(Task::getName))).
                    forEach(entry -> System.out.println(
                            entry.getKey() + " - " + entry.getValue()));
        } else if (cmd == DEADLINE) {
            tempMap.entrySet().stream().sorted(Map.Entry.comparingByValue(
                            Comparator.comparing(Task::getDeadline))).
                    forEach(entry -> System.out.println(
                            entry.getKey() + " - " + entry.getValue()));
        } else if (cmd == STATUS) {
            tempMap.entrySet().stream().sorted(Map.Entry.comparingByValue(
                            Comparator.comparing(Task::getStatus))).
                    forEach(entry -> System.out.println(
                            entry.getKey() + " - " + entry.getValue()));
        }
    }
}

