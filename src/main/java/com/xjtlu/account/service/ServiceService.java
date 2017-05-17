package com.xjtlu.account.service;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjtlu.account.dao.ServiceDao;
import com.xjtlu.service.BaseService;

@Service("serviceService")
public class ServiceService<T> extends BaseService<T> {
	private final static Logger log = Logger.getLogger(ServiceService.class);

	@Autowired
	private ServiceDao<T> dao;

	public ServiceDao<T> getDao() {
		return dao;
	}

}
