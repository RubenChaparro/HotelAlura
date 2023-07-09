package com.alura.hotelAlura.model.guests;

import com.alura.hotelAlura.infra.security.ResponseLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class RegisterGuest {

    public RegisterGuest() {
    }
    public String JWToken = ResponseLogin.jwt;

    public int response(String name, String lastname, String birthday, String country, String phone) throws IOException {

        URL url = new URL("http://localhost:8080/guests");

        Map<String, Object> param = new LinkedHashMap<>();
        param.put("name", name);
        param.put("lastname", lastname);
        param.put("birthday", birthday);
        param.put("country", country);
        param.put("phone", phone);

        ObjectMapper mapperReservation = new ObjectMapper();
        mapperReservation.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapperReservation.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(param);


        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", JWToken);
        con.setDoOutput(true);

        OutputStream outputStream = con.getOutputStream();
        byte[] input = json.getBytes(StandardCharsets.UTF_8);
        outputStream.write(input, 0, input.length);

            return con.getResponseCode();

        }
}




