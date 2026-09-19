/*******************************************************************************
*    Purpose : This manages the students using arrays and CSV, this loads
*               saves data from Details.java and Student.java, provides a menu
*               and does the task according to the choice of the user.
*******************************************************************************/



import java.io.*; // for file reading and writing

public class StudentSystem
{

    private static final int maxNum = 1000;     //can add 1000 students max
    private static final Student[] students = new Student[maxNum];      //creates an array taht can store upto 1000 student objects
    private static int count = 0;       //tracks how many students are currently stored

    public static void main(String[] args) 
    {
        readStudentsFromFile("data.csv");   //reads data from CSV file
        boolean running = true;     //used to control when to stop the menu loop
        while (running)         //while loop runs until running is false
        {
            printMenu();        //shows the menu
            int choice = readInt("Enter option: ");     //read users choice
            switch (choice)
            {
                case 1: addStudent(); writeStudentsToFile("data.csv"); break;       //adds and save
                case 2: editStudent(); writeStudentsToFile("data.csv"); break;      //edits and save
                case 3: viewAllStudents(); break;       //print all students to console
                case 4: filterByCourse(); break;        //show students filtered by courseEnrolled     
                case 5: filterByStatus(); break;        //show students filtered by FT or PT
                case 6: showHighestCWA(); break;        //finds and show students with maxCwa
                case 7: showAverageCWAPerCourse(); break;   //shows average CWA grouped by course
                case 8: showGraduationEligibility(); break; //show students eligible                  
                case 9: running = false; writeStudentsToFile("data.csv"); break;    //exit loop and save data once more
                default: System.out.println("Invalid choice");      //if invalid, outputs an error
            }
        }
    }

    private static void printMenu()     //prints the menu
    {
        System.out.println("==========================================");
        System.out.println("        Welcome to Student Central        ");
        System.out.println("==========================================");
        System.out.println("Your options for this system are listed below");
        System.out.println("1> Add new student");
        System.out.println("2> Edit student");
        System.out.println("3> View all students");
        System.out.println("4> Filter by course");
        System.out.println("5> Filter by status");
        System.out.println("6> Highest CWA");
        System.out.println("7> Average CWA for each course");
        System.out.println("8> Credit Analysis");
        System.out.println("9> Exit");
    }


