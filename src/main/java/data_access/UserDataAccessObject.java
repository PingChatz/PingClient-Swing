package data_access;

import entity.User;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.send_message.SendMessageUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * The DAO for user data.
 */
// TODO: this is just to give an idea of what the UserDAO looked like in Lab 5
public class UserDataAccessObject implements
        SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        SendMessageUserDataAccessInterface
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
    public void save(User user)
    {
        // call backend
        System.out.println("save user");
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        try
        {
            backend.register(user.getUsername(), user.getEmail(), user.getPassword());
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
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
    public Long getCurrentUserID()
    {
        return 0L;
    }

    @Override
    public void setCurrentUsername(String username)
    {
    }
}
