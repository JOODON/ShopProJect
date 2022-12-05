package com.ShopProJect.ShopProJect.entitiy;

import com.ShopProJect.ShopProJect.dto.MemberFromDto;
import com.ShopProJect.ShopProJect.entity.Cart;
import com.ShopProJect.ShopProJect.entity.Member;
import com.ShopProJect.ShopProJect.repository.CartRepository;
import com.ShopProJect.ShopProJect.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class CartTest {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

    public Member createMember(){
        //회원가입 엔티티 생성
        MemberFromDto memberFromDto=new MemberFromDto();
        memberFromDto.setEmail("test@email.com");
        memberFromDto.setName("주동호");
        memberFromDto.setAddress("인천시 주안동");
        memberFromDto.setPassword("1234");
        return Member.createMember(memberFromDto,passwordEncoder);
    }
    @Test
    @DisplayName("장바구니 회원 엔티티 매핑 조회 테스트")
    public void findCartAndMemberTest(){
        Member member = createMember();
        memberRepository.save(member);

        Cart cart= new Cart();
        cart.setMember(member);
        //Cart멤버 부분에 Member를 넣어주는 부분
        cartRepository.save(cart);

        em.flush();//저장후 트랙잭션이 끝날떄 flush를 호출하여 데이터 베이스의 반영
        em.clear();//장바구니 엔티티를 가지고 올떄 회원 엔티티를 같이 가지고 오는지 보기 위해

        Cart savedCart = cartRepository.findById(cart.getId()).orElseThrow(EntityExistsException::new);
        //지정된 장바구니 엔티티를 조회함
        assertEquals(savedCart.getMember().getId(),member.getId());
        //처음에 저장한 멤버의 아이디와 카트에 저장된 멤버 아이디를 비교
    }
}
