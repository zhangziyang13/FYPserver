package com.xjtlu.label.service;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjtlu.label.dao.LabelDao;
import com.xjtlu.service.BaseService;

@Service("labelService")
public class LabelService<T> extends BaseService<T> {
	private final static Logger log = Logger.getLogger(LabelService.class);

	@Autowired
	private LabelDao<T> dao;

	public LabelDao<T> getDao() {
		return dao;
	}

}
