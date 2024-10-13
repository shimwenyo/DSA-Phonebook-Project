package GUI;

import System.PhoneBook;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AddContactGUI {
    private JFrame frame;
    private PhoneBook phoneBook; // Connects with the PhoneBook system
    private BufferedImage backgroundImage;
    private JTextArea messageArea; // Message area to show feedback

    public AddContactGUI(PhoneBook phoneBook) { // Accept PhoneBook instance as a parameter
        this.phoneBook = phoneBook; // Assign the passed PhoneBook instance
        frame = new JFrame("Add Contact");
        frame.setSize(450, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        loadBackgroundImage(); // Load the background image
        
        // Create a custom JPanel for the background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), 800, null);
                }
            }
        };
        
        panel.setLayout(null); // Use absolute positioning
        frame.add(panel); // Add the panel to the frame
        
        initialize(panel); // Pass the panel to the initialize method
        frame.setVisible(true); // Make the frame visible
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/Theme/AddContact.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialize(JPanel panel) {
        // Create and position the name field
        JTextField nameField = new JTextField();
        nameField.setBounds(170, 373, 200, 37); // Adjust coordinates as needed
        panel.add(nameField);

        // Create and position the phone field
        JTextField phoneField = new JTextField();
        phoneField.setBounds(170, 443, 200, 37); // Adjust coordinates as needed
        panel.add(phoneField);

        // Create and position the message area
        messageArea = new JTextArea();
        messageArea.setBounds(170, 520, 280, 40); // Position for message area
        messageArea.setEditable(false);  // Make it read-only
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setOpaque(false);  // Make the message area transparent
        messageArea.setBackground(new Color(0, 0, 0, 0)); // Fully transparent background
        panel.add(messageArea); // Add message area to the panel

        // Create and position the Add button
        JButton addButton = new JButton(); // Set text for visibility
        addButton.setBounds(230, 595, 130, 60); // Resize the button
        addButton.setOpaque(false); // Make it transparent
        addButton.setContentAreaFilled(false); // No filling
        addButton.setBorderPainted(false); // No border
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String phone = phoneField.getText().trim();
                if (!name.isEmpty() && !phone.isEmpty()) {
                    if (phoneBook.addContact(name, phone)) {
                        messageArea.setText("Contact Added!"); // Display success message
                    } else {
                        messageArea.setText("Contact already exists!"); // Display error message
                    }
                } else {
                    messageArea.setText(null); // Message for empty fields
                }
            }
        });
        panel.add(addButton);

        // Create and position the Exit button
        JButton exitButton = new JButton(); // Set text for visibility
        exitButton.setBounds(85, 595, 130, 60); // Resize the button
        exitButton.setOpaque(false); // Make it transparent
        exitButton.setContentAreaFilled(false); // No filling
        exitButton.setBorderPainted(false); // No border
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PhoneBookMenuGUI(phoneBook); // Return to the main menu, passing the PhoneBook instance
                frame.dispose(); // Close AddContact GUI
            }
        });
        panel.add(exitButton);
    }
}
