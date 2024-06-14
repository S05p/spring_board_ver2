package com.seopseop.board.service.member;

import com.seopseop.board.entity.member.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberService {

    void decreasePostCnt (Member member);

    void updateMember(Member member, String nickname);

    void passwordChange(Member member, String password);

    void resign(Member member);

    Member findActiveMemberByUsername(String username);
}
