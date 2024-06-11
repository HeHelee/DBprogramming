package com.example.book_mybatis;

import com.example.book_mybatis.domain.Book;
import com.example.book_mybatis.repository.MybatisBookRepository;
import com.example.book_mybatis.service.BookService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.example.book_mybatis.repository")
public class SpringConfig {
    private final MybatisBookRepository bookRepository;

    public SpringConfig(MybatisBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Bean
    public BookService bookService() {
        return new BookService(bookRepository);
    }
}
