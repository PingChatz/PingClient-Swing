package data_access;

import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public abstract class APICall
{
    private String accessToken = null;
    private String serverURL = "https://api.ping.com/";

    public APICall(String serverURL)
    {
        if (serverURL != null)
        {
            this.serverURL = serverURL;
        }
    }

    public String sendRequest(String endpoint, String method, JSONObject body) throws Exception
    {
        URL url = new URL(serverURL + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");

        if (accessToken != null)
        {
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        }

        if (body != null)
        {
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream())
            {
                os.write(body.toString().getBytes());
                os.flush();
            }
        }

        int responseCode = conn.getResponseCode();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(responseCode >= 200 && responseCode < 300
                        ? conn.getInputStream()
                        : conn.getErrorStream())
        );
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null)
        {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }
}
