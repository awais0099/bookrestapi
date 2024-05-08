package com.practice.bookrestapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.practice.bookrestapi.dao.BookRepository;
import com.practice.bookrestapi.entities.Book;

@Component
public class BookService {
	private static List<Book> list = new ArrayList<>();
	private BookRepository bookRepository;
	static {
		list.add(new Book(2, "book 2", "xyz"));
		list.add(new Book(3, "book 3", "xyz"));
		list.add(new Book(4, "book 4", "xyz"));
		list.add(new Book(5, "book 5", "xyz"));
	}
	
	public List<Book> getAllBooks() {
//		this.bookRepository.findAll();
		Iterable<Book> booksIterable = this.bookRepository.findAll();
	    List<Book> booksList = new ArrayList<>();
	    booksIterable.forEach(booksList::add);
	    return booksList;
	}
	
	public Book getBookById(int id) {
		Book book = null;
		
		try {
//			book = list.stream().filter(e->e.getId() == id).findFirst().get();
			Optional<Book> bookOptional = this.bookRepository.findById(id);
			book = bookOptional.orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	
	public Book addBook(Book book) {
//		list.add(book);
		Book result = this.bookRepository.save(book);
		return result;
	}
	
	public void deleteBook(int id) {
		this.bookRepository.deleteById(id);
//		list = list.stream().filter(e -> e.getId() != id).collect(Collectors.toList());
	}
	
	public void updateBook(Book book, int id) {
		book.setId(id);
		this.bookRepository.save(book);
		
		/*
		 * list = list.stream().map(b -> { if (b.getId() == id) {
		 * b.setTitle(book.getTitle()); b.setAuthor(book.getAuthor()); } return b;
		 * }).collect(Collectors.toList());
		 */
	}
}
