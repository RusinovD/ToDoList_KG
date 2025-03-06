package ToDoList;

public interface TaskManagerInterface {
    //add – добавить задачу.
    void addTask();

    //list – вывести список задач.
    void listTasks();

    //edit – редактировать задачу.
    void editTask();

    //delete – удалить задачу.
    void deleteTask();

    //filter – отфильтровать задачи по статусу.
    void filterTasksByStatus();

    //sort – отсортировать задачи.
    void sortTasks();

}
