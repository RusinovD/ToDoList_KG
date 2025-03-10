package ToDoList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager tm = new TaskManager();
        UserInteraction ui = new UserInteraction();

        //Для тестов
        tm.testMethod();
        //

        while (true) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            int cmd = scanner.nextInt();
            if (cmd == 1) { //1.Добавить задачу.
                tm.addTask(ui.scanTaskName(), ui.scanTaskDescription(), ui.scanTaskDeadline(), ui.scanTaskStatus());
            } else if (cmd == 2) { //2. Вывести список задач.
                tm.listTasks();
            } else if (cmd == 3) {//3.Редактировать задачу.
                tm.editTask(tm.findTaskByName(ui.scanTaskName()));
            } else if (cmd == 4) {//4.Удалить задачу.
                tm.deleteTask(tm.findTaskByName(ui.scanTaskName()));
            } else if (cmd == 5) {//5. Отфильтровать задачи по статусу.
                tm.filterTasksByStatus(ui.scanTaskStatus());
            } else if (cmd == 6) {//6. Отсортировать задачи.
                tm.sortTasks();
            } else if (cmd == 7) {//7. Выход из системы.
                System.out.println("До свидания!");
                break;
            } else {
                System.out.println("Неверная команда");
            }
        }
    }

    private static void printMenu() {
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