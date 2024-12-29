# **Gym Management System**
## **Project Overview**
The **Gym Management System** is a Java-based program that provides 
a comprehensive structure for managing a gym. 
It handles client registration, session scheduling, gym staff, 
balance tracking, and more. The system incorporates several 
**design patterns** such as **Singleton**, **Factory**, **Observer**,
and **Inheritance**, ensuring scalability, maintainability, 
and proper encapsulation of responsibilities.

## **Project Structure**
### **Packages**

1. **`gym.management`**:
    - #### Central package for the Gym system's core logic. ####
    - Classes: Gym, Secretary, Instructor, Session, GymLogger, RegistrationToGym, RegistrationToSession, HireNewInstructor, AddNewSession, PaySaleries
    - Interfaces: Observ


2. **`gym.management.Sessions`**:
    - ####  package that contain all the sessions . ####     
    - Classes: SessionsFactory, MachinePilates, Ninja, Pilates, ThaiBoxing
    - Enums: SessionsType, ForumType
 
  
3. **`gym.customers`**:
    - ####  package that contain the basic of all customer . ####
    - Classes: Bank, Client, Person
    - Enums: Gender


4. **`gym.Excption`**:
    - ####  package that contain all the exception in the project . ####


### **Class Overview with Design Patterns**
#### **1. Gym**
- Acts as the central class in the system.
- **Design Patterns**: Singleton, Observer.
- Manages:
    - Clients, instructors, and secretary.
    - Notifications (`notifyClients`, `notifyHistory`).
    - Sessions and balance.

#### **2. Client**
- Represents the gym's clients.
- **Design Patterns**: Inheritance (from `Person`), Observer (can receive notifications from the `Gym`).

#### **3. Person**
- Abstract base class for `Client`, `Secretary`, and `Instructor`.
- **Design Patterns**: Inheritance (base class).

#### **4. Secretary**
- Handles the gym's administrative tasks.
- **Design Patterns**: Inheritance (from `Person`), Facade (implicit).

#### **5. Instructor**
- Represents a gym instructor.
- **Design Patterns**: Inheritance (from `Person`),.

#### **6. Session** (Abstract Class)
- Abstract class for different gym sessions.
- Subclasses include `MachinePilates`, `Ninja`, `Pilates`, `ThaiBoxing`.
- **Design Patterns**: Abstract Class, Factory (through `SessionFactory`).

#### **7. SessionFactory**
- Creates the new sessions dynamically.
- **Design Patterns**: Factory.

#### **8. GymLogger**
-  stores the gym's history and updates.
- **Design Patterns**: Observer (observe the `Gym`).

#### **9. Exceptions** (Custom Exceptions)
- Include classes like `ClientNotRegisteredException`, `DuplicateClientException`, `InstructorNotQualifiedException`, `InvalidAgeException`.
- **Design Patterns**: Inheritance (from `Exception` class).

#### **10. Bank**
- Store the balance of the persons (connect id to balance).
- **Design Patterns**: Singleton.

#### **11. RegistrationToGym**
- Centralized management for accessing or registering gym functionalities.
- **Design Patterns**: Singleton.

#### **12. RegistrationToSession**
- Centralized management for accessing or registering to session in the gym functionalities.
- **Design Patterns**: Singleton.

#### **13. AddNewSession**
- Centralized management for create new session in the gym functionalities.
- **Design Patterns**: Singleton.

#### **14. HireNewInstructor**
- Centralized management for hire new instructor to the gym functionalities.
- **Design Patterns**: Singleton.

#### **15. PaySaleries**
- Centralized management for payment to the gym employees functionalities.
- **Design Patterns**: Singleton.

## **Design Patterns Usage**

| **Design Pattern** | **Usage in Classes/Components**                                                                                     |
| --- |---------------------------------------------------------------------------------------------------------------------|
| **Singleton** | `Gym`, `Bank`, `RegistrationToGym`, `RegistrationToSession`, `HireNewInstructor`, `PaySaleries`, `AddNewSession`, `SessionFactory`   |
| **Factory** | `SessionFactory`                                                                                                    |
| **Observer** | `Gym`, `Secretary` (publisher), `Client`, `Instructor`, `GymLogger` (subscribers)                                   |
| **Facade** | `Secretary`                                                                                                         |
| **Abstract Class** | `Session` (base class for session types like `MachinePilates`, `Pilates`)                                           |
| **Inheritance** | `Person` (common superclass for `Client`, `Instructor`, `Secretary`), Custom Exceptions inheriting from `Exception` |

