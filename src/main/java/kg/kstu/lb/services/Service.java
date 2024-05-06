package kg.kstu.lb.services;

import kg.kstu.lb.dto.DecryptRequest;
import kg.kstu.lb.dto.DecryptResponse;
import kg.kstu.lb.dto.StatusDto;

public interface Service {
    StatusDto getStatus();

    DecryptResponse decrypt(DecryptRequest request);

}
