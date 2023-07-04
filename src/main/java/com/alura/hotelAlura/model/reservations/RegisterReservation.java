package com.alura.hotelAlura.model.reservations;

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

public class RegisterReservation {

    public RegisterReservation() {
    }

    public String JWToken = ResponseLogin.jwt;

    public int response(String entrydate, String outdate, float price, String payform) throws IOException {

        URL url = new URL("http://localhost:8080/reservations");

        Map<String, Object> param = new LinkedHashMap<>();
        param.put("entrydate", entrydate);
        param.put("outdate", outdate);
        param.put("price", price);
        param.put("payform", payform);

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

    public float priceTotal(Date entrydate, Date outdate) {

        long miliseconds = outdate.getTime() - entrydate.getTime();
        float days = (float) Duration.ofMillis(miliseconds).toDays();

        return (days * 30000)+30000;
    }
}




