package com.library.dao.api;

import com.library.model.Basket;

public interface IBasketRepository extends IBaseRepository<Basket, Integer> {

	public Basket getBasketByUser(Integer userId); 
}
