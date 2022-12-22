package com.ShopProJect.ShopProJect.dto;

import com.ShopProJect.ShopProJect.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ItemImgDto {
    private Long id;

    private String imgName;

    private String orImgName;

    private String repImgYn;

    private static ModelMapper modelMapper=new ModelMapper();

    private static ItemImgDto of (ItemImg itemImg){
        return modelMapper.map(itemImg,ItemImgDto.class);
        //Item이미지를 복사해서 객체의 자료형과 멤버 변수의 이름이 같을떄 복사해서 반환함함
   }
}
