# COMP1007-StudentControlSystem-Java-
Developed a Java-based Student Management System using arrays and CSV file handling to manage student records. Features include adding and editing students, filtering by course/status, CWA analysis, and graduation eligibility checks.

Overview 
This is a program to manage students using java, arrays and a CSV file. You can 
•  Add new students 
•  Edit students 
•  View all students 
•  Filter by course 
•  Filter by status  
•  Highest CWA, 
•  Average CWA for each course 
•  Credit Analysis (eligible to graduate or not) 
Files in this folder (23601321_IsiniAyansaPerera(PDI)) 
• Details.java- This stores the academic data for one student and validates the data such 
as courseEnrolled, yearLevel, CWA, status(FT/PT), creditsEarned. 
• Student.java- Stores students’ information such as StudentID, firstName, lastName and a 
Details object. 
• StudentSystem.java- The main program, it loads, saves the CSV and prints the menu to 
the user. 
• data.csv- The CSV file that contains the data 
• Video demonstration the work 
• The pseudocode file- The file that contains the pseudocode of my three java files. 
• COMP1007 Assignment Self‑Check Poster 
• README- Student Control System 
How to compile and run 
Navigate to the relevant folder of the files in terminal and compile the main program as 
‘javac StudentSystem.java’. Then run the program using the command ‘java StudentSystem’. 
Then the terminal will output the menu then by choosing the option functions can be done. 
Data file format (data.csv) 
The program reads this file at the start of the program and writes to it once student add or 
edits and also when the program is exited. The data of the students are in the order of- 
‘studentID,firstName,lastName,courseEnrolled,yearLevel,cwa,status,creditsEarned’. 
2 
Example: 123456,Himandi,Fernando,Networking,1,50,FT,400 
Dependancies 
• Java Development Kit (JDK) 17 
• Terminal to run the program 
• CSV file: data.csv in the same folder as the other java files 
Features of the program 
• Add new student: in this it validates all fields and it requires a unique student ID 
• Edit student: student is found by the unique ID, can press ‘Enter’ to keep an old value, 
and new values are again validates 
• View all Students: this prints all students that are saved in the CSV file in the CSV format 
• Filter by Course: this finds the other courses that is equal to what the user inputed 
• Filter by status: this finds the students that are either FT or PT based on the user input 
• Highest CWA: this would find the maximum CWA and list all the students who have it 
• Average CWA per course: this would calculate the average for all the courses and groups 
by course and prints average CWA 
• Credit Analysis: this shows whether a student is eligible or not and in the end outputs 
the total eligibility counts out of the total
