package mate.academy.onlinebookstore.service.cart.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore.repository.cart.CartItemRepository;
import mate.academy.onlinebookstore.service.cart.CartItemService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    @Override
    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }
}
