package co.icesi.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import co.icesi.model.Book;

@Service
public class BookService {

    private Map<Integer, Book> books;

    private int currentId = 1;

    public void addBook(Book book) {
        book.setId(currentId++);
        books.put(book.getId(), book);
    }

    public void init(){
        books = new HashMap<>();
    }
    
}
