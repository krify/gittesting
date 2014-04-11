package com.marketplace;


import com.marketplace.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.Button;

public class Splash extends Activity {
	Thread t;
	Button splash_id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
	
		
		
		t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					t.sleep(1000);
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					Intent i = new Intent(Splash.this,Welcome.class);
					startActivity(i);
				}
			}
		});
		t.start();
		
		
	}

}
