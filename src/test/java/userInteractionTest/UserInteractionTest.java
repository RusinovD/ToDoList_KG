package userInteractionTest;

import ToDoList.controller.UserInteraction;
import ToDoList.enums.Status;
import ToDoList.enums.TaskFields;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;


class UserInteractionTest {
    private UserInteraction userInteraction;
    private Scanner scanner;

    @BeforeEach
    void setUp() {
        scanner = Mockito.mock(Scanner.class);
        userInteraction = new UserInteraction();
        userInteraction.setScanner(scanner);
    }

    @Test
    void scanTaskNameTest() {
        Mockito.when(scanner.nextLine()).thenReturn("Тестовая задача");
        String result = userInteraction.scanTaskName();
        assertEquals("Тестовая задача", result);
    }

    @Test
    void scanTaskDescriptionTest() {
        Mockito.when(scanner.nextLine()).thenReturn("Тестовое описание");
        String result = userInteraction.scanTaskDescription();
        assertEquals("Тестовое описание", result);
    }

    @Test
    void scanTaskDeadlineTest() {
        Mockito.when(scanner.nextLine()).thenReturn("2025-05-05");
        LocalDate result = userInteraction.scanTaskDeadline();
        assertEquals(LocalDate.of(2025, 5, 5), result);
    }

    @Test
    void scanTaskStatusTest() {
        Mockito.when(scanner.nextLine()).thenReturn("DONE");
        Status result = userInteraction.scanTaskStatus();
        assertEquals(Status.DONE, result);
    }

    @Test
    void scanTaskIDTest() {
        Mockito.when(scanner.nextInt()).thenReturn(1);
        int result = userInteraction.scanTaskID();
        assertEquals(1, result);
    }

    @Test
    void scanForEditTaskTest() {
        Mockito.when(scanner.nextLine()).thenReturn("NAME");
        TaskFields result = userInteraction.scanForEditTask();
        assertEquals(TaskFields.NAME, result);
    }

    @Test
    void scanForSortTasksTest() {
        Mockito.when(scanner.nextLine()).thenReturn("DESCRIPTION");
        TaskFields result = userInteraction.scanForSortTasks();
        assertEquals(TaskFields.DESCRIPTION, result);
    }
}
