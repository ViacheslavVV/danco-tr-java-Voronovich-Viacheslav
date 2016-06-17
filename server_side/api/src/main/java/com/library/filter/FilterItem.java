package com.library.filter;

public class FilterItem {

	private SearchParam searchParam;
	private Object paramValue;
	
	public FilterItem(SearchParam searchParam, Object paramValue) {
		this.searchParam = searchParam;
		this.paramValue = paramValue;
	}

	public SearchParam getSearchParam() {
		return searchParam;
	}

	public void setSearchParam(SearchParam searchParam) {
		this.searchParam = searchParam;
	}

	public Object getParamValue() {
		return paramValue;
	}

	public void setParamValue(Object paramValue) {
		this.paramValue = paramValue;
	}
	
	
}
