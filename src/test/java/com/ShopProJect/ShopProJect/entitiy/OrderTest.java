package com.ShopProJect.ShopProJect.entitiy;

import com.ShopProJect.ShopProJect.constant.ItemSellStatus;
import com.ShopProJect.ShopProJect.entity.Item;
import com.ShopProJect.ShopProJect.entity.Order;
import com.ShopProJect.ShopProJect.entity.OrderItem;
import com.ShopProJect.ShopProJect.repository.ItemRepository;
import com.ShopProJect.ShopProJect.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class OrderTest {
    @Autowired
    OrderRepository orderRepository;
    
    @Autowired
    ItemRepository itemRepository;
    
    @PersistenceContext
    EntityManager em;
    
    public Item createItem(){
        Item item=new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("상세설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        return item;
    }
    @Test
    @DisplayName("영속성 전이 테스트")
    public void cascadeTest(){
        Order order=new Order();

        for(int i=0; i<3 ;i++){
            Item item=this.createItem();
            itemRepository.save(item);
            OrderItem orderItem=new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
            //아직 영속성 컨텍스트에 저장되자 않은 orderItem엔티티를 담아줌
        }
        orderRepository.saveAndFlush(order);//영속성 콘텍스트에 있는 객체들을 데이터베이스에 반영함

        em.clear();//영속성 컨텍스트의 상태를 초기화해줌

        Order savedOrder=orderRepository.findById(order.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(3,savedOrder.getOrderItems().size());
    }
}