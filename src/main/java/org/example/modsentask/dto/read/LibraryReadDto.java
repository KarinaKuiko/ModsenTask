package org.example.modsentask.dto.read;

import org.example.modsentask.entity.Book;
import org.example.modsentask.entity.enumeration.BookStatus;

import java.util.Date;

public record LibraryReadDto (
        Long id,
        Book book,
        Date taken,
        Date needReturn,
        BookStatus status
) {
}
