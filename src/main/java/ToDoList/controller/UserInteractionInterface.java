package ToDoList.controller;

import java.time.LocalDate;

public interface UserInteractionInterface {
    String scanTaskName();

    String scanTaskDescription();

    LocalDate scanTaskDeadline();

    Enum scanTaskStatus();

    int scanTaskID();
}
