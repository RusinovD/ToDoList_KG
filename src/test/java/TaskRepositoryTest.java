import ToDoList.Enums.Status;
import ToDoList.Repository.Task;
import ToDoList.Repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskRepositoryTest {
    TaskRepository taskRepository = new TaskRepository();
    HashMap <Integer, Task> tempMap;
    String newLine = System.lineSeparator();

    @BeforeEach
    void setUp() {
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
    void addTaskTest() {
        Task task = new Task("Задача 1", "Описание 1",
                LocalDate.of(2025, 5, 5), Status.TODO);
        taskRepository.addTask("Задача 1", "Описание 1",
                LocalDate.of(2025, 5, 5), Status.TODO);
        assertEquals(task, taskRepository.getTaskMap().get(1));
    }

    @Test
    void listTasksTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        taskRepository.listTasks();
        System.setOut(originalOut);
        assertEquals( "Список задач:" + newLine +
                        "ID 1 - Task(name=1, description=1, deadline=2025-03-03, status=DONE)" + newLine +
                        "ID 2 - Task(name=2, description=2, deadline=2025-04-04, status=IN_PROGRESS)" + newLine +
                        "ID 3 - Task(name=3, description=3, deadline=2025-05-05, status=TODO)" + newLine,
                outputStream.toString());
    }

    @Test
    void deleteNonExistTaskTest() {
        int CONSTANT_NON_EXIST_ID = 10;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        taskRepository.deleteTask(CONSTANT_NON_EXIST_ID);
        System.setOut(originalOut);
        assertEquals("Задача не найдена, повторите запрос." + newLine, outputStream.toString());
    }

    @Test
    void deleteTaskTest() {
        int CONSTANT_ID = 1;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        taskRepository.deleteTask(CONSTANT_ID);
        System.setOut(originalOut);
        assertEquals("Задача \"1\" удалена." + newLine, outputStream.toString());
    }

}
