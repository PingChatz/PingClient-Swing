package data_access;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Abstract class containing the logic to make API calls.
 */
public abstract class AbstractAPICall
{
    private String accessToken = null;
    private String serverURL = "http://pingserver-env.eba-u7hgzajj.ca-central-1.elasticbeanstalk.com/";

    public AbstractAPICall(String serverURL)
    {
        if (serverURL != null)
        {
            this.serverURL = serverURL;
        }
    }

    /**
     * Logic to send a request to the server.
     *
     * @param endpoint [description here]
     * @param method   [description here]
     * @param body     [description here]
     * @return [description here]
     * @throws Exception if the API call goes wrong
     */
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

    public final String getAccessToken()
    {
        return accessToken;
    }

    public final void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }
}
