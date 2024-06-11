package com.example.book_mybatis.service;

import com.example.book_mybatis.domain.Book;
import com.example.book_mybatis.entity.BookEntity;
import com.example.book_mybatis.repository.MybatisBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final MybatisBookRepository bookRepository;

    @Autowired
    public BookService(MybatisBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * 전체 도서 목록 조회
     */
    public List<Book.Simple> findBooks() {
        List<Book.Simple> list = new ArrayList<>();
        for (BookEntity bookEntity : bookRepository.findAll()) {
            Book.Simple book = new Book.Simple();
            book.setId(bookEntity.getId());
            book.setName(bookEntity.getName());
            book.setPublisher(bookEntity.getPublisher());
            book.setPrice(bookEntity.getPrice());
            list.add(book);
        }
        return list;
    }

    public List<Book.Simple> findCondBooks(Book.Create bookForm) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(bookForm.getName());
        bookEntity.setPublisher(bookForm.getPublisher());
        bookEntity.setPrice(bookForm.getPrice());

        List<Book.Simple> list = new ArrayList<>();
        for (BookEntity bookEntity2 : bookRepository.findCond(bookEntity)) {
            Book.Simple book2 = new Book.Simple();
            book2.setId(bookEntity2.getId());
            book2.setName(bookEntity2.getName());
            book2.setPublisher(bookEntity2.getPublisher());
            book2.setPrice(bookEntity2.getPrice());
            list.add(book2);
        }
        return list;
    }

    public BookEntity getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(
                IllegalAccessError::new
        );
    }

    public void updateBookService(Long bookId, Book.Update updateForm) {
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(
                IllegalArgumentException::new
        );

        bookEntity.setName(updateForm.getName());
        bookEntity.setPublisher(updateForm.getPublisher());
        bookEntity.setPrice(updateForm.getPrice());
        bookRepository.update(bookEntity);
    }

    public void deleteBook(Long bookId) throws IllegalAccessException {
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(
                IllegalAccessException::new
        );
        bookRepository.delete(bookEntity);
    }


    public Long addBook(Book.Create bookForm) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(bookForm.getName());
        bookEntity.setPublisher(bookForm.getPublisher());
        bookEntity.setPrice(bookForm.getPrice());
        bookRepository.save(bookEntity);
        return bookEntity.getId();
    }

}
