package com.alura.hotelAlura.model;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetListData {


    public static String JWToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkaWVnby5yb2phcyIsImlzcyI6ImhvdGVsQWx1cmEiLCJpZCI6Mn0.x9P8y1TUh5514Cbj4C_wc45Sml6guN_Z1OtoZvVcGis";

    public static JSONArray response(URL url) throws IOException {

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", JWToken);
        con.setDoOutput(true);

        int responseCode = con.getResponseCode();


        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder jsonResponse = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonResponse.append(line);
        }
        reader.close();
        JSONObject jsonObject = new JSONObject(jsonResponse.toString());

        return (JSONArray) jsonObject.get("content");
    }
}

// {"content":[{"id":1,"entrydate":"2005-12-05","outdate":"2005-12-18","price":180000.25,"payform":"efectivo"},{"id":3,"entrydate":"2018-10-05","outdate":"2018-10-05","price":180000.25,"payform":"efectivo"},{"id":4,"entrydate":"2005-10-05","outdate":"2005-10-18","price":180000.25,"payform":"Tarjeta de credito"},{"id":5,"entrydate":"2005-08-05","outdate":"2005-10-18","price":1500000.0,"payform":"efectivo"},{"id":6,"entrydate":"2005-10-18","outdate":"2005-10-18","price":15000.0,"payform":"Tarjeta de credito"}],"pageable":{"sort":{"empty":false,"sorted":true,"unsorted":false},"offset":0,"pageNumber":0,"pageSize":5,"paged":true,"unpaged":false},"last":false,"totalPages":3,"totalElements":15,"size":5,"number":0,"sort":{"empty":false,"sorted":true,"unsorted":false},"first":true,"numberOfElements":5,"empty":false}


