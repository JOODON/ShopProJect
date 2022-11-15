package com.ShopProJect.ShopProJect.repository;

import com.ShopProJect.ShopProJect.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    //첫번째 제네릭 타입에는 엔티티 타입의 클래스를 넣어줌!
    //두번쨰 제네릭 타입에는 키본키 타입을 넣어주면됨!

    List<Item> findByItemNm(String itemNm);
    List<Item> findByItemNmOrItemDetail(String itemNm,String itemDetail);

}
