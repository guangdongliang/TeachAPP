package client.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

public class AppUtils {

	/**
	 * 验证手机格式
	 */
	public static boolean isMobileNO(String mobiles) {
		/*
		 * 移动�?134�?135�?136�?137�?138�?139�?150�?151�?157(TD)�?158�?159�?187�?188
		 * 联�?�：130�?131�?132�?152�?155�?156�?185�?186 电信�?133�?153�?180�?189、（1349卫�?�）
		 * 总结起来就是第一位必定为1，第二位必定�?3�?5�?8，其他位置的可以�?0-9
		 */
		String telRegex = "[1][358]\\d{9}";// "[1]"代表�?1位为数字1�?"[358]"代表第二位可以为3�?5�?8中的�?个，"\\d{9}"代表后面是可以是0�?9的数字，�?9位�??
		if (TextUtils.isEmpty(mobiles))
			return false;
		else
			return mobiles.matches(telRegex);
	}

	/**
	 * 判断网路是否可以�?
	 * @return
	 */
	public static boolean checkNetwork(Context context) {

		if(isNetworkConnected(context)==true){
			if(isWifiConnected(context)==true || isMobileConnected(context)==true){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	/**
	 * 判断是否有网络连�?
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 判断wifi 是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 判断手机网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				return mMobileNetworkInfo.isAvailable();
			}
		}
		return false;
	}
}
