package com.example.bookmanagement.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {
    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("qqq", "adwqdwqddqwaa");
        return "index";
    }
}
