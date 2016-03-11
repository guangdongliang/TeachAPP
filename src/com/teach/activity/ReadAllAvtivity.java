package com.teach.activity;

import java.util.List;


import com.example.teachtemple.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.teach.constants.HttpConstants;

import adapter.ReadAllAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import bean.Video;
import client.util.HttpUtil;
import io.vov.vitamio.LibsChecker;

public class ReadAllAvtivity extends BaseActivity{

	ListView videoListView;
	ReadAllAdapter readAllAdapter;
	List<Video> listVideos;
	private TextView first_letter_overlay;
	private ImageView alphabet_scroller; //×ÖÄ¸¹ö¶¯²éÑ¯±í
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.jie_video);
			videoListView=(ListView)findViewById(R.id.jievideolistfile);
			listVideos=(List<Video>) getIntent().getExtras().get("list");
			readAllAdapter=new ReadAllAdapter(this, listVideos);
			videoListView.setAdapter(readAllAdapter);
			videoListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent intent = new Intent();
				intent.setClass(ReadAllAvtivity.this, JieVideoPlayer.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("video", listVideos.get(position));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
			
	}

	
}
