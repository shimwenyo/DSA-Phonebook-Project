package GUI;

import System.PhoneBook; // Import the PhoneBook class

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhoneBookMenuGUI {
    private JFrame frame;
    private JLabel backgroundLabel;  // To hold the background image
    private PhoneBook phoneBook; // Instance of PhoneBook for backend functionality

    public PhoneBookMenuGUI(PhoneBook phoneBook) {
        this.phoneBook = phoneBook; // Assign the passed PhoneBook instance
        frame = new JFrame("Phone Book Main Menu");
        initialize();
    }

    private void initialize() {
        frame.getContentPane().removeAll();  // Clear the frame
        frame.repaint();  // Repaint the frame

        // Set the size of the frame to resemble a cell phone
        int width = 450;  // Cell phone width
        int height = 800;  // Cell phone height
        frame.setSize(width, 900);  // Set frame size
        frame.setLayout(null);  // Disable layout managers

        // Load the initial background image (Main Menu)
        backgroundLabel = new JLabel();
        setBackgroundImage("/Theme/PhoneBookMENU.png", width, height);

        // Create the initial buttons for the Main Menu
        createMainMenuButtons();

        frame.getContentPane().add(backgroundLabel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Method to dynamically change the background image
    private void setBackgroundImage(String imagePath, int width, int height) {
        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource(imagePath));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedBackgroundIcon = new ImageIcon(backgroundImage);
        backgroundLabel.setIcon(resizedBackgroundIcon);
        backgroundLabel.setBounds(0, 0, width, height);
        frame.getContentPane().add(backgroundLabel);
        frame.revalidate();
        frame.repaint();
    }

    // Method to create the buttons for the main menu
    private void createMainMenuButtons() {
        frame.getContentPane().removeAll();  // Clear the previous buttons and background

        // Create buttons for the main menu
        createButton(85, 265, "ADD_CONTACT");
        createButton(260, 265, "SEARCH_CONTACT");
        createButton(85, 420, "DELETE_CONTACT");
        createButton(260, 420, "DISPLAY_CONTACTS");
        createButton(85, 575, "UPDATE_CONTACT");
        createButton(260, 575, "SORT_CONTACTS");

        frame.getContentPane().add(backgroundLabel);
        frame.revalidate();
        frame.repaint();
    }

    // Modified to remove button text and accept an action identifier
    private void createButton(int x, int y, String action) {
        JButton button = new JButton();  // No text on the button
        button.setBounds(x, y, 100, 135);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);  // No border to keep it invisible
        button.addActionListener(new ButtonClickListener(action));
        frame.getContentPane().add(button);
    }

    private class ButtonClickListener implements ActionListener {
        private final String action;

        public ButtonClickListener(String action) {
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (action) {
                case "ADD_CONTACT":
                    new AddContactGUI(phoneBook); // Pass the PhoneBook instance
                    frame.dispose(); // Close the current menu window
                    break;

                case "SEARCH_CONTACT":
                    new SearchContactGUI(phoneBook); // Pass the PhoneBook instance
                    frame.dispose(); // Close the current menu window
                    break;

                case "DELETE_CONTACT":
                    new DeleteContactGUI(phoneBook); // Pass the PhoneBook instance
                    frame.dispose(); // Close the current menu window
                    break;

                case "DISPLAY_CONTACTS":
                    new DisplayContactsGUI(phoneBook); // Pass the PhoneBook instance
                    frame.dispose(); // Close the current menu window
                    break;

                case "UPDATE_CONTACT":
                    new UpdateContactGUI(phoneBook); // Pass the PhoneBook instance
                    frame.dispose(); // Close the current menu window
                    break;

                case "SORT_CONTACTS":
                    new SortContactsGUI(phoneBook); // Pass the PhoneBook instance
                    frame.dispose(); // Close the current menu window
                    break;

                default:
                    JOptionPane.showMessageDialog(frame, "Unknown action: " + action);
            }
        }
    }
}
