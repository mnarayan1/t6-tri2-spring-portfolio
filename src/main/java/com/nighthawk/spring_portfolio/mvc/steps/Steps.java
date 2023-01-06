package com.nighthawk.spring_portfolio.mvc.steps;
import com.nighthawk.spring_portfolio.mvc.person.Person;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/*
Person is a POJO, Plain Old Java Object.
First set of annotations add functionality to POJO
--- @Setter @Getter @ToString @NoArgsConstructor @RequiredArgsConstructor
The last annotation connect to database
--- @Entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Steps {
    
    
    @Column(nullable = true)
    private Person person; 

    @Column(nullable = true)
    private int totalSteps = 0; 

    @Column(nullable = true)
    private int numActiveDays = 0; 

    @Column(nullable = true)
    private int minSteps = 0; 

    @Column(nullable = true)
    private int numDays = 0; 

    @Column(nullable = true)
    private int totalMood = 0; 

    @Column(nullable = true)
    private int totalCalories = 0; 
    
   
    
    // Constructor used when building object from an API
    public Steps(Person person) {
        this.person = person; 
    }

    
    

    public int activeDays()
        {
            numActiveDays = person.getStats().keySet().size();
            return numActiveDays;
        }
    public double averageSteps()
        {   
            
            int dailySteps;
            for (String i: person.getStats().keySet()) {
                dailySteps = (int) person.getStats().get(i).get("steps");
                totalSteps += dailySteps;
            }
            int activeDaysNumber = person.getStats().keySet().size();
            if (activeDaysNumber == 0)
            {
            return 0.0;
            }
            else
            {
            return (double) totalSteps / activeDaysNumber;
            }
        }

        public double averageCalories()
        {   
            
            int dailyCalories;
            
            for (String i: person.getStats().keySet()) {
                dailyCalories = (int) person.getStats().get(i).get("calories");
                totalCalories += dailyCalories;
            }
            int calDaysNumber = person.getStats().keySet().size();
            if (calDaysNumber == 0)
            {
            return 0.0;
            }
            else
            {
            return (double) totalCalories / calDaysNumber;
            }
        }

    public String activeCheck() {
        double avgSteps = this.averageSteps();
        String message;
        if (avgSteps >= (double) person.getGoalSteps()) {
            message = ". Good job! You met your goal steps on average";
            return message;
        }
        else {
            message = ". You haven't met your goal steps on average yet";
            return message;
        }

    }
    
        public double averageMood()
        {   
            
            int dailyMood;
            
            for (String i: person.getStats().keySet()) {
                dailyMood = (int) person.getStats().get(i).get("mood");
                totalMood += dailyMood;
            }
            int calDaysNumber = person.getStats().keySet().size();
            if (calDaysNumber == 0)
            {
            return 0.0;
            }
            else
            {
            return (double) totalMood / calDaysNumber;
            }
        }
    
    
     public String activeDaysToString(){ 
        return ( "{ \"name\": "  +person.getName()+  ", " + "\"activeDays\": "   + this.activeDays() + " }" );
     }

     public String averageStepsToString(){ 
        return ( "{ \"name\": "  +person.getName()+  ", " + "\"averageSteps\": "   + this.averageSteps() + " }" );
     }

     public String averageMoodToString(){ 
        return ( "{ \"name\": "  +person.getName()+  ", " + "\"averageMood\": "   + this.averageMood() + " }" );
     }

     public String activeCheckToString(){ 
        return ( person.getName()+  ", " + "your average steps is " + this.averageSteps() + " and your goal was " + (double) person.getGoalSteps() + this.activeCheck());
     }

     public String averageCaloriesToString(){ 
        return ( "{ \"name\": "  +person.getName()+  ", " + "\"averageCalories\": "   + this.averageCalories() + " }" );
     }
   
   
    

    //Tester Method for Person POJO
    public static void main(String[] args) {
        Steps testPersonStepsNo = new Steps();
        System.out.println(testPersonStepsNo);
        

        LocalDate locBirthday = LocalDate.of(2011, 1, 1);
        Date birthday = Date.from(locBirthday.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Person myTestPerson = new Person("bob@gmail.com", "hammer10!", "Bob the Builder", birthday, 65, 150.2, 10000, 0);
        Steps testPersonSteps = new Steps(myTestPerson);
        System.out.println(testPersonSteps);
        
    }


}