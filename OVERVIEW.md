# Entities

## User
- Long userID             (unique identifier for the user within the database)
- String username
- String passwordHash
- String email

## Thread
- Long threadID
- String name
- List<User> userList              (list of all the users that have access to the Thread)
- List<Message> messageList        (list of every message ever sent in this thread. this can be displayed in Swing as a scrollable J-thing)

## Message (Abstract)
- Long messageID          (unique identifier for the message within the database)
- Long threadID
- Long senderID           (the ID of the user that sent the message)
- Object content          (it's type will change based on the child class)

## TextMessage implements Message
- String content

# Use Cases

- signup
- login                   (this use case is coupled with displaying the list of Threads the user belongs to. Logging in and displaying available threads should happen simultaneously)
- logout                  (remove all user from the server)
- sendMessage             (sends a message. Can only be used within the context of a thread)
- addThread               (adds a thread to the user's list of threads)
- refresh                 (our program does not update automatically. You must refresh to check for new messages. This is to avoid the use of a websocket.)
- openThread               (opens up a thread, allowing the user to see messages)

# TODOs that could be useful for Logout use case team (from Lab 5 original README.md)

* * *

- `Main.java`

    - [ ] TODO: add the Logout Use Case to the app using the appBuilder

* * *

- `LoggedInView.java` (tip: refer to the other views for similar code)

    - [ ] TODO: save the logout controller in the instance variable.
    - [ ] TODO: execute the logout use case through the Controller

* * *

- `LogoutController.java` (tip: refer to the other controllers for similar code)

    - [ ] TODO: Save the interactor in the instance variable.
    - [ ] TODO: run the use case interactor for the logout use case

* * *

- `LogoutInputData.java` (should be done with the LogoutInteractor TODOs below)

    - [ ] TODO: save the current username in an instance variable and add a getter.

- `LogoutInteractor.java` (tip: refer to `ChangePasswordInteractor.java` for similar code)

    - [ ] TODO: save the DAO and Presenter in the instance variables.
    - [ ] TODO: implement the logic of the Logout Use Case

* * *

- `LogoutOutputData.java`

    - [ ] TODO: save the parameters in the instance variables.

* * *

- `LogoutPresenter.java` (tip: refer to `SignupPresenter.java` for similar code)

    - [ ] TODO: assign to the three instance variables.
    - [ ] TODO: have prepareSuccessView update the LoggedInState
    - [ ] TODO: have prepareSuccessView update the LoginState

* * *