package kg.kstu.lb.services.impl;

import kg.kstu.lb.component.CipherComponent;
import kg.kstu.lb.dto.*;
import kg.kstu.lb.entities.Node;
import kg.kstu.lb.exception.BaseException;
import kg.kstu.lb.services.Service;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ServiceImpl implements Service {

    CipherComponent cipherComponent;
    NodeService nodeService;

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


    @Override
    public List<NodeDto> getStatus() {
        return nodeService.getAll();

    }

    @Override
    public DecryptResponse decrypt(DecryptRequest request) {
        Node availableNode = nodeService.getAvailableNode();
        RestTemplate restTemplate = getRestTemplateByNodeId(availableNode.getId().intValue());

        var response = restTemplate.postForObject(
                "/decrypt",
                request,
                DecryptResponse.class
        );

        response.setComment(String.format("обработано узлом %s \n %s", availableNode.getId(), response.getComment()));

        return response;
    }

    @Override
    public DecryptResponse encrypt(EncryptRequest request) {
        var logComment = Optional.ofNullable(request.getComment()).orElse(UUID.randomUUID().toString());
        log.info("начало шифрования: %s".formatted(logComment));

        String encryptText = cipherComponent.encrypt(request.getText());

        log.info("конец шифрования: %s".formatted(logComment));

        return new DecryptResponse(request.getText(), encryptText, logComment);

    }

    private RestTemplate getRestTemplateByNodeId(Integer id){
        return switch (id){
            case 1 -> nodeOneRestTemplate;
            case 2 -> nodeTwoRestTemplate;
            case 3 -> nodeTwoRestTemplate;
            case 4 -> nodeTwoRestTemplate;
            case 5 -> nodeTwoRestTemplate;
            default -> throw new BaseException("не найдены данные ноды с id %s".formatted(id), HttpStatus.NOT_FOUND);
        };
    }
}
