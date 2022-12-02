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
    private Long id;

    @OneToOne
    //어노테이션을 작성해서 회원 엔티티와 1대1로 작성
    @JoinColumn(name = "member_id")
    //매핑할 왜래키를 지정해줌
    private Member member;
}
