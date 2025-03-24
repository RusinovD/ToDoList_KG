package ToDoList.manager;

import ToDoList.enums.Status;
import ToDoList.enums.TaskFields;

public interface TaskManagerInterface {

    void filterTasksByStatus(Status status);

    void sortTasks(TaskFields cmd);
}
