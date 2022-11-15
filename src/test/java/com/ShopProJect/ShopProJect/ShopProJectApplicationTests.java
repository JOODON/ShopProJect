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
		for (int i=0; i<10; i++){
		Item item=new Item();
		item.setItemNm("테스트 상품"+i);
		item.setPrice(10000+i);
		item.setItemDetail("테스트 상품 상세 설명"+i);
		item.setItemSellStatus(ItemSellStatus.SELL);
		item.setStockNumber(100);
		item.setRegTime(LocalDateTime.now());
		item.setUpdateTime(LocalDateTime.now());
		Item saveItem=itemRepository.save(item);
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
			List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1","테스트 상품 상세 설명5");
			for (Item item : itemList) {
				System.out.println(item.toString());
			}
			//Item(id=2, itemNm=테스트 상품1, price=10001, stockNumber=100, itemDetail=테스트 상품 상세 설명1, itemSellStatus=SELL, regTime=2022-11-15T21:37:12.914731, updateTime=2022-11-15T21:37:12.914731)
			//Item(id=6, itemNm=테스트 상품5, price=10005, stockNumber=100, itemDetail=테스트 상품 상세 설명5, itemSellStatus=SELL, regTime=2022-11-15T21:37:12.924715, updateTime=2022-11-15T21:37:12.924715)
	}
}
