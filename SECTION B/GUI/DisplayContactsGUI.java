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

public class DisplayContactsGUI {
    private JFrame frame;
    private PhoneBook phoneBook; // Connects with the PhoneBook system
    private BufferedImage backgroundImage;

    public DisplayContactsGUI(PhoneBook phoneBook) {
        this.phoneBook = phoneBook; // Initialize the PhoneBook system
        frame = new JFrame("Display Contacts");
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
            backgroundImage = ImageIO.read(getClass().getResource("/Theme/DisplayContacts.png"));
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

        // Create and position the Exit button
        JButton exitButton = new JButton(); // Set text for visibility
        exitButton.setBounds(85, 645, 130, 60); // Resize the button
        exitButton.setOpaque(false); // Make it transparent
        exitButton.setContentAreaFilled(false); // No filling
        exitButton.setBorderPainted(false); // exitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PhoneBookMenuGUI(phoneBook); // Return to the main menu
                frame.dispose(); // Close SearchContact GUI
            }
        });
        panel.add(exitButton);
    }
}
