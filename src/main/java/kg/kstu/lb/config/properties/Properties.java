package kg.kstu.lb.config.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Properties {

    @Value("${sti-service.connect-timeout}")
    Long connectTimeout;

    @Value("${sti-service.read-timeout}")
    Long readTimeout;

}
