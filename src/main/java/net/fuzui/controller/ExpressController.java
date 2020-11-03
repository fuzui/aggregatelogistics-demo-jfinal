package net.fuzui.controller;

import com.jfinal.core.Controller;
import com.jfinal.json.FastJson;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.PropKit;

import net.fuzui.model.ErrorCodeEnum;
import net.fuzui.model.QueryLogistics;
import net.fuzui.model.Res;
import net.fuzui.utils.DisposeData;
import net.kdks.config.ExpressConfig;
import net.kdks.handler.ExpressHandlers;
import net.kdks.model.ExpressParam;
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
		String jsonString= HttpKit.readData(getRequest());
		System.out.println(jsonString);
		QueryLogistics param = FastJson.getJson().parse(jsonString, QueryLogistics.class);
		ExpressParam expressParam = param;
		ErrorCodeEnum errorCodeEnum = DisposeData.checkoutQueryParam(param);
		System.out.println(PropKit.get("privilege.key"));
		if(param.getKey()==null || !param.getKey().equals(PropKit.get(KEY_NAME))) {
			errorCodeEnum =  ErrorCodeEnum.NO_AUTH;
		}
		if(!errorCodeEnum.getCode().equals(ErrorCodeEnum.SUCCESS.getCode())) {
			renderJson(new Res(errorCodeEnum.getCode(), errorCodeEnum.getMsg()));
			return;
		}
		ExpressResponse result = expressHandlers.getExpressInfo(expressParam, param.getExpressCompanyNo());
		renderJson(DisposeData.resultDispose(result));
	}
}
