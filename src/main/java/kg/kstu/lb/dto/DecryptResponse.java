package kg.kstu.lb.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DecryptResponse {
    String decryptedText;
    String encryptedText;
    String comment;
}
