package co.icesi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import co.icesi.model.Book;
import co.icesi.model.Editorial;
import co.icesi.service.EditorialService;

public class Main {

  
    
    public static void main(String[] args) {
        ApplicationContext contex = new ClassPathXmlApplicationContext("applicationContext.xml");
        Editorial editorial = new Editorial();
        editorial.setAddress("test address");
        editorial.setCountry("col");
        Book nBook = new Book();
        nBook.setAuthor("author");
        nBook.setTitle("title");
        EditorialService service = (EditorialService)contex.getBean("editorialService");
        service.addBookToEditoral(editorial, nBook);

        System.out.println(service.getEditorials());
        
    }
}
