package com.seopseop.board.service.member;

import com.seopseop.board.DTO.member.MemberSaveDTO;
import com.seopseop.board.entity.member.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberService {

    void decreasePostCnt (Member member);

    void updateMember(Member member, String nickname);

    void passwordChange(Member member, String password);

    void resign(Member member);

    Member findActiveMemberByUsername(String username);

    Member signup(MemberSaveDTO memberSaveDTO);

    // querydsl leftjoin 성능 테스트
//    List<Member> test ();
}
