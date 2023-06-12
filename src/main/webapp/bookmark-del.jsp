<%@ page import="com.example.mission2.CommonService" %>
<%@ page import="com.example.mission2.BookMarkVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="common.css" rel="stylesheet" type="text/css">
    <script>
        function bookMarkDelete() {
            var X_SWIFI_MGR_NO = document.getElementById("X_SWIFI_MGR_NO").value;
            var gubun = "deList";

            location.href = 'bookMark?X_SWIFI_MGR_NO='+X_SWIFI_MGR_NO+'&gubun='+gubun;

        }

    </script>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>북마크 삭제</h1>
<a href="index.jsp">홈</a>|<a href="history">위치 히스토리 목록</a>|<a href="load-wifi">Open API 와이파이 정보 가져오기</a>
|<a href="bookmark-list.jsp">북마크 보기</a>|<a href="bookmark-group.jsp">북마크 그룹 관리</a>
<br>
북마크를 삭제하시겠습니까?
<table id="customers">
    <%
        CommonService commonService = new CommonService();
        String X_SWIFI_MGR_NO = request.getParameter("X_SWIFI_MGR_NO");
        List<BookMarkVO> bookMarkList = commonService.selectBookMarkListDetail(X_SWIFI_MGR_NO);
        for (BookMarkVO bookMarkVO : bookMarkList){
    %>
    <input id = "bookmarkId" value="<%=bookMarkVO.getBookmarkId()%>" hidden>
    <input id = "X_SWIFI_MGR_NO" value="<%=bookMarkVO.getX_SWIFI_MGR_NO()%>" hidden>
    <tr>
        <th>북마크 이름</th>
        <td><%=bookMarkVO.getBookMarkName()%></td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td><%=bookMarkVO.getX_SWIFI_MAIN_NM()%></td>
    </tr>
    <tr>
        <th>등록일자</th>
        <td><%=bookMarkVO.getBookmarkCreateDate()%></td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <a href="bookmark-list.jsp">돌아가기</a>
            <button onclick="bookMarkDelete()">삭제</button>
        </td>

    </tr>

    <%
        }
    %>
</table>
<br/>
</body>
</html>
