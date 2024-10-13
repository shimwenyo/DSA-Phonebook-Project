import GUI.PhoneBookMenuGUI; // Import the PhoneBookMenuGUI
import System.PhoneBook; // Import the PhoneBook class
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Create a single PhoneBook instance
        PhoneBook phoneBook = new PhoneBook();

        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new PhoneBookMenuGUI(phoneBook);
        });
    }
}
