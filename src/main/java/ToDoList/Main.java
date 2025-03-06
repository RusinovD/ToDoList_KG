package ToDoList;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static ToDoList.Status.*;

public class Main {
    public static void main(String[] args) {


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