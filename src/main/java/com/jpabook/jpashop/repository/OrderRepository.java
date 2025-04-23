package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Order;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long orderId) {
        return em.find(Order.class, orderId);
    }

    // 필터 사용하면 보통 동적 쿼리르 사용함
//    public List<Order> findAll(OrderSearch orderSearch) {
//
//    }

}
