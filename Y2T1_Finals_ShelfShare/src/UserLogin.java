import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserLogin extends JPanel {

    private SuperMain superMain;

    private JPasswordField passwordField_UserPassword;
    private JTextField textField_UserUsername;
    private JButton btnRegister;
    private JButton btnReturn;
    private JButton btnLogin;
    private JLabel imgUserLoginBG;

    public UserLogin(SuperMain superMain) {
        this.superMain = superMain;
        setLayout(null); // Set layout manager to null for manual component positioning

        // Create components
        passwordField_UserPassword = createPasswordField(34, 422, 372, 30);
       // passwordField_UserPassword.setEchoChar((char) 0);
        textField_UserUsername = createTextField(34, 314, 372, 30);
        btnRegister = createButton("Register", 99, 660, 235, 21, e -> {
            System.out.println("#User Register Pressed @UserLogin.java");
            superMain.switchToUserRegisterTab();
        });
        btnReturn = createButton("Return", 163, 583, 117, 37, e -> {
            System.out.println("#User Login Pressed @UserLogin.java");
            superMain.switchToHomeTab();
        });
        btnLogin = createButton("Login", 163, 493, 116, 37, e -> {
            System.out.println("#User Login Pressed @UserLogin.java");
            superMain.switchToUserInterfaceTab();
        });
        imgUserLoginBG = new JLabel("");
        imgUserLoginBG.setIcon(new getImages().userLoginBG);
        imgUserLoginBG.setBounds(0, 0, 2052, 1290);

        // Add components to the panel
        add(passwordField_UserPassword);
        add(textField_UserUsername);
        add(btnRegister);
        add(btnReturn);
        add(btnLogin);
        add(imgUserLoginBG);
    }

    private JPasswordField createPasswordField(int x, int y, int width, int height) {
        // Create a new password field with the given position and size
        JPasswordField textField = new JPasswordField();
        textField.setBounds(x, y, width, height);
        textField.setBorder(new EmptyBorder(0, 0, 0, 0));
        return textField;
    }

    private JTextField createTextField(int x, int y, int width, int height) {
        // Create a new text field with the given position and size
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setBorder(new EmptyBorder(0, 0, 0, 0));
        return textField;
    }

    private JButton createButton(String text, int x, int y, int width, int height, ActionListener listener) {
        // Create a new button with the given text, position, size, and action listener
        JButton button = new JButton();
        button.setBounds(x, y, width, height);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(listener);
        return button;
    }

}
