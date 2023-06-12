<%@ page import="com.example.mission2.CommonService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mission2.DataVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <link href="common.css" rel="stylesheet" type="text/css">

    <title>와이파이 정보 구하기</title>
</head>
<body>
<br/>
    <h1>와이파이 정보 구하기</h1>
        <a href="index.jsp">홈</a>|<a href="history">위치 히스토리 목록</a>|<a href="load-wifi">Open API 와이파이 정보 가져오기</a>
        |<a href="bookmark-list.jsp">북마크 보기</a>|<a href="bookmark-group.jsp">북마크 그룹 관리</a>
    <br/>
    <p id="demo"></p>
        <a>LAT:</a><input type="text" id="myLat"> , <a>LNT:</a><input type="text" id="myLnt">
        <button onclick="getLocation()">내 위치 가져오기</button>
        <button onclick="getNearWifi()">근처 WIPI 정보 보기</button>
<br/>
<%
    String selectWifiList = (String) request.getAttribute("selectWifiList");
    String myLat = (String) request.getAttribute("myLat");
    String myLnt = (String) request.getAttribute("myLnt");
    List<DataVO> wifiList = new ArrayList<>();
    if(selectWifiList =="ok"){
        CommonService commonService = new CommonService();
        wifiList = commonService.dbSelect(myLat,myLnt);
    }
%>

<table id="customers">
    <tr>
        <th>거리(Km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>

        <%
            if(selectWifiList =="ok"){
            for (DataVO dataVO : wifiList){
        %>
        <tr>
            <td><%=dataVO.getDistance()%></td>
            <td><%=dataVO.getX_SWIFI_MGR_NO()%></td>
            <td><%=dataVO.getX_SWIFI_WRDOFC()%></td>
            <td><a href="detail.jsp?X_SWIFI_MGR_NO=<%=dataVO.getX_SWIFI_MGR_NO()%>&myLat=<%=myLat%>&myLnt=<%=myLnt%>"><%=dataVO.getX_SWIFI_MAIN_NM()%></a></td>
            <td><%=dataVO.getX_SWIFI_ADRES1()%></td>
            <td><%=dataVO.getX_SWIFI_ADRES2()%></td>
            <td><%=dataVO.getX_SWIFI_INSTL_FLOOR()%></td>
            <td><%=dataVO.getX_SWIFI_INSTL_TY()%></td>
            <td><%=dataVO.getX_SWIFI_INSTL_MBY()%></td>
            <td><%=dataVO.getX_SWIFI_SVC_SE()%></td>
            <td><%=dataVO.getX_SWIFI_CMCWR()%></td>
            <td><%=dataVO.getX_SWIFI_CNSTC_YEAR()%></td>
            <td><%=dataVO.getX_SWIFI_INOUT_DOOR()%></td>
            <td><%=dataVO.getX_SWIFI_REMARS3()%></td>
            <td> <%=dataVO.getLAT()%></td>
            <td> <%=dataVO.getLNT()%></td>
            <td> <%=dataVO.getWORK_DTTM()%></td>
        </tr>
        <%
            }
            }else{
        %>
        <tr>
            <td align="center" colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
        </tr>
        <%
            }
        %>

</table>


<script>
    function getNearWifi() {
        var myLat = document.getElementById("myLat").value
        var myLnt = document.getElementById("myLnt").value

        if(myLat =="" || myLnt==""){
            alert("위도와 경도를 입력해주세요");
        }else{
            location.href = "getNearWifi?myLat="+myLat+"&myLnt="+myLnt;
        }
    }

    function getLocation(){
        if ("geolocation" in navigator) {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(showPosition);
            } else {
                alert("오류입니다");
            }
        }else{

        }
    }

    function showPosition(position) {
        try {
            let x = document.getElementById("demo");
            document.getElementById("myLat").value = position.coords.latitude;
            document.getElementById("myLnt").value = position.coords.longitude;
        }catch (e){
            alert(e);
        }

    }

</script>

</body>
</html>