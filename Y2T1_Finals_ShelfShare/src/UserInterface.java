
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class UserInterface extends JPanel {
    private SuperMain superMain;
    private JTabbedPane tabbedPane_UserInterface;
    private JTable table;
    private DefaultTableModel tableModel;
    private Map<String, Integer> rentedBooksMap = new HashMap<>();
    
    private String BOOK_MANAGEMENT_DATA = "C:\\Users\\HP\\eclipse-workspace\\Y2T1_Finals_ShelfShare\\data\\Book_Management_Data.txt";
    private String RENT_DATA = "C:\\Users\\HP\\eclipse-workspace\\Y2T1_Finals_ShelfShare\\data\\Rent_Data.txt";

    private JTextField textField;

    public UserInterface(SuperMain superMain) {
        this.superMain = superMain;
        setLayout(null);

        
        // Create tabbed pane
        tabbedPane_UserInterface = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane_UserInterface.setBounds(-2, -30, 1553, 857);
        add(tabbedPane_UserInterface);

        // Create panels for each tab
        JPanel UI_HomePanel = createTabPanel("Home");
        JPanel UI_ListPanel = createTabPanel("Book List");
        JPanel UI_RentPanel = createTabPanel("RentABook");

        // Create buttons for each tab
        createButton(UI_HomePanel, "Home", 35, 165, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#Home(p1) Pressed @UserInterface.java");
                switchToUIHome();
            }
        });

        createButton(UI_HomePanel, "List", 133, 165, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#List(p1) Pressed @UserInterface.java");
                switchToUIList();
            }
        });

        createButton(UI_HomePanel, "Rent", 249, 165, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#Rent(p1) Pressed @UserInterface.java");
                switchToUIRent();
            }
        });

        createButton(UI_HomePanel, "Logout", 1015, 149, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#Logout(p1) Pressed @UserInterface.java");
                superMain.switchToHomeTab();
            }
        });

        createButton(UI_ListPanel, "Home", 39, 165, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#Home(p2) Pressed @UserInterface.java");
                switchToUIHome();
            }
        });

        createButton(UI_ListPanel, "List", 139, 165, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#List(p2) Pressed @UserInterface.java");
                switchToUIList();
            }
        });

        createButton(UI_ListPanel, "Rent", 254, 165, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#Rent(p2) Pressed @UserInterface.java");
                switchToUIRent();
            }
        });

        createButton(UI_ListPanel, "Logout", 1019, 149, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#Logout(p2) Pressed @UserInterface.java");
                superMain.switchToHomeTab();
            }
        });

        createButton(UI_RentPanel, "Home", 33, 165, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#Home(p3) Pressed @UserInterface.java");
                switchToUIHome();
            }
        });

        createButton(UI_RentPanel, "List", 134, 165, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#List(p3) Pressed @UserInterface.java");
                switchToUIList();
            }
        });

        createButton(UI_RentPanel, "Rent", 250, 165, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#Rent(p3) Pressed @UserInterface.java");
                switchToUIRent();
            }
        });

        createButton(UI_RentPanel, "Logout", 1013, 149, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#Logout(p3) Pressed @UserInterface.java");
                superMain.switchToHomeTab();
            }
        });
        
        JLabel img_userInterfaceHomeBG = new JLabel("");
        img_userInterfaceHomeBG.setBounds(0, 0, 2052, 1290);
        img_userInterfaceHomeBG.setIcon(new getImages().userInterfaceHomeBG);
        UI_HomePanel.add(img_userInterfaceHomeBG);
        
        JLabel img_userInterfaceListBG = new JLabel("");
        img_userInterfaceListBG.setBounds(0, 0, 2052, 1290);
        img_userInterfaceListBG.setIcon(new getImages().userInterfaceListBG);
        UI_ListPanel.add(img_userInterfaceListBG);
        
        JLabel img_userInterfaceRentBG = new JLabel("");
        img_userInterfaceRentBG.setBounds(0, 0, 2052, 1290);
        img_userInterfaceRentBG.setIcon(new getImages().userInterfaceRentBG);
        UI_RentPanel.add(img_userInterfaceRentBG);
        
        // Load data into the "Book List" table
        loadDataFromFile(BOOK_MANAGEMENT_DATA, tableModel);
    }
    
    private void createButton(JPanel panel, String text, int x, int y, ActionListener listener) {
        JButton button = new JButton();
        button.setBounds(x, y, 104, 27);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(listener);
        panel.add(button);
    }

    // TO SEPARATE THE TABS TO INSERT ELEMENTS SEPARATELY
    private JPanel createTabPanel(String tabName) {
        JPanel tabPanel = new JPanel();
        tabbedPane_UserInterface.addTab(tabName, null, tabPanel, null);
        tabPanel.setLayout(null);
        
        JLabel rentBtn = new JLabel("");
        rentBtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                rentSelectedBook();
        	}
        });
        rentBtn.setBounds(519, 650, 113, 34);
        
        JLabel refreshBtn = new JLabel("");
        refreshBtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                tableModel.setRowCount(0);
                loadDataFromFile(BOOK_MANAGEMENT_DATA, tableModel);
                
        	}
        });
        refreshBtn.setIcon(new ImageIcon("C:\\Users\\HP\\eclipse-workspace\\Y2T1_Finals_ShelfShare\\img\\img_refreshButton.png"));
        refreshBtn.setBounds(1029, 303, 50, 50);


        // FOR EACH TAB
        if ("RentABook".equals(tabName)) {
            // Create and add the JScrollPane with the JTable to the tab
            JScrollPane scrollPane = createTable();
            scrollPane.setBounds(89, 300, 928, 303);
            tabPanel.add(scrollPane);
            tabPanel.add(refreshBtn);
            tabPanel.add(rentBtn);

            // Add an ActionListener to the "RENT" button

        }
        
        if ("Book List".equals(tabName)) {
            JScrollPane scrollPane = createTable();
            scrollPane.setBounds(89, 300, 928, 303);
            tabPanel.add(scrollPane);
            tabPanel.add(refreshBtn);
            loadDataFromFile(BOOK_MANAGEMENT_DATA, tableModel);
            
        }
        return tabPanel;
    }
    
    private void rentSelectedBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            // No row is selected, show a message to select a book first
            JOptionPane.showMessageDialog(this, "Select a book first.", "No Book Selected", JOptionPane.WARNING_MESSAGE);
        } else {
            // A row is selected, proceed with the rest of your code
            String title = (String) tableModel.getValueAt(selectedRow, 0);

            // Update the rented book count in the map
            int timesRented = rentedBooksMap.getOrDefault(title, 0);
            rentedBooksMap.put(title, timesRented + 1);

            // Write the updated count to the text file
            writeRentedBooksCountToFile();
            JOptionPane.showMessageDialog(this, "Book Rented", "Success", JOptionPane.INFORMATION_MESSAGE);


            // You can display a message or update the UI to show the rented book count
            // For example, you can set the text of a JLabel with the count:
            // rentedBooksCountLabel.setText("Rented Book: " + timesRented);
        }
    }

    private void writeRentedBooksCountToFile() {
        try {
            // Read the existing data from RENT_DATA
            Map<String, Integer> existingCounts = new HashMap<>();

            BufferedReader reader = new BufferedReader(new FileReader(RENT_DATA));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
                    String title = parts[0];
                    int count = Integer.parseInt(parts[1]);
                    existingCounts.put(title, count);
                }
            }
            reader.close();

            // Update counts with the data in rentedBooksMap
            for (Map.Entry<String, Integer> entry : rentedBooksMap.entrySet()) {
                String title = entry.getKey();
                int newCount = entry.getValue();

                if (existingCounts.containsKey(title)) {
                    // If the book exists in the existing counts, update the count
                    existingCounts.put(title, newCount);
                } else {
                    // If the book doesn't exist, add it to the existing counts
                    existingCounts.put(title, newCount);
                }
            }

            // Write the updated data to RENT_DATA
            BufferedWriter writer = new BufferedWriter(new FileWriter(RENT_DATA, false)); // Overwrite mode

            for (Map.Entry<String, Integer> entry : existingCounts.entrySet()) {
                writer.write(entry.getKey() + " - " + entry.getValue());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    
    private JScrollPane createTable() {
        // Initialize the JTable and DefaultTableModel
        tableModel = new DefaultTableModel(new String[]{"Title", "Author", "Genre", "Price"}, 0);
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the JTable non-editable
            }
        };

        // Create and return a JScrollPane for the JTable
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    public void loadDataFromFile(String filePath, DefaultTableModel model) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split("\\|");
                    if (data.length >= 5 && "true".equals(data[4])) {
                        // If the data has at least 5 elements and the availability is true, add only the first 4 elements to the table
                        model.addRow(new String[]{data[0], data[1], data[2], data[3]});
                    }
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToUIHome() {
        tabbedPane_UserInterface.setSelectedIndex(0);
    }
    public void switchToUIList() {
        tabbedPane_UserInterface.setSelectedIndex(1);
    }

    public void switchToUIRent() {
        tabbedPane_UserInterface.setSelectedIndex(2);
    }
}
