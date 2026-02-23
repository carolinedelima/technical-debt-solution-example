package domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class Order {

    private Long id;
    private final Long userId;
    private final BigDecimal amount;
    private OrderStatus status = OrderStatus.PENDING;

    public void markPaid() {
        if (status != OrderStatus.PENDING) {
            throw new IllegalStateException("src.main.java.domain.Order cannot be marked as paid");
        }
        this.status = OrderStatus.PAID;
    }

    public void markFailed() {
        if (status != OrderStatus.PENDING) {
            throw new IllegalStateException("src.main.java.domain.Order cannot be marked as failed");
        }
        this.status = OrderStatus.FAILED;
    }
}
