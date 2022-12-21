package com.project.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String test(){
        return "index";
    }
    @GetMapping("/login")
    public String loginForm(){
        return "loginForm";
    }
}
