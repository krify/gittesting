package com.marketplace;

import org.json.JSONException;
import org.json.JSONObject;

import com.marketplace.beans.LoginDetails;
import com.marketplace.services.Services;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SignUp extends Activity{
	EditText username,email,password,phone;
	ImageView signup,cancel;
	TextView titlebar;
	
	String signup_details,u_uname,u_email,u_password,u_phone;
	
	Intent signup_Intent;
	
	JSONObject jobj;
	String  status,message,userid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.signup);
		
		//TextView titlebar;
				Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/ASENST.ttf");
				titlebar = (TextView) findViewById(R.id.signup_titlebar_tv);
				titlebar.setTypeface(tf);
				titlebar.setTextSize(40);
		
		username = (EditText) findViewById(R.id.signup_uname_et);
		email = (EditText) findViewById(R.id.signup_email_et);
		password = (EditText) findViewById(R.id.signup_password_et);
		phone = (EditText) findViewById(R.id.signup_phone_et);
		
		signup = (ImageView) findViewById(R.id.signup_signup_iv);
		cancel = (ImageView) findViewById(R.id.signup_cancel_iv);
		
		signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new signUp().execute("");
			}
		});
		
	cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
	public class signUp extends AsyncTask<String, Void, Void>{
		ProgressDialog pd;
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			try {
				jobj = new JSONObject(signup_details);
				
				 status = jobj.getString("status");
				 message = jobj.getString("message");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if(status.equals("success")){
				try {
					userid = jobj.getString("userid");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						SignUp.this);
		 
					// set title
					//alertDialogBuilder.setTitle("Your Title");
		 
					// set dialog message
					alertDialogBuilder
						.setMessage(""+message)
						.setCancelable(false)
						.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity
								dialog.cancel();
								LoginDetails.setUser_id(userid);
								signup_Intent = new Intent(SignUp.this,BrowseAdsPlacedtoday.class);
								startActivity(signup_Intent);
								
							}
						  });
					
		 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();
			
			}else{
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					SignUp.this);
	 
				// set title
				//alertDialogBuilder.setTitle("Your Title");
	 
				// set dialog message
				alertDialogBuilder
					.setMessage(""+message)
					.setCancelable(false)
					.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, close
							// current activity
							dialog.cancel();
							
						}
					  });
				
	 
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	 
					// show it
					alertDialog.show();
		}
			
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = new ProgressDialog(SignUp.this);
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.setMessage("Processing...");
			pd.show();
		}

		@Override
		protected Void doInBackground(String... params) {
			u_uname = username.getText().toString();
			u_email = email.getText().toString();
			u_password = password.getText().toString();
			u_phone = phone.getText().toString();
			
			signup_details = Services.getSignUpDetails(u_uname,u_email,u_password,u_phone);
			Log.i("signup_details", "signup_details :"+signup_details);
			return null;
		}
		
	}

}
