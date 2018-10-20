package com.example.bookmanagement.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class defaultController {

    @RequestMapping("/index")
    public String findIndex(){
       return "index";
    }

    @RequestMapping("/AdministratorLogin")
    public String findAdministratorLogin(){
        return "/Administrator/login";
    }
}
