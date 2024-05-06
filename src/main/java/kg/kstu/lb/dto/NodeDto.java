package kg.kstu.lb.dto;

import kg.kstu.lb.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NodeDto {
    Long id;
    String description;
    String ip;
    String port;
    Status status;

}

