package org.example.modsentask.service;

import lombok.RequiredArgsConstructor;
import org.example.modsentask.dto.edit.BookEditDto;
import org.example.modsentask.dto.edit.LibraryEditDto;
import org.example.modsentask.dto.read.BookReadDto;
import org.example.modsentask.dto.read.LibraryReadDto;
import org.example.modsentask.entity.Book;
import org.example.modsentask.entity.Library;
import org.example.modsentask.entity.enumeration.BookStatus;
import org.example.modsentask.mapper.LibraryMapper;
import org.example.modsentask.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final LibraryMapper libraryMapper;
    private final LibraryRepository libraryRepository;

    public LibraryReadDto create(Book book) {
        LibraryEditDto dto = new LibraryEditDto(book.getId(), null, null, BookStatus.FREE);
        return Optional.of(dto).map(libraryMapper::toLibrary)
                .map(libraryRepository::save)
                .map(libraryMapper::toReadDto)
                .orElseThrow();
    }

    public Optional<LibraryReadDto> update(LibraryEditDto libraryEditDto) {
        return libraryRepository.findByBookId(libraryEditDto.book_id())
                .map(library -> {
                    libraryMapper.map(library, libraryEditDto);
                    return library;
                })
                .map(libraryRepository::save)
                .map(libraryMapper::toReadDto);
    }

    public void delete(Long id) {
        libraryRepository.delete(libraryRepository.findByBookId(id).get());
    }
}
