package org.example.modsentask.controller;

import lombok.RequiredArgsConstructor;
import org.example.modsentask.dto.edit.BookEditDto;
import org.example.modsentask.dto.edit.LibraryEditDto;
import org.example.modsentask.dto.read.BookReadDto;
import org.example.modsentask.entity.UpdateRequest;
import org.example.modsentask.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookReadDto>> findAll(@RequestParam(required = false) Long id) {
        return id == null
                ? ok().body(bookService.findAll())
                : bookService.findById(id)
                .map(obj -> ok()
                        .body(List.of(obj)))
                .orElseGet(notFound()::build);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookReadDto> findByIsbn(@PathVariable("isbn") String isbn) {
        return bookService.findByIsbn(isbn)
                .map(obj -> ok()
                        .body(obj))
                .orElseGet(notFound()::build);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookReadDto> create(@RequestBody BookEditDto dto) {
        return ok().body(bookService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookReadDto> update(@PathVariable("id") Long id,
                                              @RequestBody UpdateRequest updateRequest) {
        BookEditDto bookEditDto = updateRequest.getBookEditDto();
        LibraryEditDto libraryEditDto = updateRequest.getLibraryEditDto();

        return bookService.update(id, bookEditDto, libraryEditDto)
                .map(obj -> ok().body(obj))
                .orElseGet(notFound()::build);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookService.delete(id);
    }

    @GetMapping("/free")
    public ResponseEntity<List<BookReadDto>> getFreeBooks() {
        return ok().body(bookService.getFreeBooks());
    }

}
