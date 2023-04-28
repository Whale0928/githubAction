package com.example.github.githubaction.member.service;

import com.example.github.githubaction.member.repository.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class MemberServiceTest {

    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member("John", "test@email","");
    }
    @Test
    @DisplayName("회원가입")
    void joinMember() {
        Member member = new Member("Jim", "test@email","");
        Assertions.assertNotNull(member);
    }

    @Test
    @DisplayName("이메일 변경")
    void changeEmail() {
        String email = member.getEmail();
        member.changeEmail("changeEmail@google.com");
        Assertions.assertNotEquals(email, member.getEmail());
    }

    @Test
    @DisplayName("전화번호 변경")
    void changePhone() {
        String phone = member.getPhone();
        member.changePhone("01000001234");
        Assertions.assertNotEquals(phone, member.getPhone());
    }
}