package use_case.signup;

/**
 * The Input Data for the Signup Use Case.
 */
// TODO: overwrite with our code
public class SignupInputData {

    private final String username;
    private final String password;
    private final String repeatPassword;
    private final String email;

    public SignupInputData(String username, String password, String repeatPassword, String email) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.email = email;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getEmail() {
        return email;
    }
}
