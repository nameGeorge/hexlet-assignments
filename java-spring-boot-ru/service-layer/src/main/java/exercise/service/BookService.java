package exercise.service;

import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Book;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAll() {
        var books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::map)
                .toList();
    }

    public BookDTO create(BookCreateDTO bookCreateDTO) {
        Book book = bookMapper.map(bookCreateDTO);
        bookRepository.saveAndFlush(book);
        return bookMapper.map(book);
    }

    public BookDTO get(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found book with id: " + id));
        return bookMapper.map(book);
    }

    public BookDTO update(BookUpdateDTO bookUpdateDTO, Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found book with id: " + id));
        bookMapper.update(bookUpdateDTO, book);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    // END
}
