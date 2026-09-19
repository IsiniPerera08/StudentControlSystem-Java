# COMP1007 – Student Control System (Java)

A Java-based **Student Management System** developed. The system uses **arrays and CSV file handling** to store, manage, and analyse student records through a simple terminal-based interface.

## Overview

The Student Control System allows users to manage student academic records and perform different types of analysis.

### Main Functions

* Add new students
* Edit existing student records
* View all students
* Filter students by course
* Filter students by study status (FT/PT)
* Find students with the highest CWA
* Calculate average CWA for each course
* Analyse credit completion and graduation eligibility

---

## Project Structure

| File                                    | Description                                                                                                                 |
| --------------------------------------- | --------------------------------------------------------------------------------------------------------------------------- |
| `Details.java`                          | Stores and validates a student's academic information, including course, year level, CWA, study status, and credits earned. |
| `Student.java`                          | Stores a student's personal information, including Student ID, first name, last name, and their `Details` object.           |
| `StudentSystem.java`                    | Main program that loads student data, displays the menu, handles user input, and saves updated data to the CSV file.        |
| `data.csv`                              | CSV file used to store student records.                                                                                     |
| `Pseudocode`                            | Contains the pseudocode for the three Java classes.                                                                         |                                                                           |
| `Video Demonstration`                   | Demonstrates the functionality of the completed system.                                                                     |
| `README.md`                             | Documentation for the project.                                                                                              |

---

## Technologies Used

* **Java**
* **Arrays**
* **CSV File Handling**
* **Object-Oriented Programming**
* **Terminal / Command Line**

---

## Dependencies

The following are required to run the program:

* **Java Development Kit (JDK) 17**
* A terminal or command-line interface
* `data.csv` located in the same folder as the Java files

---

## How to Compile and Run

### 1. Navigate to the project folder

Open a terminal and navigate to the folder containing the Java files.

```bash
cd path/to/project
```

### 2. Compile the program

Compile the main Java program using:

```bash
javac StudentSystem.java
```

### 3. Run the program

```bash
java StudentSystem
```

The program will display a menu in the terminal. Select an option from the menu to perform the required operation.

---

## Data File Format

The system uses a CSV file named `data.csv` to store student records.

The data is stored in the following order:

```text
studentID,firstName,lastName
```
