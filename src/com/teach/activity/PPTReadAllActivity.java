package com.teach.activity;

import java.util.List;

import com.example.teachtemple.R;
import com.poi.poiandroid.MainActivity;

import adapter.ReadAllAdapter;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import bean.Video;
import io.vov.vitamio.LibsChecker;


public class PPTReadAllActivity extends BaseActivity{

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
				intent.putExtra("path", listVideos.get(position).getOriurl());
				intent.setClass(PPTReadAllActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
			
	}
}
