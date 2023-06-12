package com.example.mission2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String id = request.getParameter("id");
        if(id != null && !id.isEmpty()){
            CommonService commonService = new CommonService();
            commonService.dbHistoryDelete(id);
        }

        request.getRequestDispatcher("history.jsp").forward(request,response);
    }
}
