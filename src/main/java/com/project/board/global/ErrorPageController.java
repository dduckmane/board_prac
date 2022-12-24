package com.project.board.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
@Controller
public class ErrorPageController {
    @RequestMapping("/error-page/404")
    public String errorPage404(HttpServletRequest request, HttpServletResponse
            response) {
        log.info("errorPage 404");
        return "error-page/404";
    }
    @RequestMapping("/error-page/500")
    public String errorPage500(HttpServletRequest request, HttpServletResponse
            response, BindingResult bindingResult, Model model) {
        log.info("errorPage 500");
        model.addAttribute("errors", bindingResult.getAllErrors());
        return "error-page/500";
    }
}
