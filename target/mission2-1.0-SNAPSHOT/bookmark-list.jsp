<%@ page import="com.example.mission2.CommonService" %>
<%@ page import="com.example.mission2.BookMarkVO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: com123
  Date: 2023-06-12
  Time: 오전 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        #customers {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #customers td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #customers tr:nth-child(even){background-color: #f2f2f2;}

        #customers tr:hover {background-color: #ddd;}

        #customers th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #04AA6D;
            color: white;
        }
    </style>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>와이파이 정보 구하기</h1>
<a href="index.jsp">홈</a>|<a href="history">위치 히스토리 목록</a>|<a href="load-wifi">Open API 와이파이 정보 가져오기</a>
|<a href="bookmark-list.jsp">북마크 보기</a>|<a href="bookmark-group.jsp">북마크 그룹 관리</a>
<br>
<table id="customers">
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>와이파이명</th>
        <th>등록일자</th>
        <th>비고</th>
    </tr>
    <tr>
            <%
        CommonService commonService = new CommonService();
        String bookmarkId = request.getParameter("bookmarkId");
        List<BookMarkVO> bookMarkList = commonService.selectBookMarkList();
            for (BookMarkVO bookMarkVO : bookMarkList){
        %>
    <tr>
        <td><%=bookMarkVO.getBookmarkId()%></td>
        <td><%=bookMarkVO.getBookMarkName()%></td>
        <td><%=bookMarkVO.getX_SWIFI_MAIN_NM()%></td>
        <td><%=bookMarkVO.getBookmarkCreateDate()%></td>
        <td><button onclick="location.href = 'bookmark-del.jsp?X_SWIFI_MGR_NO=<%=bookMarkVO.getX_SWIFI_MGR_NO()%>'">삭제</button></td>
    </tr>
    <%
        }
    %>
    </tr>
</table>
</body>
</html>
