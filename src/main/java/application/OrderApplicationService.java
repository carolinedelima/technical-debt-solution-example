package application;

import domain.Order;
import domain.OrderRequest;
import domain.event.OrderPaidEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import service.OrderService;
import infrastructure.payment.PaymentService;

@Service
@RequiredArgsConstructor
public class OrderApplicationService {

    private final OrderService orderService;
    private final PaymentService paymentService;
    private final ApplicationEventPublisher eventPublisher;

    public void process(OrderRequest request) {

        Order order = orderService.create(request);

        try {
            paymentService.charge(order);
            orderService.markPaid(order.getId());
            eventPublisher.publishEvent(new OrderPaidEvent(order.getId()));
        } catch (Exception ex) {
            orderService.markFailed(order.getId());
            throw ex;
        }
    }
}
