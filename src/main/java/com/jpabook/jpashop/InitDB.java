package com.jpabook.jpashop;

import com.jpabook.jpashop.domain.*;
import com.jpabook.jpashop.domain.item.Book;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        public void dbInit() {
            Member member = new Member();
            member.setName("riku");
            member.setAddress(new Address("1","1","1"));
            em.persist(member);

            Member member2 = new Member();
            member2.setName("mark");
            member2.setAddress(new Address("1","1","1"));
            em.persist(member2);

            Book book1 = CreateBook(100, 10000, "wish1");
            em.persist(book1);

            Book book2 = CreateBook(628, 62800, "wish2");
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 62800, 1);
            Delivery delivery = Delivery.CreateDelivery(member.getAddress());
            Order order1 = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order1);

            OrderItem orderItem3 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem4 = OrderItem.createOrderItem(book2, 62800, 1);
            Delivery delivery2 = Delivery.CreateDelivery(member2.getAddress());
            Order order2 = Order.createOrder(member2, delivery2, orderItem3, orderItem4);
            em.persist(order2);

        }

        private static Book CreateBook(int stockQuantity, int price, String wish1) {
            Book book1 = new Book();
            book1.setName(wish1);
            book1.setStockQuantity(stockQuantity);
            book1.setPrice(price);
            return book1;
        }
    }

}
