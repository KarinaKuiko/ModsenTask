package org.example.modsentask.dto.edit;

public record BookEditDto (
        String isbn,
        String name,
        String genre,
        String description,
        String author
) {
}
