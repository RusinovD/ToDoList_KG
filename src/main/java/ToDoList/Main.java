package ToDoList;

import ToDoList.Controller.UserInteraction;
import ToDoList.Enums.ConsoleCommand;
import ToDoList.Manager.TaskManager;
import ToDoList.Repository.TaskRepository;

import java.util.Scanner;

import static ToDoList.Enums.ConsoleCommand.*;

public class Main {
    public static void main(String[] args) {

        TaskRepository taskRepository = new TaskRepository();
        TaskManager taskManager = new TaskManager(taskRepository);
        UserInteraction userInteraction = new UserInteraction();

        while (true) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            ConsoleCommand cmd = ConsoleCommand.valueOf(scanner.nextLine());
            if (cmd == ADD) {
                taskRepository.addTask(userInteraction.scanTaskName(), userInteraction.scanTaskDescription(),
                        userInteraction.scanTaskDeadline(), userInteraction.scanTaskStatus());
            } else if (cmd == LIST) {
                taskRepository.listTasks();
            } else if (cmd == EDIT) {
                taskRepository.editTask(userInteraction.scanTaskID());
            } else if (cmd == DELETE) {
                taskRepository.deleteTask(userInteraction.scanTaskID());
            } else if (cmd == FILTER) {
                taskManager.filterTasksByStatus(userInteraction.scanTaskStatus());
            } else if (cmd == SORT) {
                taskManager.sortTasks(userInteraction.scanForSortTasks());
            } else if (cmd == EXIT) {
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