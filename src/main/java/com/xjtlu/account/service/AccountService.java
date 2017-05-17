package com.xjtlu.account.service;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjtlu.account.dao.AccountDao;
import com.xjtlu.page.BasePage;
import com.xjtlu.service.BaseService;
import com.xjtlu.util.ClassReflectUtil;

@Service("accountService")
public class AccountService<T> extends BaseService<T> {
	private final static Logger log = Logger.getLogger(AccountService.class);

	@Autowired
	private AccountDao<T> dao;

	public AccountDao<T> getDao() {
		return dao;
	}

	public List<T> autoCompletion(Object input) throws Exception {
		return getDao().autoCompletion(input);
	}

	public List<T> mainSearch(BasePage page) throws Exception {
		Integer rowCount = queryByCount(page);
		page.getPager().setRowCount(rowCount);
		return getDao().mainSearch(page);
	}

	public List<T> recommendSearch(BasePage page) throws Exception {
		return getDao().recommendSearch(page);
	}

	public void addIfNotExists(T t) throws Exception {
		// 设置主键.字符类型采用UUID,数字类型采用自增
		ClassReflectUtil.setIdKeyValue(t, "id", UUID.randomUUID().toString());
		getDao().addIfNotExists(t);
	}
}
