
# Project Accessibility Report

## Adherence to Principles of Universal Design

### the prompt (to remove when done)

1) For each Principle of Universal Design, write 2-3 sentences — or point form notes — explaining which features your program adhere to that principle. If you do not have any such features, you can either:

(a) Describe features that you could implement in the future that would adhere to the principle or

(b) Explain why the principle does not apply to a program like yours.

### Equitable Use

* We have implemented provisions for privacy and data security by using a RESTful API in our program. User passwords are hashed before being stored in our server, providing an additional layer of protection for the user's sensitive information(explain RESTApi and how the user's passwords are hashed)_
* Our design is very similar to many familiar messaging applications, giving it a feeling of familiarity _(give an example of this)_
* _ideally one more thing here_

### Flexibility in Use

_explain why we fail in this category and how we could improve_

### Simple and Intuitive Use

Our program is simple in its concept and use cases, which translates directly into it's simplicity of use:

* It is easy to navigate seamlessly between different parts of the program (all of our "to view" use cases embody this)
* _more stuff here_


### Perceptible Information

As our program makes minimal usage of colours and only accommodates people without visual impairments, here are a few improvements we could make to our program to improve in this category:
* Implementing intuitive auditory cues that fire whenever the users press certain buttons
    (ex: the "plane flying" sound effect commonly used to signal that a message has been sent)
* Implementing dark mode, to make the program more accessible to individuals who are particularly sensitive to bright lights
* Making certain text components of our UI larger

### Tolerance for Error

* when the user goes to add a new thread, the program is lenient in what it accepts in terms of te formatting of the list of users
_TODO: make sure that this is true in the code_
* Users can retry inputting their password multiple times
* If the user hits the message size limit, their text in the text field is not cleared, ensuring that they are able to adjust their message accordingly and not have to rewrite it entirely
* Similarly, if the user poorly formats the list of users when creating a new thread, the text field is not cleared
_TODO: make sure both of these features are implemented_

### Low Physical Effort 

We have designed our program to be very simple to use, and it's necessary functions can be executed with very 
low physical effort. There are also no loading times. However, the use of a manual refresh button makes it slightly 
more tedious to stay up to date with incoming messages. We could mitigate against this by using a Web Socket, 
establishing a continuous communication bridge between our server and the program.

### Size and Space for Approach and Use

As our program is a software application, this principle is automatically satisfied. 

***

## Target Demographic

_Write a paragraph (3-6 sentences) about who you would market your program towards,
if you were to sell or license your program to customers.
This could be a specific category of users, such as "students", or more vague, such as "people who like games".
Try to give a bit more detail along with the category._

***

## Potentially Marginalized Demographics

_Write a paragraph about whether your program is less likely to be used by certain demographics.
Your discussion here should be informed by the content of our embedded ethics modules this term._