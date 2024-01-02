package mate.academy.onlinebookstore.repository.cart;

import mate.academy.onlinebookstore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    public Optional<CartItem> findByIdAndShoppingCartId(Long id, Long shoppingCartId);
}
