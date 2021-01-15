package net.fuzui.controller;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.json.FastJson;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.PropKit;

import net.fuzui.model.ErrorCodeEnum;
import net.fuzui.model.QueryLogistics;
import net.fuzui.model.QueryPriceLogistics;
import net.fuzui.model.Res;
import net.fuzui.utils.DisposeData;
import net.kdks.config.ExpressConfig;
import net.kdks.handler.ExpressHandlers;
import net.kdks.model.ExpressResponse;

/**
 * 接口
 * 
 * @author Ze.Wang
 * @date 2020-11-04 00:07:27
 */
public class ExpressController extends Controller {
	private final ExpressHandlers expressHandlers;
	private final String KEY_NAME = "privilege.key";
	public ExpressController() {
		ExpressConfig config = ExpressConfig.builder()
				.shunfengConfig(PropKit.get("express.sf.partnerID"),PropKit.get("express.sf.requestID"),PropKit.get("express.sf.checkWord"),PropKit.getInt("express.sf.isProduct"))
				.shentongConfig(PropKit.get("express.sto.appkey"), PropKit.get("express.sto.secretKey"), PropKit.getInt("express.sto.isProduct"))
				.baishiConfig(PropKit.get("express.htky.partnerID"), PropKit.get("express.htky.secretKey"), PropKit.getInt("express.htky.isProduct"))
				.yuantongConfig(PropKit.get("express.yto.appkey"), PropKit.get("express.yto.secretKey"), PropKit.get("express.yto.userId"),PropKit.getInt("express.yto.isProduct"))
				.zhongtongConfig(PropKit.get("express.zto.companyId"), PropKit.get("express.zto.secretKey"), PropKit.getInt("express.zto.isProduct"))
				.build();
		this.expressHandlers = new ExpressHandlers(config);
	}
	public void index() {
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		getResponse().addHeader("Access-Control-Allow-Methods", "POST, GET");
		getResponse().addHeader("Access-Control-Max-Age", "3600");
		getResponse().addHeader("Access-Control-Allow-Headers",
				"Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		String jsonString= HttpKit.readData(getRequest());
		System.out.println(jsonString);
		QueryLogistics param = FastJson.getJson().parse(jsonString, QueryLogistics.class);
		List<String> expressNos = new ArrayList<String>();
		expressNos.add(param.getExpressNo());
		param.setExpressNos(expressNos);
		ErrorCodeEnum errorCodeEnum = DisposeData.checkoutQueryParam(param);
		System.out.println(PropKit.get("privilege.key"));
		if(param.getKey()==null || !param.getKey().equals(PropKit.get(KEY_NAME))) {
			errorCodeEnum =  ErrorCodeEnum.NO_AUTH;
		}
		if(!errorCodeEnum.getCode().equals(ErrorCodeEnum.SUCCESS.getCode())) {
			renderJson(new Res(errorCodeEnum.getCode(), errorCodeEnum.getMsg()));
			return;
		}
		ExpressResponse result = expressHandlers.getExpressInfo(param, param.getExpressCompanyNo());
		renderJson(DisposeData.resultDispose(result, true));
	}
	
	public void batch() {
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		getResponse().addHeader("Access-Control-Allow-Methods", "POST, GET");
		getResponse().addHeader("Access-Control-Max-Age", "3600");
		getResponse().addHeader("Access-Control-Allow-Headers",
				"Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		String jsonString= HttpKit.readData(getRequest());
		System.out.println(jsonString);
		QueryLogistics param = FastJson.getJson().parse(jsonString, QueryLogistics.class);
		ErrorCodeEnum errorCodeEnum = DisposeData.checkoutQueryParam(param);
		System.out.println(PropKit.get("privilege.key"));
		if(param.getKey()==null || !param.getKey().equals(PropKit.get(KEY_NAME))) {
			errorCodeEnum =  ErrorCodeEnum.NO_AUTH;
		}
		if(!errorCodeEnum.getCode().equals(ErrorCodeEnum.SUCCESS.getCode())) {
			renderJson(new Res(errorCodeEnum.getCode(), errorCodeEnum.getMsg()));
			return;
		}
		ExpressResponse result = expressHandlers.getExpressInfo(param, param.getExpressCompanyNo());
		renderJson(DisposeData.resultDispose(result, false));
	}
	
	public void price() {
		getResponse().addHeader("Access-Control-Allow-Origin", "*");
		getResponse().addHeader("Access-Control-Allow-Methods", "POST, GET");
		getResponse().addHeader("Access-Control-Max-Age", "3600");
		getResponse().addHeader("Access-Control-Allow-Headers",
				"Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		String jsonString= HttpKit.readData(getRequest());
		System.out.println(jsonString);
		QueryPriceLogistics param = FastJson.getJson().parse(jsonString, QueryPriceLogistics.class);
		ErrorCodeEnum errorCodeEnum = DisposeData.checkoutQueryPriceParam(param);
		System.out.println(PropKit.get("privilege.key"));
		if(param.getKey()==null || !param.getKey().equals(PropKit.get(KEY_NAME))) {
			errorCodeEnum =  ErrorCodeEnum.NO_AUTH;
		}
		if(!errorCodeEnum.getCode().equals(ErrorCodeEnum.SUCCESS.getCode())) {
			renderJson(new Res(errorCodeEnum.getCode(), errorCodeEnum.getMsg()));
			return;
		}
		ExpressResponse result = expressHandlers.getExpressPrice(param, param.getExpressCompanyNo());
		renderJson(DisposeData.resultDispose(result, false));
	}
	
	public void test() {
		String testjson = "1123";
		renderJson(testjson);
	}
}
