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
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class AdminLogin extends JPanel {

    private SuperMain superMain;

    private JPasswordField passwordField_AdminPassword;
    private JTextField textField_AdminUsername;
    private JButton btnReturn;
    private JButton btnLogin;
    private JToggleButton btnShowPass;
    private getImages getImage;

    private JLabel imgAdminLoginBG;

    public AdminLogin(SuperMain superMain) {
        this.superMain = superMain;
        getImage = new getImages();
        setLayout(null); // Set layout manager to null for manual component positioning

        // Create components
        passwordField_AdminPassword = createPasswordField(437, 385, 391, 33);
        textField_AdminUsername = createTextField(437, 276, 391, 33);
        btnReturn = createButton("RETURN", 528, 650, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#Return Pressed @AdminLogin.java");
                superMain.switchToHomeTab();
            }
        });
        btnLogin = createButton("LOGIN", 528, 524, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#Login Pressed @AdminLogin.java");
                superMain.switchToAdminDashboardTab();
            }
        });
        // Create the toggle button for showing/hiding the password
        btnShowPass = createToggleButton(785, 378, 64, 64);
        btnShowPass.setIcon(getImage.hiddenPasswordICON); // Set the default icon
        
        imgAdminLoginBG = new JLabel("");
        imgAdminLoginBG.setIcon(new getImages().adminLoginBG);
        imgAdminLoginBG.setBounds(0, 0, 2052, 1290);
        
        // Add components to the panel
        add(btnShowPass);
        add(passwordField_AdminPassword);
        add(textField_AdminUsername);
        add(btnReturn);
        add(btnLogin);
        add(imgAdminLoginBG);
    }

    private JPasswordField createPasswordField(int x, int y, int width, int height) {
        // Create a new password field with the given position and size
        JPasswordField textField = new JPasswordField();
        textField.setBounds(437, 384, 329, 33);
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

    private JButton createButton(String text, int x, int y, ActionListener listener) {
        // Create a new button with the given text and position
        JButton button = new JButton();
        button.setBounds(x, y, 116, 40);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(listener);
        return button;
    }
    
    private JToggleButton createToggleButton(int x, int y, int width, int height) {
        JToggleButton button = new JToggleButton();
        button.setBounds(x, y, width, height);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#Show Password Pressed @AdminLogin.java");

                if (button.isSelected()) {
                    passwordField_AdminPassword.setEchoChar((char) 0); // Show the password
                    button.setIcon(getImage.visiblePasswordICON);
                } else {
                    passwordField_AdminPassword.setEchoChar('\u2022'); // Hide the password (use your desired character)
                    button.setIcon(getImage.hiddenPasswordICON);
                }
            }
        });

        return button;
    }
}
