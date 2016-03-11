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

	// ��������������ı���
		EditText etName, etPass;
		// ���������������ť
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
		 titleBar.showCenterTitle("�û���¼");
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
		// ��ȡ�����������༭��
				etName = (EditText) findViewById(R.id.userEditText);
				etPass = (EditText) findViewById(R.id.pwdEditText);
				// ��ȡ�����е�������ť
				bnLogin = (Button) findViewById(R.id.bnLogin);
				bnLogin.setOnClickListener(new OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						// ִ������У��
						if (validate())  //��
						{
							// �����¼�ɹ�
							if (loginPro())  //��
							{
								// ����Main Activity
								Intent intent = new Intent(LoginActivity.this,EmperimentListActivity.class);
								close();
								startActivity(intent);
								// ������Activity
								finish();
							}
							else
							{
								DialogUtil.showDialog(LoginActivity.this
									, "�û����ƻ�������������������룡", false);
							}
						}
					}
				});
	}

	private boolean loginPro()
	{
		// ��ȡ�û�������û���������
		String userName = etName.getText().toString();
		String pwd = etPass.getText().toString();
		JSONObject jsonObj;
		try
		{
			jsonObj = query(userName, pwd);
			// ���userId ����0
			if (jsonObj.getInt("userId") > 0)
			{
				return true;
			}
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(this
				, "��������Ӧ�쳣�����Ժ����ԣ�", false);
			e.printStackTrace();
		}

		return false;
	}

	// ���û�������û������������У��
	private boolean validate()
	{
		String userName = etName.getText().toString().trim();
		if (userName.equals(""))
		{
			DialogUtil.showDialog(this, "�û��˻��Ǳ����", false);
			return false;
		}
		String pwd = etPass.getText().toString().trim();
		if (pwd.equals(""))
		{
			DialogUtil.showDialog(this, "�û������Ǳ����", false);
			return false;
		}
		return true;
	}

	// ���巢������ķ���
	private JSONObject query(String userName, String password)
		throws Exception
	{
		// ʹ��Map��װ�������
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		map.put("password", password);
		// ���巢�������URL
		String url = HttpConstants.HTTP_LOGIN;
		// ��������
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
