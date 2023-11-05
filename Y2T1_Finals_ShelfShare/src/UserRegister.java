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

public class UserRegister extends JPanel {

    private SuperMain superMain;

    private JTextField textField_Email;
    private JTextField textField_Name;
    private JTextField textField_Username;
    private JPasswordField passwordField_Password;
    private JPasswordField passwordField_PasswordRetype;
    private JButton btnReturn;
    private JButton btnRegister;
    private JLabel img_userRegisterBG;

    public UserRegister(SuperMain superMain) {
        this.superMain = superMain;
        setLayout(null); // Set layout manager to null for manual component positioning

        // Create components
        textField_Email = createTextField(404, 383, 374, 19);
        add(textField_Email);

        textField_Name = createTextField(404, 267, 374, 19);
        add(textField_Name);

        textField_Username = createTextField(404, 330, 374, 19);
        add(textField_Username);

        passwordField_Password = createPasswordField(404, 437, 374, 19);
        add(passwordField_Password);

        passwordField_PasswordRetype = createPasswordField(404, 491, 374, 19);
        add(passwordField_PasswordRetype);

        btnReturn = createButton("Return", 999, 42, 118, 35, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#Return Pressed @UserRegister.java");
                superMain.switchToUserLoginTab();
            }
        });
        add(btnReturn);

        btnRegister = createButton("Register", 516, 592, 118, 35, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#User Register Pressed @UserRegister.java");
                superMain.switchToUserLoginTab();
            }
        });
        add(btnRegister);

        img_userRegisterBG = new JLabel("");
        img_userRegisterBG.setBounds(0, 0, 2052, 1290);
        img_userRegisterBG.setIcon(new getImages().userRegisterBG);
        add(img_userRegisterBG);
    }

    private JTextField createTextField(int x, int y, int width, int height) {
        // Create a new text field with the given position and size
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setOpaque(false);
        textField.setBorder(new EmptyBorder(0, 0, 0, 0));
        return textField;
    }

    private JPasswordField createPasswordField(int x, int y, int width, int height) {
        // Create a new password field with the given position and size
        JPasswordField textField = new JPasswordField();
        textField.setBounds(x, y, width, height);
        textField.setOpaque(false);
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
