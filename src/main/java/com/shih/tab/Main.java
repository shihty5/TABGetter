package com.shih.tab;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
	static final Logger logger = LogManager.getLogger(Main.class.getName());
	public static final String PROXY_HOST = "webproxy.wlb.nsroot.net";
	public static final String PROXY_PORT = "8080";
	// 定义即将访问的链接
	public static final String TARGET_URL = "http://www.baidu.com";
	public static final String URL = "http://www.zhihu.com/explore/recommendations";
	public static final String RT_JENKINS_URL = "http://pfdwlnx1i.nam.nsroot.net:8080";
	
	public static void main(String[] args) {

		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST,
				Integer.parseInt(PROXY_PORT)));
		//String result = CommonUtil.getContextFromUrl(URL, proxy);
		//logger.info(CommonUtil.RegexString(result, "src=\"(.+?)\""));
		//logger.info(result);
		//ArrayList<String> resultList = CommonUtil.RegexStringList(result, "question_link.+?>(.+?)<");
		String content = Spider.SendGet(RT_JENKINS_URL, null);  
		ArrayList<Zhihu> myZhihu = Spider.GetRecommendations(content, null);   
		
		logger.info(myZhihu);
	}
}
