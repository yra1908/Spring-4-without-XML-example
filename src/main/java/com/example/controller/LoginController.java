package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(@RequestParam(value = "error",required = false) String error,
                        ModelMap model) {
        if (error != null) {
            model.addAttribute("error", "true");
        }
        return "login";
    }
    
    /*@RequestMapping(value="/loginFailed", method=RequestMethod.GET)
    public String loginFailed(ModelMap model) {
        model.addAttribute("error", "true");
        return "login";
    }*/
    
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout(ModelMap model) {        
        return "logout";
    }
    
    @RequestMapping(value="/403", method=RequestMethod.GET)
    public String error403Forbiden(ModelMap model) {        
        return "403";
    }

}
