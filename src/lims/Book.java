package lims;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {

    private final  SimpleIntegerProperty id;
    private final  SimpleStringProperty book_name;
    private final  SimpleStringProperty book_author;
    
    
    public Book(int id, String book_name, String book_author) {
        this.id = new SimpleIntegerProperty(id);
        this.book_name = new SimpleStringProperty(book_name);
        this.book_author = new SimpleStringProperty(book_author);
        
    }

    public int getId() {
        return id.get();
    }
    public void setId(int qnt) {
        this.id.set(qnt);
    }

   

    public String getBookName() {
        return book_name.get();
    }

    public void setBookName(String book_name) {
        this.book_name.set(book_name);
    }
    
    public String getBookAuthor() {
        return book_author.get();
    }

    public void setBookAuthor(String book_author) {
        this.book_author.set(book_author);
    }
}
    