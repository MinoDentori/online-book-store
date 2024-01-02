package mate.academy.onlinebookstore.repository.cart;

import java.util.Optional;
import mate.academy.onlinebookstore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    public Optional<CartItem> findByIdAndShoppingCartId(Long id, Long shoppingCartId);
}
