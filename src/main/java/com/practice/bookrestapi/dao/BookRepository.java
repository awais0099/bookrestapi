package com.practice.bookrestapi.dao;

import org.springframework.data.repository.CrudRepository;

import com.practice.bookrestapi.entities.Book;

public interface BookRepository extends CrudRepository<Book,Integer>{

}
