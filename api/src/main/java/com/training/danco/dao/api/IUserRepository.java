package com.training.danco.dao.api;

import org.hibernate.Session;

import com.training.danco.model.User;

public interface IUserRepository extends IBaseRepository<User, Integer> {

	public User getUserByLogin(Session session, String login);
}
