package net.fuzui.model;

import java.util.List;

import lombok.Data;
import net.kdks.constant.RequestConstant;

/**
 * 查询参数
 * 
 * @author Ze.Wang
 * @date 2020-11-04 00:08:07
 */
@Data
public class QueryBatchAllParam {
	
	private String format = RequestConstant.JSON;
	
	private List<QueryBatchAllParamItem> items;
	
	/**
	 * 是否展示路由
	 */
	private boolean isViewRoute = true;
	
	/**
	 * 是否展示原生信息
	 */
	private boolean isViewOriginal = false;
	
	private String key;

}
