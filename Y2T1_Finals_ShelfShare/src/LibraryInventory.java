import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//Book Node
class Book {
 String title;
 String author;
 String genre;
 double price;
 boolean available;
 Book next;

 public Book(String title, String author, String genre, double price, boolean available) {
     this.title = title;
     this.author = author;
     this.genre = genre;
     this.price = price;
     this.available = available;
     this.next = null;
 }
}

class LibraryInventory {
    Book head;

    public void addBook(String title, String author, String genre, double price, boolean available) {
        Book newBook = new Book(title, author, genre, price, available);
        if (head == null) {
            head = newBook;
        } else {
            Book current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newBook;
        }
        saveInventoryToFile();
    }

    public void removeBook(int index) {
        if (index == 0) {
            head = head.next;
        } else {
            int i = 0;
            Book current = head;
            while (i < index - 1) {
                current = current.next;
                i++;
            }
            current.next = current.next.next;
        }
        saveInventoryToFile();
    }

    public void editBook(int index, String title, String author, String genre, double price, boolean available) {
        int i = 0;
        Book current = head;
        while (i < index) {
            current = current.next;
            i++;
        }
        current.title = title;
        current.author = author;
        current.genre = genre;
        current.price = price;
        current.available = available;
        saveInventoryToFile();
    }

    public Object[][] getInventoryData() {
        if (head == null) {
            return new Object[0][0];
        } else {
            Book current = head;
            int count = 0;
            while (current != null) {
                current = current.next;
                count++;
            }
            Object[][] data = new Object[count][5];
            current = head;
            int row = 0;
            while (current != null) {
                data[row][0] = current.title;
                data[row][1] = current.author;
                data[row][2] = current.genre;
                data[row][3] = current.price;
                data[row][4] = current.available ? "Available" : "Not Available";
                current = current.next;
                row++;
            }
            return data;
        }
    }

    public void saveInventoryToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\HP\\eclipse-workspace\\Y2T1_Finals_ShelfShare\\data\\Book_Management_Data.txt"))) {
            Book current = head;
            while (current != null) {
                String bookInfo = String.format("%s|%s|%s|%.2f|%b\n", current.title, current.author, current.genre, current.price, current.available);
                writer.write(bookInfo);
                current = current.next;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadInventoryFromFile() {
        try (Scanner scanner = new Scanner(new File("C:\\Users\\HP\\eclipse-workspace\\Y2T1_Finals_ShelfShare\\data\\Book_Management_Data.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String title = parts[0];
                    String author = parts[1];
                    String genre = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    boolean available = Boolean.parseBoolean(parts[4]);
                    addBook(title, author, genre, price, available);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}