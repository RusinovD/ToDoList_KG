package ToDoList;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInteraction implements UserInteractionInterface {

    Scanner scanner = new Scanner(System.in);

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
        System.out.println("Установите статус задачи (TODO, IN_PROGRESS, DONE:");
        return Status.valueOf(scanner.nextLine());
    }
}