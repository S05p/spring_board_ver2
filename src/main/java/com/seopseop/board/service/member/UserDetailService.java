package com.seopseop.board.service.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.seopseop.board.entity.JpaBaseTimeEntity;
import com.seopseop.board.entity.member.Member;
import com.seopseop.board.entity.member.QMember;
import com.seopseop.board.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.NonUniqueObjectException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final JPAQueryFactory queryFactory;

    QMember member = QMember.member;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member result = queryFactory.selectFrom(member)
                .where(member.username.eq(username),
                        member.deletedTrue.eq(false))
                .fetchOne();

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("일반유저"));

        return new User(username,result.getPassword(),authorities);
    }
}
