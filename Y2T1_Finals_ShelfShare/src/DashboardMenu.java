import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Cursor;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class DashboardMenu extends JPanel {

    private SuperMain superMain;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextArea RentedDisplay;  
    private String FILE_PATH = "C:\\Users\\HP\\eclipse-workspace\\Y2T1_Finals_ShelfShare\\data\\Book_Management_Data.txt";
    private String RENT_DATA = "C:\\Users\\HP\\eclipse-workspace\\Y2T1_Finals_ShelfShare\\data\\Rent_Data.txt";

    public DashboardMenu(SuperMain superMain) {
        this.superMain = superMain;

        setLayout(null);

        tableModel = new DefaultTableModel(new String[]{"Title", "Author", "Genre", "Price"}, 0);
        

        RentedDisplay = new JTextArea(); // Initialize the JTextArea here
        RentedDisplay.setFont(new Font("Arial", Font.PLAIN, 17));
        RentedDisplay.setEditable(false);
        RentedDisplay.setLineWrap(true);
        RentedDisplay.setBounds(705, 260, 367, 432);
        add(RentedDisplay);
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the JTable non-editable
            }
        };

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(34, 260, 555, 432);
        add(scrollPane);

        // Create the "Next" button
        JButton btnNext = createButton("Next", 1001, 93, e -> {
            System.out.println("#Next Pressed @DashboardMenu.java");
            superMain.switchToStockManagerTab();
        });
        add(btnNext);

        // Create the "Logout" button
        JButton btnLogout = createButton("Logout", 872, 93, e -> {
            System.out.println("#Logout Pressed @DashboardMenu.java");
            superMain.switchToHomeTab();
        });
        add(btnLogout);

        JLabel refreshBtn = new JLabel("");
        refreshBtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                refreshTableData();
        	}
        });
        refreshBtn.setIcon(new ImageIcon("C:\\Users\\HP\\eclipse-workspace\\Y2T1_Finals_ShelfShare\\img\\img_refreshButton.png"));
        refreshBtn.setBounds(1022, 171, 50, 50);
        add(refreshBtn);

        // Create the dashboard background image
        JLabel imgDashboardBG = new JLabel();
        imgDashboardBG.setBounds(0, 0, 2052, 1290);
        imgDashboardBG.setIcon(new getImages().adminDashboardBG);
        add(imgDashboardBG);

        loadDataFromFile();
        loadDataFromFile2();

    }
    
    // METHODS
    public void loadDataFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split("\\|");
                    if (data.length >= 5 && "true".equals(data[4])) {
                        // If the data has at least 5 elements and the availability is true, add only the first 4 elements to the table
                        tableModel.addRow(new String[]{data[0], data[1], data[2], data[3]});
                    }
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadDataFromFile2() {
        try {
            File file = new File(RENT_DATA);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                StringBuilder data = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    data.append(line).append("\n");
                }

                br.close();

                // Set the data to the JTextArea
                RentedDisplay.setText(data.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshTableData() {
        // Clear the current data in the table
        tableModel.setRowCount(0);

        // Load data from the text file
        loadDataFromFile();
        loadDataFromFile2();
    }
    
    private JButton createButton(String text, int x, int y, ActionListener listener) {
        // Create a new button with the given text, position, size, and action listener
        JButton button = new JButton();
        button.setBounds(x, y, 113, 41);
        button.addActionListener(listener);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        return button;
    }
}
