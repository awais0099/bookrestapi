package com.practice.bookrestapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.bookrestapi.entities.Book;
import com.practice.bookrestapi.services.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public List<Book> getBooks() {
		return this.bookService.getAllBooks();
	}
	
	/*
	 * @RequestMapping(value = "/books/{id}", method = RequestMethod.GET) public
	 * Book getBook(@PathVariable("id") int id) { return
	 * this.bookService.getBookById(id); }
	 */
	
	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		Book book = this.bookService.getBookById(id);
		
		if(book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.of(Optional.of(book));
	}
	
	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book book2 = null;
		try {
			book2 = this.bookService.addBook(book);
			return ResponseEntity.of(Optional.of(book2));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable int id) {
		try {
			this.bookService.deleteBook(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Book> update(@RequestBody Book book, @PathVariable int id) {
		try {
			this.bookService.updateBook(book, id);
			return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
}
