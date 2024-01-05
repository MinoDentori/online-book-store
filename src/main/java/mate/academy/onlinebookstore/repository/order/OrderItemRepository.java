package mate.academy.onlinebookstore.repository.order;

import mate.academy.onlinebookstore.model.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Set<OrderItem> findAllByOrderId(Long orderId, Pageable pageable);
}
