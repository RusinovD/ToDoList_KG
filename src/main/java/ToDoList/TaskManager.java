package ToDoList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static ToDoList.Status.*;


public class TaskManager implements TaskManagerInterface {
    List<Task> listForWorkWithTaskRepositiry = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    static TaskManager tm = new TaskManager();

    @Override
    public void addTask() {
        Task task = createTask();
        System.out.println("Введите название задачи (Введите \"отмена\" для выхода):");
        String name = scanner.nextLine();
        if (name.equals("отмена")) {
            return;
        }
        task.setName(name);
        System.out.println("Введите описание задачи (Введите \"отмена\" для выхода):");
        String description = scanner.nextLine();
        if (description.equals("отмена")) {
            return;
        }
        task.setDescription(description);
        System.out.println("Установите срок задачи (в формате ГГГГ-ММ-ДД " +
                "(Введите \"0\", если не хотите устанавливать срок на задачу):");
        String deadline = scanner.nextLine();
        if (deadline.equals("0")) {
            task.setDeadline(null);
        } else {
            task.setDeadline(LocalDate.parse(deadline));
        }
        task.setStatus(TODO);
        listForWorkWithTaskRepositiry = TaskRepository.tr.getTaskList();
        listForWorkWithTaskRepositiry.add(task);
        TaskRepository.tr.setTaskList(listForWorkWithTaskRepositiry);
    }

