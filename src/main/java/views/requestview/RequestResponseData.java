package views.requestview;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.time.Duration;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RequestResponseData {

    private JSONObject jsonResponse;
    private int codeResponse;
    private static String  jwtToken;


    public RequestResponseData(JSONObject jsonResponse, int responseCode, HttpURLConnection con) {
        this.jsonResponse = jsonResponse;
        this.codeResponse = responseCode;
    }

    public RequestResponseData(int responseCode) {
        this.codeResponse = responseCode;
    }

    public static String getJwtToken() {
        return jwtToken;
    }

    public static void setJwtToken(String jwtToken) {
        RequestResponseData.jwtToken = jwtToken;
    }

    public static float priceTotal(Date entrydate, Date outdate) {

        long miliseconds = outdate.getTime() - entrydate.getTime();
        float days = (float) Duration.ofMillis(miliseconds).toDays();

        return (days * 30000)+30000;
    }
}
