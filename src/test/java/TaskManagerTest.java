import ToDoList.Enums.Status;
import ToDoList.Enums.TaskFields;
import ToDoList.Manager.TaskManager;
import ToDoList.Repository.Task;
import ToDoList.Repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskManagerTest {
    Map<Integer, Task> tempMap;
    TaskRepository taskRepository = new TaskRepository();
    TaskManager taskManager = new TaskManager(taskRepository);
    String newLine = System.lineSeparator();

    @BeforeEach
    void setMap() {
        tempMap = new HashMap<>();
        Task task1 = new Task("1", "1", LocalDate.of(2025, 3, 3), Status.DONE);
        Task task2 = new Task("2", "2", LocalDate.of(2025, 4, 4), Status.IN_PROGRESS);
        Task task3 = new Task("3", "3", LocalDate.of(2025, 5, 5), Status.TODO);
        tempMap.put(1, task1);
        tempMap.put(2, task2);
        tempMap.put(3, task3);
        taskRepository.setTaskMap(tempMap);
    }

    @Test
    void filterTasksByStatusDONETest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        taskManager.filterTasksByStatus(Status.DONE);
        System.setOut(originalOut);
        assertEquals("Задачи со статусом DONE:" + newLine + "ID 1 - Task(name=1, description=1, " +
                "deadline=2025-03-03, status=DONE)" + newLine, outputStream.toString());
    }

    @Test
    void filterTasksByStatusTODOTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        taskManager.filterTasksByStatus(Status.TODO);
        System.setOut(originalOut);
        assertEquals("Задачи со статусом TODO:" + newLine + "ID 3 - Task(name=3, description=3, " +
                "deadline=2025-05-05, status=TODO)" + newLine, outputStream.toString());
    }

    @Test
    void filterTasksByStatusInProgressTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        taskManager.filterTasksByStatus(Status.IN_PROGRESS);
        System.setOut(originalOut);
        assertEquals("Задачи со статусом IN_PROGRESS:" + newLine + "ID 2 - Task(name=2, description=2, " +
                "deadline=2025-04-04, status=IN_PROGRESS)" + newLine, outputStream.toString());
    }
    @Test
    void sortTasksByNameTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        taskManager.sortTasks(TaskFields.NAME);
        System.setOut(originalOut);
        assertEquals( "1 - Task(name=1, description=1, deadline=2025-03-03, status=DONE)" + newLine +
                        "2 - Task(name=2, description=2, deadline=2025-04-04, status=IN_PROGRESS)" + newLine +
                        "3 - Task(name=3, description=3, deadline=2025-05-05, status=TODO)" + newLine,
                outputStream.toString());
    }

    @Test
    void sortTasksByDeadlineTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        taskManager.sortTasks(TaskFields.DEADLINE);
        System.setOut(originalOut);
        assertEquals( "1 - Task(name=1, description=1, deadline=2025-03-03, status=DONE)" + newLine +
                        "2 - Task(name=2, description=2, deadline=2025-04-04, status=IN_PROGRESS)" + newLine +
                        "3 - Task(name=3, description=3, deadline=2025-05-05, status=TODO)" + newLine,
                outputStream.toString());
    }

    @Test
    void sortTasksByStatusTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        taskManager.sortTasks(TaskFields.STATUS);
        System.setOut(originalOut);
        assertEquals("2 - Task(name=2, description=2, deadline=2025-04-04, status=IN_PROGRESS)" + newLine +
                        "3 - Task(name=3, description=3, deadline=2025-05-05, status=TODO)" + newLine +
                        "1 - Task(name=1, description=1, deadline=2025-03-03, status=DONE)" + newLine,
                outputStream.toString());
    }
}
