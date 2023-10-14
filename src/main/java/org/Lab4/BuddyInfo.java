package org.Lab4;
import jakarta.persistence.*;


@Entity
public class BuddyInfo {
    @Id
    @GeneratedValue
    private Integer id = null;
    private String name;
    private String number;


    /**
     * Constructor for the BuddyInfo Class
     * @param name - name of the Buddy
     * @param number - number of the Buddy
     */
    public BuddyInfo(String name, String number) {
        this.name = name;
        this.number = number;
    }

    /**
     * Second constructor for the BuddyInfo Class
     * used to be compatible with JPA
     */
    public BuddyInfo(){

    }

    /**
     * Method to return the number of the buddy
     * @return number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Method to return the name of the buddy
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to return the id of the Buddy Info object
     * @return id of the BuddyInfo entity
     */
    public Integer getId() { return id; }

    public void setName(String budName){
        this.name = budName;
    }

    public void setNumber (String budNumber){
        this.number = budNumber;
    }

    /**
     * Override method to help print BuddyInfo objects
     * @return string format of the BuddyInfo object
     */
    @Override
    public String toString() {
        return "Id: "+ id + "Name: " + name + "  Number: " + number;
    }
}
