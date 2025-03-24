package ToDoList.controller;

import ToDoList.enums.Status;
import ToDoList.enums.TaskFields;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
        while (true) {
            try {
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат даты, введите дату снова:");
            }
        }
    }

    @Override
    public Status scanTaskStatus() {
        System.out.println("Введите статус задачи (TODO, IN_PROGRESS, DONE):");
        while (true) {
            try {
                return Status.valueOf(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Неверно введен статус, введите еще раз:");
            }
        }
    }

    @Override
    public int scanTaskID() {
        System.out.println("Введите ID задачи для поиска:");
        return scanner.nextInt();
    }

    public TaskFields scanForEditTask() {
        printMenuForEditTask();
        while (true) {
            try {
                return TaskFields.valueOf(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный ввод, что в задаче хотите отредактировать?");
            }
        }
    }

    private void printMenuForEditTask() {
        System.out.println("""
                Что в задаче хотите отредактировать?
                NAME - Название.
                DESCRIPTION - Описание.
                DEADLINE - Срок.
                STATUS - Статус.
                EXIT - Выход.""");
    }

    public TaskFields scanForSortTasks() {
        printMenuForSortTasks();
        while (true) {
            try {
                return TaskFields.valueOf(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный ввод, по какому признаку надо отсортировать задачи?");
            }
        }
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