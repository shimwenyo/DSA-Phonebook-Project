package GUI;

import System.PhoneBook;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Collections;

public class SortContactsGUI {
    private JFrame frame;
    private PhoneBook phoneBook; // Connects with the PhoneBook system
    private BufferedImage backgroundImage;

    public SortContactsGUI(PhoneBook phoneBook) {
        this.phoneBook = phoneBook; // Initialize the PhoneBook system
        frame = new JFrame("Sort Contacts");
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
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
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
            backgroundImage = ImageIO.read(getClass().getResource("/Theme/SortContact.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialize(JPanel panel) {
        // Create the table model
        String[] columnNames = {"Name", "Phone Number"};
        List<String> contacts = phoneBook.getAllContacts(); // Get all contacts

        // Prepare data for the table
        String[][] data = new String[contacts.size()][2];
        for (int i = 0; i < contacts.size(); i++) {
            String[] contact = contacts.get(i).split(": ");
            data[i][0] = contact[0]; // Name
            data[i][1] = contact[1]; // Phone Number
        }

        // Create a JTable to display the contacts
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(65, 280, 300, 350); // Position and size for the scroll pane
        panel.add(scrollPane); // Add scroll pane to the panel

        // Create and position the A-Z button
        JButton azButton = new JButton(); // Set button text for visibility
        azButton.setBounds(260, 632, 70, 40); // Resize the button
        azButton.setOpaque(false); // Make it transparent
        azButton.setContentAreaFilled(false); // No filling
        azButton.setBorderPainted(false); // No border
        azButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortContacts(table, true); // Sort A-Z
            }
        });
        panel.add(azButton); // Add A-Z button to the panel

        // Create and position the Z-A button
        JButton zaButton = new JButton(); // Set button text for visibility
        zaButton.setBounds(260, 690, 70, 40); // Resize the button
        zaButton.setOpaque(false); // Make it transparent
        zaButton.setContentAreaFilled(false); // No filling
        zaButton.setBorderPainted(false); // No border
        zaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortContacts(table, false); // Sort Z-A
            }
        });
        panel.add(zaButton); // Add Z-A button to the panel

        // Create and position the Exit button
        JButton exitButton = new JButton(); // Set text for visibility
        exitButton.setBounds(85, 645, 130, 60); // Resize the button
        exitButton.setOpaque(false); // Make it transparent
        exitButton.setContentAreaFilled(false); // No filling
        exitButton.setBorderPainted(false); // No border
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PhoneBookMenuGUI(phoneBook); // Return to the main menu
                frame.dispose(); // Close SortContact GUI
            }
        });
        panel.add(exitButton); // Add exit button to the panel
    }

    private void sortContacts(JTable table, boolean ascending) {
        List<String> contacts = phoneBook.getAllContacts(); // Get current contacts

        // Sort the contacts based on the selected order
        if (ascending) {
            Collections.sort(contacts); // Sort A-Z
        } else {
            Collections.sort(contacts, Collections.reverseOrder()); // Sort Z-A
        }

        // Update the table model with sorted contacts
        String[][] data = new String[contacts.size()][2];
        for (int i = 0; i < contacts.size(); i++) {
            String[] contact = contacts.get(i).split(": ");
            data[i][0] = contact[0]; // Name
            data[i][1] = contact[1]; // Phone Number
        }

        // Update the table with new data
        table.setModel(new javax.swing.table.DefaultTableModel(data, new String[]{"Name", "Phone Number"}));
    }
}
