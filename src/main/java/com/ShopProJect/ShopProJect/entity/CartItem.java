package com.ShopProJect.ShopProJect.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    //하나의 장바구니에는 여러가지 상품이 들어갈수 있으르모 다대일 관계로 매핑을 해줌

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    //하나의 상품또한 여러가지 장바구니에 담길수 있으므로 다대일 매핑을 해줌

    private int count;
    //장바구니의 담기는 개수를 넣어줌줌
}
