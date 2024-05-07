package kg.kstu.lb.services.impl;

import kg.kstu.lb.dto.StatusDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class HealthCheckServiceImpl {

    @Qualifier("nodeOneRestTemplate")
    RestTemplate nodeOneRestTemplate;

    @Qualifier("nodeTwoRestTemplate")
    RestTemplate nodeTwoRestTemplate;

    @Qualifier("nodeThreeRestTemplate")
    RestTemplate nodeThreeRestTemplate;

    NodeService nodeService;


    public void check() {
        doRequest(nodeOneRestTemplate, 1L);
        doRequest(nodeTwoRestTemplate, 2L);
        doRequest(nodeThreeRestTemplate, 3L);

    }

    private void doRequest(RestTemplate restTemplate, Long id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/status");

        var response = restTemplate.getForObject(
                builder.build().toUriString(),
                StatusDto.class
        );

        var currentStatus = response.getStatus();

        if (nodeService.getById(id).getStatus().equals(currentStatus)) {
            nodeService.setStatus(id, currentStatus);
        }
    }
}
