package com.ShopProJect.ShopProJect.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass//공통 매핑정보가 필요할떄 사용하는 어노테이션으로 부모클래스를 상속 받는 자식 클래스에 매핑정보만 제공
@Getter @Setter
public abstract class BaseTimeEntity {

    @CreatedDate//시간 자동 저장
    @Column(updatable = false)
    private LocalDateTime regTime;

    @LastModifiedDate
    private LocalDateTime updateTime;
}
