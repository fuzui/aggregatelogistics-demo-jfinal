package net.fuzui.utils;

import net.fuzui.model.ErrorCodeEnum;
import net.fuzui.model.QueryLogistics;
import net.fuzui.model.Res;
import net.kdks.constant.ExpressResponseStatus;
import net.kdks.enums.ExpressCompanyCodeEnum;
import net.kdks.model.ExpressResponse;
import net.kdks.utils.StringUtils;

/**
 * 数据处理
 * @author Ze.Wang
 * @date 2020-11-04 00:08:52
 */
public class DisposeData {
	public static ErrorCodeEnum checkoutQueryParam(QueryLogistics queryLogistics) {
		if(StringUtils.isEmpty(queryLogistics.getExpressCompanyNo())) {
			return ErrorCodeEnum.NOT_EXPRESS_COMPANY_NO;
		}
		if(StringUtils.isEmpty(queryLogistics.getExpressNo())) {
			return ErrorCodeEnum.NOT_EXPRESS_NO;
		}
		if(ExpressCompanyCodeEnum.SF.getValue().equals(queryLogistics.getExpressCompanyNo())
				&& StringUtils.isEmpty(queryLogistics.getMobile())) {
			return ErrorCodeEnum.NOT_TEL;
		}

		return ErrorCodeEnum.SUCCESS;
	}
	
	public static Res resultDispose(ExpressResponse response) {
		if(ErrorCodeEnum.EXPRESS_NO_NOT_EXIST.getMsg().equals(response.getMsg())) {
			return new Res(ErrorCodeEnum.EXPRESS_NO_NOT_EXIST.getCode(), response.getMsg());
		}
		if(ExpressResponseStatus.FAIL.equals(response.getCode())) {
			return Res.failed(response.getMsg());
		}
		return Res.ok(response.getData());
	}
}
