package net.fuzui;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.json.FastJsonFactory;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;

import net.fuzui.controller.ExpressController;

/**
 * 启动类
 * 
 * @author Ze.Wang
 * @date 2020-11-04 00:05:58
 */
public class ExpressConfig extends JFinalConfig {
	
	static Prop p;
	
    public static void main(String[] args) {
        UndertowServer.start(ExpressConfig.class, 80, true);
    }
    
    /**
	 * PropKit.useFirstFound(...) 使用参数中从左到右最先被找到的配置文件
	 * 从左到右依次去找配置，找到则立即加载并立即返回，后续配置将被忽略
	 */
	static void loadConfig() {
		if (p == null) {
			p = PropKit.useFirstFound("config-prod.txt", "config-dev.txt");
		}
	}
	@Override
    public void configConstant(Constants me) {
    	loadConfig();
    	me.setDevMode(p.getBoolean("devMode", false));
       me.setJsonFactory(new FastJsonFactory());
    }
	@Override
    public void configRoute(Routes me) {
       me.add("/jfinal/logisticsServer/query", ExpressController.class);
    }
	@Override
    public void configEngine(Engine me) {}
	@Override
    public void configPlugin(Plugins me) {}
	@Override
    public void configInterceptor(Interceptors me) {}
	@Override
    public void configHandler(Handlers me) {}
}
