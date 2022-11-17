package com.ShopProJect.ShopProJect;

import com.ShopProJect.ShopProJect.constant.ItemSellStatus;
import com.ShopProJect.ShopProJect.entity.Item;
import com.ShopProJect.ShopProJect.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ShopProJectApplicationTests {
	@Autowired
	ItemRepository itemRepository;

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
}

