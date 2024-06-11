package com.example.book_mybatis.repository;

import com.example.book_mybatis.entity.BookEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaBookRepository implements BookRepository{
    private final EntityManager em;

    public JpaBookRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(BookEntity book) {
        em.persist(book);
    }

    @Override
    public List<BookEntity> findAll() {
        List<BookEntity> result = em.createQuery("select b from book b", BookEntity.class)
                .getResultList();
        return result;
    }

    @Override
    public Optional<BookEntity> findById(Long id) {
        BookEntity book = em.find(BookEntity.class,id);
        return Optional.ofNullable(book);
    }

    @Override
    public List<BookEntity> findCond(BookEntity book) {
        String jpql = "select b from book b where b.name LIKE concat('%', :inputName, '%')and b.publisher LIKE concat('%',:inputPublisher,'%')";
        TypedQuery<BookEntity> query = em.createQuery(jpql, BookEntity.class);
        query.setParameter("inputName",book.getName());
        query.setParameter("inputPublisher",book.getPublisher());
        List<BookEntity> result = query.getResultList();
        return result;
    }

    @Override
    public void delete(BookEntity book) {
        System.out.println("*** Repository.delete 시작 ***");
        em.remove(book);
    }
}
