package com.library.dao.api;

import com.library.model.User;

public interface IUserRepository extends IBaseRepository<User, Integer> {
	
	public User getUserByBasket(Integer basketId);
	public User getUserByRequest(Integer requestId);
	public User getUserByLogin(String login);
}
