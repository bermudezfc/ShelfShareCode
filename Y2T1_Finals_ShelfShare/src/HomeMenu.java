import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeMenu extends JPanel {

    private SuperMain superMain; // A reference to the main SuperMain class

    public HomeMenu(SuperMain superMain) { // Constructor
        this.superMain = superMain;
        setLayout(null); // Set layout manager to null for manual component positioning

        // Create the "Admin Login" button
        JButton btnAdminLogin = createButton("Admin Login", 971, 645);
        btnAdminLogin.addActionListener(e -> { // Add an action listener to the "Admin Login" button
            System.out.println("#Admin Login Pressed @HomeMenu.java");
            superMain.switchToAdminLoginTab(); // Call a method in SuperMain to switch to the Admin Login tab
        });

        // Create the "User Login" button
        JButton btnUserLogin = createButton("User Login", 945, 52);
        btnUserLogin.addActionListener(e -> {
        	 System.out.println("#User Login Pressed @HomeMenu.java");
        	 superMain.switchToUserLoginTab(); // Call a method in SuperMain to switch to the User Login tab
        });

        // Create the home background image
        JLabel imgHomeBG = new JLabel("");
        imgHomeBG.setIcon(new getImages().homeBG);
        imgHomeBG.setBounds(0, 0, 2052, 1290);
        add(imgHomeBG);
    }

    private JButton createButton(String text, int x, int y) {
        // Create a new button with the given text and position
        JButton button = new JButton();
        button.setBounds(x, y, 154, 45);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        add(button);
        return button;
    }
}
