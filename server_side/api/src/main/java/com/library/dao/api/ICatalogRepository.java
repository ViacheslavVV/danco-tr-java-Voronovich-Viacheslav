package com.library.dao.api;

import java.util.List;

import com.library.filter.FilterItem;
import com.library.model.Catalog;

public interface ICatalogRepository extends IBaseRepository<Catalog, Integer> {

	public Catalog getCatalogByBook(Integer bookId);
	public Catalog getParentCatalogByCatalog(Integer catalogId);
	public List<Catalog> getSubcatalogsByCatalog(Integer catalogId);
	public List<Catalog> getCatalogsByParams(FilterItem ... filters);
}
