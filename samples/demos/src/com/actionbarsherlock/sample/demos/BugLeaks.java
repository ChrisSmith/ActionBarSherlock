package com.actionbarsherlock.sample.demos;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockActivity;

public class BugLeaks extends SherlockActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.bug_leaks);
    	
    	//create a large bitmap so we can see the View hierarchy leak
    	Bitmap b = Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_8888);
    	Canvas c = new Canvas(b);
    	Paint paint = new Paint();
    	
    	//randomly paint our bitmap for fun
    	Random r = new Random();
    	paint.setARGB(255, r.nextBoolean() ? 255 : 0,r.nextBoolean() ? 255 : 0, r.nextBoolean() ? 255 : 0);
    	c.drawPaint(paint);
    	ImageView v = (ImageView) findViewById(R.id.imageView1);
    	v.setImageBitmap(b);
    }
    
    
    //Now just rotate the device a few times and watch the heap grow
    //after a few times you'll get an out of memory exception
    
    //Use this to observe it from the terminal
    //adb shell dumpsys meminfo com.actionbarsherlock.sample.demos
    
}
