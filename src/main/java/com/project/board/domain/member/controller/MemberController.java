package com.project.board.domain.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/logoutHandler")
    public String logout(){
        return "logout";
    }
}
