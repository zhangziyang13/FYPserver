package com.xjtlu.account.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.xjtlu.util.CnToPy;
import com.xjtlu.util.Constant;
import com.xjtlu.util.HtmlUtil;
import com.xjtlu.util.HttpUtil;

import ch.hsr.geohash.GeoHash;

import com.xjtlu.controller.BaseAction;
import com.xjtlu.account.entity.Account;
import com.xjtlu.account.page.AccountPage;
import com.xjtlu.account.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseAction {

	private final static Logger log = Logger.getLogger(AccountController.class);
	private static double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	// Servrice start
	@Autowired(required = false) // 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private AccountService<Account> accountService;

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public ModelAndView list(AccountPage page, HttpServletRequest request) throws Exception {
		Map<String, Object> context = getRootMap();
		return forword("manage/com/xjtlu/account/account", context);
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
	public void datalist(AccountPage page, HttpServletResponse response) throws Exception {
		List<Account> dataList = accountService.queryByList(page);
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
		Account entity = (Account) JSONObject.toBean(jsonObject, Account.class);
		Map<String, Object> context = new HashMap<String, Object>();
		if (entity.getId() == null || StringUtils.isBlank(entity.getId().toString())) {
			accountService.add(entity);
		} else {
			accountService.update(entity);
		}
		sendSuccessMessage(response, "保存成功~");
	}

	@RequestMapping("/getId")
	public void getId(String id, HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap();
		Account entity = accountService.queryById(id);
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
		accountService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

	/**
	 * 主搜索
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mainSearch")
	public void mainSearch(AccountPage a, String longitude, String latitude, String range, HttpServletResponse response)
			throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Account> dataList = new ArrayList<Account>();
		List<Account> resultList = new ArrayList<Account>();
		List<Account> recommendList = new ArrayList<Account>();
		List<String> labelList = new ArrayList<>();
		List<String> typeList = new ArrayList<>();

		double lat1 = Double.parseDouble(latitude);
		double lng1 = Double.parseDouble(longitude);
		// 第一种方法：用jar包获取geohash
		GeoHash userGeohash = GeoHash.withCharacterPrecision(lat1, lng1, 8);
		String geoHash = userGeohash.toBase32();
		// 第二种方法：自定义
		// com.xjtlu.util.geohash.GeoHash g = new
		// com.xjtlu.util.geohash.GeoHash(lat1, lng1);
		// String geoHash = g.getGeoHashBase32();

		// 距离范围
		double maxRange = 10000;
		if (range != null) {
			switch (range) {
			case "<1km":
				geoHash = geoHash.substring(0, 4);
				maxRange = 1000;
				break;
			case "<2km":
				geoHash = geoHash.substring(0, 4);
				maxRange = 2000;
				break;
			case "<5km":
				geoHash = geoHash.substring(0, 3);
				maxRange = 5000;
				break;
			case "<10km":
				geoHash = geoHash.substring(0, 3);
				maxRange = 10000;
				break;
			default:
				break;
			}
			a.setCoordinate(geoHash + "%");
		}

		// // 类型
		// if (a.getType() != null) {
		// if (a.getType().equals("Catering")) {
		//
		// }
		// }

		// 价格范围
		Integer minBudget = 0;
		Integer maxBudget = 9999;
		if (a.getBudget() != null) {
			minBudget = Integer.valueOf((a.getBudget().split("-"))[0].trim());
			maxBudget = Integer.valueOf((a.getBudget().split("-"))[1].trim());
		}
		a.setMinBudget(minBudget);
		a.setMaxBudget(maxBudget);

		int start = (a.getPage() - 1) * 10;
		int end = a.getPage() * 10;
		int total = 0;
		// 首次查询——根据输入条件
		a.setRealname("%" + a.getRealname() + "%");
		dataList = accountService.mainSearch(a);
		// 结果数量正常，计算距离
		if (a.getOrderBy() != null && a.getOrderBy().equals("Distance")) {
			for (int i = 0; i < dataList.size(); i++) {
				Account b = dataList.get(i);
				double lat2 = Double.parseDouble(b.getLatitude());
				double lng2 = Double.parseDouble(b.getLongitude());
				double distance = GetDistance(lat1, lng1, lat2, lng2);
				if (distance < maxRange) {
					b.setDistance(distance);
					if (resultList.size() == 0) {
						resultList.add(b);
					} else {
						for (int j = 0; j < resultList.size(); j++) {
							Account c = resultList.get(j);
							if (b.getDistance() < c.getDistance()) {
								resultList.add(j, b);
								break;
							}
							if (j == resultList.size() - 1) {
								resultList.add(b);
								break;
							}
						}
					}
				}
			}
		} else {
			for (int i = 0; i < dataList.size(); i++) {
				Account b = dataList.get(i);
				double lat2 = Double.parseDouble(b.getLatitude());
				double lng2 = Double.parseDouble(b.getLongitude());
				double distance = GetDistance(lat1, lng1, lat2, lng2);
				if (distance < maxRange) {
					b.setDistance(distance);
					resultList.add(b);
				}
			}
		}
		total = resultList.size();
		if (resultList.size() >= end) {
			resultList = resultList.subList(start, end);
		} else if (resultList.size() <= start) {
			resultList = new ArrayList<>();
		} else if (resultList.size() > start && resultList.size() < end) {
			resultList = resultList.subList(start, resultList.size());
		}

		// 推荐列表算法**************需完善**************
		if (total == 0) {
			recommendList = accountService.recommendSearch(a);
		} else if (total < 10) {
			// 定义一个map来存放每个元素出现的次数
			Map<String, Integer> labelCount = new HashMap<String, Integer>();
			for (int i = 0; i < resultList.size(); i++) {
				Account c = resultList.get(i);

				// 循环计算标签数量
				String cLabel = c.getLabel();
				Integer g = labelCount.get(cLabel);
				if (g == null) {
					labelCount.put(cLabel, 1);

				} else {
					labelCount.put(cLabel, g + 1);
				}
			}

			for (String key : labelCount.keySet()) {
				System.out.println(key + "出现了 " + labelCount.get(key) + "次");
				labelList.add(key);
			}
			a.setLabelList(labelList);
			recommendList = accountService.recommendSearch(a);
		}

		// 为推荐列表计算距离
		if (recommendList.size() > 0) {
			for (int i = 0; i < recommendList.size(); i++) {
				Account b = recommendList.get(i);
				double lat2 = Double.parseDouble(b.getLatitude());
				double lng2 = Double.parseDouble(b.getLongitude());
				double distance = GetDistance(lat1, lng1, lat2, lng2);
				b.setDistance(distance);
				recommendList.set(i, b);
			}
		}

		jsonMap.put("total", total);
		jsonMap.put("rows", resultList);
		jsonMap.put("recommend", recommendList);
		HtmlUtil.writerJson(response, jsonMap);
	}

	/**
	 * 添加商户数据
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insertShop")
	public void insertShop(HttpServletResponse response) throws Exception {
		for (int q = 0; q < 1000; q++) {
			String loca = randomLonLat(120.499999, 120.811111, 31.222222, 31.371111);
			int radius = 10000;
			int page_size = 10;
			for (int page_num = 0; page_num < 40; page_num++) {
				String BaiduUrl = Constant.urlAPI2 + "&radius=" + radius + "&page_size=" + page_size + "&page_num="
						+ page_num + "&location=" + loca;
				String json = HttpUtil.get(BaiduUrl, null, "utf-8");
				JSONObject jsonObject = JSONObject.fromObject(json);
				List<JSONObject> results = (List<JSONObject>) jsonObject.get("results");
				for (int i = 0; i < results.size(); i++) {
					JSONObject result = results.get(i);
					JSONObject detail = (JSONObject) result.get("detail_info");
					JSONObject location = (JSONObject) result.get("location");
					Account entity = new Account();
					entity.setUsername("admin");
					entity.setPassword("123456");
					if (result.get("name") != null) {
						entity.setRealname(result.get("name").toString());
						String pingyin = CnToPy.getPingYin(result.get("name").toString());
						entity.setNickname(pingyin);
					} else {
						entity.setRealname("未知");
						entity.setNickname("weizhi");
					}
					if (result.get("telephone") != null) {
						entity.setTelephone(result.get("telephone").toString());
					}
					entity.setCountry("中国");
					entity.setProvince("江苏");
					entity.setCity("苏州");
					entity.setDistrict(result.get("address").toString());
					entity.setAddress("中国江苏省苏州市" + result.get("address").toString());
					entity.setLongitude(location.get("lng").toString());
					entity.setLatitude(location.get("lat").toString());
					GeoHash userGeohash = GeoHash.withCharacterPrecision(
							Double.parseDouble(location.get("lat").toString()),
							Double.parseDouble(location.get("lng").toString()), 12);
					String geoHash = userGeohash.toBase32();
					entity.setCoordinate(geoHash);
					if (detail.get("tag") != null) {
						entity.setLabel(detail.get("tag").toString());
					}
					if (detail.get("type") != null) {
						entity.setType(detail.get("type").toString());
					}
					if (detail.get("overall_rating") != null) {
						entity.setOverallRating(new BigDecimal(detail.get("overall_rating").toString()));
					}
					if (detail.get("service_rating") != null) {
						entity.setServiceRating(new BigDecimal(detail.get("service_rating").toString()));
					}
					if (detail.get("environment_rating") != null) {
						entity.setEnvironmentRating(new BigDecimal(detail.get("environment_rating").toString()));
					}
					if (detail.get("price") != null) {
						entity.setBudget(new BigDecimal(detail.get("price").toString()));
					}
					if (detail.get("detail_url") != null) {
						entity.setDetailURL(detail.get("detail_url").toString());
					}
					if (detail.get("comment_num") != null) {
						entity.setCommentNum(Integer.parseInt(detail.get("comment_num").toString()));
					}
					accountService.addIfNotExists(entity);
				}
			}
		}
	}

	/**
	 * 添加商户数据
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insertShopFromGoogle")
	public void insertShopFromGoogle(HttpServletResponse response) throws Exception {
		// for (int q = 0; q < 1000; q++) {
		String loca = randomLonLat(151.18758, 151.203462, -33.874096, -33.862646);
		int radius = 10000;
		String url_1 = Constant.urlAPI3 + "&radius=" + radius + "&location=" + loca;
		String json_1 = HttpUtil.get(url_1, null, "utf-8");
		JSONObject object_1 = JSONObject.fromObject(json_1);
		List<JSONObject> results_1 = (List<JSONObject>) object_1.get("results");
		for (int i = 0; i < results_1.size(); i++) {
			JSONObject result = results_1.get(i);
			JSONObject geometry = (JSONObject) result.get("geometry");
			JSONObject location = (JSONObject) geometry.get("location");

			Account entity = new Account();
			entity.setUsername("admin");
			entity.setPassword("123456");
			if (result.get("name") != null) {
				entity.setRealname(result.get("name").toString());
				entity.setNickname(result.get("name").toString());
			} else {
				entity.setRealname("unknown");
				entity.setNickname("unknown");
			}

			if (result.get("place_id") != null) {
				String placeId = (String) result.get("place_id");
				String placeUrl = Constant.urlAPI4 + "&placeid=" + placeId;
				String placeJson = HttpUtil.get(placeUrl, null, "utf-8");
				JSONObject placeObject = JSONObject.fromObject(placeJson);
				JSONObject placeDetail = (JSONObject) placeObject.get("result");
				if (placeDetail.get("formatted_phone_number") != null) {
					entity.setTelephone(placeDetail.get("formatted_phone_number").toString());
				}
				if (placeDetail.get("formatted_address") != null) {
					entity.setAddress(placeDetail.get("formatted_address").toString());
				}
				if (placeDetail.get("url") != null) {
					entity.setDetailURL(placeDetail.get("url").toString());
				}
			}
			entity.setCountry("Australia");
			entity.setCity("Sydney");
			entity.setLongitude(location.get("lng").toString());
			entity.setLatitude(location.get("lat").toString());
			GeoHash userGeohash = GeoHash.withCharacterPrecision(Double.parseDouble(location.get("lat").toString()),
					Double.parseDouble(location.get("lng").toString()), 12);
			String geoHash = userGeohash.toBase32();
			entity.setCoordinate(geoHash);
			if (result.get("rating") != null) {
				entity.setOverallRating(new BigDecimal(result.get("rating").toString()));
			}

			if (result.get("types") != null) {
				List<String> labelList = (List<String>) result.get("types");
				String label = "";
				for (int q = 0; q < labelList.size(); q++) {
					if (q == 0) {
						label = label + labelList.get(q);
					} else {
						label = label + ";" + labelList.get(q);
					}
				}
				entity.setLabel(label);
			}
			entity.setType("cater");
			if (result.get("price_level") != null) {
				entity.setBudget(new BigDecimal(result.get("price_level").toString()));
			}
			accountService.addIfNotExists(entity);
		}

	}

	public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000d) / 10000d;
		s = s * 1000;
		return s;
	}

	public String randomLonLat(double MinLon, double MaxLon, double MinLat, double MaxLat) {
		BigDecimal db = new BigDecimal(Math.random() * (MaxLon - MinLon) + MinLon);
		String lon = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();
		db = new BigDecimal(Math.random() * (MaxLat - MinLat) + MinLat);
		String lat = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();
		return lat + "," + lon;
	}
}
