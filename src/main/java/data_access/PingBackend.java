package data_access;

import org.json.JSONObject;

public class PingBackend extends APICall
{

    // Class Constructor
    public PingBackend(String serverURL)
    {
        super(serverURL);
    }

    /**
     * Method used to register a new account.
     * @param username of the new user
     * @param email of the new user
     * @param password of the new user
     * @return the json of the result.
     * @throws Exception If api call goes wrong.
     */
    public JSONObject register(String username, String email, String password) throws Exception
    {
        // Construct the body
        JSONObject body = new JSONObject();
        body.put("username", username);
        body.put("email", email);
        body.put("password", password);

        // Call API
        String response = sendRequest("api/v1/auth/register", "POST", body);
        // Return results as a JSON
        return new JSONObject(response);
    }

    public JSONObject login(String email, String password) throws Exception
    {
        // Construct the body
        JSONObject body = new JSONObject();
        body.put("email", email);
        body.put("password", password);

        // Call API
        String response = sendRequest("api/v1/auth/login", "POST", body);

        JSONObject jsonResponse = new JSONObject(response);
        // Save auth token
        this.setAccessToken(jsonResponse.optString("auth_token"));
        // Return results as a JSON
        return jsonResponse;
    }

    public JSONObject createThread(String threadName, String[] participantUsernames) throws Exception
    {
        // Construct the body
        JSONObject body = new JSONObject();
        body.put("threadName", threadName);
        body.put("participantUsernames", participantUsernames);

        // Call API
        String response = sendRequest("api/v1/threads", "POST", body);
        // Return results as a JSON
        return new JSONObject(response);
    }

    public JSONObject sendMessage(int threadId, String messageType, String content) throws Exception
    {
        // Construct the body
        JSONObject body = new JSONObject();
        body.put("threadId", threadId);
        body.put("messageType", messageType);
        body.put("content", content);

        // Call API
        String response = sendRequest("api/v1/messages", "POST", body);
        // Return results as a JSON
        return new JSONObject(response);
    }

    public JSONObject getMessages(int threadId) throws Exception
    {
        // Call API
        String response = sendRequest("api/v1/messages/" + threadId, "GET", null);
        // Return results as a JSON
        return new JSONObject(response);
    }

    public JSONObject getThreads() throws Exception
    {
        // Call API
        String response = sendRequest("api/v1/threads", "GET", null);
        // Return results as a JSON
        return new JSONObject(response);
    }


    public String helloWorld() throws Exception
    {
        // Call API and Return
        return sendRequest("api/hello", "GET", null);
    }

    /**
     * This deletes the user's access token when the user logs out.
     */
    public void logout()
    {

        this.setAccessToken(null);
    }
}
