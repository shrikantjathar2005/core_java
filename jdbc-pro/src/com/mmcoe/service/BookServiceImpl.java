package com.mmcoe.service;

import java.util.List;
import java.util.stream.Collectors;

import com.mmcoe.dao.BookDao;
import com.mmcoe.pojo.Book;

public class BookServiceImpl implements BookService {

	private BookDao dao;
	
	// Dependency Injection
	public BookServiceImpl(BookDao dao) {
		this.dao = dao;
	}
	
	@Override
	public boolean save(Book b) {
		return dao.save(b);
	}

	@Override
	public Book find(int isbn) throws BookNotFoundException {
		return dao.find(isbn).orElseThrow(() ->
			new BookNotFoundException("Book not found with ISBN: " +isbn));
	}

	@Override
	public List<Book> list() {
		return dao.list();
	}

	@Override
	public boolean delete(int isbn) throws BookNotFoundException {
		if(dao.delete(isbn))
			return true;
		else
			throw new BookNotFoundException("Book not found with ISBN: " +isbn);
	}

	@Override
	public List<Book> findByPrice(double min, double max) {
		return dao.findByPrice(min, max);
	}

	@Override
	public List<Book> listOrderByTitle() {
		return dao.list().stream().sorted(
				(b1, b2) -> b1.getTitle().compareTo(b2.getTitle()))
				.collect(Collectors.toList());
	}
}


