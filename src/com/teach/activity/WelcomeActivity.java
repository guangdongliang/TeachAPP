package com.teach.activity;
import android.os.Bundle;
import android.os.Handler;

import com.example.teachtemple.R;
import com.teach.db.SharedPreferenceDb;

public class WelcomeActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_welcome);
		
		if(new SharedPreferenceDb(this).getIsFirstInstall()==false){
			new SharedPreferenceDb(this).setIsFirstInstall();
			SkipActivityforClass(this, ClientActivity.class);
			finish();
			if(new SharedPreferenceDb(WelcomeActivity.this).getAnimation()==true){
				overridePendingTransition(R.anim.scale_translate_rotate,
						R.anim.my_alpha_action);
			}
			
		}else{
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					SkipActivityforClass(WelcomeActivity.this
							, LoginActivity.class);
					finish();
					if(new SharedPreferenceDb(WelcomeActivity.this).getAnimation()==true){
						overridePendingTransition(R.anim.push_left_in,
								R.anim.push_left_out);
					}
					
				}
			}, 1000);
		}
		
		
	}
	
	
}
