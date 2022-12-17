package com.ShopProJect.ShopProJect.entitiy;

import com.ShopProJect.ShopProJect.constant.ItemSellStatus;
import com.ShopProJect.ShopProJect.entity.*;
import com.ShopProJect.ShopProJect.repository.ItemRepository;
import com.ShopProJect.ShopProJect.repository.MemberRepository;
import com.ShopProJect.ShopProJect.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
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

    @Autowired
    OrderItemRepository orderItemRepository;
    @PersistenceContext
    EntityManager em;

    public Item createItem() {
        Item item = new Item();
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
    public void cascadeTest() {
        Order order = new Order();

        for (int i = 0; i < 3; i++) {
            Item item = this.createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
            //아직 영속성 컨텍스트에 저장되자 않은 orderItem엔티티를 담아줌
        }
        orderRepository.saveAndFlush(order);//영속성 콘텍스트에 있는 객체들을 데이터베이스에 반영함

        em.clear();//영속성 컨텍스트의 상태를 초기화해줌

        Order savedOrder = orderRepository.findById(order.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(3, savedOrder.getOrderItems().size());
    }

    @Autowired
    MemberRepository memberRepository;

    public Order crateOrder() {
        Order order = new Order();

        for (int i = 0; i < 3; i++) {
            Item item = this.createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        Member member = new Member();
        memberRepository.save(member);

        order.setMember(member);
        orderRepository.save(order);
        return order;
    }
    @Test
    @DisplayName("고아객체 제거 테스트")
    public void orphanRemovalTest(){
        Order order=this.crateOrder();
        order.getOrderItems().remove(0);//Order엔티티에서 관리하고있는 리스트의 0번째 인덱스 요소를 제거함
        em.flush();

    }
    @Test
    @DisplayName("지연 로딩 테스트")
    public void lazyLoadingTest(){
        Order order=this.crateOrder();
        //기존에 만들었던 주문 생성 메소드로 값을 받아옴
        Long orderItemId=order.getOrderItems().get(0).getId();

        em.flush();
        em.clear();

        OrderItem orderItem=orderItemRepository.findById(orderItemId).orElseThrow(EntityExistsException::new);
        //orderItem을 데이터베아스에서 조회함
        System.out.println("Order Class:  "+orderItem.getOrder().getClass());
        System.out.println("=====================================");
        orderItem.getOrder().getOrderDate();
        System.out.println("=====================================");
    }
}