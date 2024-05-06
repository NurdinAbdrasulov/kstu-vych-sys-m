package kg.kstu.lb.controllers;


import kg.kstu.lb.dto.NodeDto;
import kg.kstu.lb.services.impl.NodeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("node")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NodeController {

    NodeService service;

    @GetMapping
    public ResponseEntity<List<NodeDto>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
}
