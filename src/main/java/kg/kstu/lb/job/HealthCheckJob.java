package kg.kstu.lb.job;


import kg.kstu.lb.services.impl.HealthCheckServiceImpl;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;


@Component
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Slf4j
public class HealthCheckJob {

    HealthCheckServiceImpl service;

    @Scheduled(fixedRate = 5000)
    public void checkServersHealth(){
        log.info("job started");

        service.check();

        log.info("job ended");
    }
}
