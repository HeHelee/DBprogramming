package com.example.book_mybatis.repository;

import com.example.book_mybatis.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    void save(BookEntity bookEntity);

    List<BookEntity> findAll();

    List<BookEntity> findCond(BookEntity bookEntity);
    Optional<BookEntity> findById(Long bookId);

    void delete(BookEntity bookEntity);


}
