package kg.kstu.lb.services.impl;

import kg.kstu.lb.component.CipherComponent;
import kg.kstu.lb.dto.DecryptRequest;
import kg.kstu.lb.dto.DecryptResponse;
import kg.kstu.lb.dto.StatusDto;
import kg.kstu.lb.enums.Status;
import kg.kstu.lb.services.Service;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class ServiceImpl implements Service {

    final CipherComponent cipherComponent;

    static Status status = Status.AVAILABLE;
    static LocalDateTime startDate;

    @Value("${decryption-time}")
    Long decryptionTime;


    @Override
    public StatusDto getStatus() {
        return new StatusDto(status);
    }

    @Override
    public DecryptResponse decrypt(DecryptRequest request) {
        var logComment = Optional.ofNullable(request.getComment()).orElse(UUID.randomUUID().toString());
        log.info("начало расшифровки: %s".formatted(logComment));
        status = Status.IN_PROGRESS;
        startDate = LocalDateTime.now();
        String decryptedText = cipherComponent.decrypt(request.getEncryptedText());

        do {
        }
        while (LocalDateTime.now().isBefore(startDate.plusSeconds(decryptionTime)));
        status = Status.AVAILABLE;
        log.info("конец расшифровки: %s".formatted(logComment));

        return new DecryptResponse(decryptedText, request.getEncryptedText(), logComment);
    }
}
