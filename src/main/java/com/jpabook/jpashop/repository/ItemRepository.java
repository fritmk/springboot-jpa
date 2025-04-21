package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.item.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    private void save(Item item) {

        // 아이템은 처음에 아이디가 없음.
        if (item.getId() == null) {
            em.persist(item); // 완전 신규로 등록
        } else {
            // 업데이트 비슷한 것. 아이디값이 없다는 것은 완전히 새로 생성한 객체라는 뜻.
            // 이미 등록된 것을 어디서 가져온 것임. 따라서, 업데이트라고 이해하면 됨. 실제 업데이트는 아니지만...~
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }



}
