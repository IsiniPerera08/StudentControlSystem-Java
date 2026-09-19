/*******************************************************************************                                                       
*    Purpose : This java file contains the student's ID, firstName, lastName   
*              and a details object                                                                                                                      
*******************************************************************************/


//this class stores each student's persona info and academic details
public final class Student{     //'final' so that cannot make a subclass out of this class
    private String studentID;   //stores the unique studentID in String
    private String firstName;   //store the firstName in string
    private String lastName;    //store the lastName in string
    private Details details;    //it is a seperate object tht holds course/yr/cwa/status/credits

    // efault constructor- creates a blank student object so program initializes with validation
    public Student(){
        this.studentID= "";     //empty string for studentID
        this.firstName= "";     //empty string for firstName
        this.lastName= "";      //empty string for lastName
        this.details = new Details();       //creates an empty Details object this avoids null errors
    }

    //Parameterized constuctor= used when all student data is already known
    //Calls the setter methods so that all validation rules are applied
    public Student(String studentID, String firstName, String lastName, Details details){
        setStudentID(studentID);        //validates and assign student ID
        setFirstName(firstName);        //validates and assign firstName
        setLastName(lastName);          //validates and assign lastName
        setDetails(details);            //validates and assign the Details object
    }


    //Getters- allows other classes to access private variables
    public String getStudentID(){
        return studentID;           //returns the student's ID
    }

    public String getFirstName(){
        return firstName;           //returns the student's firstName
    }

    public String getLastName(){
        return lastName;            //returns the student's lastName
    }
    
    public Details getDetails(){
        return details;             //returns the details object for this student
    }


    //Setters- sets values for variables and validates
    public void setStudentID(String studentID){
        if(studentID == null || studentID.trim().isEmpty()){        //check if its null or empty
            throw new IllegalArgumentException("StudentID cannot be empty");
        }
        this.studentID = studentID.trim();      //removes the unwanted space and assign
    }

    public void setFirstName(String firstName){
        if (firstName == null || firstName.trim().isEmpty()){       //check if its null or empty
            throw new IllegalArgumentException("First name cannot be empty");
        }
        this.firstName = firstName.trim();      //removes the unwanted space and assign
    }

    public void setLastName(String lastName){
        if (lastName == null || lastName.trim().isEmpty()){         //checks if its null or empty
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        this.lastName = lastName.trim();        //removes the unwated space and assign
    }

    public void setDetails(Details details){
        if (details == null){           //cannot assign null values
            throw new IllegalArgumentException("Details cannot be empty");
        }
        this.details = details;         //store the given details object
    }


    //convert student data to CSV format 
    public String toCSV(){      //each value is seperated by commas
        return  studentID + "," + firstName + "," + lastName + "," + details.toCSV();
    }
}
