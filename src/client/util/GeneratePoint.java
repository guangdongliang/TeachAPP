package client.util;



import android.R.color;
import android.R.integer;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Surface;
import android.view.SurfaceView;
import android.widget.SeekBar;

public class GeneratePoint extends Thread {
	
	SurfaceView suf;
	
	public GeneratePoint(SurfaceView suf){
		
		this.suf=suf;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	
			Canvas canvas=suf.getHolder().lockCanvas();
			Paint mPaint = new Paint();    
	        mPaint.setColor(Color.RED);// ª≠± Œ™¬Ã…´    
	        mPaint.setStrokeWidth(2);// …Ë÷√ª≠± ¥÷œ∏  
			for (int i=0;i<11;i++){
				canvas.drawLine(suf.getWidth()/10*i, 0, suf.getWidth()/10*i, suf.getHeight(), mPaint);
				canvas.drawLine(0, suf.getHeight()/10*i,suf.getWidth(), suf.getHeight()/10*i, mPaint);
			}
			suf.getHolder().unlockCanvasAndPost(canvas);

		
		
	}

	public void DrawGraphic(SeekBar skBar1,SeekBar skBar2,SeekBar skBar3,int griphicStyle){
		Canvas canvas=suf.getHolder().lockCanvas();
		canvas.drawColor(Color.BLACK);
		Paint mPaint = new Paint();    
        mPaint.setColor(Color.RED);// ª≠± Œ™¬Ã…´    
        mPaint.setStrokeWidth(2);// …Ë÷√ª≠± ¥÷œ∏  
		for (int i=0;i<11;i++){
			canvas.drawLine(suf.getWidth()/10*i, 0, suf.getWidth()/10*i, suf.getHeight(), mPaint);
			canvas.drawLine(0, suf.getHeight()/10*i,suf.getWidth(), suf.getHeight()/10*i, mPaint);
		}
		int [] y;
		int space=suf.getWidth()/10;
		y=new int[suf.getWidth()];
		if(griphicStyle==1){
			for(int i=0;i<suf.getWidth();i++){
				y[i]=(int)(Math.sin(2*Math.PI*skBar1.getProgress()*i*skBar3.getProgress()/(200*space))*skBar2.getProgress()*5)+suf.getHeight()/2;//∆µ¬ 0-100£¨∑˘÷µ0-500£¨∑÷±Ê¬ 0-1.
			}
		}
		else{
			for(int i=0;i<suf.getWidth();i++){
				if((i/(int)(0.1*suf.getWidth()*(0.5/skBar1.getProgress()/skBar3.getProgress()*100)))%2==0)
				{
					y[i]=skBar2.getProgress()*5+suf.getHeight()/2;
				}
				else {
					y[i]=suf.getHeight()/2-skBar2.getProgress()*5;
				}
			}
		}
		mPaint.setColor(Color.GREEN);
		for(int i=1;i<y.length;i++){
			canvas.drawLine(i-1, y[i-1], i, y[i], mPaint);
		}
		suf.getHolder().unlockCanvasAndPost(canvas);
	}

}
