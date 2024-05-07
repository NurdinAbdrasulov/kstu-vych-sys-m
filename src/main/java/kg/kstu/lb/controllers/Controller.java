package kg.kstu.lb.controllers;

import jakarta.validation.Valid;
import kg.kstu.lb.dto.*;
import kg.kstu.lb.services.Service;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Controller {

    Service service;

    @PostMapping("decrypt")
    public ResponseEntity<DecryptResponse> decrypt(@RequestBody @Valid DecryptRequest request) {
        return ResponseEntity.ok(service.decrypt(request));
    }

    @PostMapping("encrypt")
    public ResponseEntity<DecryptResponse> encrypt(@RequestBody @Valid EncryptRequest request) {
        return ResponseEntity.ok(service.encrypt(request));
    }



    @GetMapping("servers/status")
    public ResponseEntity<List<NodeDto>> getStatus(){
        return ResponseEntity.ok(service.getStatus());
    }
}

