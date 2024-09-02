package org.example.modsentask.dto.edit;

import org.example.modsentask.entity.Book;
import org.example.modsentask.entity.enumeration.BookStatus;

import java.util.Date;

public record LibraryEditDto(
        Long book_id,
        Date taken,
        Date needReturn,
        BookStatus status
) {
}
