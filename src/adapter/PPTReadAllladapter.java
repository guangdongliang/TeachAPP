package adapter;

import java.util.List;

import com.example.teachtemple.R;

import adapter.ReadAllAdapter.ViewHolder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import bean.Video;
import client.util.AsynImageLoader;

public class PPTReadAllladapter extends BaseAdapter{
	List<Video> listVideos;
	int local_postion = 0;
	boolean imageChage = false;
	private LayoutInflater mLayoutInflater;
	public PPTReadAllladapter(Context context, List<Video> listVideos){
		mLayoutInflater = LayoutInflater.from(context);
		this.listVideos = listVideos;
	}
	@Override
	public int getCount() {
		return listVideos.size();
	}
	
	@Override
	public Object getItem(int position) {
		return position;
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.video_list_view, null);
			holder.img = (ImageView)convertView.findViewById(R.id.video_img);
			holder.title = (TextView)convertView.findViewById(R.id.video_title);
			holder.time = (TextView)convertView.findViewById(R.id.video_time);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder)convertView.getTag();
		}
			holder.title.setText(listVideos.get(position).getName());//ms
			//long min = listVideos.get(position).getDuration() /1000 / 60;
			//long sec = listVideos.get(position).getDuration() /1000 % 60;
			//holder.time.setText(min+" : "+sec);
			holder.time.setText(listVideos.get(position).getIntro());
			holder.img.setImageResource(R.drawable.welcome);
			
		
		return convertView;
	}

	public final class ViewHolder{
		public ImageView img;
		public TextView title;
		public TextView time;
	}
}
