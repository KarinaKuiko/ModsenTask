package org.example.modsentask.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.modsentask.dto.edit.BookEditDto;
import org.example.modsentask.entity.Library;
import org.example.modsentask.dto.edit.LibraryEditDto;
import org.example.modsentask.dto.read.BookReadDto;
import org.example.modsentask.entity.enumeration.BookStatus;
import org.example.modsentask.mapper.BookMapper;
import org.example.modsentask.repository.BookRepository;
import org.example.modsentask.repository.LibraryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final LibraryService libraryService;
    private final LibraryRepository libraryRepository;

    public List<BookReadDto> findAll() {
        return bookRepository.findAll()
                .stream().map(bookMapper::toReadDto)
                .toList();
    }

    public Optional<BookReadDto> findById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toReadDto);
    }

    public Optional<BookReadDto> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(bookMapper::toReadDto);
    }

    public BookReadDto create(BookEditDto dto) {
        return Optional.of(dto).map(bookMapper::toBook)
                .map(bookRepository::save)
                .map(book -> {
                    libraryService.create(book);
                    return book;
                })
                .map(bookMapper::toReadDto)
                .orElseThrow();
    }

    public Optional<BookReadDto> update(Long id, BookEditDto dto, LibraryEditDto libraryEditDto) {
        return bookRepository.findById(id)
                .map(book -> {
                    log.info("before libraryService");
                    if (libraryEditDto != null) {
                        libraryService.update(libraryEditDto);
                    }
                    log.info("after libraryService");
                    bookMapper.map(book, dto);
                    return book;
                })
                .map(bookRepository::save)
                .map(bookMapper::toReadDto);
    }

    public void delete(Long id) {
        libraryService.delete(id);
        bookRepository.deleteById(id);
    }

    public List<BookReadDto> getFreeBooks() {
        return libraryRepository.findByStatus(BookStatus.FREE)
                .stream().map(Library::getBook) // Предполагается, что есть метод getBook()
                .toList()
                .stream().map(bookMapper::toReadDto)
                .toList();
    }

}
