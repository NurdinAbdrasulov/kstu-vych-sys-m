package kg.kstu.lb.repo;

import kg.kstu.lb.entities.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepo extends JpaRepository<Node, Long> {
}
