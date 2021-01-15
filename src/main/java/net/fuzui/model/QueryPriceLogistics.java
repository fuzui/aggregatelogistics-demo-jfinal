package net.fuzui.model;

import net.kdks.model.ExpressParam;
import net.kdks.model.ExpressPriceParam;

/**
 * 查询参数
 * 
 * @author Ze.Wang
 * @date 2020-11-04 00:08:07
 */
public class QueryPriceLogistics extends ExpressPriceParam {
	
	private static final long serialVersionUID = 1L;
	
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
	
}
