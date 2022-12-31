package com.ShopProJect.ShopProJect.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "item_img")
@Getter @Setter
public class ItemImg extends BaseTimeEntity{
    @Id
    @Column(name = "item_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName;
    //이미지 파일명
    private String oriImgName;
    //원본 이미지 파일명
    private String imgUrl;

    private String repimgYn;
    //대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    //상품 엔티티와 다대일 단반향 관계로 매핑
    @JoinColumn(name = "item_id")
    private Item item;

    public void updateItemImg(String oriImgName,String imgName,String imgUrl){
        this.oriImgName=oriImgName;
        this.imgName=imgName;
        this.imgUrl=imgUrl;
    }
    //파리미터로 넘겨서 정보이미지를 업데이트 하는 생성자 생성
}
