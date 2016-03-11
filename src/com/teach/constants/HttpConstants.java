package com.teach.constants;

public class HttpConstants {

	
	public final static String HTTP_HEAD="http://";
	
	//广域网中
	public static final String HTTP_IP ="115.156.144.90:8080";
	//局域网中
	//public static final String HTTP_IP ="192.168.191.1:8080";
	
	public final static String HTTP_CONTEXT="/simplest_video_website";
	
	public final static String HTTP_REQUEST=HTTP_HEAD+HTTP_IP+HTTP_CONTEXT;
	
	/**
	 * 用户协议
	 */
	public static String HTTP_AGREEMENT=HTTP_REQUEST+"/agreement.jsp";
	
	/**
	 * 用户注册
	 */
	public static String HTTP_REGISTER=HTTP_REQUEST+"/android/register.jsp";
	
	/**
	 * 用户登录
	 */
	public static String HTTP_LOGIN=HTTP_REQUEST+"/android/login.jsp";
	
	/**
	 * 获得所有视频
	 */
	public static String HTTP_READ_ALL="http://115.156.144.90:8080/simplest_video_website/android/ReadAll.jsp";
}
