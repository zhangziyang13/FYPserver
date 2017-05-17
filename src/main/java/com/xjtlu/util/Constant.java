package com.xjtlu.util;

import java.util.ResourceBundle;

import org.apache.commons.lang.math.NumberUtils;

public class Constant {


	public static String baiduAK = "naG8GczDniE2hWXeuxy4jXGTQjDvpqi3";
	public static String googleKey = "AIzaSyAsSUyrkbSpcPhh1bTWvayfyKJiMKsCejI";
	public static String urlAPI = "http://api.map.baidu.com/place/v2/search?q=美食&scope=2&region=苏州&city_limit=true&output=json&ak=" + baiduAK;
	public static String urlAPI2 = "http://api.map.baidu.com/place/v2/search?query=美食&scope=2&region=苏州&city_limit=true&output=json&ak=" + baiduAK;
	public static String urlAPI3 = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?&type=restaurant&key=" + googleKey;
	public static String urlAPI4 = "https://maps.googleapis.com/maps/api/place/details/json?key=" + googleKey;
	public static String urlAPI5 = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=" + googleKey;
	
	/**
	 * 超级管理员常量
	 * @author  www.jeecg.org
	 *
	 */
	public static enum SuperAdmin {
		NO(0, "否"), YES(1,"是");
		public int key;
		public String value;
		private SuperAdmin(int key, String value) {
			this.key = key;
			this.value = value;
		}
		public static SuperAdmin get(int key) {
			SuperAdmin[] values = SuperAdmin.values();
			for (SuperAdmin object : values) {
				if (object.key == key) {
					return object;
				}
			}
			return null;
		}
	}

}