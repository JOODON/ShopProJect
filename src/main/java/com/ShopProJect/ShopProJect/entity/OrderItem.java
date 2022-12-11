package com.ShopProJect.ShopProJect.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue//기본키 매핑
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    //하나의 상품은 여러주문 상품으로 들어갈수 있기 때문에 주문 상품에 대하여 다대일 단방향 매핑을 해줌

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    //하나의 주문의 여러개의 상품을 주문할수 있으므로 주문상품 엔티티와 주문에티티 관계를 다대일 단방향으로 매핑을 먼저 설정해줌

    private int orderPrice;

    private int count;

    private LocalDateTime localDateTime;

    private LocalDateTime updateTime;


}
