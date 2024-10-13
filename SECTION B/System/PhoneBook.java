package System;

import java.util.*;

public class PhoneBook {
    private Map<String, String> contacts;

    public PhoneBook() {
        contacts = new HashMap<>();  // Using HashMap for simplicity and efficient search

        // Adding default contacts
        addContact("Alice", "085 453 7890");
        addContact("Bob", "081 567 8901");
        addContact("Charlie", "081 678 9012");
    }

    // Function to add a contact, only adds if contact doesn't already exist
    public boolean addContact(String name, String phone) {
        if (contacts.containsKey(name)) {
            return false;  // Contact already exists
        } else {
            contacts.put(name, phone);
            return true;  // Contact added successfully
        }
    }

    // Function to search a contact by name or phone number, returns the contact details or null if not found
    public String searchContact(String name, String phone) {
        // Check by name
        if (contacts.containsKey(name)) {
            return name + ": " + contacts.get(name); // Return found contact by name
        }

        // Check by phone number
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            if (entry.getValue().equals(phone)) {
                return entry.getKey() + ": " + entry.getValue(); // Return found contact by phone
            }
        }

        return null; // Not found
    }

    // Function to display all contacts, returns a list of contacts
    public List<String> getAllContacts() {
        List<String> contactList = new ArrayList<>();
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            contactList.add(entry.getKey() + ": " + entry.getValue());
        }
        return contactList;
    }

    // Function to delete a contact by name
    public boolean deleteContact(String name) {
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            return true;  // Contact deleted successfully
        } else {
            return false;  // Contact not found
        }
    }

    // Function to update a contact (name and/or phone)
    public boolean updateContact(String oldName, String newName, String newPhone) {
        if (contacts.containsKey(oldName)) {
            contacts.remove(oldName); // Remove the old entry
            // Add the new entry with new name or keep the old name if newName is empty
            contacts.put(newName.isEmpty() ? oldName : newName, newPhone);
            return true;  // Contact updated successfully
        } else {
            return false;  // Contact not found
        }
    }

    // Function to check the size of the contact list
    public boolean hasContacts() {
        return !contacts.isEmpty();
    }
}
