package com.jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // 꼭 스트링으로.
    private DeliveryStatus status; // READY, COMP

    public static Delivery CreateDelivery(Address address) {
        Delivery delivery = new Delivery();
        delivery.setAddress(address);
        delivery.setStatus(DeliveryStatus.READY);
        return delivery;
    }



}