    private static void readStudentsFromFile(String fileName){     //reads students from CSV file
        count = 0;                                                  //resets how many students we have before loading
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){    //reads from the file one line at a time
            br.readLine();                           //reads and ignores the header line (column names)
            String current;                                         //holds each data line
            while ((current = br.readLine()) != null && count < maxNum){            //keeps reading until file ends or array is full
                String[] parts = current.split(",", -1);            //split the CSV file into parts, -1 keeps the empty fields
                if (parts.length < 8){      //if less than 8, the line is incomplete
                    System.out.println("Skipping row: " + current); //tells the user we skipped it
                    continue;           //moves to the next line
                }


                try{
                    String studentID      = parts[0].trim();        //Column1: studentID
                    String firstName      = parts[1].trim();        //Column2: firstName
                    String lastName       = parts[2].trim();        //Column3: lastName
                    String courseEnrolled = parts[3].trim();        //Column4: course
                    int yearLevel         = Integer.parseInt(parts[4].trim());        //Column5: yearLevel
                    double cwa            = Double.parseDouble(parts[5].trim());      //Column6: cwa
                    String status         = parts[6].trim();        //Column7: status
                    int creditsEarned     = Integer.parseInt(parts[7].trim());        //Column8: creditsEarned

                    Details d = new Details();              //create Details object for academic fields
                    d.setCourseEnrolled(courseEnrolled);    //this validates and sets course
                    d.setYearLevel(yearLevel);              //this validates and set yearLevel
                    d.setCwa(cwa);                          //this validates and sets cwa
                    d.setStatus(status);                    //this validates and set Status
                    d.setCreditsEarned(creditsEarned);      //validates and set credits

                    students[count++]= new Student(studentID, firstName, lastName, d);      //build student and store in array
                } 
                catch (Exception e){        //catches number parsing or validation errors
                    System.out.println("Error: skipped : " + e.getMessage());       //show reason; continue safely
                }
            }
        } 
        catch (IOException ex){             //file not found or cannot open/read
            System.out.println("CSV not found, it will be created on first save");
        }
    }


    private static void writeStudentsToFile(String fileName){       //writes the current array back to CSV
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))){       //opens it to write, overwrite and autoclose
            pw.println("StudentID,FirstName,LastName,CourseEnrolled,YearLevel,CWA,Status,CreditsEarned");       //header line
            for (int i = 0; i < count; i++){        //iterate over filled portion of the array
                pw.println(students[i].toCSV());    //delegate CSV format to Student.toCSV()
            }
        } 
        catch (IOException e) {             //file writing
            System.out.println("Error writing to file: " + e.getMessage());         //report the issue to the console
        }
    }


    private static void viewAllStudents(){      //prints students in memory
        if (count == 0){
            System.out.println("No students available");        //outputs that no students available
        } 
        else{                           //else print for each
            for (int i = 0; i < count; i++){
                System.out.println(students[i].toCSV());        //print one student per line in CSV style
            }
        }
    }


    private static void filterByCourse(){           //shows only students in a specific course
        String course = readLine("Enter a course: ").trim().toLowerCase(); //input is in lowercase
        boolean matchFound = false;     //tracks if anything matched
        for (int i = 0; i < count; i++){        //scan all students
            String courseName= students[i].getDetails().getCourseEnrolled();        //gets the i-th student
            if (courseName != null && courseName.toLowerCase().equals(course)){     //checks if student has no course and lowercased
                System.out.println(students[i].toCSV());        //if the course matches,we print that student in CSV
                matchFound = true;      //Flag tht tells the code after the code whether atleast one match found
            }
        }
        if (!matchFound){
            System.out.println("No students found for course: " + course);
        }
    }


    private static void filterByStatus(){           //shows FT or PT students
        String statusEntered = readLine("Enter status (FT/PT): ").trim().toUpperCase();     //input is to uppercase
        if (!statusEntered.equals("FT") && !statusEntered.equals("PT")) {       //validates allowed values
            System.out.println("Input invalid");            //early exit if not FT/PT
            return;
        }
        boolean matchFound = false;         //track if anything matched
        for (int i = 0; i < count; i++){    //scan all students
            if (students[i].getDetails().getStatus().equals(statusEntered)){        //status equality check
                System.out.println(students[i].toCSV());        //print matching student, print in CSV
                matchFound = true;      //match is then true
            }
        }
        if (!matchFound){       //if no matches, gives an output
            System.out.println("No students with status: " + statusEntered);
        }
    }


    private static void addStudent(){
        if (count >= maxNum){       //checks the capacity
            System.out.println("No space");     //when out of space outputs
            return;         //stops from here
        }

        String id;      //holds new students ID
        while (true){   //keeps asking until valid
            id = readLine("Student ID: ").trim(); //read and trim
            if (id.isEmpty()){      //not allowed to be empty
                System.out.println("StudentID cannot be empty");        //outputs it
                continue;           //skips it and asks again
            }
            if (indexOfId(id) != -1){       //checks if ID already exists
                System.out.println("ID already exists");
                continue;       //skips and asks again
            }
            break;      //if valid, exits the loop
        }

        String first = readNonEmpty("First name: ");        //must not be empty
        String last  = readNonEmpty("Last name: ");         //must not be empty

        Details d = new Details();      //creates empty Details to fill gradually

        while (true){       //keeps asking until input is valid
            try{
                d.setCourseEnrolled(readNonEmpty("Course Enrolled: "));     //must not be empty
                break;      //exits and
            } 
            catch (IllegalArgumentException e){     //if empty throws an error message
                System.out.println("Error: " + e.getMessage());
            }
        }
        while (true){       //keeps asking until input is a valid integer
            try{
                d.setYearLevel(readInt("Year level (1-4): ", 1, 4));
                break;
            } 
            catch (IllegalArgumentException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        while (true){
            try{
                d.setCwa(readDouble("CWA (0-100): ", 0, 100));
                break;
            } 
            catch (IllegalArgumentException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        while (true){
            try{
                d.setStatus(readNonEmpty("Status (FT/PT): "));
                break;
            } 
            catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        while (true){
            try{
                d.setCreditsEarned(readInt("Credits earned (0-400): ", 0, 400));
                break;
            } 
            catch (IllegalArgumentException e){
                System.out.println("Error: " + e.getMessage());
            }
        }

        try{           //final construction and insert into array
            students[count++] = new Student(id, first, last, d);        //add to the in-memory list
            System.out.println("Student added");        //to give a successful message
        } 
        catch (IllegalArgumentException e){
            System.out.println("Failed to add Student: " + e.getMessage()); //if invalid, gives an error message
        }
    }


    private static void editStudent(){          //allows updating fields
        String id = readLine("Enter the Student ID: ").trim();      //asks whom to edit
        int indexId = indexOfId(id);            //find index in array
        if (indexId == -1){                     //if not found
            System.out.println("Student not found");                //ouputs an error message
            return;                             //stops editing
        }

        Student s= students[indexId];       //get the target student
        Details d = s.getDetails();         //get their details
        System.out.println("Editing: " + s.toCSV());        //shows the current record


        //for each field, read new value, if blank keep old, else validate and set
        String first = readLine("New firstName (" + s.getFirstName() + "): ");
        if (!first.trim().isEmpty()){
            try { s.setFirstName(first); } catch (IllegalArgumentException e) { System.out.println("Ignored: " + e.getMessage()); 
            }
        }

        String last = readLine("New lastName (" + s.getLastName() + "): ");
        if (!last.trim().isEmpty()){
            try { s.setLastName(last); } catch (IllegalArgumentException e) { System.out.println("Ignored: " + e.getMessage()); 
            }
        }

        String course = readLine("New course (" + d.getCourseEnrolled() + "): ");
        if (!course.trim().isEmpty()){
            try { d.setCourseEnrolled(course); } catch (IllegalArgumentException e) { System.out.println("Ignored: " + e.getMessage()); 
            }
        }

        String yearString = readLine("New year (" + d.getYearLevel() + "): ");
        if (!yearString.trim().isEmpty()){
            try { d.setYearLevel(Integer.parseInt(yearString.trim())); } catch (Exception e) { System.out.println("Ignored: Year must be 1 to 4"); 
            }
        }

        String cwaString = readLine("New CWA (" + String.format("%.2f", d.getCwa()) + "): ");
        if (!cwaString.trim().isEmpty()){
            try { d.setCwa(Double.parseDouble(cwaString.trim())); } catch (Exception e) { System.out.println("Ignored: CWA must be 0 to 100"); 
            }
        }

        String status = readLine("New status (FT/PT) (" + d.getStatus() + "): ");
        if (!status.trim().isEmpty()){
            try { d.setStatus(status); } catch (IllegalArgumentException e) { System.out.println("Ignored: " + e.getMessage()); 
            }
        }

        String creditsString = readLine("New credits earned (" + d.getCreditsEarned() + "): ");
        if (!creditsString.trim().isEmpty()){
            try { d.setCreditsEarned(Integer.parseInt(creditsString.trim())); } catch (Exception e) { System.out.println("Ignored: credits earned must be 0 to 400"); 
            }
        }
        System.out.println("Edit complete");        //once done, outputs message
    }


    private static void showHighestCWA(){       //prints the max CWA and all students who have it
        if (count == 0){                        //means no data so nothing to calculate
            System.out.println("No students");
            return;
        }

        double max = students[0].getDetails().getCwa();     //start by assuming  first student's CWA is max
        for (int i = 1; i < count; i++){                    //loops through all remaining students to find the actual highest CWA
            double c = students[i].getDetails().getCwa();
            if (c > max) max = c;       //updates when highest CWA is found; c=CURRENT CWA, max=HIGHEST CWA
        }

        int shown = 0;         //keeps track of how many students hv this highest CWA (like ties) 
        for (int i = 0; i < count; i++){    //go through the list again to print all students who share that CWA
            if (students[i].getDetails().getCwa() == max){
                System.out.println(students[i].toCSV());        //prints full details of the student in CSV format
                shown++;        //increase the counter each time we print one
            }
        }
        System.out.printf("Highest CWA = %.2f (students shown: %d)%n", max, shown); //%.d:whole num, %n:new line
    }


    private static void showAverageCWAPerCourse(){      //calcultes per course average CWA using simple arrays
        if (count == 0){                                //if no data, it stops
            System.out.println("No students");          //outputs it
            return;
        }

        String[] courses = new String[maxNum];           //temporary list of distinct course names
        int nCourses = 0;                                //how many distinct courses found

        for (int i = 0; i < count; i++){        //collect distinct course names
            String c = students[i].getDetails().getCourseEnrolled();
            if ( c == null) c = "";         //makes current null to empty string
            boolean seen = false;           //checks if course already in the list
            for (int j = 0; j < nCourses; j++){
                if (courses[j].equals(c)){
                    seen = true;            //if found, mark and stop the inner loop
                    break; 
                }
            }
            if (!seen) courses[nCourses++] = c;     //if not found adds to list
        }

        //for each distinct course, calculate average CWA
        for (int j = 0; j < nCourses; j++){
            String target = courses[j];         //the current course we are averaging
            double sum = 0.0;                   //sum of CWAs for this course
            int n = 0;                          //how many students in course
            for (int i = 0; i < count; i++){    //scan all students and accumulate
                if (students[i].getDetails().getCourseEnrolled().equals(target)){
                    sum += students[i].getDetails().getCwa();
                    n++;
                }
            }
            double avg = (n == 0) ? 0.0 : sum/n;        //guard divide by zero (should not happen)
            System.out.println(target + " -> Average CWA: " + String.format("%.2f", avg) + " (n=" + n + ")");       //prints the result
        }
    }


    private static void showGraduationEligibility(){        //print each student's eligibilty 
        if (count==0){          //if current students are empty
            System.out.println("No Students");
            return;
        }

        int eligible = 0;       //counts how many are eligible

        for (int i = 0; i< count; i++){     //loops through al stored students
            boolean canGraduate = students[i].getDetails().isEligibleToGraduate();    //true if credits >= 400
            if (canGraduate) eligible++;   //adss 1 to the counter when eligible

            System.out.println("Student ID: " +students[i].getStudentID()); //prints ID of student
            System.out.println("Student First Name: " +students[i].getFirstName()); //prints firstName of student
            System.out.println("Student Last Name: " +students[i].getLastName());   //prints lastName of student
            System.out.println("Eligibility: " +(canGraduate? "Eligible" : "Not Eligible")); //if can eligible it outputs
        }
        System.out.println("Eligible to graduate: " +eligible+ " out of " +count);   //prints the toal eligible out of total students
    }


    private static int indexOfId(String id){                        //returns array indec of studentID, or -1 if not found
        String key = (id == null) ? "" : id.trim();                 //normalize null "" and removes spaces
        for (int i = 0; i < count; i++){                            //linear search
            if (students[i].getStudentID().equals(key)) return i;   //match return index immediately
        }
        return -1;                                                  //no match found
    }


    private static String readLine(String prompt){                  //prints a prompt and returns the line the user typed
        System.out.print(prompt);                                   //show prompt without newline
        try{
            return new BufferedReader(new InputStreamReader(System.in)).readLine();     //read a line from console
        } 
        catch (IOException e){
            return "";          //when error, returns empty string to keep program running
        }
    }


    private static String readNonEmpty(String prompt){          //keeps asking until user types a non empty line
        String s;                                               //stores user input
        do{
            s = readLine(prompt);                               //reads the input
            if (s == null) s = "";                              //normalize null to empty string
            s = s.trim();                                       //remove surrounding spaces
            if (s.isEmpty()) System.out.println("Cannot be empty.");        //warning if empty
        } 
        while (s.isEmpty());            //repeats until not empty
        return s;                       //returns the valid text
    }


    private static int readInt(String prompt){          //reads an int safely, keeps asking until valid
        while (true){                                   //infinite loop, breaks when valid
            try {
                return Integer.parseInt(readLine(prompt).trim());       //tries parsing
            } 
            catch (Exception e){                        //any failure gives a warning and asks again
                System.out.println("Invalid number.");
            }
        }
    }


    private static int readInt(String prompt, int min, int max){    //reads int within [min, max], keeps asking otherwise
        int val;                                                    //holds parsed value
        do{
            val = readInt(prompt);                                  //reads a plain int first
            if (val < min || val > max)                             //checks the boundaries
                System.out.println("Enter a value in [" + min + ", " + max + "].");     //tell user the allowed range
        } 
        while (val < min || val > max);                             //repeat until in range
        return val;                                                 //return the validated value
    }


    private static double readDouble(String prompt, double min, double max){        //reads double within [min,max], retry when error
        double val;                                                  //holds parsed value
        do{
            try{
                val = Double.parseDouble(readLine(prompt).trim());   //attempts parsing
            } 
            catch (Exception e){
                val = min - 1;                                       //force loop to continue on failure
            }
            if (val < min || val > max)
                System.out.println("Enter a number in [" + min + ", " + max + "].");        //show allowed range
        } 
        while (val < min || val > max);                              //repeat until valid
        return val;                                                  //return validated double
    }
}
