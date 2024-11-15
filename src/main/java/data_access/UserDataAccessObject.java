package data_access;

import entity.User;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * The DAO for user data.
 */
// TODO: this is just to give an idea of what the UserDAO looked like in Lab 5
public class UserDataAccessObject implements
        SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        LogoutUserDataAccessInterface {

    @Override
    public boolean existsByName(String username) {
        return false;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public User get(String username) {
        return null;
    }

    @Override
    public String getCurrentUsername() {
        return "";
    }

    @Override
    public void setCurrentUsername(String username) {
    }
}
