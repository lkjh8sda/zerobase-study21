package com.example.mission2;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "getNearWifi", value = "/getNearWifi")
public class WifiServlet extends HttpServlet {
    private String message;

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        CommonService commonService = new CommonService();
        String myLat = request.getParameter("myLat");
        String myLnt = request.getParameter("myLnt");

        if(myLat != null && myLnt != null){
            commonService.dbHistoryInsert(myLat,myLnt);
        }

        request.setAttribute("selectWifiList","ok");
        request.setAttribute("myLat",myLat);
        request.setAttribute("myLnt",myLnt);
        request.getRequestDispatcher("index.jsp").forward(request,response);


    }

    public void destroy() {
    }
}