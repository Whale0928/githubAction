package com.example.github.githubaction.member.service;

import com.example.github.githubaction.member.repository.Member;

public class MemberService {


    public Member joinMember(String name, String email, String phone) {
        return new Member(name, email, phone);
    }

    void changeEmail(Member member,String email) {
        member.changeEmail(email);
    }

    void changePhone(Member member,String phone) {
        member.changePhone(phone);
    }
}
