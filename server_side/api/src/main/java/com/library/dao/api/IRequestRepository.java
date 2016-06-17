package com.library.dao.api;

import java.util.List;

import com.library.filter.FilterItem;
import com.library.model.Request;

public interface IRequestRepository extends IBaseRepository<Request, Integer> {

	public List<Request> getRequestsByBook(Integer bookId);
	public List<Request> getRequestsByUser(Integer userId);
	public List<Request> getRequestsByParams(FilterItem ... filters);
}
