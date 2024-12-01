package data_access;

import entity.User;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.add_thread.AddThreadUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.send_message.SendMessageUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.IOException;

/**
 * The DAO for user data.
 */
// TODO: implement this once server is up
public class UserDataAccessObject implements
        SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        SendMessageUserDataAccessInterface,
        AddThreadUserDataAccessInterface
{
    private final PingBackend backend;

    public UserDataAccessObject(PingBackend backend)
    {
        this.backend = backend;
    }

    @Override
    public boolean existsByName(String username)
    {
        return false;
    }

    @Override
    public JSONObject validateCredentials(String email, String password)
    {
        try
        {
            JSONObject response = backend.login(email, password);
            if (response != null && response.has("authToken"))
            {
                backend.setAccessToken(response.optString("authToken"));
            }
            return response;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void save(User user) throws Exception
    {
        try
        {
            String response = backend.register(user.getUsername(), user.getEmail(), user.getPassword());
            JSONObject jsonResponse = new JSONObject(response);

            if (jsonResponse.has("error"))
            {
                // Server returned an error; throw an exception with the error message
                throw new Exception(jsonResponse.getString("message"));
            }
            // If no error, registration was successful
        } catch (IOException e)
        {
            // Handle network errors
            throw new Exception("Network error occurred while registering. Please check your connection.");
        } catch (JSONException e)
        {
            // Handle JSON parsing errors
            throw new Exception("Unexpected server response. Please try again later.");
        } catch (Exception e)
        {
            // General exception
            throw new Exception("An error occurred during registration: " + e.getMessage());
        }
    }

    @Override
    public User get(String username)
    {
        return null;
    }

    @Override
    public String getCurrentUsername()
    {
        return "";
    }

    @Override
    public void setCurrentUsername(String username)
    {
    }

    @Override
    public Long getCurrentUserID()
    {
        return 0L;
    }

    @Override
    public void logout()
    {
        this.backend.logout();
    }
}
