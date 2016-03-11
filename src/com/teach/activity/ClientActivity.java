package com.teach.activity;




import com.example.teachtemple.R;
import com.example.teachtemple.R.id;
import com.example.teachtemple.R.layout;
import com.example.teachtemple.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import client.util.GeneratePoint;
import android.widget.AdapterView.OnItemSelectedListener;

public class ClientActivity extends Activity{

	static SeekBar seekBar1,seekBar2,seekBar3;
    static GeneratePoint generatePoint;
    SurfaceView suf;
    Spinner sp;
    String[] string={"·½²¨","ÕýÏÒ²¨"};
    static int griphicStyle=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.client_activity);
		SurfaceView suf=(SurfaceView)findViewById(R.id.SurfaceView01);
		seekBar1=(SeekBar)findViewById(R.id.skBar1);
		seekBar2=(SeekBar)findViewById(R.id.skBar2);
		seekBar3=(SeekBar)findViewById(R.id.skBar3);
		generatePoint=new GeneratePoint(suf);
		generatePoint.start();
		seekBar1.setOnSeekBarChangeListener(new SeekBarClickListener());
		seekBar2.setOnSeekBarChangeListener(new SeekBarClickListener());
		seekBar3.setOnSeekBarChangeListener(new SeekBarClickListener());
		suf.setOnClickListener(new clickListener());
		sp=(Spinner)findViewById(R.id.Spinner01);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,string);
		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				griphicStyle=(int)id;
				ClientActivity.generatePoint.DrawGraphic(ClientActivity.seekBar1, ClientActivity.seekBar2, ClientActivity.seekBar3, ClientActivity.griphicStyle);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		Log.d("OnCreate", "Create");
		
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

 class clickListener implements OnClickListener{

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ClientActivity.generatePoint.DrawGraphic(ClientActivity.seekBar1, ClientActivity.seekBar2, ClientActivity.seekBar3, ClientActivity.griphicStyle);
	}
}
	class SeekBarClickListener implements SeekBar.OnSeekBarChangeListener{

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			new clickListener().onClick(seekBar);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
	
	

}
