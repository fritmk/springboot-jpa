package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = false)
    public void saveItem(Item item) {
        itemRepository.save(item);
    }


    // 데이터 수정 - (1) 변경 감지
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);

        // 사실 이렇게 하지 않아야한다.
        // 나중에 이런 식으로 하면 유지보수할 때 어디서 수정해야하는 지 다 찾아야 함.
        // 따라서 엔티티 안에 업데이트하는 메서드를 만들어 놓는 것이 좋음.

        // findItem.change(name, price, stockQuantity); <- 이런 식으로
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
    }


    // 이렇게 위임하는 거면 service 에 꼭 만들 필요가 있을까? 컨트롤러에서 바로 레파지토리를 해도 되지 않나.
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }




}
