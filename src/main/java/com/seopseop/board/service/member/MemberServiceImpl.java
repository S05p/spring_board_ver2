package com.seopseop.board.service.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seopseop.board.DTO.member.MemberSaveDTO;
import com.seopseop.board.Exception.NotExistMember;
import com.seopseop.board.entity.member.Member;
import com.seopseop.board.entity.member.QMember;
import com.seopseop.board.entity.post.QPost;
import com.seopseop.board.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        memberRepository.save(member);
    }

    @Override
    public void resign (Member member) {
        member.resign();
        memberRepository.save(member);
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
    @Override
    public Member signup (MemberSaveDTO memberSaveDTO) {
        Member mem = new Member(memberSaveDTO.getUsername(),memberSaveDTO.getPassword(),memberSaveDTO.getNickname());
        memberRepository.save(mem);
        return mem;
    }

//    @Override
//    public List<Member> test () {
//
//        QMember qMember = QMember.member;
//        QPost qPost = QPost.post;
//
//        List<Member> allMember = queryFactory.selectFrom(qMember)
//                .leftJoin(qMember.written_post,qPost)
//                .limit(200L)
//                .orderBy(qMember.id.desc())
//                .fetch();
//
//        return allMember;
//    }
}
