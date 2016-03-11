package com.teach.activity;

import com.example.teachtemple.R;
import com.teach.constants.HttpConstants;
import com.teach.db.SharedPreferenceDb;
import com.teach.view.*;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import client.util.AppUtils;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;


public class RegisterActivity extends BaseActivity implements OnClickListener,OnCheckedChangeListener {
	private TitleBar titleBar;
	private TextView tvAgreement;
	
	private EditTextWithDel etPhone;
	
	private EditTextWithDel etPwd;
	
	private CheckBox ck;
	
	private Button btnRegister;
	
	
	private boolean isSelect=false;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.register);
		initViews();
		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		TitleBar titleBar=(TitleBar)findViewById(R.id.titleBar);
		 if(!"0".equals(String.valueOf(new SharedPreferenceDb(RegisterActivity.this).getChangeTheme()))){
			 titleBar.setBackgroundColor(new SharedPreferenceDb(RegisterActivity.this).getChangeTheme());
		 }else{
			 titleBar.setBackgroundColor(getResources().getColor(R.color.red));
		 }
		 titleBar.showLeft("ע��", getResources().getDrawable(R.drawable.app_back), new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					finish();
				}
			});
	}
	
	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();
		
		tvAgreement=(TextView)findViewById(R.id.tvAboutAgreement);
		tvAgreement.setOnClickListener(this);
		etPhone=(EditTextWithDel)findViewById(R.id.accounts);
		etPwd=(EditTextWithDel)findViewById(R.id.password);
		ck=(CheckBox)findViewById(R.id.ck);
		btnRegister=(Button)findViewById(R.id.btnRegister);
		btnRegister.setOnClickListener(this);
		ck.setOnCheckedChangeListener(this);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0==tvAgreement){
			SkipActivityforClass(RegisterActivity.this, AgreementActivity.class);
		}else if(arg0==btnRegister){
			
			checkUserInput();
		}
	}

	
	public void checkUserInput(){
		if(!"".equals(etPhone.getText().toString().trim())){
			
			if(AppUtils.isMobileNO(etPhone.getText().toString().trim())==true){
				
				if(!etPwd.getText().toString().trim().isEmpty()){
					
						
						if(isSelect){
							if(AppUtils.checkNetwork(RegisterActivity.this)==true){
								executeHttp();
							}else{
								showToast("�ף�����û��������!");
							}
							
						}else{
							showToast("�ף���鿴���û�Э��Ŷ��");
						}
					
				}else{
					showToast("���벻��Ϊ��");
				}
				
			}else{
				showToast("�ֻ������ʽ����Ŷ");
			}
		}else {
			showToast("�ֻ����벻��Ϊ��");
		}
	}
	
	
	public void executeHttp(){
		
		
		
		AjaxParams parms=new AjaxParams();
		parms.put("userName", etPhone.getText().toString().trim());
		parms.put("password", etPwd.getText().toString().trim());
		
		FinalHttp fh=new FinalHttp();
		initProgressDialog();
		fh.post(HttpConstants.HTTP_REGISTER, parms, new AjaxCallBack<Object>() {
			
			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				Log.d("TAG", strMsg+errorNo);  
				Toast.makeText(RegisterActivity.this, "����������ʧ��...", Toast.LENGTH_LONG).show();
				close();
			}
			
			@Override
			public void onSuccess(Object t) {
				// TODO Auto-generated method stub
				super.onSuccess(t);
				showToast("ע�᷵�ع���������:"+String.valueOf(t));
				close();
			}
		});
	}
	
	public void showToast(String str){
		Toast.makeText(RegisterActivity.this, str, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
		// TODO Auto-generated method stub
		if(isChecked){
			isSelect=true;
		}else{
			isSelect=false;
		}
	}
}
