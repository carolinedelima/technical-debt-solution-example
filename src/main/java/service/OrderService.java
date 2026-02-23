package service;

import domain.User;
import repository.OrderRepository;
import repository.UserRepository;
import domain.Order;
import domain.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Transactional
    public Order create(OrderRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Order order = new Order(user.getId(), request.getAmount());

        return orderRepository.save(order);
    }

    @Transactional
    public void markPaid(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.markPaid();
    }

    @Transactional
    public void markFailed(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.markFailed();
    }
}