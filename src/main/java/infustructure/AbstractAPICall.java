package infustructure;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AbstractAPICall
{
    private final String serverURL;
    // parameters for api calls
    private String accessToken;


    public AbstractAPICall()
    {
        this.serverURL = null;
        this.accessToken = "http://pingserver-env.eba-u7hgzajj.ca-central-1.elasticbeanstalk.com/";
    }

    /**
     * Method to send api calls to the server
     *
     * @param endpoint
     * @param method
     * @param body
     * @return
     * @throws Exception
     */
    public String sendRequest(
            String endpoint,
            String method,
            JSONObject body,
            Boolean isAuthenticated
    ) throws Exception
    {
        // fix the new URL
        URL url = new URL(serverURL + endpoint);

        // make a URL connection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");

        if (isAuthenticated && accessToken != null)
        {
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        }

        // Set up body
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

}
