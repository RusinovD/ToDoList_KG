package ToDoList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static ToDoList.Status.*;

public class TaskManager implements TaskManagerInterface {
    UserInteraction ui = new UserInteraction();
    TaskRepository tr = new TaskRepository();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void addTask(String taskName, String taskDescription, LocalDate taskDeadline, Status taskStatus) {
        Task task = new Task();

        task.setName(taskName);
        task.setDescription(taskDescription);
        task.setDeadline(taskDeadline);
        task.setStatus(taskStatus);

        tr.getTasksList().add(task);

        System.out.println("Задача " + task + " добавлена.");
    }

    @Override
    public void listTasks() {
        if (tr.getTasksList().isEmpty()) {
            System.out.println("В списке нет задач!");
        } else {
            System.out.println("Список задач:");
            tr.getTasksList().forEach(System.out::println);
        }
    }

    @Override
    public void editTask(Task taskToEdit) {//edit – редактировать задачу.
        if (taskToEdit == null) {
            return;
        }
        while (true) {
            printMenuForEditTask(); //ok
            int cmd = scanner.nextInt();
            if (cmd == 1) { //1.Название.
                taskToEdit.setName(ui.scanTaskName());
                break;
            } else if (cmd == 2) { //2.Описание.
                taskToEdit.setDescription(ui.scanTaskDescription());
                break;
            } else if (cmd == 3) {//3.Срок.
                taskToEdit.setDeadline(ui.scanTaskDeadline());
                break;
            } else if (cmd == 4) {//4.Статус.
                taskToEdit.setStatus(ui.scanTaskStatus());
                break;
            } else if (cmd == 5) {//5.Отмена.
                return;
            } else {
                System.out.println("Неверная команда");
            }
        }
        System.out.println("Задача " + taskToEdit + " изменена.");
    }

    @Override
    public void deleteTask(Task taskToRemove) {
        tr.getTasksList().remove(taskToRemove);
        System.out.println("Задача удалена.");
    }

    @Override
    public void filterTasksByStatus(Status status) {
        System.out.println("Задачи со статусом " + status + ":");
        tr.getTasksList().stream().filter(e -> e.getStatus().equals(status)).forEach(System.out::println);
    }

    @Override
    public void sortTasks() {
        while (true) {
            System.out.println("По какому признаку надо отсортировать задачи?");
            System.out.println("1 - по названию; 2 - по сроку; 3 - статусу; 4 - отмена");
            int cmd = scanner.nextInt();
            if (cmd == 1) {
                tr.setTasksList(tr.getTasksList().stream().sorted(Comparator.comparing(Task::getName).
                                thenComparing(Task::getDeadline).
                                thenComparing(Task::getStatus)).
                        toList());
                tr.getTasksList().forEach(System.out::println);
                break;
            } else if (cmd == 2) {
                tr.setTasksList(tr.getTasksList().stream().sorted(Comparator.comparing(Task::getDeadline).
                                thenComparing(Task::getName).
                                thenComparing(Task::getStatus)).
                        toList());
                tr.getTasksList().forEach(System.out::println);
                break;
            } else if (cmd == 3) {
                tr.setTasksList(tr.getTasksList().stream().sorted(Comparator.comparing(Task::getStatus).
                                thenComparing(Task::getName).
                                thenComparing(Task::getDeadline)).
                        toList());
                tr.getTasksList().forEach(System.out::println);
                break;
            } else if (cmd == 4) {
                return;
            } else {
                System.out.println("не верная команда, попробуйте снова");
            }
        }
    }

    public Task findTaskByName(String taskName) {
        while (true) {
            List<Task> newList = new ArrayList<>();
            for (Task taskSearch : tr.getTasksList()) {
                if (taskSearch.getName().toLowerCase().contains(taskName.toLowerCase())) {
                    newList.add(taskSearch);
                }
            }
            if (newList.isEmpty()) {
                System.out.println("Такая задача не найдена, повторить поиск?(да/нет)");
                String cmd = scanner.nextLine();
                if (cmd.equals("нет")) {
                    return null;
                }
            } else if (newList.size() > 1) {
                System.out.println("По вашему запросу найдено больше одной задачи:");
                newList.forEach(System.out::println);
                System.out.println("Повторите запрос.");
                return null;
            } else {
                System.out.println("Задача найдена:");
                Task task = newList.get(0);
                System.out.println(task);
                return task;
            }
        }
    }

    private static void printMenuForEditTask() {
        System.out.println("Что в задаче хотите отредактировать?:");
        System.out.println("1.Название.");
        System.out.println("2.Описание.");
        System.out.println("3.Срок.");
        System.out.println("4.Статус.");
        System.out.println("5.Отмена.");
    }

    public void testMethod() {
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
        tr.getTasksList().add(taskTest1);
        tr.getTasksList().add(taskTest6);
        tr.getTasksList().add(taskTest5);
        tr.getTasksList().add(taskTest4);
        tr.getTasksList().add(taskTest3);
        tr.getTasksList().add(taskTest2);
    }
}

