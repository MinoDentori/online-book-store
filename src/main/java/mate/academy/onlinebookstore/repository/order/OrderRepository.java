package mate.academy.onlinebookstore.repository.order;

import mate.academy.onlinebookstore.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o JOIN FETCH o.orderItems WHERE o.user.id = :userId")
    Set<Order> findOrdersByUserId(Long userId);

    @EntityGraph(attributePaths = {"orderItems"})
    Optional<Order> findByIdAndUserId(Long orderId, Long userId);

}
