package com.teach.activity;


import com.example.teachtemple.R;
import com.teach.constants.HttpConstants;

import android.os.Bundle;
import android.webkit.WebView;

public class AgreementActivity extends BaseActivity {

	private WebView webView;
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		
		
		setContentView(R.layout.agreement);
		webView=(WebView)findViewById(R.id.myWebView);
		
		webView.loadUrl(HttpConstants.HTTP_AGREEMENT);
		 
	}
	
	
}
