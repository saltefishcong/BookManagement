package com.example.bookmanagement.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class defaultController {

    @RequestMapping("/index")
    public String findIndex(){
       return "index";
    }

    @RequestMapping(value = "/AdministratorLogin")
    public String findAdministratorLogin(){
        return "/Administrator/login";
    }

    @RequestMapping(value = "/administratorAdd")
    public String administratorAdd(){
         return "/Administrator/register";
    }

    @RequestMapping(value = "/findUserLogin")
    public String findUserLogin(){
        return "User/login";
    }

    @RequestMapping(value = "/findAdministratorIndex")
    public String findAdministratorIndex(){
       return "/Administrator/index.html";
    }
}
