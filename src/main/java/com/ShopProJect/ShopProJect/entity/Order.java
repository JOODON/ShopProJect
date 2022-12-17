package com.ShopProJect.ShopProJect.entity;


import com.ShopProJect.ShopProJect.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;
    //하나의 회원은 여러 주문을 할수 있기 때문에 다대일 방향으로 매핑을 해줌

    private LocalDateTime orderDate;//주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;//주문 상태


    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    //주문 상품 엔티티랑 1대다 매핑 외래키가 order_item 테이블에 있으므로 연관관계 주인은 orderItem
    //부모속성 엔티티의 영속성 상태변화를 자식에게 모두 전이하는 all을 사용함
    //고아객체 제거를 하기위해서 orphanRemoval = true 를 붙혀줌

    private List<OrderItem> orderItems=new ArrayList<>();
    //하나의 주문이 여러상품을가지고 있으므로 List자료형을 가지고 매핑해줌

    private LocalDateTime regTime;

    private LocalDateTime updateTime;



}
//영속성 전의란 부모가 One 자식이 Many로 잡아놓고 One이 삭제가 되었을때 같이 없어져야되는건지 물어보는 거임