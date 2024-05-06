package kg.kstu.lb.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Status {
    AVAILABLE("Доступен"),
    IN_PROGRESS("В процессе"),
    NO_ANSWER("нет ответа"),
    ;


    String description;
}
