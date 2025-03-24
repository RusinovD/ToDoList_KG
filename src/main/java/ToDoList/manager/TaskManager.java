package ToDoList.manager;

import ToDoList.enums.Status;
import ToDoList.enums.TaskFields;
import ToDoList.model.Task;
import ToDoList.repository.TaskRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static ToDoList.enums.TaskFields.*;

public class TaskManager implements TaskManagerInterface {
    private TaskRepository taskRepository;

    public TaskManager(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void filterTasksByStatus(Status status) {
        Map<Integer, Task> tempMap = new HashMap<>();
        Map<Integer, Task> taskRepositoryMap = taskRepository.getTaskMap();
        for (int id : taskRepositoryMap.keySet()) {
            if (taskRepositoryMap.get(id).getStatus().equals(status)) {
                tempMap.put(id, taskRepositoryMap.get(id));
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
            tempMap.entrySet().stream().
                    sorted(Map.Entry.comparingByValue(Comparator.comparing(Task::getName))).
                    forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));
        } else if (cmd == DEADLINE) {
            tempMap.entrySet().stream().
                    sorted(Map.Entry.comparingByValue(Comparator.comparing(Task::getDeadline))).
                    forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));
        } else if (cmd == STATUS) {
            tempMap.entrySet().stream().
                    sorted(Map.Entry.comparingByValue(Comparator.comparing(Task::getStatus))).
                    forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));
        }
    }
}

