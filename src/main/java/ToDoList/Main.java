package ToDoList;

import ToDoList.controller.UserInteraction;
import ToDoList.enums.ConsoleCommand;
import ToDoList.enums.TaskFields;
import ToDoList.manager.TaskManager;
import ToDoList.model.Task;
import ToDoList.repository.TaskRepository;

import java.util.Scanner;

import static ToDoList.enums.ConsoleCommand.*;
import static ToDoList.enums.TaskFields.*;
import static ToDoList.enums.TaskFields.EXIT;

public class Main {
    public static void main(String[] args) {

        TaskRepository taskRepository = new TaskRepository();
        TaskManager taskManager = new TaskManager(taskRepository);
        UserInteraction userInteraction = new UserInteraction();

        while (true) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            ConsoleCommand cmd = null;
            try {
                cmd = ConsoleCommand.valueOf(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный ввод команды, введите снова:");
            }
            if (cmd == ADD) {
                taskRepository.addTask(userInteraction.scanTaskName(), userInteraction.scanTaskDescription(),
                        userInteraction.scanTaskDeadline(), userInteraction.scanTaskStatus());
            } else if (cmd == LIST) {
                taskRepository.listTasks();
            } else if (cmd == EDIT) {
                while (true) {
                    int id = userInteraction.scanTaskID();
                    Task task = taskRepository.isTaskContains(id);
                    if (task == null) {
                        break;
                    }
                    TaskFields taskFields = userInteraction.scanForEditTask();
                    if (taskFields == EXIT) {
                        System.out.println("Отмена.");
                        break;
                    }
                    if (taskFields == NAME) {
                        taskRepository.editTaskName(task, userInteraction.scanTaskName());
                    } else if (taskFields == DESCRIPTION) {
                        taskRepository.editTask(task, userInteraction.scanTaskDescription());
                    } else if (taskFields == DEADLINE) {
                        taskRepository.editTask(task, userInteraction.scanTaskDeadline());
                    } else if (taskFields == STATUS) {
                        taskRepository.editTask(task, userInteraction.scanTaskStatus());
                    }
                }
            } else if (cmd == DELETE) {
                taskRepository.deleteTask(userInteraction.scanTaskID());
            } else if (cmd == FILTER) {
                taskManager.filterTasksByStatus(userInteraction.scanTaskStatus());
            } else if (cmd == SORT) {
                taskManager.sortTasks(userInteraction.scanForSortTasks());
            } else if (cmd == ConsoleCommand.EXIT) {
                System.out.println("До свидания!");
                break;
            } else {
                System.out.println("Неверная команда");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                \nВыберите команду:
                ADD – Добавить задачу.
                LIST – Вывести список задач.
                EDIT – Редактировать задачу.
                DELETE – Удалить задачу.
                FILTER – Отфильтровать задачи по статусу.
                SORT – Отсортировать задачи.
                EXIT – Выход из системы.""");
    }
}