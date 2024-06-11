package com.example.book_mybatis.repository;

import com.example.book_mybatis.entity.BookEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface MybatisBookRepository {
    void save(BookEntity bookEntity);

    List<BookEntity> findAll();

    List<BookEntity> findCond(BookEntity bookEntity);
    Optional<BookEntity> findById(Long bookId);

    void update(BookEntity bookEntity);
    void delete(BookEntity bookEntity);

}
