package com.ShopProJect.ShopProJect.repository;

import com.ShopProJect.ShopProJect.entity.Item;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.repository.query.Param;


import java.awt.print.Pageable;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long>,QuerydslPredicateExecutor<Item>{

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
    @Query("SELECT i FROM Item i WHERE i.itemDetail like %:itemDetail% order by i.price desc ")
    //위에부분을 보면 테이블 Item을 i라는 이름으로 대신써주는 모습!
    List<Item> findItemDetail(@Param("itemDetail") String itemDetail);
    //첫번쨰 @Param부분에는 Like뒤에 들어  올녀석을 변수로 지정해주는 모습!
    //변수를 넣을떄?는 :을 넣어서 작성하면됨
    @Query(value="SELECT * FROM Item i WHERE i.item_Detail LIKE %:itemDetail% order by i.price desc ",nativeQuery = true)
    List<Item> findItemDetailByNative(@Param("itemDetail") String itemDetail);

}
