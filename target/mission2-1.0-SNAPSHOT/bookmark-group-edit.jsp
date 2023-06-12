<%@ page import="com.example.mission2.CommonService" %>
<%@ page import="com.example.mission2.BookMarkVO" %>
<%@ page import="java.util.List" %>
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
    <script>
        function bookMarkUpdate() {
            var bookMarkName = document.getElementById("bookMarkName").value;
            var order = document.getElementById("order").value;
            var bookmarkId = document.getElementById("bookmarkId").value;
            var gubun = "up";

            location.href = "bookMark?bookMarkName="+bookMarkName+"&order="+order+"&gubun="+gubun+"&bookmarkId="+bookmarkId;

        }

    </script>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>북마크 그룹 추가</h1>
<a href="index.jsp">홈</a>|<a href="history">위치 히스토리 목록</a>|<a href="load-wifi">Open API 와이파이 정보 가져오기</a>
|<a href="bookmark-list.jsp">북마크 보기</a>|<a href="bookmark-group.jsp">북마크 그룹 관리</a>
<table id="customers">
        <%
            CommonService commonService = new CommonService();
            String bookmarkId = request.getParameter("bookmarkId");
            List<BookMarkVO> bookMarkList = commonService.dbSelectBookMarkDetail(bookmarkId);
            for (BookMarkVO bookMarkVO : bookMarkList){
        %>
        <input id = "bookmarkId" value="<%=bookMarkVO.getBookmarkId()%>" hidden>
        <tr>
            <th>북마크 이름</th>
            <td><input id="bookMarkName" value="<%=bookMarkVO.getBookMarkName()%>"></td>
        </tr>
        <tr>
            <th>순서</th>
            <td><input id="order" value="<%=bookMarkVO.getORDER()%>"></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <a href="bookmark-group.jsp">돌아가기</a>
                <button onclick="bookMarkUpdate()">수정</button>
            </td>

        </tr>

    <%
        }
    %>
</table>
<br/>
</body>
</html>
