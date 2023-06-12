<%@ page import="com.example.mission2.DataVO" %>
<%@ page import="com.example.mission2.CommonService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="common.css" rel="stylesheet" type="text/css">
    <title>위치 히스토리 목록</title>
</head>
<body>
<h1>위치 히스토리 목록</h1>
<a href="index.jsp">홈</a>|<a href="history">위치 히스토리 목록</a>|<a href="load-wifi">Open API 와이파이 정보 가져오기</a>
|<a href="bookmark-list.jsp">북마크 보기</a>|<a href="bookmark-group.jsp">북마크 그룹 관리</a>
<br/>
<table id="customers">
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
        <%
        CommonService commonService = new CommonService();
        List<DataVO> historyList = commonService.dbHistorySelect();
            for (DataVO dataVO : historyList){
        %>
    <tr>
        <td><%=dataVO.getID()%></td>
        <td><%=dataVO.getLAT()%></td>
        <td><%=dataVO.getLNT()%></td>
        <td><%=dataVO.getWORK_DTTM()%></td>
        <td><button onclick="historyDelete(<%=dataVO.getID()%>)">삭제</button></td>
    </tr>
        <%
            }
        %>
</table>
</body>
</html>
