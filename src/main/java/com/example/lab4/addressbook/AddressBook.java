package com.example.lab4.addressbook;

import com.example.lab4.buddyinfo.BuddyInfo;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;


@Entity
public class AddressBook {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<BuddyInfo> buddies;

    public AddressBook() {
        buddies = new HashSet();
    }

    public AddressBook(long id) {

        buddies = new HashSet();
        this.id = id;
    }

    public void addBuddy(BuddyInfo Buddy) {
        if (Buddy != null) {
            buddies.add(Buddy);
        }
    }

    public void removeBuddy(BuddyInfo buddy) {
        Iterator<BuddyInfo> buddyIterator = buddies.iterator();
        while (buddyIterator.hasNext()) {
            if (buddyIterator.next() == buddy) {
                buddyIterator.remove();
            }
        }
    }

    public Collection<BuddyInfo> getBuddies() {
        return this.buddies;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        String addressBookString = "AddressBook ID: " + id + "\n";
        for (BuddyInfo buddy : buddies) {
            // This line may be causing the LazyInitializationException error
            addressBookString = addressBookString + "\n" + buddy.toString();
        }
        return addressBookString;
    }

    public static void main(String[] args) {
        System.out.println("Address Book");

        // Create buddy objects
        BuddyInfo buddy1 = new BuddyInfo("Tom", "Carleton", "613");
        BuddyInfo buddy2 = new BuddyInfo("Diana", "Carleton", "647");
        BuddyInfo buddy3 = new BuddyInfo("Simon", "Seneca", "905");

        // Create an address book object and add the buddies
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy1);
        addressBook.addBuddy(buddy2);
        addressBook.addBuddy(buddy3);

        // Print the address book
        addressBook.toString();
    }
}