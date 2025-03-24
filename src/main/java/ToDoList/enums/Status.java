package ToDoList.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    IN_PROGRESS(1),
    TODO(2),
    DONE(3);

    private final int priorityForSorting;
}
