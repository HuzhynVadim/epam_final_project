package ua.nure.huzhyn.model.entity.enums;

import java.math.BigDecimal;

public enum CarType {

    COMPARTMENT(new BigDecimal(300)),

    RESERVED_SEAT(new BigDecimal(200)),

    COMMON(new BigDecimal(100));

    private BigDecimal price;

    CarType(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}