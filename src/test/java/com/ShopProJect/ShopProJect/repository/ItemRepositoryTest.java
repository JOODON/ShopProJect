package com.ShopProJect.ShopProJect.repository;

import com.ShopProJect.ShopProJect.constant.ItemSellStatus;
import com.ShopProJect.ShopProJect.entity.Item;
import com.ShopProJect.ShopProJect.entity.QItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.logging.impl.*;
import org.thymeleaf.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;
    //영속성 컨텍스트를 사용하기 위해 어노테이션을 이용해 빈을 주입
    @Test
    public void createItemList() {
        for (int i = 0; i < 10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item saveItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }
    //Item(id=2, itemNm=테스트 상품1, price=10001, stockNumber=100, itemDetail=테스트 상품 상세 설명1, itemSellStatus=SELL, regTime=2022-11-15T21:38:06.708535, updateTime=2022-11-15T21:38:06.708535)

    @Test
    @DisplayName("상품명 ,상품 상세설명 or 테스트")
    public void findByItemNmOrItemDetailTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }
    //Item(id=2, itemNm=테스트 상품1, price=10001, stockNumber=100, itemDetail=테스트 상품 상세 설명1, itemSellStatus=SELL, regTime=2022-11-15T21:37:12.914731, updateTime=2022-11-15T21:37:12.914731)
    //Item(id=6, itemNm=테스트 상품5, price=10005, stockNumber=100, itemDetail=테스트 상품T 상세 설명5, itemSellStatus=SELL, regTime=2022-11-15T21:37:12.924715, updateTime=2022-11-15T21:37:12.924715)

    @Test
    @DisplayName("가격 LessThen Test")
    public void findByPriceLessThenTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
        //Item(id=1, itemNm=테스트 상품0, price=10000, stockNumber=100, itemDetail=테스트 상품 상세 설명0, itemSellStatus=SELL, regTime=2022-11-16T09:08:15.758644, updateTime=2022-11-16T09:08:15.758644)
        //Item(id=2, itemNm=테스트 상품1, price=10001, stockNumber=100, itemDetail=테스트 상품 상세 설명1, itemSellStatus=SELL, regTime=2022-11-16T09:08:15.936706, updateTime=2022-11-16T09:08:15.936706)
        //Item(id=3, itemNm=테스트 상품2, price=10002, stockNumber=100, itemDetail=테스트 상품 상세 설명2, itemSellStatus=SELL, regTime=2022-11-16T09:08:15.941708, updateTime=2022-11-16T09:08:15.941708)
        //Item(id=4, itemNm=테스트 상품3, price=10003, stockNumber=100, itemDetail=테스트 상품 상세 설명3, itemSellStatus=SELL, regTime=2022-11-16T09:08:15.948714, updateTime=2022-11-16T09:08:15.948714)
        //Item(id=5, itemNm=테스트 상품4, price=10004, stockNumber=100, itemDetail=테스트 상품 상세 설명4, itemSellStatus=SELL, regTime=2022-11-16T09:08:15.956716, updateTime=2022-11-16T09:08:15.956716)
    }

    @Test
    @DisplayName("가격 LessThen 내림 차순 정렬 Test")
    public void findByPriceLessThenOrderByDescTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
        //Item(id=5, itemNm=테스트 상품4, price=10004, stockNumber=100, itemDetail=테스트 상품 상세 설명4, itemSellStatus=SELL, regTime=2022-11-16T09:16:37.546793, updateTime=2022-11-16T09:16:37.546793)
        //Item(id=4, itemNm=테스트 상품3, price=10003, stockNumber=100, itemDetail=테스트 상품 상세 설명3, itemSellStatus=SELL, regTime=2022-11-16T09:16:37.541791, updateTime=2022-11-16T09:16:37.541791)
        //Item(id=3, itemNm=테스트 상품2, price=10002, stockNumber=100, itemDetail=테스트 상품 상세 설명2, itemSellStatus=SELL, regTime=2022-11-16T09:16:37.536790, updateTime=2022-11-16T09:16:37.536790)
        //Item(id=2, itemNm=테스트 상품1, price=10001, stockNumber=100, itemDetail=테스트 상품 상세 설명1, itemSellStatus=SELL, regTime=2022-11-16T09:16:37.531789, updateTime=2022-11-16T09:16:37.531789)
        //Item(id=1, itemNm=테스트 상품0, price=10000, stockNumber=100, itemDetail=테스트 상품 상세 설명0, itemSellStatus=SELL, regTime=2022-11-16T09:16:37.364613, updateTime=2022-11-16T09:16:37.364613)
    }

    @Test
    @DisplayName("쿼리를 이용한 상품 조회 테스트")
    public void findItemDetail() {
        this.createItemList();
        List<Item> itemList = itemRepository.findItemDetail("테스트 상품 상세 설명");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
        //Item(id=9, itemNm=테스트 상품8, price=10008, stockNumber=100, itemDetail=테스트 상품 상세 설명8, itemSellStatus=SELL, regTime=2022-11-16T22:48:18.071577, updateTime=2022-11-16T22:48:18.071577)
        //Item(id=8, itemNm=테스트 상품7, price=10007, stockNumber=100, itemDetail=테스트 상품 상세 설명7, itemSellStatus=SELL, regTime=2022-11-16T22:48:18.068577, updateTime=2022-11-16T22:48:18.068577)
        //Item(id=7, itemNm=테스트 상품6, price=10006, stockNumber=100, itemDetail=테스트 상품 상세 설명6, itemSellStatus=SELL, regTime=2022-11-16T22:48:18.066578, updateTime=2022-11-16T22:48:18.066578)
        //Item(id=6, itemNm=테스트 상품5, price=10005, stockNumber=100, itemDetail=테스트 상품 상세 설명5, itemSellStatus=SELL, regTime=2022-11-16T22:48:18.063577, updateTime=2022-11-16T22:48:18.063577)
        //Item(id=5, itemNm=테스트 상품4, price=10004, stockNumber=100, itemDetail=테스트 상품 상세 설명4, itemSellStatus=SELL, regTime=2022-11-16T22:48:18.060575, updateTime=2022-11-16T22:48:18.060575)
        //Item(id=4, itemNm=테스트 상품3, price=10003, stockNumber=100, itemDetail=테스트 상품 상세 설명3, itemSellStatus=SELL, regTime=2022-11-16T22:48:18.057574, updateTime=2022-11-16T22:48:18.057574)
        //Item(id=3, itemNm=테스트 상품2, price=10002, stockNumber=100, itemDetail=테스트 상품 상세 설명2, itemSellStatus=SELL, regTime=2022-11-16T22:48:18.053574, updateTime=2022-11-16T22:48:18.053574)
        //Item(id=2, itemNm=테스트 상품1, price=10001, stockNumber=100, itemDetail=테스트 상품 상세 설명1, itemSellStatus=SELL, regTime=2022-11-16T22:48:18.050572, updateTime=2022-11-16T22:48:18.050572)
        //Item(id=1, itemNm=테스트 상품0, price=10000, stockNumber=100, itemDetail=테스트 상품 상세 설명0, itemSellStatus=SELL, regTime=2022-11-16T22:48:17.947549, updateTime=2022-11-16T22:48:17.947549)
    }

    @Test
    @DisplayName("네이티브 쿼리를 이용한 상품 조회 테스트")
    public void findItemDetailByNative() {
        this.createItemList();
        List<Item> itemList = itemRepository.findItemDetailByNative("테스트 상품 상세 설명");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
        //위에꺼랑 똑같은 코드인데 쿼리문만 상속 받아서 사용함
    }
    @Test
    @DisplayName("Querydsl 조회테스트1")
    public void queryDslTest(){
        this.createItemList();
        JPAQueryFactory queryFactory=new JPAQueryFactory(em);
        //JPAQueryFactory 동적으로 생성 합니다 파라미터로 객체 넣어서 주입!
        QItem qItem=QItem.item;
        JPAQuery<Item> query= queryFactory.selectFrom(qItem).
                where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%"+"테스트 상세 설명"+"%"))
                .orderBy(qItem.price.desc());
        //실질적인 쿼리문을 자바 코르로 사용하는 부분!

        List<Item> itemList=query.fetch();
        //자바 메소드중 하나인 fetch를 이용하여 쿼리 결과를 리스트로 반환한다

        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }
    public void createItemList2() {
        for (int i = 0; i <=5; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item saveItem = itemRepository.save(item);
        }
        for (int i = 6; i <=10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item saveItem = itemRepository.save(item);
        }
    }
    @Test
    @DisplayName("상품 QueryDsl 조회테스트2")
    public void queryDslTest2(){
        this.createItemList2();

        BooleanBuilder booleanBuilder=new BooleanBuilder();
        QItem item=QItem.item;

        String itemDetail="테스트 상품 상세 설명";
        int price=10003;
        String itemSellStart="SELL";//팔기

        booleanBuilder.and(item.itemDetail.like("%"+itemDetail+"%"));
        booleanBuilder.and(item.price.gt(price));

        if(StringUtils.equals(itemSellStart, ItemSellStatus.SELL)){
            booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }
        PageRequest pageable=PageRequest.of(0,5);
        Page<Item> itemPage=itemRepository.findAll((Predicate) booleanBuilder, (org.springframework.data.domain.Pageable) pageable);

        System.out.println("total elements :" + itemPage.getTotalElements());

        List<Item> resultItemList = itemPage.getContent();
        for(Item resultItem : resultItemList){
            System.out.println(resultItem.toString());
        }

    }
}