package com.library.dao.api;

import java.util.List;

import com.library.filter.FilterItem;
import com.library.model.Author;

public interface IAuthorRepository extends IBaseRepository<Author, Integer> {

	public Author getAuthorByBook(Integer bookId);
	public List<Author> getAuthorsByParams(FilterItem ... filters);
}
