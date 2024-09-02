package org.example.modsentask.mapper;

import org.example.modsentask.config.MapperConfiguration;
import org.example.modsentask.dto.edit.BookEditDto;
import org.example.modsentask.dto.edit.LibraryEditDto;
import org.example.modsentask.dto.read.BookReadDto;
import org.example.modsentask.dto.read.LibraryReadDto;
import org.example.modsentask.entity.Book;
import org.example.modsentask.entity.Library;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class)
public interface LibraryMapper {
    LibraryReadDto toReadDto(Library library);
    Library toLibrary(LibraryEditDto dto);
    void map(@MappingTarget Library to, LibraryEditDto from);
}
