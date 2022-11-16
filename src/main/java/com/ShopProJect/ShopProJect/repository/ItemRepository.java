package com.ShopProJect.ShopProJect.repository;

import com.ShopProJect.ShopProJect.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    //첫번째 제네릭 타입에는 엔티티 타입의 클래스를 넣어줌!
    //두번쨰 제네릭 타입에는 키본키 타입을 넣어주면됨!

    List<Item> findByItemNm(String itemNm);
    //이름으로 찾기
    List<Item> findByItemNmOrItemDetail(String itemNm,String itemDetail);
    //이름이랑 상세 설명으로 찾기
    List<Item> findByPriceLessThan(Integer price);
    //가격보다 적은 가격으로 찾기
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
    //가격을 내림차순으로 조회
    //Asc는 오름차순 Desc는 내림차순!
}
