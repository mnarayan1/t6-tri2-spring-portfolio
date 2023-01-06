package com.nighthawk.spring_portfolio.mvc.person;

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
@Entity
@TypeDef(name="json", typeClass = JsonType.class)
public class Person {
    
    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // email, password, roles are key attributes to login and authentication
    @NotEmpty
    @Size(min=5)
    @Column(unique=true)
    @Email
    private String email;

    @NotEmpty
    private String password;

    // @NonNull, etc placed in params of constructor: "@NonNull @Size(min = 2, max = 30, message = "Name (2 to 30 chars)") String name"
    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Column(nullable = true)
    private Integer height=0;

    @Column(nullable = true)
    private Double weight=0.0;

    @NonNull
    private Integer totalSteps = 0; 

    //private double weight;
    //private int goalSteps;
    

    /* HashMap is used to store JSON for daily "stats"
    "stats": {
        "2022-11-13": {
            "calories": 2200,
            "steps": 8000
        }
    }
    */
    @Type(type="json")
    @Column(columnDefinition = "jsonb")
    private Map<String,Map<String, Object>> stats = new HashMap<>(); 
    

    // Constructor used when building object from an API
    public Person(String email, String password, String name , Date dob , int height , double weight, int goalSteps, int totalSteps) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.height = height;
        this.weight = weight;
        this.totalSteps = 0;
    }

    

    // A custom getter to return age from dob attribute
    public int getAge() {
        if (this.dob != null) {
            LocalDate birthDay = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return Period.between(birthDay, LocalDate.now()).getYears(); }
        return -1;
    }

    public int getGoalSteps() {
        if (this.getAge() >= 20) {
            return 10000; } 
        else if ((this.getAge() < 20) && (this.getAge() > 11)) {
            return 12500;
        }
        return 13500;
    }

    public int addSteps(int steps)
    {
        this.totalSteps = 0;
        this.totalSteps += steps;
        return this.totalSteps;
    }


    public String personNameToString(){
        return ( "{ \"name\": "  +this.name + " }" );
     }	

    public String personAttributesToString(){
        return ( "{ \"name\": "  +this.name+  ", " + "\"email\": "  +this.email+ ", " + "\"password\": "  +this.password+ ", " + "\"dob\": "  +this.dob+ ", " + "\"age\": "  +this.getAge()+", " + "\"height\": "  +this.height+", " + "\"weight\": "  +this.weight+ "\"goalSteps\": "  +this.getGoalSteps()+" }"  );
     }
    
    public String personStepsToString(int finalTotalSteps){
        return ( "{ \"name\": "  +this.name +  ", " + "\"totalSteps\": "  +finalTotalSteps + " }" );
    }

    // public String toString() { 
    //     return personNameToString(); 
    //  }

   
    

    //Tester Method for Person POJO
    public static void main(String[] args) {
        Person testPersonNotBob = new Person();
        System.out.println("\nTesting zero argument...");
        System.out.println(testPersonNotBob);
        System.out.println("zero argument get email: " + testPersonNotBob.getEmail());

        LocalDate locBirthday = LocalDate.of(2011, 1, 1);
        Date birthday = Date.from(locBirthday.atStartOfDay(ZoneId.systemDefault()).toInstant());

        System.out.println("\nTesting all argument...");
        Person testPerson = new Person("bob@gmail.com", "hammer10!", "Bob the Builder", birthday, 65, 150.2, 10000, 0);
        System.out.println("email: " + testPerson.getEmail());
        System.out.println("name: " + testPerson.getName());
        System.out.println("password: " + testPerson.getPassword());

        testPerson.setPassword("theNewHammer11!");
        //testPerson.setDob(2022-11-10);
        System.out.println("new password: " + testPerson.getPassword());
        System.out.println("date of birth: " + testPerson.getDob());
        System.out.println("age: " + testPerson.getAge());

        System.out.println(testPerson.personNameToString());
        System.out.println(testPerson.personAttributesToString());
    }


}