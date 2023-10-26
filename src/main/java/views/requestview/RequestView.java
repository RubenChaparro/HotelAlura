package views.requestview;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

public class RequestView {

    public static RequestResponseData conection(String mapping, String method, Map<String, Object> data) throws IOException {

        URL url = new URL("http://localhost:8080/" + mapping);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method);
        con.setRequestProperty("Content-Type", "application/json");
        if (!Objects.equals(mapping, "login")) {
            con.setRequestProperty("Authorization", RequestResponseData.getJwtToken());
        }
        con.setDoOutput(true);

        if (Objects.equals(method, "POST") || Objects.equals(method, "PUT")) {
            ObjectMapper mapperReservation = new ObjectMapper();
            mapperReservation.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            ObjectWriter objectWriter = mapperReservation.writer().withDefaultPrettyPrinter();
            String json = objectWriter.writeValueAsString(data);

            OutputStream outputStream = con.getOutputStream();
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder jsonResponse = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonResponse.append(line);
        }
        reader.close();

        if (!jsonResponse.isEmpty()) {
            JSONObject jsonObject = new JSONObject(jsonResponse.toString());

            if (Objects.equals(mapping, "login")) {
                RequestResponseData.setJwtToken(jsonObject.getString("jwtToken"));
            }

            return new RequestResponseData(jsonObject, con.getResponseCode());
        }
        return new RequestResponseData(con.getResponseCode());
    }
}

// $2a$12$MNv6Wj12drAcWrcElDX0yutb90LDFU/n13WONSlaKwXq7nIzfE8Ky