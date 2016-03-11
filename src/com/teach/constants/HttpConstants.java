package com.teach.constants;

public class HttpConstants {

	
	public final static String HTTP_HEAD="http://";
	
	//��������
	public static final String HTTP_IP ="115.156.144.90:8080";
	//��������
	//public static final String HTTP_IP ="192.168.191.1:8080";
	
	public final static String HTTP_CONTEXT="/simplest_video_website";
	
	public final static String HTTP_REQUEST=HTTP_HEAD+HTTP_IP+HTTP_CONTEXT;
	
	/**
	 * �û�Э��
	 */
	public static String HTTP_AGREEMENT=HTTP_REQUEST+"/agreement.jsp";
	
	/**
	 * �û�ע��
	 */
	public static String HTTP_REGISTER=HTTP_REQUEST+"/android/register.jsp";
	
	/**
	 * �û���¼
	 */
	public static String HTTP_LOGIN=HTTP_REQUEST+"/android/login.jsp";
	
	/**
	 * ���������Ƶ
	 */
	public static String HTTP_READ_ALL="http://115.156.144.90:8080/simplest_video_website/android/ReadAll.jsp";
}
