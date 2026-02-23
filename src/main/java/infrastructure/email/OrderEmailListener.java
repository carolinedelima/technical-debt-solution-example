package infrastructure.email;

import domain.event.OrderPaidEvent;
import repository.OrderRepository;
import domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class OrderEmailListener {

    private final EmailService emailService;
    private final OrderRepository orderRepository;

    @TransactionalEventListener
    public void handle(OrderPaidEvent event) {

        Order order = orderRepository.findById(event.orderId())
                .orElseThrow(() -> new IllegalArgumentException("src.main.java.domain.Order not found"));

        emailService.sendOrderConfirmation(order);
    }
}
