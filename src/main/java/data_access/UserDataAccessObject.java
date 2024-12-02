package data_access;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import entity.User;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * The DAO for user data.
 */
public class UserDataAccessObject implements
        SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        LogoutUserDataAccessInterface
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
            return response;
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    @Override
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
        }
        catch (IOException ioException)
        {
            // Handle network errors
            throw new Exception("Network error occurred while registering. Please check your connection.");
        }
        catch (JSONException jsonException)
        {
            // Handle JSON parsing errors
            throw new Exception("Unexpected server response. Please try again later.");
        }
        catch (Exception exception)
        {
            // General exception
            throw new Exception("An error occurred during registration: " + exception.getMessage());
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
    public void logout()
    {
        this.backend.logout();
    }
}
