package ToDoList.Manager;

import ToDoList.Enums.Status;
import ToDoList.Enums.TaskFields;

public interface TaskManagerInterface {

    void filterTasksByStatus(Status status);

    void sortTasks(TaskFields cmd);
}
