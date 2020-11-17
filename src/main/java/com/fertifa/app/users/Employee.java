package com.fertifa.app.users;

import com.fertifa.app.helpser.ChookiManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class Employee extends HttpServlet {

    @GetMapping(value={"/Employee", "/employee", "/employee/","/Employee/"})
    public ModelAndView employeeLoginPageGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        if(ChookiManager.isUserLogged(request,response,3)){
            String url = "/employee/employee-dashboard";
            modelAndView.setViewName(url);
        }else {

            String url = "/employee/login/employeeloginpage.jsp";
            modelAndView.setViewName(url);
        }
        return modelAndView;
    }
    @PostMapping(value={"/Employee", "/employee", "/employee/","/Employee/"})
    public ModelAndView employeeLoginPagePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/employee");
        return modelAndView;
    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request,response);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if(ChookiManager.isUserLogged(request,response,3)){
//            String url = request.getServletPath()+"/employee-dashboard";
//            response.sendRedirect(url);
//        }else {
//
//            String url = "/employee/login/";
//            response.sendRedirect(url);
//        }
//    }
}