    @Override
    public void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println("В списке нет задач!");
        } else {
            System.out.println("Список задач:");
            taskList.forEach(System.out::println);
        }
    }

    @Override
    public void editTask() {//edit – редактировать задачу.
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Какую задачу хотите отредактировать?");
            String str = scanner.nextLine();
            List<Task> newList = new ArrayList<>();
            for (Task taskSearch : taskList) {
                if (taskSearch.getName().toLowerCase().contains(str.toLowerCase())) {
                    newList.add(taskSearch);
                }
            }
            if (newList.isEmpty()) {
                System.out.println("Такая задача не найдена, повторить поиск?(да/нет)");
                Scanner scanner1 = new Scanner(System.in);
                String cmd = scanner1.nextLine();
                if (cmd.equals("нет")) {
                    return;
                }
            } else if (newList.size() == 1) {
                System.out.println("Задача найдена:");
                Task task = newList.get(0);
                System.out.println(task);
                while (true) {
                    printMenuForEditTask();
                    Scanner scanner2 = new Scanner(System.in);
                    int cmd = scanner2.nextInt();
                    if (cmd == 1) { //1.Название.
                        newName(task);
                        return;
                    } else if (cmd == 2) { //2.Описание.
                        newDescription(task);
                        return;
                    } else if (cmd == 3) {//3.Срок.
                        newDeadline(task);
                        return;
                    } else if (cmd == 4) {//4.Статус.
                        newStatus(task);
                        return;
                    } else if (cmd == 5) {//5.Отмена.
                        break;
                    } else {
                        System.out.println("Неверная команда");
                    }
                }
            } else {
                System.out.println("По вашему запросу найдено больше одной задачи:");
                taskList.forEach(System.out::println);
                System.out.println("Повторите запрос.");
            }
        }
    }

    @Override
    public void deleteTask() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Какую задачу хотите удалить?");
            String str = scanner.nextLine();
            List<Task> newList = new ArrayList<>();
            for (Task taskSearch : taskList) {
                if (taskSearch.getName().toLowerCase().contains(str.toLowerCase())) {
                    newList.add(taskSearch);
                }
            }
            if (newList.isEmpty()) {
                System.out.println("Такая задача не найдена, повторить поиск?(да/нет)");
                Scanner scanner1 = new Scanner(System.in);
                String cmd = scanner1.nextLine();
                if (cmd.equals("нет")) {
                    return;
                }
            } else if (newList.size() == 1) {
                System.out.println("Удаляем задачу...");
                taskList.remove(newList.get(0));
                System.out.println("...задача удалена!");
            } else {
                System.out.println("По вашему запросу найдено больше одной задачи:");
                taskList.forEach(System.out::println);
                System.out.println("Удалить все найденные задачи?(да/нет)");
                Scanner scanner3 = new Scanner(System.in);
                String cmd = scanner3.nextLine();
                if (cmd.equals("да")) {
                    for (Task taskToRemove : newList) {
                        taskList.remove(taskToRemove);
                    }
                    System.out.println("Все задачи удалены!");
                    return;
                }
            }
        }
    }


    @Override
    public void filterTasksByStatus() {
        System.out.println("Задачи с каким статусом вывести?(1 - TODO; 2 - IN_PROGRESS; 3 - DONE; 4 - отмена.");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int cmd = scanner.nextInt();
            if (cmd == 1) {
                taskList.stream().filter(e -> e.getStatus().equals(TODO)).forEach(System.out::println);
                break;
            } else if (cmd == 2) {
                taskList.stream().filter(e -> e.getStatus().equals(IN_PROGRESS)).forEach(System.out::println);
                break;
            } else if (cmd == 3) {
                taskList.stream().filter(e -> e.getStatus().equals(DONE)).forEach(System.out::println);
                break;
            } else if (cmd == 4) {
                break;
            } else {
                System.out.println("Неверная команда");
            }
        }
    }

    @Override
    public void sortTasks() {
        while (true) {
            System.out.println("По какому признаку надо отсортировать задачи?");
            System.out.println("1 - по названию; 2 - по сроку; 3 - статусу; 4 - отмена");
            Scanner scanner = new Scanner(System.in);
            int cmd = scanner.nextInt();
            if (cmd == 1) {
                tm.setTaskList(taskList.stream().sorted(Comparator.comparing(Task::getName).
                                thenComparing(Task::getDeadline).
                                thenComparing(Task::getStatus)).
                        toList());
                taskList.forEach(System.out::println);
                break;
            } else if (cmd == 2) {
                tm.setTaskList(taskList.stream().sorted(Comparator.comparing(Task::getDeadline).
                                thenComparing(Task::getName).
                                thenComparing(Task::getStatus)).
                        toList());
                taskList.forEach(System.out::println);
                break;
            } else if (cmd == 3) {
                tm.setTaskList(taskList.stream().sorted(Comparator.comparing(Task::getStatus).
                                thenComparing(Task::getName).
                                thenComparing(Task::getDeadline)).
                        toList());
                taskList.forEach(System.out::println);
                break;
            } else if (cmd == 4) {
                break;
            } else {
                System.out.println("не верная команда, попробуйте снова");
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

    private void newName(Task task) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите новое название:");
        task.setName(scanner.nextLine());
        System.out.println("Название изменено.");
        System.out.println(task);
    }

    private void newDescription(Task task) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите новое описание:");
        task.setDescription(scanner.nextLine());
        System.out.println("Описание изменено.");
        System.out.println(task);
    }

    private void newDeadline(Task task) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите новый срок (в формате ГГГГ-ММ-ДД:: ");
        task.setDeadline(LocalDate.parse(scanner.nextLine()));
        System.out.println("Срок изменен.");
        System.out.println(task);
    }

    private void newStatus(Task task) {
        while (true) {
            System.out.println("Какой срок установить?(1 - TODO; 2 - IN_PROGRESS; 3 - DONE");
            Scanner scanner = new Scanner(System.in);
            int cmd = scanner.nextInt();
            if (cmd == 1) {
                task.setStatus(TODO);
                break;
            } else if (cmd == 2) {
                task.setStatus(IN_PROGRESS);
                break;
            } else if (cmd == 3) {
                task.setStatus(DONE);
                break;
            } else if (cmd == 4) {
                break;
            } else {
                System.out.println("Неверная команда");
            }
        }
        System.out.println("Статус изменен.");
        System.out.println(task);
    }
    private Task createTask(){
        return new Task();
    }
}
