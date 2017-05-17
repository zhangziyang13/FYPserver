package com.xjtlu.account.service;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjtlu.account.dao.TypeDao;
import com.xjtlu.service.BaseService;

@Service("typeService")
public class TypeService<T> extends BaseService<T> {
	private final static Logger log = Logger.getLogger(TypeService.class);

	@Autowired
	private TypeDao<T> dao;

	public TypeDao<T> getDao() {
		return dao;
	}

}
