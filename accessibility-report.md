
# Project Accessibility Report

## Adherence to Principles of Universal Design

### Equitable Use

* We have implemented provisions for privacy and data security by using a RESTful API in our program. User passwords are hashed before being stored in our server, providing an additional layer of protection for sensitive information.
* The logout functionality is designed to fully erase all working data from the program, ensuring that no user information is left behind and further protecting privacy.
* With a minimalist design and straightforward flow, our interface is accessible and appealing to a wide range of users, promoting an intuitive experience for everyone.

* We could have further improved the equability of our program by introducing multi-language support.

### Flexibility in Use

Currently, our program does not offer sufficient flexibility in use. It lacks key features such as adjustable text size and font type, and alternative input methods like voice commands , allowing for the flexibility that many users rely on. These features are things that we plan on adding in future iterations of the program.

### Simple and Intuitive Use

Our program was specifically designed for simplicity and intuitive use:
* It is easy to navigate seamlessly between different parts of the program, and the buttons to do so are clearly labelled (all of our "to view" use cases embody this).
* When the user makes an invalid input, our program effective prompts them, as illustrated by the specific and informative error messages related to creating a new chat, logging in, or sending a message.
* The visual design of our program, as well as it's intuitive flow of use, closely align with many widely-used messaging applications such as iMessage, which providing users with a familiar and intuitive interface. More specifically, in our chat view, the chats sent by the current user have a light-blue background, that most messaging app users are familiar with.


### Perceptible Information

Although our program does not currently support many accessibility features pertaining to perception, we have planned to implement the following features to improve in this area:
* Intuitive auditory cues that fire whenever the users press certain buttons. For example, the "plane flying" sound effect commonly used to signal that a message has been sent.
* Dark mode, to make the program more accessible to users who are particularly sensitive to bright lights, or those in low-light environments.
* As we have mentioned above, we plan on giving users the option to change the font size of the application, making it more perceptible to users with poor-vision. 

### Tolerance for Error

* If a user wants to add a new thread, the program is lenient in what it accepts as a list of users in the thread, as demonstrated by the regex.
* If the user inputs invalid data, the text fields are not cleared after the error message, allowing the user to adjust their inputs accordingly without having to restart. This is implemented in all parts of our program, including the authentication views,the chat view and the view for creating a new thread.
* Users can retry inputting their password multiple times.

### Low Physical Effort 

The program is designed to require minimal physical effort to use. Functions like sending messages or navigating through threads can be done with just a few clicks or key presses. The absence of significant loading times further supports low physical effort. However, the manual refresh button, which requires users to manually update incoming messages, could be improved by integrating a WebSocket for continuous communication, reducing the effort needed to stay updated.

### Size and Space for Approach and Use

As a software application, the principle of providing ample space for approach and use is inherently satisfied. The program does not require specialized hardware or physical accommodations and can be used on any standard computer that has followed the necessary installation instructions.
***

## Target Demographic

If we were to market our program, we would target students and professionals who need efficient, secure communication tools. The simplicity and flexibility of our messaging app make it ideal for users who want a straightforward way to send and receive messages without distractions. Additionally, the emphasis on data security would appeal to individuals who prioritize protecting their privacy, such as students or professionals handling sensitive information.

***

## Potentially Marginalized Demographics

Our program  is less likely to be used by potentially marginalized demographics, such as individuals with visual impairments or poor vision, users with certain cognitive limitations such as dyslexia, or users who rely on alternative data entry mechanisms.