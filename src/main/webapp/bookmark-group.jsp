<%@ page import="com.example.mission2.CommonService" %>
<%@ page import="com.example.mission2.BookMarkVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="common.css" rel="stylesheet" type="text/css">
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>와이파이 정보 구하기</h1>
<a href="index.jsp">홈</a>|<a href="history">위치 히스토리 목록</a>|<a href="load-wifi">Open API 와이파이 정보 가져오기</a>
|<a href="bookmark-list.jsp">북마크 보기</a>|<a href="bookmark-group.jsp">북마크 그룹 관리</a>
<br>
<button onclick="location.href='bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>
<table id="customers">
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>순서</th>
        <th>등록일자</th>
        <th>수정일자</th>
        <th>비고</th>
    </tr>
    <tr>
            <%
        CommonService commonService = new CommonService();
        List<BookMarkVO> bookMarkList = commonService.dbBookMarkSelect();
            for (BookMarkVO bookMarkVO : bookMarkList){
        %>
    <tr>
        <td><%=bookMarkVO.getBookmarkId()%></td>
        <td><%=bookMarkVO.getBookMarkName()%></td>
        <td><%=bookMarkVO.getORDER()%></td>
        <td><%=bookMarkVO.getCreateDate()%></td>
    <%
    if(bookMarkVO.getUpdateDate() == null){
    %>
        <td></td>
    <%
        }else{
    %>
    <td><%=bookMarkVO.getUpdateDate()%></td>
    <%
        }
    %>
        <td><button onclick="location.href = 'bookmark-group-edit.jsp?bookmarkId=<%=bookMarkVO.getBookmarkId()%>'">수정</button>
            <button onclick="location.href = 'bookMark?bookmarkId=<%=bookMarkVO.getBookmarkId()%>'+'&gubun=de'">삭제</button></td>
    </tr>
    <%
        }
    %>
    </tr>
</table>
</body>
</html>
