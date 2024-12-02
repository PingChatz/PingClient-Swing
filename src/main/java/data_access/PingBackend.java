package data_access;

import org.json.JSONObject;

/**
 * Object for making calls to the Ping Backend Server.
 */
public class PingBackend extends AbstractAPICall
{
    private static final String POST = "POST";
    private static final String GET = "GET";

    // Class Constructor
    public PingBackend(String serverURL)
    {
        super(serverURL);
    }

    /**
     * Method used to register a new account.
     *
     * @param username of the new user
     * @param email    of the new user
     * @param password of the new user
     * @return the state of success of the call
     * @throws Exception if api call goes wrong.
     */
    public String register(String username, String email, String password) throws Exception
    {
        // Construct the body
        JSONObject body = new JSONObject();
        body.put("username", username);
        body.put("email", email);
        body.put("password", password);

        // Call API
        return sendRequest("api/v1/auth/register", POST, body);
    }

    /**
     * Method to log in a user.
     * @param usernameOrEmail the identifier for the user
     * @param password the user's password
     * @return the API authentication of a successfully logged-in user
     * @throws Exception if api call goes wrong.
     */
    public JSONObject login(String usernameOrEmail, String password) throws Exception
    {
        try
        {
            // Construct the body
            JSONObject body = new JSONObject();
            body.put("usernameOrEmail", usernameOrEmail);
            body.put("password", password);

            // Call API
            String response = sendRequest("api/v1/auth/login", "POST", body);

            // Parse the response as a JSON object
            JSONObject responseJSON = new JSONObject(response);

            if (responseJSON.has("authToken"))
            {
                this.setAccessToken(responseJSON.optString("authToken"));
            }

            // Return results as a JSON
            return responseJSON;
        }
        catch (Exception exception)
        {
            System.out.println("Login failed: " + exception.getMessage());
            throw exception;
        }
    }

    /**
     * Method to save a new thread in the server.
     *
     * @param threadName           the name of the thread to be created
     * @param participantUsernames the users in the thread to be created
     * @return a json object with all the data needed to create a Thread object
     * @throws Exception if the API call goes wrong
     */
    public JSONObject createThread(String threadName, String[] participantUsernames) throws Exception
    {
        // Construct the body
        JSONObject body = new JSONObject();
        body.put("threadName", threadName);
        body.put("threadName", threadName);
        body.put("participants", participantUsernames);

        // Call API
        String response = sendRequest("api/v1/threads", POST, body);
        // Return results as a JSON
        return new JSONObject(response);
    }

    /**
     * Method to save a new message in the server, within a thread.
     *
     * @param threadId    the ID of the thread to associate the message with
     * @param messageType the ype of the message to save
     * @param content     the content of the message to save
     * @return a json object with all the data needed to create a Message object
     * @throws Exception if the API call goes wrong
     */
    public JSONObject sendMessage(int threadId, String messageType, String content) throws Exception
    {
        // Construct the body
        JSONObject body = new JSONObject();
        body.put("threadId", threadId);
        body.put("messageType", messageType);
        body.put("content", content);

        // Call API
        String response = sendRequest("api/v1/messages", POST, body);
        // Return results as a JSON
        return new JSONObject(response);
    }

    /**
     * Method to get all the messages in a given thread.
     *
     * @param threadId the ID of the thread to get messages from
     * @return a json object with a list of message data necessary to create a List of Message objects
     * @throws Exception if the API call goes wrong
     */
    public JSONObject getMessages(int threadId) throws Exception
    {
        // Call API
        String response = sendRequest("api/v1/messages/" + threadId, GET, null);
        // Return results as a JSON
        return new JSONObject(response);
    }

    /**
     * Method to get all the threads currently saved in the server.
     *
     * @return a json object with...
     * @throws Exception if the API call goes wrong
     */
    public JSONObject getThreads() throws Exception
    {
        // Call API
        String response = sendRequest("api/v1/threads", GET, null);
        // Return results as a JSON
        return new JSONObject(response);
    }

    /**
     * This deletes the user's access token when the user logs out.
     */
    public void logout()
    {

        this.setAccessToken(null);
    }
}
