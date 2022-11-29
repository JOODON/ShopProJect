package com.ShopProJect.ShopProJect.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

//유효성 검사 라이브러리?
@Getter @Setter
public class MemberFromDto {
    @NotBlank(message = "이름은 필수 입력 값 입니다")//0 또는 "" 문자열인지 검사
    private String name;

    @NotEmpty(message = "이메일은 필수 입력 값 입니다")//문자열의 길이 0인지 검사
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값 입니다")//문자열의 길이 0인지 검사
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상 16자 이하로 입력해주세요")
    private String password;
    @NotEmpty(message = "주소는 필수 입력 값 입니다.")
    private String address;
}
