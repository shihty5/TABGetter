package com.shih.tab;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

	public static String getContextFromUrl(String url, Proxy proxy) {

		// 定义一个字符串用来存储网页内容
		String result = "";
		// 定义一个缓冲字符输入流
		BufferedReader in = null;

		try {
			// 将string转成url对象
			URL realUrl = new URL(url);
			// 初始化一个链接到那个url的连接
			URLConnection connection = realUrl.openConnection(proxy);
			// 开始实际的连接
			connection.connect();
			// 初始化 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			// 用来临时存储抓取到的每一行的数据
			String line;
			while ((line = in.readLine()) != null) {
				// 遍历抓取到的每一行并将其存储到result里面
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	
	public static String RegexString(String targetStr, String patternStr){
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(targetStr);
		if(matcher.find()){
			return matcher.group(1);
		}
		return "Nothing";
	}
	
    static ArrayList<String> RegexStringList(String targetStr, String patternStr) {  
        // 预定义一个ArrayList来存储结果  
        ArrayList<String> results = new ArrayList<String>();  
        // 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容  
        Pattern pattern = Pattern.compile(patternStr);  
        // 定义一个matcher用来做匹配  
        Matcher matcher = pattern.matcher(targetStr);  
        // 如果找到了  
        boolean isFind = matcher.find();  
        // 使用循环将句子里所有的kelvin找出并替换再将内容加到sb里  
        while (isFind) {  
            //添加成功匹配的结果  
            results.add(matcher.group(1) + "\n");  
            // 继续查找下一个匹配对象  
            isFind = matcher.find();  
        }  
        return results;  
    }  
}
