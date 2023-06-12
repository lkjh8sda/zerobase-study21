
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
        function insertBookMark() {
            var bookMarkName = document.getElementById("bookMarkName").value;
            var order = document.getElementById("order").value;
            var gubun = "in";
            location.href = "bookMark?bookMarkName="+bookMarkName+"&order="+order+"&gubun="+gubun;
        }

    </script>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>북마크 그룹 추가</h1>
<a href="index.jsp">홈</a>|<a href="history">위치 히스토리 목록</a>|<a href="load-wifi">Open API 와이파이 정보 가져오기</a>
|<a href="bookmark-list.jsp">북마크 보기</a>|<a href="bookmark-group.jsp">북마크 그룹 관리</a>
<table id="customers">
        <tr>
            <th>북마크 이름</th>
            <td><input type="text" id="bookMarkName" ></td>
        </tr>
        <tr>
            <th>순서</th>
            <td><input type="text" id="order"></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><button onclick="insertBookMark()">추가</button></td>
        </tr>

</table>
<br/>
</body>
</html>
