# PingClient Swing Application

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Screenshot](#screenshot)
- [License](#license)
- [Feedback](#feedback)
- [Contributing](#contributing)
- [Authors](#authors)

---

## Project Overview
**PingClient Swing Application** is a lightweight Java-based messaging client built with a clean architecture. This project was developed to demonstrate effective software design principles, including modularity, testability, and the use of the Model-View-Controller (MVC) pattern. The application allows users to:
- Log in or sign up.
- Create and manage message threads.
- Send and receive messages in real-time.

The project was built as part of the CSC207 curriculum at the University of Toronto to reinforce object-oriented programming concepts and design patterns.

---

## Features
- **User Authentication**:
  - Login and signup functionality with form validation.
  - Secure backend communication using REST APIs.
- **Thread Management**:
  - View and manage a list of message threads.
  - Create new threads with user-defined participants.
- **Real-Time Messaging**:
  - Send and receive messages in a selected thread.
  - Refresh functionality to view the latest messages.
- **Clean Architecture**:
  - Separation of concerns with clearly defined layers.
  - Testable and scalable code structure.

---

## Installation
To set up and run the PingClient Swing Application, follow the steps below:

### Prerequisites
- **Java Development Kit (JDK)** version 11 or higher.
- **Maven** for dependency management.
- An active internet connection for fetching dependencies.

### Steps
1. Clone the repository:
    ```bash
    git clone https://github.com/your-repo/PingClientSwing.git
    cd PingClientSwing
    ```
2. Build the project using Maven:
    ```bash
    mvn clean install
    ```
3. Run the application:
    ```bash
    mvn exec:java -Dexec.mainClass="app.Main"
    ```

### Dependencies
The application uses the following libraries:
- **[org.json](https://github.com/stleary/JSON-java)** for JSON parsing.
- **[OkHttp](https://square.github.io/okhttp/)** for REST API requests.
- **JUnit 4 and 5** for testing.
- **[Guava](https://github.com/google/guava)** for utility functions.

---

## Usage
### Launching the Application
Once installed, run the main class (`app.Main`) to start the PingClient Swing Application. 

### Using the Application
1. **Login or Sign Up**:
   - Enter your credentials to log in or sign up.
   - Navigate to the threads view upon successful login.
2. **Managing Threads**:
   - View existing threads.
   - Create new threads by specifying a name and participants.
3. **Messaging**:
   - Select a thread to view messages.
   - Send new messages and refresh the thread to view updates.

### Placeholder for UI Screenshot
*(Insert a screenshot of the application's main UI here.)*

---

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.

---

## Feedback
We value your feedback to improve this project! Please reach out through:
- Issues tab on [GitHub](https://github.com/your-repo/PingClientSwing/issues).
- Fill out our [feedback form](#).

---

## Contributing
Contributions to this project are welcome! To contribute:
1. Fork the repository.
2. Create a new branch for your feature:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes and push to your branch:
   ```bash
   git push origin feature-name
   ```
4. Submit a pull request with a detailed explanation of your changes.

### Guidelines
- Ensure your code follows the style guidelines (`maven-checkstyle-plugin` is enforced).
- Provide adequate tests for new features or bug fixes.
- Respect the project's existing architecture and design principles.

---

## Authors
- **CSC207: Group 167**
  - [Ali Rahbar](https://github.com/crypto-a)
  - [Wilton Miller](https://github.com/wiltonmiller)
  - [Matteo Gentili](https://github.com/MatteoGentili24)
  - [Benedict Cummins-Mburu](https://github.com/bennypk1)
  - [Benjamin Gavriely](https://github.com/Benjamin-Uoft)
  - [Robert Reder](https://github.com/Roppax)

---

Enjoy using PingClient Swing Application! 🎉
