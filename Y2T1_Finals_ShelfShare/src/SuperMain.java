import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPasswordField;

public class SuperMain {

	private HomeMenu homePanel;
	private AdminLogin adminLogin;
	private DashboardMenu dashboardPanel;
	private ManagerMenu stockManagerPanel;
	private UserLogin userLoginPanel;
	private UserRegister userRegisterPanel;
	private UserInterface userInterfacePanel;
	
	private JFrame MainFrame;
	private JTabbedPane tabbedPane_MainInterface;


	public SuperMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MainFrame = new JFrame();
		MainFrame.setTitle("ShelfShare - Book Rental");
		MainFrame.setBounds(100, 100, 1166, 751);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.getContentPane().setLayout(null);
		MainFrame.setResizable(false);
		MainFrame.setLocationRelativeTo(null);
		MainFrame.setVisible(true);

		tabbedPane_MainInterface = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_MainInterface.setBounds(0, -30, 1553, 857);
		MainFrame.getContentPane().add(tabbedPane_MainInterface);
		
		homePanel = new HomeMenu(this);
		tabbedPane_MainInterface.addTab("Home Tab", null, homePanel, null);

		adminLogin = new AdminLogin(this);
		tabbedPane_MainInterface.addTab("Admin Login", null, adminLogin, null);

		dashboardPanel = new DashboardMenu(this);
		tabbedPane_MainInterface.addTab("Dashboard Tab", null, dashboardPanel, null);

		stockManagerPanel = new ManagerMenu(this);
		tabbedPane_MainInterface.addTab("Stock Manager Tab", null, stockManagerPanel, null);

		userLoginPanel = new UserLogin(this);
		tabbedPane_MainInterface.addTab("User Login Tab", null, userLoginPanel, null);

		userRegisterPanel = new UserRegister(this);
		tabbedPane_MainInterface.addTab("User Register Tab", null, userRegisterPanel, null);

		userInterfacePanel = new UserInterface(this);
		tabbedPane_MainInterface.addTab("User Interface Tab", null, userInterfacePanel, null);
		
		
	}
	
	public void switchToHomeTab() {
		 tabbedPane_MainInterface.setSelectedIndex(0);
	}
	
	public void switchToAdminLoginTab() {
		 tabbedPane_MainInterface.setSelectedIndex(1);
	}
	
	public void switchToAdminDashboardTab() {
		 tabbedPane_MainInterface.setSelectedIndex(2);
	}
	
	public void switchToStockManagerTab() {
		 tabbedPane_MainInterface.setSelectedIndex(3);
	}
	
	public void switchToUserLoginTab() {
		 tabbedPane_MainInterface.setSelectedIndex(4);
	}
	
	public void switchToUserRegisterTab() {
		 tabbedPane_MainInterface.setSelectedIndex(5);
	}
	
	public void switchToUserInterfaceTab() {
		 tabbedPane_MainInterface.setSelectedIndex(6);
	}
	
}
