package com.ShopProJect.ShopProJect.repository;

import com.ShopProJect.ShopProJect.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByEmail(String email);
    //중복 검사를 위해 이메일이 있는지 확인!
}
