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
    @Getter
    private static String  jwtToken;


    public RequestResponseData(JSONObject jsonResponse, int responseCode) {
        this.jsonResponse = jsonResponse;
        this.codeResponse = responseCode;
    }

    public RequestResponseData(int responseCode) {
        this.codeResponse = responseCode;
    }

    public static void setJwtToken(String jwtToken) {
        RequestResponseData.jwtToken = jwtToken;
    }


}
