package com.fertifa.app.adminSide.communication.constances;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class PopupResponseMessage extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, java.io.IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet NewServlet</title>");
        out.println("</head>");
        out.println("<body onLoad=\"myFunction();\">");
        out.println("<script>");
        out.println("function myFunction(){");
        out.println("alert(\"you have a call\")");
        out.println("}");
        out.println("</script>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
} //end doGet

