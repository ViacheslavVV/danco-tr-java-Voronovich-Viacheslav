package com.library.dao.api;

import java.util.List;

import com.library.filter.FilterItem;
import com.library.model.Profile;

public interface IProfileRepository extends IBaseRepository<Profile, Integer> {

	public Profile getProfileByUser(Integer userId);
	public List<Profile> getProfilesByParams(FilterItem ... filters);
}
