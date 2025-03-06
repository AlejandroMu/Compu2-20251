package co.icesi.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Editorial {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String city;
    private String country;

    List<Book> books;
}
