package infrastructure.email;

import domain.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailService {
    void sendOrderConfirmation(Order order);
}