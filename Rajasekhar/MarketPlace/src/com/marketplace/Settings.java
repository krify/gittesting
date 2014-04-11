package com.marketplace;


import com.marketplace.beans.LoginDetails;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class Settings extends Activity{
	
	TextView account,changepassword,logout;
	LoginDetails user_logout_status;
	ImageView cancel;
	TextView titlebar;
	
	Intent settings_Intent;
	
	final Context context = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.settings);
		
		//TextView titlebar;
				Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/ASENST.ttf");
				titlebar = (TextView) findViewById(R.id.settings_titlebar_tv);
				titlebar.setTypeface(tf);
				titlebar.setTextSize(40);
		
		user_logout_status = new LoginDetails();
		
		account = (TextView) findViewById(R.id.settings_account_tv);
		changepassword = (TextView) findViewById(R.id.settings_change_password_tv);
		logout = (TextView) findViewById(R.id.settings_logout_tv);
		
		cancel = (ImageView) findViewById(R.id.settings__cancel_iv);
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			finish();
			}
		});
		
		logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				LoginDetails.setLoginstatus("Logout");

				LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.alert_ok_cancel, null);
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						Settings.this);
				
				alertDialogBuilder.setView(promptsView);
				
				TextView fp_status = (TextView)promptsView.findViewById(R.id.alert_ok_cancel_message_tv);
				TextView alertdialog_ok = (TextView)promptsView.findViewById(R.id.alert_ok_cancel_ok_tv);
				TextView alertdialog_cancel = (TextView)promptsView.findViewById(R.id.alert_ok_cancel_cancel_tv);
				// set title
				// alertDialogBuilder.setTitle("Your Title");
				// create alert dialog
				final AlertDialog alertDialog = alertDialogBuilder.create();
				fp_status.setText("Are you sure, you want to logout");
				alertdialog_ok.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						alertDialog.dismiss();
						settings_Intent = new Intent(Settings.this, Welcome.class);
						// Closing all the Activities
						settings_Intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						// Add new Flag to start new Activity

						// Staring Login Activity
						Settings.this.startActivity(settings_Intent);
					}
				});
				alertdialog_cancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						alertDialog.dismiss();
					}
				});
				// show it
				alertDialog.show();
				/*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						Settings.this);
				
					// set title
					//alertDialogBuilder.setTitle("Your Title");
				
					// set dialog message
					alertDialogBuilder
						.setCancelable(false)
						.setMessage("Are you sure, you want to logout")
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity
								dialog.cancel();
								
								settings_Intent = new Intent(Settings.this, Welcome.class);
								// Closing all the Activities
								settings_Intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								// Add new Flag to start new Activity

								// Staring Login Activity
								Settings.this.startActivity(settings_Intent);
								
							}
						  })
						  .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity
								dialog.cancel();
								
							}
						  });
					
		 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();*/

			

			}
		});
		
		changepassword.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				settings_Intent = new Intent(Settings.this, ChangePassword.class);
				startActivity(settings_Intent);
			}
		});
		account.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				settings_Intent = new Intent(Settings.this, AccountUpdate.class);
				startActivity(settings_Intent);
			}
		});
		
		
	}

}
