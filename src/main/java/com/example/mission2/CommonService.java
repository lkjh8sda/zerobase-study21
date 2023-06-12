package com.example.mission2;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommonService {

    public List<DataVO> dbSelect(String myLat, String myLnt){

        List<DataVO> dataList = new ArrayList<>();

        Connection con = null;
        // 쿼리에서 ?를 쓰고 밑에 setString로 변수값 넣어줌
        PreparedStatement statement = null;
        ResultSet rs  = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\SpringStudy\\mission2\\mission2.sqlite");

            String sql = " select round(SQRT(POWER(? - LAT, 2) + POWER(? - LNT, 2)), 4)as distance, *"+
                        " from tb_wifi "+
                        " order by distance asc "+
                        " limit 20; ";

            statement = con.prepareStatement(sql);
            statement.setString(1, myLat);
            statement.setString(2, myLnt);
            rs = statement.executeQuery();

            while (rs.next()){
                String X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
                String distance = rs.getString("distance");
                String X_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_ADRES1 = rs.getString("X_SWIFI_ADRES1");
                String X_SWIFI_ADRES2 = rs.getString("X_SWIFI_ADRES2");
                String X_SWIFI_INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
                String X_SWIFI_INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
                String X_SWIFI_INSTL_MBY = rs.getString("X_SWIFI_INSTL_MBY");
                String X_SWIFI_SVC_SE = rs.getString("X_SWIFI_SVC_SE");
                String X_SWIFI_CMCWR = rs.getString("X_SWIFI_CMCWR");
                String X_SWIFI_CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
                String X_SWIFI_INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
                String X_SWIFI_REMARS3 = rs.getString("X_SWIFI_REMARS3");
                String LAT = rs.getString("LAT");
                String LNT = rs.getString("LNT");
                String WORK_DTTM = rs.getString("WORK_DTTM");

                DataVO dataVO = new DataVO();
                dataVO.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);
                dataVO.setX_SWIFI_WRDOFC(X_SWIFI_WRDOFC);
                dataVO.setDistance(distance);
                dataVO.setX_SWIFI_MAIN_NM(X_SWIFI_MAIN_NM);
                dataVO.setX_SWIFI_ADRES1(X_SWIFI_ADRES1);
                dataVO.setX_SWIFI_ADRES2(X_SWIFI_ADRES2);
                dataVO.setX_SWIFI_INSTL_FLOOR(X_SWIFI_INSTL_FLOOR);
                dataVO.setX_SWIFI_INSTL_TY(X_SWIFI_INSTL_TY);
                dataVO.setX_SWIFI_INSTL_MBY(X_SWIFI_INSTL_MBY);
                dataVO.setX_SWIFI_SVC_SE(X_SWIFI_SVC_SE);
                dataVO.setX_SWIFI_CMCWR(X_SWIFI_CMCWR);
                dataVO.setX_SWIFI_CNSTC_YEAR(X_SWIFI_CNSTC_YEAR);
                dataVO.setX_SWIFI_INOUT_DOOR(X_SWIFI_INOUT_DOOR);
                dataVO.setX_SWIFI_REMARS3(X_SWIFI_REMARS3);
                dataVO.setLAT(LAT);
                dataVO.setLNT(LNT);
                dataVO.setWORK_DTTM(WORK_DTTM);

                dataList.add(dataVO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if(!rs.isClosed()){
                    rs.close();
                }
                if(!statement.isClosed()){
                    statement.close();
                }
                if(!con.isClosed()){
                    con.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public List<DataVO> dbSelectDetail(String X_SWIFI_MGR_NO,String myLat,String myLnt){

        List<DataVO> dataList = new ArrayList<>();

        Connection con = null;
        // 쿼리에서 ?를 쓰고 밑에 setString로 변수값 넣어줌
        PreparedStatement statement = null;
        ResultSet rs  = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\SpringStudy\\mission2\\mission2.sqlite");

            String sql = "select round(SQRT(POWER(? - LAT, 2) + POWER(? - LNT, 2)), 4)as distance, *"+
                    " from tb_wifi "+
                    " where X_SWIFI_MGR_NO = ?;";

            statement = con.prepareStatement(sql);

            statement.setString(1, myLat);
            statement.setString(2, myLnt);
            statement.setString(3, X_SWIFI_MGR_NO);
            rs = statement.executeQuery();

            while (rs.next()){
                //X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
                String X_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
                String distance = rs.getString("distance");
                String X_SWIFI_ADRES1 = rs.getString("X_SWIFI_ADRES1");
                String X_SWIFI_ADRES2 = rs.getString("X_SWIFI_ADRES2");
                String X_SWIFI_INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
                String X_SWIFI_INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
                String X_SWIFI_INSTL_MBY = rs.getString("X_SWIFI_INSTL_MBY");
                String X_SWIFI_SVC_SE = rs.getString("X_SWIFI_SVC_SE");
                String X_SWIFI_CMCWR = rs.getString("X_SWIFI_CMCWR");
                String X_SWIFI_CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
                String X_SWIFI_INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
                String X_SWIFI_REMARS3 = rs.getString("X_SWIFI_REMARS3");
                String LAT = rs.getString("LAT");
                String LNT = rs.getString("LNT");
                String WORK_DTTM = rs.getString("WORK_DTTM");

                DataVO dataVO = new DataVO();
                dataVO.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);
                dataVO.setX_SWIFI_WRDOFC(X_SWIFI_WRDOFC);
                dataVO.setDistance(distance);
                dataVO.setX_SWIFI_MAIN_NM(X_SWIFI_MAIN_NM);
                dataVO.setX_SWIFI_ADRES1(X_SWIFI_ADRES1);
                dataVO.setX_SWIFI_ADRES2(X_SWIFI_ADRES2);
                dataVO.setX_SWIFI_INSTL_FLOOR(X_SWIFI_INSTL_FLOOR);
                dataVO.setX_SWIFI_INSTL_TY(X_SWIFI_INSTL_TY);
                dataVO.setX_SWIFI_INSTL_MBY(X_SWIFI_INSTL_MBY);
                dataVO.setX_SWIFI_SVC_SE(X_SWIFI_SVC_SE);
                dataVO.setX_SWIFI_CMCWR(X_SWIFI_CMCWR);
                dataVO.setX_SWIFI_CNSTC_YEAR(X_SWIFI_CNSTC_YEAR);
                dataVO.setX_SWIFI_INOUT_DOOR(X_SWIFI_INOUT_DOOR);
                dataVO.setX_SWIFI_REMARS3(X_SWIFI_REMARS3);
                dataVO.setLAT(LAT);
                dataVO.setLNT(LNT);
                dataVO.setWORK_DTTM(WORK_DTTM);

                dataList.add(dataVO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if(!rs.isClosed()){
                    rs.close();
                }
                if(!statement.isClosed()){
                    statement.close();
                }
                if(!con.isClosed()){
                    con.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public void dbInsert(int start, int end){
        Connection con = null;
        // 쿼리에서 ?를 쓰고 밑에 setString로 변수값 넣어줌
        PreparedStatement statement = null;
        ResultSet rs  = null;
        String result = "";
        int affected = 0;
        String sql = "";

        try {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
            /*URL*/
            urlBuilder.append("/" + URLEncoder.encode("6c777642526c6b6a3837417a75704b","UTF-8") );
            urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") );
            urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(start),"UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(end),"UTF-8"));

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/xml");

            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\SpringStudy\\mission2\\mission2.sqlite");

            BufferedReader bf;

            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            } else {
                bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            }
            result = bf.readLine();

            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
            JsonObject jsonObject2 = jsonObject.getAsJsonObject("TbPublicWifiInfo");


            JsonArray jsonArray = (JsonArray) jsonObject2.get("row");


            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject ob = (JsonObject) jsonArray.get(i);
                String X_SWIFI_MGR_NO = String.valueOf(ob.get("X_SWIFI_MGR_NO")).replaceAll("\"", "");
                String X_SWIFI_WRDOFC = String.valueOf(ob.get("X_SWIFI_WRDOFC")).replaceAll("\"", "");
                String X_SWIFI_MAIN_NM = String.valueOf(ob.get("X_SWIFI_MAIN_NM")).replaceAll("\"", "");
                String X_SWIFI_ADRES1 = String.valueOf(ob.get("X_SWIFI_ADRES1")).replaceAll("\"", "");
                String X_SWIFI_ADRES2 = String.valueOf(ob.get("X_SWIFI_ADRES2")).replaceAll("\"", "");
                String X_SWIFI_INSTL_FLOOR = String.valueOf(ob.get("X_SWIFI_INSTL_FLOOR")).replaceAll("\"", "");
                String X_SWIFI_INSTL_TY = String.valueOf(ob.get("X_SWIFI_INSTL_TY")).replaceAll("\"", "");
                String X_SWIFI_INSTL_MBY = String.valueOf(ob.get("X_SWIFI_INSTL_MBY")).replaceAll("\"", "");
                String X_SWIFI_SVC_SE = String.valueOf(ob.get("X_SWIFI_SVC_SE")).replaceAll("\"", "");
                String X_SWIFI_CMCWR = String.valueOf(ob.get("X_SWIFI_CMCWR")).replaceAll("\"", "");
                String X_SWIFI_CNSTC_YEAR = String.valueOf(ob.get("X_SWIFI_CNSTC_YEAR")).replaceAll("\"", "");
                String X_SWIFI_INOUT_DOOR = String.valueOf(ob.get("X_SWIFI_INOUT_DOOR")).replaceAll("\"", "");
                String X_SWIFI_REMARS3 = String.valueOf(ob.get("X_SWIFI_REMARS3")).replaceAll("\"", "");
                String LAT = String.valueOf(ob.get("LAT")).replaceAll("\"", "");
                String LNT = String.valueOf(ob.get("LNT")).replaceAll("\"", "");
                String WORK_DTTM = String.valueOf(ob.get("WORK_DTTM")).replaceAll("\"", "");

                sql = " insert into tb_wifi (X_SWIFI_MGR_NO,X_SWIFI_WRDOFC,X_SWIFI_MAIN_NM,X_SWIFI_ADRES1,X_SWIFI_ADRES2,X_SWIFI_INSTL_FLOOR,X_SWIFI_INSTL_TY,X_SWIFI_INSTL_MBY,X_SWIFI_SVC_SE,X_SWIFI_CMCWR,X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3,LAT,LNT,WORK_DTTM) "+
                        " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); ";

                statement = con.prepareStatement(sql);

            statement.setString(1, X_SWIFI_MGR_NO);
            statement.setString(2, X_SWIFI_WRDOFC);
            statement.setString(3, X_SWIFI_MAIN_NM);
            statement.setString(4, X_SWIFI_ADRES1);
            statement.setString(5, X_SWIFI_ADRES2);
            statement.setString(6, X_SWIFI_INSTL_FLOOR);
            statement.setString(7, X_SWIFI_INSTL_TY);
            statement.setString(8, X_SWIFI_INSTL_MBY);
            statement.setString(9, X_SWIFI_SVC_SE);
            statement.setString(10, X_SWIFI_CMCWR);
            statement.setString(11, X_SWIFI_CNSTC_YEAR);
            statement.setString(12, X_SWIFI_INOUT_DOOR);
            statement.setString(13, X_SWIFI_REMARS3);
            statement.setString(14, LAT);
            statement.setString(15, LNT);
            statement.setString(16, WORK_DTTM);
            affected = statement.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(statement != null && !statement.isClosed()){
                    statement.close();
                }
                if(con != null && !con.isClosed()){
                    con.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dbDelete(){
        Connection con = null;
        // 쿼리에서 ?를 쓰고 밑에 setString로 변수값 넣어줌
        PreparedStatement statement = null;
        ResultSet rs  = null;
        String result = "";
        int affected = 0;
        String sql = "";

        try {

            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\SpringStudy\\mission2\\mission2.sqlite");

            //데이터 삭제
            sql = " delete from tb_wifi; ";
            statement = con.prepareStatement(sql);
            affected = statement.executeUpdate();
            //데이터 삭제 끝

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(statement != null && !statement.isClosed()){
                    statement.close();
                }
                if(con != null && !con.isClosed()){
                    con.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<DataVO> dbHistorySelect(){

        List<DataVO> dataList = new ArrayList<>();

        Connection con = null;
        // 쿼리에서 ?를 쓰고 밑에 setString로 변수값 넣어줌
        PreparedStatement statement = null;
        ResultSet rs  = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\SpringStudy\\mission2\\mission2.sqlite");

            String sql = "select *"+
                    " from tb_location_history "+
                    " limit 20; ";

            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()){
                String ID = rs.getString("ID");
                String LAT = rs.getString("LAT");
                String LNT = rs.getString("LNT");
                String WORK_DTTM = rs.getString("WORK_DTTM");

                DataVO dataVO = new DataVO();
                dataVO.setID(ID);
                dataVO.setLAT(LAT);
                dataVO.setLNT(LNT);
                dataVO.setWORK_DTTM(WORK_DTTM);

                dataList.add(dataVO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if(!rs.isClosed()){
                    rs.close();
                }
                if(!statement.isClosed()){
                    statement.close();
                }
                if(!con.isClosed()){
                    con.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public void dbHistoryInsert(String lat, String lnt){
        Connection con = null;
        // 쿼리에서 ?를 쓰고 밑에 setString로 변수값 넣어줌
        PreparedStatement statement = null;
        ResultSet rs  = null;
        String result = "";

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\SpringStudy\\mission2\\mission2.sqlite");

                String LAT = String.valueOf(lat);
                String LNT = String.valueOf(lnt);

                String sql = " insert into tb_location_history (lat,lnt,WORK_DTTM) "+
                        " values(?,?,datetime('now','localtime')); ";

                statement = con.prepareStatement(sql);

                statement.setString(1, LAT);
                statement.setString(2, LNT);
                int affected = statement.executeUpdate();

    } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(statement != null && !statement.isClosed()){
                    statement.close();
                }
                if(con != null && !con.isClosed()){
                    con.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void dbHistoryDelete(String id){
        Connection con = null;
        // 쿼리에서 ?를 쓰고 밑에 setString로 변수값 넣어줌
        PreparedStatement statement = null;
        ResultSet rs  = null;
        String result = "";
        int affected = 0;
        String sql = "";

        try {

            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\SpringStudy\\mission2\\mission2.sqlite");

            //데이터 삭제
            sql = " delete from tb_location_history "+
                    "where id = ?;";
            statement = con.prepareStatement(sql);
            statement.setString(1,id);
            affected = statement.executeUpdate();
            //데이터 삭제 끝

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(statement != null && !statement.isClosed()){
                    statement.close();
                }
                if(con != null && !con.isClosed()){
                    con.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<BookMarkVO> dbBookMarkSelect(){

        List<BookMarkVO> dataList = new ArrayList<>();

        Connection con = null;
        // 쿼리에서 ?를 쓰고 밑에 setString로 변수값 넣어줌
        PreparedStatement statement = null;
        ResultSet rs  = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\SpringStudy\\mission2\\mission2.sqlite");

            String sql = "select *"+
                    " from tb_bookmark "+
                    "order by 'order' asc; ";

            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()){
                String bookmarkId = rs.getString("bookmarkId");
                String bookMarkName = rs.getString("bookMarkName");
                String order = rs.getString("ORDER");
                String createDate = rs.getString("createDate");
                String updateDate = rs.getString("updateDate");

                BookMarkVO bookMarkVO = new BookMarkVO();
                bookMarkVO.setBookmarkId(bookmarkId);
                bookMarkVO.setBookMarkName(bookMarkName);
                bookMarkVO.setORDER(order);
                bookMarkVO.setCreateDate(createDate);
                bookMarkVO.setUpdateDate(updateDate);

                dataList.add(bookMarkVO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if(!rs.isClosed()){
                    rs.close();
                }
                if(!statement.isClosed()){
                    statement.close();
                }
                if(!con.isClosed()){
                    con.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }
    public void dbBookMarkInsert(String bookMarkName, String order){
        Connection con = null;
        // 쿼리에서 ?를 쓰고 밑에 setString로 변수값 넣어줌
        PreparedStatement statement = null;
        ResultSet rs  = null;
        String result = "";

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\SpringStudy\\mission2\\mission2.sqlite");


            String sql = " insert into tb_bookmark (bookMarkName,'order',createDate) "+
                    " values(?,?,datetime('now','localtime')); ";

            statement = con.prepareStatement(sql);

            statement.setString(1, bookMarkName);
            statement.setString(2, order);
            int affected = statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(statement != null && !statement.isClosed()){
                    statement.close();
                }
                if(con != null && !con.isClosed()){
                    con.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void dbBookMarkDelete(String bookmarkId){
        Connection con = null;
        // 쿼리에서 ?를 쓰고 밑에 setString로 변수값 넣어줌
        PreparedStatement statement = null;
        ResultSet rs  = null;
        String result = "";
        int affected = 0;
        String sql = "";

        try {

            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\SpringStudy\\mission2\\mission2.sqlite");

            //데이터 삭제
            sql = " delete from tb_bookmark "+
                    "where bookmarkId = ?;";
            statement = con.prepareStatement(sql);
            statement.setString(1,bookmarkId);
            affected = statement.executeUpdate();
            //데이터 삭제 끝

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(statement != null && !statement.isClosed()){
                    statement.close();
                }
                if(con != null && !con.isClosed()){
                    con.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void dbBookMarkUpdate(String bookmarkId, String bookMarkName, String order){
        Connection con = null;
        // 쿼리에서 ?를 쓰고 밑에 setString로 변수값 넣어줌
        PreparedStatement statement = null;
        ResultSet rs  = null;
        String result = "";
        int affected = 0;
        String sql = "";

        try {

            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\SpringStudy\\mission2\\mission2.sqlite");

            //데이터 삭제
            sql = " update tb_bookMark "+
                    " set bookMarkName = ? , 'order' = ?, updateDate = datetime('now','localtime')"+
                    "where bookmarkId = ?;";
            statement = con.prepareStatement(sql);
            statement.setString(1,bookMarkName);
            statement.setString(2,order);
            statement.setString(3,bookmarkId);
            affected = statement.executeUpdate();
            //데이터 삭제 끝

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(statement != null && !statement.isClosed()){
                    statement.close();
                }
                if(con != null && !con.isClosed()){
                    con.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<BookMarkVO> dbSelectBookMarkDetail(String bookmarkId){

        List<BookMarkVO> dataList = new ArrayList<>();

        Connection con = null;
        // 쿼리에서 ?를 쓰고 밑에 setString로 변수값 넣어줌
        PreparedStatement statement = null;
        ResultSet rs  = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\SpringStudy\\mission2\\mission2.sqlite");

            String sql = "select *"+
                    " from tb_bookmark "+
                    " where bookmarkId = ?;";

            statement = con.prepareStatement(sql);

            statement.setString(1, bookmarkId);
            rs = statement.executeQuery();

            while (rs.next()){
                String bookMarkName = rs.getString("bookMarkName");
                String order = rs.getString("order");


                BookMarkVO dataVO = new BookMarkVO();
                dataVO.setBookmarkId(bookmarkId);
                dataVO.setBookMarkName(bookMarkName);
                dataVO.setORDER(order);


                dataList.add(dataVO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if(!rs.isClosed()){
                    rs.close();
                }
                if(!statement.isClosed()){
                    statement.close();
                }
                if(!con.isClosed()){
                    con.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }
    public List<BookMarkVO> selectBookMarkList(){

        List<BookMarkVO> dataList = new ArrayList<>();

        Connection con = null;
        // 쿼리에서 ?를 쓰고 밑에 setString로 변수값 넣어줌
        PreparedStatement statement = null;
        ResultSet rs  = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\SpringStudy\\mission2\\mission2.sqlite");

            String sql = " select a.bookmarkId, a.bookMarkName, b.X_SWIFI_MGR_NO, b.X_SWIFI_MAIN_NM, b.bookmarkCreateDate "+
            " from tb_bookmark a, tb_wifi b "+
            " where a.bookmarkId = b.bookmarkId; ";

            statement = con.prepareStatement(sql);

            rs = statement.executeQuery();

            while (rs.next()){
                String X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
                String bookMarkName = rs.getString("bookMarkName");
                String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
                String bookmarkCreateDate = rs.getString("bookmarkCreateDate");
                String bookmarkId = rs.getString("bookmarkId");

                BookMarkVO dataVO = new BookMarkVO();
                dataVO.setBookmarkId(bookmarkId);
                dataVO.setX_SWIFI_MAIN_NM(X_SWIFI_MAIN_NM);
                dataVO.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);
                dataVO.setBookmarkCreateDate(bookmarkCreateDate);
                dataVO.setBookMarkName(bookMarkName);


                dataList.add(dataVO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if(!rs.isClosed()){
                    rs.close();
                }
                if(!statement.isClosed()){
                    statement.close();
                }
                if(!con.isClosed()){
                    con.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }
    public List<BookMarkVO> selectBookMarkListDetail(String X_SWIFI_MGR_NO){

        List<BookMarkVO> dataList = new ArrayList<>();

        Connection con = null;
        // 쿼리에서 ?를 쓰고 밑에 setString로 변수값 넣어줌
        PreparedStatement statement = null;
        ResultSet rs  = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\SpringStudy\\mission2\\mission2.sqlite");

            String sql = " select a.bookmarkId, a.bookMarkName, b.X_SWIFI_MGR_NO, b.X_SWIFI_MAIN_NM, b.bookmarkCreateDate "+
                    " from tb_bookmark a, tb_wifi b "+
                    " where a.bookmarkId = b.bookmarkId "+
                    " and b.X_SWIFI_MGR_NO = ?; ";

            statement = con.prepareStatement(sql);
            statement.setString(1,X_SWIFI_MGR_NO);
            rs = statement.executeQuery();

            while (rs.next()){
                X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
                String bookMarkName = rs.getString("bookMarkName");
                String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
                String bookmarkCreateDate = rs.getString("bookmarkCreateDate");
                String bookmarkId = rs.getString("bookmarkId");

                BookMarkVO dataVO = new BookMarkVO();
                dataVO.setBookmarkId(bookmarkId);
                dataVO.setX_SWIFI_MAIN_NM(X_SWIFI_MAIN_NM);
                dataVO.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);
                dataVO.setBookmarkCreateDate(bookmarkCreateDate);
                dataVO.setBookMarkName(bookMarkName);


                dataList.add(dataVO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if(!rs.isClosed()){
                    rs.close();
                }
                if(!statement.isClosed()){
                    statement.close();
                }
                if(!con.isClosed()){
                    con.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }
    public void addBookMark(String bookmarkId, String X_SWIFI_MGR_NO){
        Connection con = null;
        // 쿼리에서 ?를 쓰고 밑에 setString로 변수값 넣어줌
        PreparedStatement statement = null;
        ResultSet rs  = null;
        String result = "";

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\SpringStudy\\mission2\\mission2.sqlite");


            String sql = " update tb_wifi "+
                    " set bookmarkId = ? , bookmarkCreateDate = datetime('now','localtime')"+
                    "where X_SWIFI_MGR_NO = ?;";

            statement = con.prepareStatement(sql);

            statement.setString(1, bookmarkId);
            statement.setString(2, X_SWIFI_MGR_NO);
            int affected = statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(statement != null && !statement.isClosed()){
                    statement.close();
                }
                if(con != null && !con.isClosed()){
                    con.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void removeBookMark(String X_SWIFI_MGR_NO){
        Connection con = null;
        // 쿼리에서 ?를 쓰고 밑에 setString로 변수값 넣어줌
        PreparedStatement statement = null;
        ResultSet rs  = null;
        String result = "";
        int affected = 0;
        String sql = "";

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\SpringStudy\\mission2\\mission2.sqlite");

            //데이터 삭제
            sql = " update tb_wifi "+
                    " set bookmarkId = null , bookmarkCreateDate = null"+
                    " where X_SWIFI_MGR_NO = ?; ";
            statement = con.prepareStatement(sql);
            statement.setString(1,X_SWIFI_MGR_NO);
            affected = statement.executeUpdate();
            //데이터 삭제 끝

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(statement != null && !statement.isClosed()){
                    statement.close();
                }
                if(con != null && !con.isClosed()){
                    con.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
