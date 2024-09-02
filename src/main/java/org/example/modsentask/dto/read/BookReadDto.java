package org.example.modsentask.dto.read;

import java.util.UUID;

public record BookReadDto(
        Long id,
        String isbn,
        String name,
        String genre,
        String description,
        String author
) {
}
