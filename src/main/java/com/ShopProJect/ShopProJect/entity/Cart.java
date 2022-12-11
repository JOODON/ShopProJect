package com.ShopProJect.ShopProJect.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="cart")
@Getter @Setter
@ToString

public class Cart {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    //AUTO : DB 종류에 따라 JPA가 알맞은 것을 선택합니다.
    private Long id;

    @OneToOne
    //어노테이션을 작성해서 회원 엔티티와 1대1로 작성
    //회원 아이디에는 한개에 장바구니만 가질수 있으므로 memberid랑 1대1매핑을 해주는부분이다.
    @JoinColumn(name = "member_id")
    //매핑할 왜래키를 지정해줌
    private Member member;
}
