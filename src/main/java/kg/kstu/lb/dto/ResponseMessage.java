package kg.kstu.lb.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatusCode;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseMessage<T> {
    T result;
    HttpStatusCode code;
    String details;
}
