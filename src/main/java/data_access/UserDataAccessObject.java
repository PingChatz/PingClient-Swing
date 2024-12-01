package data_access;

import entity.User;
import org.json.JSONObject;
import use_case.add_thread.AddThreadUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.send_message.SendMessageUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

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
    public JSONObject validateCredentials(String email, String password) {
        try {
            JSONObject response = backend.login(email, password);
            if (response != null && response.has("authToken")) {
                System.out.println("Logged in successfully");
                System.out.println(response.optString("authToken"));
                backend.setAccessToken(response.optString("authToken"));
            }
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User user)
    {
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
    public Long getCurrentUserID()
    {
        return 0L;
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
