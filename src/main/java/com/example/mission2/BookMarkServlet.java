package com.example.mission2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookMark")
public class BookMarkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookMarkName = req.getParameter("bookMarkName");
        String order = req.getParameter("order");
        String bookmarkId = req.getParameter("bookmarkId");
        String gubun =  req.getParameter("gubun");
        String X_SWIFI_MGR_NO = req.getParameter("X_SWIFI_MGR_NO");

        CommonService commonService = new CommonService();
        if("up".equals(gubun)){
            commonService.dbBookMarkUpdate(bookmarkId,bookMarkName,order);
            req.getRequestDispatcher("/bookmark-group.jsp").forward(req,resp);
        }else if("in".equals(gubun)){
            commonService.dbBookMarkInsert(bookMarkName,order);
            req.getRequestDispatcher("/bookmark-group.jsp").forward(req,resp);
        }else if("sd".equals(gubun)){
            commonService.dbSelectBookMarkDetail(bookmarkId);
        }else if("de".equals(gubun)){
            commonService.dbBookMarkDelete(bookmarkId);
            req.getRequestDispatcher("/bookmark-group.jsp").forward(req,resp);
        }else if("addBookMark".equals(gubun)){
            commonService.addBookMark(bookmarkId,X_SWIFI_MGR_NO);
            req.getRequestDispatcher("/bookmark-list.jsp").forward(req,resp);
        }else if("deList".equals(gubun)){
            commonService.removeBookMark(X_SWIFI_MGR_NO);
            req.getRequestDispatcher("/bookmark-list.jsp").forward(req,resp);
        }

       // req.getRequestDispatcher("/bookmark-group-add.jsp").forward(req,resp);
    }
}
