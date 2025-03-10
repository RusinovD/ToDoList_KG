package ToDoList;

import java.time.LocalDate;

public interface TaskManagerInterface {
    //add – добавить задачу.
    void addTask(String taskName, String taskDescription, LocalDate deadline, Status taskStatus);

    //taskList – вывести список задач.
    void listTasks();

    //edit – редактировать задачу.
    void editTask(Task task);

    //delete – удалить задачу.
    void deleteTask(Task task);

    //filter – отфильтровать задачи по статусу.
    void filterTasksByStatus(Status status);

    //sort – отсортировать задачи.
    void sortTasks();
}
