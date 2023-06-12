<%@ page import="com.example.mission2.CommonService" %>
<%@ page import="com.example.mission2.DataVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mission2.BookMarkVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="common.css" rel="stylesheet" type="text/css">
    <script>
        function getSelect() {
            var select = document.getElementById('bookmark');
            var option = select.options[select.selectedIndex].value;
            document.getElementById('selectedBookmark').value = option;
        }

        function addBookMark(){
            var bookmarkId = document.getElementById('selectedBookmark').value
            var X_SWIFI_MGR_NO = document.getElementById('X_SWIFI_MGR_NO').value;
            location.href ='bookMark?bookmarkId='+bookmarkId+'&gubun=addBookMark'+'&X_SWIFI_MGR_NO='+X_SWIFI_MGR_NO;
        }
    </script>

    <title>상세</title>
</head>
<body>
<h1>와이파이 정보 구하기</h1>
<a href="index.jsp">홈</a>|<a href="history">위치 히스토리 목록</a>|<a href="load-wifi">Open API 와이파이 정보 가져오기</a>
|<a href="bookmark-list.jsp">북마크 보기</a>|<a href="bookmark-group.jsp">북마크 그룹 관리</a>
<br/>
<%
    CommonService commonService = new CommonService();
    List<BookMarkVO> bookMarkList =  commonService.dbBookMarkSelect();
%>
<select id = "bookmark" onchange="getSelect()">
    <option>북마크 그룹 이름 선택</option>
    <%
        for (BookMarkVO bookMarkVO : bookMarkList){
    %>
    <option value="<%=bookMarkVO.getBookmarkId()%>"><%=bookMarkVO.getBookMarkName()%></option>
    <%
        }
    %>
</select>
<input id = "selectedBookmark" type="text" hidden>
<button onclick="addBookMark()">북마크 추가하기</button>


<%
    String X_SWIFI_MGR_NO = request.getParameter("X_SWIFI_MGR_NO");
    String myLat =  request.getParameter("myLat");
    String myLnt = request.getParameter("myLnt");
    List<DataVO> wifiList =  commonService.dbSelectDetail(X_SWIFI_MGR_NO,myLat,myLnt);
%>
<table id="customers">
    <%

        for (DataVO dataVO : wifiList){
    %>
    <tr>
        <th>거리(Km)</th>
        <td><%=dataVO.getDistance()%></td>
    </tr>
    <tr>
        <th>관리번호</th>
        <input id ="X_SWIFI_MGR_NO" type="text" value="<%=dataVO.getX_SWIFI_MGR_NO()%>" hidden>
        <td><%=dataVO.getX_SWIFI_MGR_NO()%></td>
    </tr>
    <tr>
        <th>자치구</th>
        <td><%=dataVO.getX_SWIFI_WRDOFC()%></td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td><%=dataVO.getX_SWIFI_MAIN_NM()%></td>
    </tr>
    <tr>
        <th>도로명주소</th>
        <td><%=dataVO.getX_SWIFI_ADRES1()%></td>
    </tr>
    <tr>
        <th>상세주소</th>
        <td><%=dataVO.getX_SWIFI_ADRES2()%></td>
    </tr>
    <tr>
        <th>설치위치(층)</th>
        <td><%=dataVO.getX_SWIFI_INSTL_FLOOR()%></td>
    </tr>
    <tr>
        <th>설치유형</th>
        <td><%=dataVO.getX_SWIFI_INSTL_TY()%></td>
    </tr>
    <tr>
        <th>설치기관</th>
        <td><%=dataVO.getX_SWIFI_INSTL_MBY()%></td>
    </tr>
    <tr>
        <th>서비스구분</th>
        <td><%=dataVO.getX_SWIFI_SVC_SE()%></td>
    </tr>
    <tr>
        <th>망종류</th>
        <td><%=dataVO.getX_SWIFI_CMCWR()%></td>
    </tr>
    <tr>
        <th>설치년도</th>
        <td><%=dataVO.getX_SWIFI_CNSTC_YEAR()%></td>
    </tr>
    <tr>
        <th>실내외구분</th>
        <td><%=dataVO.getX_SWIFI_INOUT_DOOR()%></td>
    </tr>
    <tr>
        <th>WIFI접속환경</th>
        <td><%=dataVO.getX_SWIFI_REMARS3()%></td>
    </tr>
    <tr>
        <th>X좌표</th>
        <td> <%=dataVO.getLAT()%></td>
    </tr>
    <tr>
        <th>Y좌표</th>
        <td> <%=dataVO.getLNT()%></td>
    </tr>
    <tr>
        <th>작업일자</th>
        <td> <%=dataVO.getWORK_DTTM()%></td>
    </tr>

    <%
        }
    %>

</table>
</body>
</html>
