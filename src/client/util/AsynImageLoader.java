package client.util;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class AsynImageLoader {

	
	//缓存下载过的图片
	private Map<String,SoftReference<Bitmap>> caches;
	
	//任务队列
	private List<Task> taskQueue;
	
	private boolean isRunning=false;
	
	public AsynImageLoader() {
		// TODO Auto-generated constructor stub
		caches=new HashMap<String, SoftReference<Bitmap>>();
		taskQueue=new ArrayList<AsynImageLoader.Task>();
		//启动图片下载线程
		isRunning=true;
		new Thread(r).start();
	}
	
	/**
	 * 
	 * @param imageView �?要延迟就加载的图片对�?
	 * @param url 图片的url
	 * @param resId 图片加载过程中显示的图片
	 */
	public void showImageAsyn(ImageView imageView,String url,int resId){
		
		imageView.setTag(url);
		Bitmap bitmap=loadImageAsyn(url, getImageCallback(imageView, resId));
		
		if(bitmap==null){
			imageView.setImageResource(resId);
		}else{
			imageView.setImageBitmap(bitmap);
		}
	}
	
	
	
	
	public Bitmap loadImageAsyn(String path,ImageCallback callback){
		
		
		if(caches.containsKey(path)){
			
			//取出引用
			SoftReference<Bitmap> rf=caches.get(path);
			
			//通过软缓存取出图�?
			Bitmap bitmap=rf.get();
			
			//如果该图片已经被释放了，则将path移除�?
			if(bitmap==null){
				caches.remove(path);
			}else{
				//如果图片未被释放则直接返回图�?
				return bitmap;
			}
		}else{
			
			//如果缓存中不存在该图片，则创建下载图片任�?
			Task task=new Task();
			task.path=path;
			task.callback=callback;
			if(!taskQueue.contains(task)){
				
				//加入到任务队列中�?
				taskQueue.add(task);
				
				//唤醒任务队列
				synchronized (r) {  
                    r.notify();  
                }  
				
			}
			
		}
		//缓存中没有图片则返回null
		return null;
		
	}
	
	/**
	 * 获取图片的回�?
	 * @param imageView
	 * @param resId
	 * @return
	 */
	public ImageCallback getImageCallback(final ImageView imageView,final int resId){
		
		return new ImageCallback() {
			
			@Override
			public void loadImage(String path, Bitmap bitmap) {
				// TODO Auto-generated method stub
				
				if(path.equals(imageView.getTag().toString())){
					
					imageView.setImageBitmap(bitmap);
				}else{
					imageView.setImageResource(resId);
				}
			}
		};
	}
	
	
	Runnable r=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			while(isRunning){
				
				//当队列中还有未处理的的任务时，执行下载任�?
				while(taskQueue.size()>0){
					
					//获取第一个任务，并将之从任务队列中删�?
					Task task=taskQueue.remove(0);
					//将下载的图片添加到缓存中
					
					task.bitmap=getHttpBitmap(task.path);
					caches.put(task.path, new SoftReference<Bitmap>(task.bitmap));
					
					if(myHandler!=null){
						//创建消息对象，并将完成的任务添加到消息对象中
						Message msg=myHandler.obtainMessage();
						msg.obj=task;
						//发�?�handler 消息
						myHandler.sendMessage(msg);
					}
				}
				
				//如果队列中为空，则令线程等待
				synchronized (this) {  
                    try {  
                        this.wait();  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
				
				
			}
		}
	};
	
	Handler myHandler=new Handler(){
		
		@Override
		public void handleMessage(android.os.Message msg) {
			
			//子线程中返回的下载完成任�?
			Task task=(Task)msg.obj;
			//调用callback对象的loadImage方法，并将图片的路径和图片的回传给adapter
			task.callback.loadImage(task.path, task.bitmap);
		};
	};
	
	 //回调接口  
    public interface ImageCallback{  
        void loadImage(String path, Bitmap bitmap);  
    } 
	
	
	class Task{
		
		//下载任务的路�?
		String path;
		
		//下载的图�?
		Bitmap bitmap;
		
		//回掉对象
		ImageCallback callback;
		
	}
	
	
	
	/**------------------------------------------------
	 * 通过图片的url加载网络图片  
	 * @param url
	 * @return
	 */
    public static Bitmap getHttpBitmap(String url){  
    	
    	
        URL myFileURL;  
        Bitmap bitmap=null;  
        try{  
            myFileURL = new URL(url);  
            //获得连接  
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();  
            //设置超时时间�?6000毫秒，conn.setConnectionTiem(0);表示没有时间限制  
            conn.setConnectTimeout(6000);  
            //连接设置获得数据�?  
            conn.setDoInput(true);  
            //不使用缓�?  
            conn.setUseCaches(false);  
            //这句可有可无，没有影�?  
            //conn.connect();  
            //得到数据�?  
            InputStream is = conn.getInputStream();  
            //解析得到图片  
            bitmap = BitmapFactory.decodeStream(is);  
            //关闭数据�?  
            is.close();  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        return bitmap;  
          
    }  
}
