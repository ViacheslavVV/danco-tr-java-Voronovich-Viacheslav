package com.library.dao.api;

import java.util.List;

import com.library.filter.FilterItem;
import com.library.model.Book;

public interface IBookRepository extends IBaseRepository<Book, Integer> {

	public List<Book> getBooksByAuthor(Integer authorId);
	public List<Book> getBooksByBasket(Integer basketId);
	public List<Book> getBooksByCatalog(Integer catalogId);
	public Book getBookByRequest(Integer requestId);
	public List<Book> getBooksByParams(FilterItem ... filters);	
}
