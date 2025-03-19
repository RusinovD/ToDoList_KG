package ToDoList.Controller;

import ToDoList.Enums.Status;
import ToDoList.Enums.TaskFields;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInteraction implements UserInteractionInterface {

    Scanner scanner = new Scanner(System.in);

    public void setScanner(Scanner scanner) { //для тестов
        this.scanner = scanner;
    }

    @Override
    public String scanTaskName() {
        System.out.println("Введите название задачи:");
        return scanner.nextLine();
    }

    @Override
    public String scanTaskDescription() {
        System.out.println("Введите описание задачи:");
        return scanner.nextLine();
    }

    @Override
    public LocalDate scanTaskDeadline() {
        System.out.println("Установите срок задачи (в формате ГГГГ-ММ-ДД:");
        String taskDeadline = scanner.nextLine();
        return LocalDate.parse(taskDeadline);
    }

    @Override
    public Status scanTaskStatus() {
        System.out.println("Введите статус задачи (TODO, IN_PROGRESS, DONE):");
        return Status.valueOf(scanner.nextLine());
    }

    @Override
    public int scanTaskID() {
        System.out.println("Введите ID задачи для поиска:");
        return scanner.nextInt();
    }

    public TaskFields scanForEditTask() {
        printMenuForEditTask();
        return TaskFields.valueOf(scanner.nextLine());
    }

    private static void printMenuForEditTask() {
        System.out.println("""
                Что в задаче хотите отредактировать?:
                NAME - Название.
                DESCRIPTION - Описание.
                DEADLINE - Срок.
                STATUS - Статус.
                EXIT - Выход.""");
    }

    public TaskFields scanForSortTasks() {
        printMenuForSortTasks();
        return TaskFields.valueOf(scanner.nextLine());
    }

    private static void printMenuForSortTasks() {
        System.out.println("""
                По какому признаку надо отсортировать задачи?
                NAME - по названию;
                DEADLINE - по сроку;
                STATUS - статусу;
                EXIT - отмена""");
    }
}