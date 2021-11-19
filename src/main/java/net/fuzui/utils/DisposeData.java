package net.fuzui.utils;

import java.util.List;

import net.fuzui.model.ErrorCodeEnum;
import net.fuzui.model.QueryBatchAllParam;
import net.fuzui.model.QueryBatchAllParamItem;
import net.fuzui.model.QueryLogistics;
import net.fuzui.model.QueryPriceLogistics;
import net.fuzui.model.Res;
import net.kdks.constant.ExpressResponseStatus;
import net.kdks.enums.ExpressCompanyCodeEnum;
import net.kdks.model.ExpressPriceResult;
import net.kdks.model.ExpressResponse;
import net.kdks.model.ExpressResult;
import net.kdks.utils.StringUtils;

/**
 * 数据处理
 * @author Ze.Wang
 * @date 2020-11-04 00:08:52
 */
public class DisposeData {
	public static ErrorCodeEnum checkoutQueryParam(QueryLogistics queryLogistics) {
		if(queryLogistics == null) {
			return ErrorCodeEnum.NOT_PARAM;
		}
		if(StringUtils.isEmpty(queryLogistics.getExpressCompanyNo())) {
			return ErrorCodeEnum.NOT_EXPRESS_COMPANY_NO;
		}
		if(queryLogistics.getExpressNos().size() == 0) {
			return ErrorCodeEnum.NOT_EXPRESS_NO;
		}
		if(ExpressCompanyCodeEnum.SF.getValue().equals(queryLogistics.getExpressCompanyNo())
				&& StringUtils.isEmpty(queryLogistics.getMobile())) {
			return ErrorCodeEnum.NOT_TEL;
		}

		return ErrorCodeEnum.SUCCESS;
	}
	
	
	public static ErrorCodeEnum checkoutQueryBatchParam(QueryBatchAllParam queryBatchAllParam) {
		if(queryBatchAllParam == null) {
			return ErrorCodeEnum.NOT_PARAM;
		}
		List<QueryBatchAllParamItem> items = queryBatchAllParam.getItems();
		if(items == null) {
			return ErrorCodeEnum.NOT_PARAM;
		}
		for (QueryBatchAllParamItem item: items) {
			if(StringUtils.isEmpty(item.getExpressCompanyNo())) {
				return ErrorCodeEnum.NOT_EXPRESS_COMPANY_NO;
			}
			if(null == item.getExpressNo() || "".equals(item.getExpressNo())) {
				System.out.println(item.getExpressNo());
				return ErrorCodeEnum.NOT_EXPRESS_NO;
			}
			if(ExpressCompanyCodeEnum.SF.getValue().equals(item.getExpressCompanyNo())
					&& StringUtils.isEmpty(item.getMobile())) {
				return ErrorCodeEnum.NOT_TEL;
			}
		}
		return ErrorCodeEnum.SUCCESS;
	}
	public static ErrorCodeEnum checkoutQueryPriceParam(QueryPriceLogistics queryPriceLogistics) {
		if(queryPriceLogistics == null) {
			return ErrorCodeEnum.NOT_PARAM;
		}
		return ErrorCodeEnum.SUCCESS;
	}
	
	public static Res resultDispose(ExpressResponse response, boolean single) {
		if(ErrorCodeEnum.EXPRESS_NO_NOT_EXIST.getMsg().equals(response.getMsg())) {
			return new Res(ErrorCodeEnum.EXPRESS_NO_NOT_EXIST.getCode(), response.getMsg());
		}
		if(ExpressResponseStatus.FAIL.equals(response.getCode())) {
			return Res.failed(response.getMsg());
		}
		return Res.ok(response.getData());
	}
	
}
