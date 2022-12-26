package com.project.board.domain.member.controller;

import com.project.board.domain.member.service.MemberService;
import com.project.board.global.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/user/withdrawal")
    public String withdrawal(@AuthenticationPrincipal PrincipalDetails principalDetails){
        memberService.withdrawal(principalDetails.getMember());

        return "redirect:/logout";
    }
}
