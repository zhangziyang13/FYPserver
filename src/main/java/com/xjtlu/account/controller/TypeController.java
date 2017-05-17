package com.xjtlu.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
import com.xjtlu.util.HtmlUtil;
import com.xjtlu.controller.BaseAction;
import com.xjtlu.account.entity.Type;
import com.xjtlu.account.page.TypePage;
import com.xjtlu.account.service.TypeService;

@Controller
@RequestMapping("/type")
public class TypeController extends BaseAction {

	private final static Logger log = Logger.getLogger(TypeController.class);

	// Servrice start
	@Autowired(required = false) // 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private TypeService<Type> typeService;

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public ModelAndView list(TypePage page, HttpServletRequest request) throws Exception {
		Map<String, Object> context = getRootMap();
		return forword("manage/com/xjtlu/account/type", context);
	}

	/**
	 * ilook 首页
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/dataList")
	public void datalist(TypePage page, HttpServletResponse response) throws Exception {
		List<Type> dataList = typeService.queryByList(page);
		// 设置页面数据
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}

	/**
	 * 添加或修改数据
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public void save(String json, HttpServletResponse response) throws Exception {
		JSONObject jsonObject = JSONObject.fromObject(json);
		Type entity = (Type) JSONObject.toBean(jsonObject, Type.class);
		Map<String, Object> context = new HashMap<String, Object>();
		if (entity.getId() == null || StringUtils.isBlank(entity.getId().toString())) {
			typeService.add(entity);
		} else {
			typeService.update(entity);
		}
		sendSuccessMessage(response, "保存成功~");
	}

	@RequestMapping("/getId")
	public void getId(String id, HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap();
		Type entity = typeService.queryById(id);
		if (entity == null) {
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", entity);
		HtmlUtil.writerJson(response, context);
	}

	@RequestMapping("/delete")
	public void delete(String[] id, HttpServletResponse response) throws Exception {
		typeService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}
