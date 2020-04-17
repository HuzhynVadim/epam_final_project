package ua.nure.huzhyn.model.entity.enums;

public enum CarType {
    COMPARTMENT("20",300),
    RESERVED_SEAT("30",200),
    COMMON("40",100);

    CarType(String seats, int price) {
    }
}
