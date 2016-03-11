package com.teach.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.teachtemple.Callbacks;
import com.example.teachtemple.R;
import com.example.teachtemple.R.layout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.teach.constants.HttpConstants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import bean.Video;
import client.util.DialogUtil;
import client.util.HttpUtil;

public class EmperimentListActivity extends BaseActivity implements Callbacks{

	
	List<Video> listVideos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emlist);
	}

	@Override
	public void onItemSelected(Integer id, Bundle bundle) {
		// TODO Auto-generated method stub
		Intent intent=null;
		switch((int)id)
		{
		case 0:
			intent =new Intent(this,ClientActivity.class);
			startActivity(intent);
			break;
		case 1:
			initProgressDialog();
			
			Thread a= new Thread(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					/*try {
						JSONArray j=JSONArray.fromObject(HttpUtil.getRequest(HttpConstants.HTTP_READ_ALL));
						listVideos=(List<Video>)JSONArray.toList(j, Video.class);
						readAllAdapter=new ReadAllAdapter(ReadAllAvtivity.this, listVideos);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create() ; 
					try {
						String str;
						Map<String, String> map = new HashMap<String, String>();
						map.put("islive", "0");
						listVideos= gson.fromJson(str=HttpUtil.postRequest(HttpConstants.HTTP_READ_ALL,map), new TypeToken<List<Video>>(){}.getType());
						Log.d("接收1", "asdfasdgasdf");
						Log.d("接收", str);
						System.out.println("收到"+str);
					} catch (JsonSyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}};
				a.start();
				
				while(a.isAlive()){}
				Bundle bu = new Bundle();
				bu.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) listVideos);
				close();
			intent=new Intent(this,ReadAllAvtivity.class);
			intent.putExtras(bu);
			startActivity(intent);
			break;
		/*case 2:
			intent=new Intent(this,DigitalCircuitActivity.class);
			startActivity(intent);
			break;*/
		case 2:
			initProgressDialog();
			
			Thread a1=new Thread(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					/*try {
						JSONArray j=JSONArray.fromObject(HttpUtil.getRequest(HttpConstants.HTTP_READ_ALL));
						listVideos=(List<Video>)JSONArray.toList(j, Video.class);
						readAllAdapter=new ReadAllAdapter(ReadAllAvtivity.this, listVideos);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create() ; 
					try {
						String str;
						Map<String, String> map = new HashMap<String, String>();
						map.put("islive", "1");
						listVideos= gson.fromJson(str=HttpUtil.postRequest(HttpConstants.HTTP_READ_ALL,map), new TypeToken<List<Video>>(){}.getType());
						Log.d("接收1", "asdfasdgasdf");
						Log.d("接收", str);
						System.out.println("收到"+str);
					} catch (JsonSyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}};
				a1.start();
				while(a1.isAlive()){}
				Bundle bu1 = new Bundle();
				bu1.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) listVideos);
			intent=new Intent(this,PPTReadAllActivity.class);
			intent.putExtras(bu1);
			close();
			startActivity(intent);
			break;
		default:DialogUtil.showDialog(this, "该实验未更新", false);
		}
	}
	
	

}
