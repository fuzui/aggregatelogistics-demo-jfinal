package net.fuzui.controller;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;

import net.fuzui.utils.XmlUtils;

/**
 * 接口
 * 
 * @author Ze.Wang
 * @date 2020-11-04 00:07:27
 */
public class BackController extends Controller {
	public void index() {
		String resule = "result";
		renderJson(resule);
	}
	public void shunfeng() {
		JSONObject jsonObject = new JSONObject();
		String jsonString= HttpKit.readData(getRequest());
		System.out.println(jsonString);
		String resule = "result";
		Document document = null;
		try {
			document = DocumentHelper.parseText(jsonString);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // 获取根节点元素对象
        Element root = document.getRootElement();
        XmlUtils.iterateNodes(root, jsonObject);
        System.out.println(jsonObject);
		renderJson(resule);
	}
	public void yuantong() {
		String resule = "result";
		String jsonString= HttpKit.readData(getRequest());
		System.out.println(jsonString);
		renderJson(resule);
	}
	
}
