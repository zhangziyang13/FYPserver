package com.xjtlu.label.controller;

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
import com.xjtlu.entity.BaseEntity;
import com.xjtlu.entity.BaseEntity.DELETED;
import com.xjtlu.label.entity.Label;
import com.xjtlu.label.page.LabelPage;
import com.xjtlu.label.service.LabelService;

@Controller
@RequestMapping("/label")
public class LabelController extends BaseAction {

	private final static Logger log = Logger.getLogger(LabelController.class);

	// Servrice start
	@Autowired(required = false) // 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private LabelService<Label> labelService;

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public ModelAndView list(LabelPage page, HttpServletRequest request) throws Exception {
		Map<String, Object> context = getRootMap();
		return forword("manage/com/xjtlu/label/label", context);
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
	public void datalist(LabelPage page, HttpServletResponse response) throws Exception {
		List<Label> dataList = labelService.queryByList(page);
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
		Label entity = (Label) JSONObject.toBean(jsonObject, Label.class);
		Map<String, Object> context = new HashMap<String, Object>();
		if (entity.getId() == null || StringUtils.isBlank(entity.getId().toString())) {
			labelService.add(entity);
		} else {
			labelService.update(entity);
		}
		sendSuccessMessage(response, "保存成功~");
	}

	@RequestMapping("/getId")
	public void getId(String id, HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap();
		Label entity = labelService.queryById(id);
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
		labelService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}
