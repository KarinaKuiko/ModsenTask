package org.example.modsentask.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.modsentask.dto.edit.BookEditDto;
import org.example.modsentask.dto.edit.LibraryEditDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRequest {
    private BookEditDto bookEditDto;
    private LibraryEditDto libraryEditDto;
}
