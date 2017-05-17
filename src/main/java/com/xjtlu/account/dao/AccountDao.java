package com.xjtlu.account.dao;

import java.util.List;

import com.xjtlu.dao.BaseDao;
import com.xjtlu.page.BasePage;

public interface AccountDao<T> extends BaseDao<T> {

	public List<T> autoCompletion(Object input);

	public List<T> mainSearch(BasePage page);

	public List<T> recommendSearch(BasePage page);

	public void addIfNotExists(T t);
}
