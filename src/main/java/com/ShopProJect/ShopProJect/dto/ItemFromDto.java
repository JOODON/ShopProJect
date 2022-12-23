package com.ShopProJect.ShopProJect.dto;

import com.ShopProJect.ShopProJect.constant.ItemSellStatus;
import com.ShopProJect.ShopProJect.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ItemFromDto {
    private Long id;

    @NotBlank(message = "상품명은 필수 입력값 입니다")
    private String itemNm;

    @NotNull(message = "가격은 필수값 입니다")
    private Integer price;

    @NotBlank(message = "이름은 필수 값 입니다")
    private String itemDetail;

    @NotNull(message = "재고는 필수 가격 입니다")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList=new ArrayList<>();
    //이미지 정보를 저장하는 리스트
    private List<Long> itemImgIds= new ArrayList<>();
    //이미지 아이디를 저장하는 리스트
    private static ModelMapper modelMapper=new ModelMapper();

    public Item createItem(){
        return modelMapper.map(this,Item.class);
        //객체를 복사해서 DTO 객체간의 데이터를 복사해서 반환해주는 메소드
    }
    public static ItemFromDto of(Item item){
        return modelMapper.map(item,ItemFromDto.class);
        //객체를 복사해서 DTO 객체간의 데이터를 복사해서 반환해주는 메소드
    }
}
