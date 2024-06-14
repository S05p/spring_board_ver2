package com.seopseop.board.service.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seopseop.board.Exception.NotExistMember;
import com.seopseop.board.entity.member.Member;
import com.seopseop.board.entity.member.QMember;
import com.seopseop.board.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final JPAQueryFactory queryFactory;

    QMember member = QMember.member;

    @Override
    public void decreasePostCnt(Member member) {
        member.decreasePostCnt();
    }

    @Override
    public void updateMember(Member member, String nickname) {
        member.updateNickname(nickname);
    }

    @Override
    public void passwordChange(Member member, String password) {
        member.passwordChange(password);
    }

    @Override
    public void resign (Member member) {
        member.resign();
    }

    @Override
    public Member findActiveMemberByUsername (String username) {
        Member mem = queryFactory.selectFrom(member)
                .where(member.username.eq(username),
                        member.deletedTrue.eq(false))
                .fetchOne();
        if (mem == null) {
            throw new NotExistMember();
        }
        return mem;
    }
}
