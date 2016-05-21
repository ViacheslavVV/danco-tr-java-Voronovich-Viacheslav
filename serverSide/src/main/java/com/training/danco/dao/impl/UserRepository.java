package com.training.danco.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.training.danco.dao.api.IUserRepository;
import com.training.danco.model.User;

public class UserRepository extends AbstractRepository<User, Integer> implements IUserRepository{

	@Override
	public User getUserByLogin(Session session, String login) {
		
		Criteria criteria = session.createCriteria(User.class);
		User user = (User) criteria.add(Restrictions.eq("login", login)).uniqueResult();
		return user;
	}

}
