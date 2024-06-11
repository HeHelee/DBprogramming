package com.example.book_mybatis;

import com.example.book_mybatis.repository.BookRepository;
import com.example.book_mybatis.repository.JpaBookRepository;
import com.example.book_mybatis.service.BookService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringConfig {
    private EntityManager em; //JPA 용

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em; //JPA용
    }

    @Bean
    public BookService bookService() {
        return new BookService(bookRepository()); //JPA용
    }

    @Bean
    public BookRepository bookRepository() {
        return new JpaBookRepository(em); //JPA용
    }
}
