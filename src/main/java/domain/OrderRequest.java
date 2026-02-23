package domain;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class OrderRequest {

    private Long userId;
    private BigDecimal amount;

}