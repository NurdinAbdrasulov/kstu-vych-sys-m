package kg.kstu.lb.services;

import kg.kstu.lb.dto.*;

import java.util.List;

public interface Service {
    List<NodeDto> getStatus();

    DecryptResponse decrypt(DecryptRequest request);

    DecryptResponse encrypt(EncryptRequest request);
}
