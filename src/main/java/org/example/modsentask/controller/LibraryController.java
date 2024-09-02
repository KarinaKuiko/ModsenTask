package org.example.modsentask.controller;

import lombok.RequiredArgsConstructor;
import org.example.modsentask.dto.edit.BookEditDto;
import org.example.modsentask.dto.edit.LibraryEditDto;
import org.example.modsentask.dto.read.BookReadDto;
import org.example.modsentask.dto.read.LibraryReadDto;
import org.example.modsentask.entity.Library;
import org.example.modsentask.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;


}
