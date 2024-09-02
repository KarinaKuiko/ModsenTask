package org.example.modsentask.mapper;

import org.example.modsentask.config.MapperConfiguration;
import org.example.modsentask.dto.edit.BookEditDto;
import org.example.modsentask.dto.read.BookReadDto;
import org.example.modsentask.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class)
public interface BookMapper {
    BookReadDto toReadDto(Book book);
    Book toBook(BookEditDto dto);
    void map(@MappingTarget Book to, BookEditDto from);
}
