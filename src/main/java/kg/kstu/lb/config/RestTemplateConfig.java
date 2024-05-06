package kg.kstu.lb.config;

import kg.kstu.lb.config.properties.Properties;
import kg.kstu.lb.services.impl.NodeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestTemplateConfig {

    Properties properties;
    NodeService nodeService;

    @Bean
    public RestTemplate nodeOneRestTemplate() {
        Long nodeId = 1L;

        return new RestTemplateBuilder()
                .rootUri(String.join(":", nodeService.getById(nodeId).getIp(), nodeService.getById(nodeId).getPort()))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .setConnectTimeout(Duration.ofSeconds(this.properties.getConnectTimeout()))
                .setReadTimeout(Duration.ofSeconds(this.properties.getReadTimeout()))
                .build();
    }

}
