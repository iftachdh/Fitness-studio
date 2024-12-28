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
    - Classes: Gym, Secretary, Instructor, Session, RegistrationToGym, RegistrationToSession
    - Interfaces: Observ
    - Enums: ForumType


2. **`gym.management.Sessions`**:
    - ####  package that contain all the sessions . ####     
    - Classes: SessionsFactory, GymLogger, MachinePilates, Ninja, Pilates, ThaiBoxing
    - Enums: SessionsType
 
  
3. **`gym.customers`**:
    - ####  package that contain the basic of all customer . ####
    - Classes: Bank, Client, Person
    - Enums: Gender


4. **`gym.Excption`**:
    - ####  package that contain all the exception in the project . ####


### **Class Overview with Design Patterns**
#### **1. Gym**
- Acts as the central class in the system.
- **Design Patterns**: Singleton, Observer, Facade (implicit)
- Manages:
    - Clients, instructors, and secretaries.
    - Notifications (`notifyClients`, `notifyHistory`).
    - Sessions and balance.

#### **2. Client**
- Represents the gym's clients.
- **Design Patterns**: Observer (can receive notifications from the `Gym`).

#### **3. Person**
- Abstract base class for `Client`, `Secretary`, and `Instructor`.
- **Design Patterns**: Inheritance (base class).

#### **4. Secretary**
- Handles the gym's administrative tasks.
- **Design Patterns**: Inheritance (from `Person`).

#### **5. Instructor**
- Represents a gym instructor.
- **Design Patterns**: Observer (subscriber for updates), Inheritance.

#### **6. Session** (Abstract Class)
- Abstract class for different gym sessions.
- Subclasses include `MachinePilates`, `Ninja`, `Pilates`, `ThaiBoxing`.
- **Design Patterns**: Abstract Class, Factory (through `SessionFactory`).

#### **7. SessionFactory**
- Creates instances of session types dynamically.
- **Design Patterns**: Factory.

#### **8. Vlog**
- Logs and stores the gym's history and updates.
- **Design Patterns**: Observer (implements log updates).

#### **9. Exceptions** (Custom Exceptions)
- Include classes like `ClientNotRegisteredException`, `DuplicateClientException`, etc.
- **Design Patterns**: Inheritance (from `Exception` class).

#### **10. Bank**
- Handles financial aspects of the gym.
- **Design Patterns**: Singleton.

#### **11. RegistrationToGym & RegistrationToSession**
- Centralized management for accessing or registering gym functionalities.
- **Design Patterns**: Singleton.

## **Design Patterns Usage**

| **Design Pattern** | **Usage in Classes/Components**                                                                                     |
| --- |---------------------------------------------------------------------------------------------------------------------|
| **Singleton** | `Gym`, `Bank`, `RegistrationToGym`, `RegistrationToSession`                                                         |
| **Factory** | `SessionFactory`                                                                                                    |
| **Observer** | `Gym` (publisher), `Client`, `Instructor`, `GymLogger` (subscribers)                                                |
| **Abstract Class** | `Session` (base class for session types like `MachinePilates`, `Pilates`)                                           |
| **Inheritance** | `Person` (common superclass for `Client`, `Instructor`, `Secretary`), Custom Exceptions inheriting from `Exception` |

