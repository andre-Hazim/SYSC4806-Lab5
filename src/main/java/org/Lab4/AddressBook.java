package org.Lab4;



import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue
    private Integer id;

    /* array to store all the buddies in the address book */

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BuddyInfo> buds;

    /**
     * Constructor for the Address book class
     */
    public AddressBook(){
        buds = new ArrayList<>();
    }

    /**
     * @param b BuddyInfo object to be added in the address book
     */
    public  void addBuddy(BuddyInfo b){
        buds.add(b);
    }

    /**
     * @param b BuddyInfo object to be removed from address book
     */
    public void removeBuddy(BuddyInfo b){
        buds.remove(b);
    }

    /**
     * Method to fetch the buddies in the address book
     * @return buds - list of buddies in address book
     */
    public List<BuddyInfo> getBuds(){
        return buds;
    }

    /**
     * Method to get the id of the Address Book
     * @return id of the Address Book
     */
    public Integer getId() {
        return id;
    }

    /**
     * Method to print contents in the address book
     */
    public void print(){
        for (BuddyInfo buddy : buds) {
            System.out.println(buddy);
        }
    }

    /**
     * @return Buddies in a string
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("id: " + id + " buddies: {");
        for (BuddyInfo buddy : buds) {
            result.append(buddy.toString()).append("\n");
        }
        result.append("}\n");
        return result.toString();
    }
}
