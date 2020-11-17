package com.fertifa.app.startcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartApp {

    @GetMapping("/")
    public ModelAndView startApp(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/splash.jsp");
        return modelAndView;
    }
}

