package kg.kstu.lb.repo;

import kg.kstu.lb.entities.Node;
import kg.kstu.lb.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NodeRepo extends JpaRepository<Node, Long> {
    Optional<Node> findDistinctFirstByStatus(Status status);
}
