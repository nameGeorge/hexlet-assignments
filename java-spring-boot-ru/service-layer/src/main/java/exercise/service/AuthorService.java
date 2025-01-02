package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> getAll() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::map)
                .toList();
    }

    public AuthorDTO create(AuthorCreateDTO authorCreateDTO) {
        Author author = authorMapper.map(authorCreateDTO);
        authorRepository.saveAndFlush(author);
        return authorMapper.map(author);
    }

    public AuthorDTO get(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found author with id: " + id));
        return authorMapper.map(author);
    }

    public AuthorDTO update(AuthorUpdateDTO authorUpdateDTO, Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found author with id: " + id));
        authorMapper.update(authorUpdateDTO, author);
        authorRepository.save(author);
        return authorMapper.map(author);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
    // END
}
