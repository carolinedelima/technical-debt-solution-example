package infrastructure.payment;

import domain.Order;

public interface PaymentService {
    void charge(Order order);
}