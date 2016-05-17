package com.training.danco.services.api;

import java.util.List;

import com.training.danco.model.Audit;

public interface IAuditService {

	public Integer set(Audit audit);
	public Audit get(Integer id);
	public Boolean update(Audit audit);
	public Boolean delete(Integer auditId);
	public List<Audit> getAll();
}
