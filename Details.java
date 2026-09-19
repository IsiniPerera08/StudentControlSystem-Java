/*******************************************************************************
*    Purpose : This file contains data of the a students academic information 
*               such as courseEnrolled, yearLevel, cwa, status, creditsEarned
*******************************************************************************/

//This cllass stores and manages the academic details of a student
//used final so a subclass cannot be made our of Details so cannot be extended

public final class Details{
    private String courseEnrolled;      //stores the course enrolled
    private int yearLevel;              //stores the current year level ( 1 - 4 )
    private double cwa;                 //stores the Course Wighted Average ( 0 - 100 )
    private String status;              //stores the status ("FT" or "PT")
    private int creditsEarned;          //stores credits earned ( o - 400 )

    //Default constructor-- creates a blank details object so program initialize objects without errors
    public Details(){
        this.courseEnrolled = "";       //empty string for course
        this.yearLevel = 1;             //default year level set to 1 (as the 1st yr)
        this.cwa = 0.0;                 //default CWA is 0.00 until values are entered
        this.status = "FT";             //default status is Full time ("FT")
        this.creditsEarned = 0;         //default credits earned is 0 until values entered
    }


    //Parametarized constructor- allows to create a Detailed object directly with real values
    //Calls the setter methods so that all validation rules are applied
    public Details(String courseEnrolled, int yearLevel, double cwa, String status, int creditsEarned){
        setCourseEnrolled(courseEnrolled);      //validates and assign courseEnrolled
        setYearLevel(yearLevel);                //validates and assign yearLevel
        setCwa(cwa);                            //validates and assign CWA
        setStatus(status);                      //validates and assign status
        setCreditsEarned(creditsEarned);        //validates and assign creditsEarned
    }

    //Getters- returns values stored in private variable
    public String getCourseEnrolled(){          //returns the student's courseEnrolled
        return courseEnrolled;
    }
    public int getYearLevel(){                  //returns the yearLevel of the student
        return yearLevel;
    }
    public double getCwa(){                     //returns the student's CWA
        return cwa;
    }
    public String getStatus(){                  //returns the student's status ("FT" or "PT")
        return status;
    }
    public int getCreditsEarned(){              //returns the students creditsEarned
        return creditsEarned;
    }


    //Setters- sets values for variables and validates
    public void setCourseEnrolled(String courseEnrolled){                   //set course Enrolled
        if (courseEnrolled == null || courseEnrolled.trim().isEmpty()){     //checks if its null or empty
            throw new IllegalArgumentException("Course cannot be empty");  
        }
        this.courseEnrolled= courseEnrolled.trim();     //if valid, removes the extra spaces and assign
    }

    public void setYearLevel(int yearLevel){        //set yearLevel
        if (yearLevel < 1 || yearLevel > 4){        //checks if within the range
            throw new IllegalArgumentException("Year Level must be between 1 and 4");
        }
        this.yearLevel= yearLevel;          //if valid, stores yearLevel
    }

    public void setCwa(double cwa){         //set CWA
        if (cwa < 0.0 || cwa > 100.0){      //checks if within the range
            throw new IllegalArgumentException("CWA must be between 0 and 100");
        } 
        this.cwa= cwa;          //if valid, stores CWA
    }

    public void setStatus(String status){           //set status
        if (status == null){                        //checks if its null
            throw new IllegalArgumentException("Status cannot be empty");
        }
            String s = status.trim().toUpperCase();     //removes the spaces of the input & converts to uppercase
                if ( !s.equals("FT") && !s.equals("PT")){   //checks if its "FT" or "PT"
                    throw new IllegalArgumentException("Status must be capitalized (FT) or (PT)");
                }
                this.status = s;        //if valid, stores status in uppercase
    }

    public void setCreditsEarned(int creditsEarned){        //sets credits earned
        if (creditsEarned < 0 || creditsEarned > 400){      //checks if within the range
            throw new IllegalArgumentException("Credits earned must be between 0 and 400");
        }
        this.creditsEarned= creditsEarned;      //if valid, stores creditsEarned
    }


    //checks if the student can graduate or not (creditsEarned > 400)
    public boolean isEligibleToGraduate(){
        return creditsEarned >= 400;        //returns true if > 400 else false when <400
    }


    //convert the object's data into CSV 
    public String toCSV(){        //each value is seperated by commas
        return  courseEnrolled+ "," +
                yearLevel+ "," +
                cwa+ "," +
                status+ "," +
                creditsEarned;
    }
}
