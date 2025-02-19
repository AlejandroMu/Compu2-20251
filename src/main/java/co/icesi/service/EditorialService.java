package co.icesi.service;

import java.util.HashMap;
import java.util.Map;

import co.icesi.model.Book;
import co.icesi.model.Editorial;

public class EditorialService {
 
    private BookService bookService;
    private Map<Integer, Editorial> editorials;
    private int currentId;

    public void init(){
        editorials = new HashMap<>();
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void addBookToEditoral(Editorial editorial, Book ... books){
        editorial.setId(currentId++);
        for (Book book : books) {
            book.setEditorial(editorial);
            bookService.addBook(book);
        }
        editorials.put(editorial.getId(), editorial);
    }

    public Map<Integer, Editorial> getEditorials() {
        return editorials;
    }
    
}
