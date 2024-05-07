package kg.kstu.lb.services.impl;

import kg.kstu.lb.dto.StatusDto;
import kg.kstu.lb.enums.Status;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
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

    @Qualifier("nodeFourRestTemplate")
    RestTemplate nodeFourRestTemplate;

    @Qualifier("nodeFiveRestTemplate")
    RestTemplate nodeFiveRestTemplate;

    NodeService nodeService;


    public void check() {
        doRequest(nodeOneRestTemplate, 1L);
        doRequest(nodeTwoRestTemplate, 2L);
        doRequest(nodeThreeRestTemplate, 3L);
        doRequest(nodeFourRestTemplate, 4L);
        doRequest(nodeFiveRestTemplate, 5L);
    }

    private void doRequest(RestTemplate restTemplate, Long id) {
        try {
            var response = restTemplate.getForObject(
                    "/status",
                    StatusDto.class
            );

            var currentStatus = response.getStatus();

            if (!nodeService.getById(id).getStatus().equals(currentStatus)) {
                nodeService.setStatus(id, currentStatus);
            }
        } catch (HttpStatusCodeException | ResourceAccessException e) {
            if (!nodeService.getById(id).getStatus().equals(Status.NO_ANSWER)) {
                nodeService.setStatus(id, Status.NO_ANSWER);
            }
        }
    }
}
