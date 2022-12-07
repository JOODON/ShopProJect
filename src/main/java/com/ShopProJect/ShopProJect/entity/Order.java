package com.ShopProJect.ShopProJect.entity;


import com.ShopProJect.ShopProJect.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    private LocalDateTime orderDate;//주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;//주문 상태

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

}
