package com.alura.hotelAlura.model.reservations;

import org.json.JSONArray;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteReservation {

    public int delete(URL url) throws IOException {

        String JWToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkaWVnby5yb2phcyIsImlzcyI6ImhvdGVsQWx1cmEiLCJpZCI6Mn0.x9P8y1TUh5514Cbj4C_wc45Sml6guN_Z1OtoZvVcGis";

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", JWToken);
            con.setDoOutput(true);

            return con.getResponseCode();
        }
    }