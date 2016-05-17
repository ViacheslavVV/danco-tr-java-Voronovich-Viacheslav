package com.training.danco.services.api;

import java.util.List;
import com.training.danco.model.User;

public interface IUserService {
	public Integer set(User user);
	public User get(Integer id);
	public Boolean update(User user);
	public Boolean delete(Integer userId);
	public List<User> getAll();
}
