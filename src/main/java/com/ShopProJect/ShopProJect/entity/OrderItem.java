package com.ShopProJect.ShopProJect.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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


}
