# Design
Group 04 - "[Lumberjack Notes]"  
Date and location: March 28, 2021  
Group Members: Gustavo Valencia, Armando Martinez, Stefan Mihailovic, Nicholas Botticelli, Collin Rampata, Kyle Austria

## 1. Description

Lumberjack Notes is a free note-taking application designed to help students and professionals collaborate and share
their notes with their colleagues. Our application aims to provide a user-friendly interface with an intuitive
note-taking environment. Users of Lumberjack Notes will have the ability to customize and modify their workspace to
fit their needs. Users will also have the option to share their notes with fellow students or colleagues.

While Lumberjack Notes is still in early development, the app aims to provide a customizable and user-friendly
interface, as well as an unobtrusive note-taking environment. The user can edit and rearrange the tools in the toolbar
to their liking. These tools can also be utilized while typing so that users note-taking experience is more fluid. The
user will also have easy access to a search function that will allow them to quickly view other notes they have
written. They will also be able to quickly access or modify their profile.

GitHub: https://github.com/CS386-Group-4/LumberjackNotes

Trello: https://trello.com/b/3lbd1jw9/cs-386-notes-app

## 2. Architecture

The architecture we implemented for Lumberjack Notes is MVC (Model–View–Controller). The model layer describes the data
structure to hold information, the view layer contains the representation of the user interface presented to the user,
and the controller layer accepts user input and controls model data. We used this architecture for our app for its
simplicity and effectiveness in structuring the code in an organized and extensible manner.

![Architecture](./images/Deliverable_5_Design_Architecture.png)

## 3. Class diagram

![UML Diagram](./images/Deliverable_5_Design_UML_Diagram.png)

## 4. Sequence diagram

![Sequence Diagram](./images/Deliverable_5_Design_Sequence_Diagram.png)

**Use Case**: Take Notes  
**Actor**: User  
**Description**: The user creates a page where they are able to take notes  
**Preconditions**: The user has created an account on the platform  
**Post-conditions**: The user should have a workspace where they can take and edit notes  
**Main Flow**:
1. The user selects a workspace
2. The user can write or draw notes
3. The user should be able to save their notes and come back to them at a later date

**Alternate Flow**:
- None

## 5. Design Patterns

### Bridge Design Pattern (Structural)

LoginActivity will instantiate one instance of UserProfile, which will be used across the app. This makes logical sense
since there can only be one user of the app at a time.

![Bridge Design Pattern UML Diagram](./images/D5_Bridge_Design_Pattern.png)

The Notes class can be found [here](../android_app/app/src/main//java/org/cs386group4/lumberjacknotes/models/Notes.java).

The UserProfile class can be found [here](../android_app/app/src/main//java/org/cs386group4/lumberjacknotes/models/UserProfile.java).

### Singleton Design Pattern (Creational)

The user's notes are abstracted away to an array of Notes objects, which is then used in the UserProfile object. This
separates the responsibilities between user data management and note data management.

![Singleton Design Pattern UML Diagram](./images/D5_Singleton_Design_Pattern.png)

The LoginActivity class can be found: [here](../android_app/app/src/main//java/org/cs386group4/lumberjacknotes/ui/LoginActivity.java).

The Notes class can be found [here](../android_app/app/src/main//java/org/cs386group4/lumberjacknotes/models/Notes.java).

The UserProfile class can be found [here](../android_app/app/src/main//java/org/cs386group4/lumberjacknotes/models/UserProfile.java).

## 6. Design Principles

### S: Single responsibility principle (SRP)

![SRP Example UML Diagram](./images/D5_SRP_Example.png)

The design we implemented observes the Single responsibility principle. The Tutorial class has the sole responsibility
of displaying information to the user about the application.

### O: Open/closed principle (OCP)

![OCP Example UML Diagram](./images/D5_OCP_Example.png)

Our design also observes the Open/closed principle. The Tools class is extendable but closed for modification.

### L: Liskov substitution principle (LSP)

The Liskov Substitution Principle states that every subclass should be substitutable for their parent class. Our design
does not use any subclasses so far, so this principle does not yet apply.

### I: Interface segregation principle (ISP)

The Interface Segregation Principle states that a client should never be forced to implement an interface or depend on
methods that they do not use.

### D: Dependency inversion principle (DIP)

The Dependency Inversion Principle states that entities must depend on abstractions not concretions, which allows
decoupling. Since we don’t have any code interfaces yet, this principle does not apply.
