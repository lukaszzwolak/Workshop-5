package pl.coderslab.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
    List<pl.coderslab.model.Book> getBooks();

    Optional<pl.coderslab.model.Book> get(Long id);

    void add(pl.coderslab.model.Book book);

    void delete(Long id);

    void update(pl.coderslab.model.Book book);
}
