package kg.kstu.lb.controllers;

import jakarta.validation.Valid;
import kg.kstu.lb.dto.DecryptRequest;
import kg.kstu.lb.dto.DecryptResponse;
import kg.kstu.lb.dto.StatusDto;
import kg.kstu.lb.enums.Status;
import kg.kstu.lb.services.Service;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Controller {

    Service service;

    @PostMapping("decrypt")
    public ResponseEntity<DecryptResponse> decrypt(@RequestBody @Valid DecryptRequest request) {
        return ResponseEntity.ok(service.decrypt(request));
    }


    @GetMapping("servers/status")
    public ResponseEntity<StatusDto> getStatus(){
        return ResponseEntity.ok(service.getStatus());
    }
}

