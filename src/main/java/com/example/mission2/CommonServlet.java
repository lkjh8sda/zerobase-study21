package com.example.mission2;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;

@WebServlet("/load-wifi")
public class CommonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CommonService commonService = new CommonService();
        String result = "";
        URL url = new URL("http://openapi.seoul.go.kr:8088/6c777642526c6b6a3837417a75704b/json/TbPublicWifiInfo/1/1/");
        BufferedReader bf;
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        } else {
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        }

        result = bf.readLine();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
        JsonObject jsonObject2 = jsonObject.getAsJsonObject("TbPublicWifiInfo");
        JsonElement count = jsonObject2.get("list_total_count");
        int countToInt = count.getAsInt();
        int iter = (countToInt/1000);
        if(countToInt%1000 != 0){
            iter +=1;
        }
        commonService.dbDelete();
        int start = 1;
        int end = 1000;
        for (int i = 0; i < iter; i++) {
            if(end >= countToInt) end = countToInt;
            commonService.dbInsert(start, end);
            start += 1000;
            end += 1000;
        }
        req.setAttribute("count",countToInt);
        req.getRequestDispatcher("load-wifi.jsp").forward(req,resp);

    }
}
