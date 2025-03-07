package ToDoList;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static ToDoList.Status.*;

public class Main {
    public static void main(String[] args) {
        //для тестов
        Task taskTest1 = new Task
                ("Задача 1", "Описание 1", LocalDate.of(2025, 3, 29), DONE);
        Task taskTest2 = new Task
                ("Задача 2", "Описание 2", LocalDate.of(2025, 4, 1), TODO);
        Task taskTest3 = new Task
                ("Задача 3", "Описание 3", LocalDate.of(2025, 1, 29), DONE);
        Task taskTest4 = new Task
                ("Задача 4", "Описание 4", LocalDate.of(2025, 6, 1), IN_PROGRESS);
        Task taskTest5 = new Task
                ("Задача 5", "Описание 5", LocalDate.of(2025, 12, 29), TODO);
        Task taskTest6 = new Task
                ("Задача 6", "Описание 6", LocalDate.of(2026, 10, 1), IN_PROGRESS);
        List<Task> list = TaskManager.tm.getTaskList();
        list.add(taskTest1);
        list.add(taskTest6);
        list.add(taskTest5);
        list.add(taskTest4);
        list.add(taskTest3);
        list.add(taskTest2);
        TaskManager.tm.setTaskList(list);
        //


        while (true) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            int cmd = scanner.nextInt();
            if (cmd == 1) { //1.Добавить задачу.
                TaskManager.tm.addTask();
            } else if (cmd == 2) { //2.Вывести список задач.
                TaskManager.tm.listTasks();
            } else if (cmd == 3) {//3.Редактировать задачу.
                TaskManager.tm.editTask();
            } else if (cmd == 4) {//4.Удалить задачу.
                TaskManager.tm.deleteTask();
            } else if (cmd == 5) {//5.Отфильтровать задачи по статусу.
                TaskManager.tm.filterTasksByStatus();
            } else if (cmd == 6) {//6.Отсортировать задачи.
                TaskManager.tm.sortTasks();
            } else if (cmd == 7) {//7.Выход из системы.
                System.out.println("До свидания!");
                break;
            } else {
                System.out.println("Неверная команда");
            }
        }
    }

    public static void printMenu(){
        System.out.println("Выберите команду:");
        System.out.println("1.Добавить задачу.");
        System.out.println("2.Вывести список задач.");
        System.out.println("3.Редактировать задачу.");
        System.out.println("4.Удалить задачу.");
        System.out.println("5.Отфильтровать задачи по статусу.");
        System.out.println("6.Отсортировать задачи.");
        System.out.println("7.Выход из системы.");
    }
}