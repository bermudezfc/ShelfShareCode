import java.awt.Cursor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Scanner;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ManagerMenu extends JPanel {
	private SuperMain superMain;
	// CALLS THE LINKEDLIST IMPLEMENTATION CLASS
    private LibraryInventory library = new LibraryInventory();
    private DefaultTableModel tableModel;
    private JTable table;
    private int selectedRowIndex = -1;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField genreField;
    private JTextField priceField;
    private JCheckBox availableCheckBox;

    public ManagerMenu(SuperMain superMain) {
        this.superMain = superMain;

        setLayout(null);
        
        // JTABLE
        tableModel = new DefaultTableModel(new String[]{"Title", "Author", "Genre", "Price", "Availability"}, 0);
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the JTable non-editable
            }
        };
        
        // FOR EDIT FUNCTION
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRowIndex = table.getSelectedRow();
                if (selectedRowIndex >= 0) {
                    titleField.setText((String) tableModel.getValueAt(selectedRowIndex, 0));
                    authorField.setText((String) tableModel.getValueAt(selectedRowIndex, 1));
                    genreField.setText((String) tableModel.getValueAt(selectedRowIndex, 2));
                    priceField.setText(String.valueOf(tableModel.getValueAt(selectedRowIndex, 3)));
                    availableCheckBox.setSelected(tableModel.getValueAt(selectedRowIndex, 4).equals("Available"));
                }
            }
        });


        JLabel editBtn = new JLabel("");
        editBtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                if (selectedRowIndex >= 0) {
                    String title = titleField.getText();
                    String author = authorField.getText();
                    String genre = genreField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    boolean available = availableCheckBox.isSelected();
                    library.editBook(selectedRowIndex, title, author, genre, price, available);
                    updateTable();
                    titleField.setText("");
                    authorField.setText("");
                    genreField.setText("");
                    priceField.setText("");
                    availableCheckBox.setSelected(false);
                    table.clearSelection();
                    
                    JOptionPane.showMessageDialog(editBtn, "Edit Successful");
                }
        	}
        });

        
        JLabel removeBtn = new JLabel("");
        removeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (selectedRowIndex >= 0) {
                        library.removeBook(selectedRowIndex);
                        updateTable();
                        JOptionPane.showMessageDialog(removeBtn, "Removed");
                    } else {
                        throw new Exception("Please select a row first.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JLabel clearBtn = new JLabel("");
        clearBtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                titleField.setText("");
                authorField.setText("");
                genreField.setText("");
                priceField.setText("");
                table.clearSelection();

        	}
        });
        
        JLabel addBtn = new JLabel("");
        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {


                    String title = titleField.getText();
                    String author = authorField.getText();
                    String genre = genreField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    boolean available = availableCheckBox.isSelected();
                    if (!title.isEmpty() && !author.isEmpty() && !genre.isEmpty()) {
                        library.addBook(title, author, genre, price, available);
                        updateTable();
                    }
                    
                    titleField.setText("");
                    authorField.setText("");
                    genreField.setText("");
                    priceField.setText("");
                    availableCheckBox.setSelected(false);
                    JOptionPane.showMessageDialog(addBtn, "Book Added");

                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addBtn.setBounds(739, 522, 104, 44);
        add(addBtn);
        
        clearBtn.setBounds(951, 601, 104, 44);
        add(clearBtn);


        removeBtn.setBounds(739, 601, 104, 44);
        add(removeBtn);
        editBtn.setBounds(951, 522, 104, 44);
        add(editBtn);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(29, 175, 555, 432);
        add(scrollPane);

        titleField = new JTextField(20);
        titleField.setBounds(677, 413, 166, 19);
        add(titleField);

        authorField = new JTextField(20);
        authorField.setBounds(966, 277, 166, 19);
        add(authorField);

        genreField = new JTextField(20);
        genreField.setBounds(677, 276, 166, 19);
        add(genreField);

        priceField = new JTextField(10);
        priceField.setBounds(966, 413, 166, 19);
        add(priceField);

        availableCheckBox = new JCheckBox("Available");
        availableCheckBox.setBounds(677, 463, 83, 21);
        add(availableCheckBox);



        library.loadInventoryFromFile();
        updateTable();
        
        
        // Create the "Return" button
     // Add an action listener to the "Return" button
        JButton btnReturn = createButton("Return", 1003, 94, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("#Return Pressed @ManagerMenu.java");
                superMain.switchToAdminDashboardTab();
            }
        });
        add(btnReturn);

        // Create the manager background image
        JLabel img_ManagerBG = new JLabel("");
        img_ManagerBG.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		
        	}
        });
        img_ManagerBG.setBounds(0, 0, 2052, 1290);
        img_ManagerBG.setIcon(new getImages().adminManagerBG);
        add(img_ManagerBG);
        


    }
    
    // METHODS
    
    private void updateTable() {
        Object[][] data = library.getInventoryData();
        tableModel.setDataVector(data, new String[]{"Title", "Author", "Genre", "Price", "Availability"});
    }

    public void display() {
        setVisible(true);
    }


    private JButton createButton(String text, int x, int y, ActionListener listener) {
        // Create a new button with the given text, position, and size
        JButton button = new JButton();
        
        button.setBounds(x, y, 117, 40);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(listener);
        return button;
    }
}
