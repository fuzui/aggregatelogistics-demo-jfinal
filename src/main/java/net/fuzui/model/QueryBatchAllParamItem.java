package net.fuzui.model;

import lombok.Data;
import net.kdks.enums.ExpressCompanyCodeEnum;

/**
 * 查询参数
 * 
 * @author Ze.Wang
 * @date 2020-11-04 00:08:07
 */
@Data
public class QueryBatchAllParamItem {
	
	private String expressNo;
	
	private String expressCompanyNo;
	
	private String mobile;
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof QueryBatchAllParamItem) {
			QueryBatchAllParamItem paramItem = (QueryBatchAllParamItem) o;
			if(paramItem.getExpressNo().equals(this.getExpressNo()) &&
					paramItem.getExpressCompanyNo().equals(this.getExpressCompanyNo())) {
				if(ExpressCompanyCodeEnum.SF.getValue().equals(this.getExpressCompanyNo())) {
					return paramItem.getMobile().equals(this.getMobile());
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		String code = this.getExpressCompanyNo();
		if(ExpressCompanyCodeEnum.SF.getValue().equals(this.getExpressCompanyNo())) {
			return code+ "#" + this.getMobile();
		}
		return code;
	}
	
}
