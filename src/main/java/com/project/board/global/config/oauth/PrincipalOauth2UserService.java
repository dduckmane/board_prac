package com.project.board.global.config.oauth;

import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import com.project.board.domain.member.repository.MemberRepository;
import com.project.board.domain.member.repository.SearchInfoRepository;
import com.project.board.global.config.auth.PrincipalDetails;
import com.project.board.global.config.provider.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    private final BCryptPasswordEncoder encoder;
    private final MemberRepository memberRepository;
    private final SearchInfoRepository searchInfoRepository;
    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Oauth2UserInfo oauth2UserInfo=null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            log.info("구글 로그인 요청");
            oauth2UserInfo=new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
            log.info("페이스북 로그인 요청");
            oauth2UserInfo=new FaceBookUserInfo(oAuth2User.getAttributes());
        }else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            log.info("네이버 로그인 요청");
            oauth2UserInfo=new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
        }else if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
            log.info("카카오 로그인 요청");
            oauth2UserInfo=new KakaoUserInfo(oAuth2User.getAttributes());
        }else {
            log.info("우리는 구글과 네이버와 페이스북만 지원해요");
        }


        String provider = oauth2UserInfo.getProvider();
        String providerId = oauth2UserInfo.getProviderId();
        String username=provider+"_"+providerId;
        String password= encoder.encode("겟인데어");
        String email = oauth2UserInfo.getEmail();
        String role="ROLE_USER";
        String name=oauth2UserInfo.getName();

        Member member = memberRepository.findByUsername(username).orElse(null);

        if(member==null){
            member = Member.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .name(name)
                    .build();
            memberRepository.save(member);

            SearchInfo searchInfo = new SearchInfo(member);
            searchInfoRepository.save(searchInfo);

        }else {
            log.info("로그인을 이미 한적이 있습니다. 당신은 자동회원가입이 되어있습니다.");
        }

        return new PrincipalDetails(member,oAuth2User.getAttributes());

    }


}
