package com.fertifa.app.company;

import com.fertifa.app.helpser.ChookiManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class Employer extends HttpServlet implements Servlet {

    @GetMapping(value={"/employer","/Employer","/employer/","/Employer/"})
    public ModelAndView employeeLoginPageGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        if(ChookiManager.isUserLogged(request,response,3)){
            String url = "/employer/employer-dashboard/dashboard.jsp";
            modelAndView.setViewName(url);
        }else {
            String url = "/employer/login/employerloginpage.jsp";
            modelAndView.setViewName(url);
        }
        return modelAndView;
    }

    @PostMapping(value={"/employer","/Employer","/employer/","/Employer/"})
    public ModelAndView employeeLoginPagePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employer");
        return modelAndView;
    }

//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request,response);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if(ChookiManager.isUserLogged(request,response,2)){
//            String url = request.getServletPath()+"/employer-dashboard";
//            response.sendRedirect(url);
//        }else {
//
//            String url = "/employer/login";
//            response.sendRedirect(url);
//        }
//    }
}
