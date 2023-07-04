package com.alura.hotelAlura.infra.security;

import com.alura.hotelAlura.model.reservations.RegisterReservation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseLogin {

    public static String jwt;
    public int response(String usuario, String contrasenha) throws IOException {

        URL url = new URL("http://localhost:8080/login");

        Map<String, Object> param = new LinkedHashMap<>();
        param.put("login", usuario);
        param.put("password", contrasenha);

        ObjectMapper mapperLogin = new ObjectMapper();
        mapperLogin.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapperLogin.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(param);


        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        OutputStream outputStream = con.getOutputStream();
        byte[] input = json.getBytes(StandardCharsets.UTF_8);
        outputStream.write(input, 0, input.length);

        int responseCode = con.getResponseCode();


        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder jsonResponse = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonResponse.append(line);
        }

        jwt = jsonResponse.toString();

            return responseCode;
        }

    }




