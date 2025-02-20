package co.icesi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import co.icesi.model.Book;
import co.icesi.model.Editorial;

public class EditorialService {
 
    private BookService bookService;
    private Map<Integer, Editorial> editorials;
    private int currentId;

    public void init(){
        editorials = new HashMap<>();
        Editorial e = Editorial.builder()
                    .name("Editorial created in init")
                    .address("Created init").build();
        Book b = Book.builder().author("Book created in edit service").build();
        addBookToEditoral(e, b);
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

    public List<Editorial> getEditorials() {
        return editorials.values().stream().collect(Collectors.toList());
    }
    
}
