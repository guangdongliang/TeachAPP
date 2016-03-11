package com.teach.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;


import com.example.teachtemple.R;
import com.example.teachtemple.R.id;
import com.example.teachtemple.R.layout;
import com.example.teachtemple.R.menu;
import com.teach.constants.HttpConstants;
import com.teach.db.SharedPreferenceDb;
import com.teach.view.TitleBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import client.util.DialogUtil;
import client.util.HttpUtil;

public class LoginActivity extends BaseActivity {

	// 定义界面中两个文本框
		EditText etName, etPass;
		// 定义界面中两个按钮
		Button bnLogin;
		private RelativeLayout tvRegister;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initViews();
		
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		TitleBar titleBar=(TitleBar)findViewById(R.id.TitleBar);
		 if(!"0".equals(String.valueOf(new SharedPreferenceDb(LoginActivity.this).getChangeTheme()))){
			 titleBar.setBackgroundColor(new SharedPreferenceDb(LoginActivity.this).getChangeTheme());
		 }else{
			 titleBar.setBackgroundColor(getResources().getColor(R.color.red));
		 }
		 titleBar.showCenterTitle("用户登录");
	}

	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();
		tvRegister=(RelativeLayout)findViewById(R.id.newUserRegister);
		tvRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initProgressDialog();
				SkipActivityforClass(LoginActivity.this, RegisterActivity.class);
				if(new SharedPreferenceDb(LoginActivity.this).getAnimation()==true){
					overridePendingTransition(R.anim.fade, R.anim.hold);
				}
			}
		});
		// 获取界面中两个编辑框
				etName = (EditText) findViewById(R.id.userEditText);
				etPass = (EditText) findViewById(R.id.pwdEditText);
				// 获取界面中的两个按钮
				bnLogin = (Button) findViewById(R.id.bnLogin);
				bnLogin.setOnClickListener(new OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						// 执行输入校验
						if (validate())  //①
						{
							// 如果登录成功
							if (loginPro())  //②
							{
								// 启动Main Activity
								Intent intent = new Intent(LoginActivity.this,EmperimentListActivity.class);
								close();
								startActivity(intent);
								// 结束该Activity
								finish();
							}
							else
							{
								DialogUtil.showDialog(LoginActivity.this
									, "用户名称或者密码错误，请重新输入！", false);
							}
						}
					}
				});
	}

	private boolean loginPro()
	{
		// 获取用户输入的用户名、密码
		String userName = etName.getText().toString();
		String pwd = etPass.getText().toString();
		JSONObject jsonObj;
		try
		{
			jsonObj = query(userName, pwd);
			// 如果userId 大于0
			if (jsonObj.getInt("userId") > 0)
			{
				return true;
			}
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(this
				, "服务器响应异常，请稍后再试！", false);
			e.printStackTrace();
		}

		return false;
	}

	// 对用户输入的用户名、密码进行校验
	private boolean validate()
	{
		String userName = etName.getText().toString().trim();
		if (userName.equals(""))
		{
			DialogUtil.showDialog(this, "用户账户是必填项！", false);
			return false;
		}
		String pwd = etPass.getText().toString().trim();
		if (pwd.equals(""))
		{
			DialogUtil.showDialog(this, "用户口令是必填项！", false);
			return false;
		}
		return true;
	}

	// 定义发送请求的方法
	private JSONObject query(String userName, String password)
		throws Exception
	{
		// 使用Map封装请求参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		map.put("password", password);
		// 定义发送请求的URL
		String url = HttpConstants.HTTP_LOGIN;
		// 发送请求
		return new JSONObject(HttpUtil.postRequest(url, map));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
