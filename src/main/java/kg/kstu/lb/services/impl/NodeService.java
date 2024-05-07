package kg.kstu.lb.services.impl;

import kg.kstu.lb.dto.NodeDto;
import kg.kstu.lb.entities.Node;
import kg.kstu.lb.enums.Status;
import kg.kstu.lb.exception.BaseException;
import kg.kstu.lb.repo.NodeRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NodeService {

    NodeRepo repo;


    public List<NodeDto> getAll() {
        return repo.findAll().stream()
                .map(this::toNodeDto)
                .toList();
    }

    public NodeDto setStatus(Long id, Status status){
        Node node = getById(id);
        node.setStatus(status);

        return toNodeDto(repo.save(node));
    }


    public Node getById(Long id){
        return repo.findById(id).orElseThrow(() -> new BaseException("node with id %s not fount".formatted(id), HttpStatus.NOT_FOUND));
    }

    public Node getAvailableNode(){
        return repo.findDistinctFirstByStatus(Status.AVAILABLE)
                .orElseThrow(() -> new BaseException("Всу узлы заняты, попробуйте позже", HttpStatus.OK));
    }


    private NodeDto toNodeDto(Node node) {
        return NodeDto.builder()
                .id(node.getId())
                .description(node.getDescription())
                .ip(node.getIp())
                .port(node.getPort())
                .status(node.getStatus())
                .build();
    }
}
