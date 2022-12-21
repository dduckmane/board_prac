package com.project.board.global.config.auth;

import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    //form에서 username으로 와야 받아진다.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElseGet(()->new Member("optional"));
        if(member.getId()!=null){
            return new PrincipalDetails(member);
        }else {
            //없다는 의미가 회원가입이 안되어 있다는 의미이다.
            //여기다가 로그인 실패시 로직을 짜거나 하면 된다.
            return null;
        }
    }
}
