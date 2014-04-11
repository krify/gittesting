package com.marketplace;

import org.json.JSONException;
import org.json.JSONObject;

import com.marketplace.beans.LoginDetails;
import com.marketplace.services.Services;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginMarketPlace extends Activity{
	
	EditText username,password;
	ImageView login,cancel,forgot_password;
	TextView titlebar;
	
	String l_uname,l_password;
	
	String login_details;
	JSONObject jobj;
	String status,message;
	
	Intent login_Intent;
	final Context context = this;
	String forgot_password_resultdata, fp_status, fp_message;
	JSONObject  fpobj;
	public String email;
	TextView dialog_cancel,dialog_ok;
	EditText dialog_email;
	
	public static String uid = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_marketplace);
		
		
		
		Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/ASENST.ttf");
		titlebar = (TextView) findViewById(R.id.login_titlebar_tv);
		titlebar.setTypeface(tf);
		titlebar.setTextSize(40); 
				
				
		  final Dialog dialog= new Dialog(context);
		  dialog.setContentView(R.layout.forgotpassword_dialog);
		  
		  dialog_email = (EditText)dialog.findViewById(R.id.forgotpassword_et);
		  // dialog_cancel = (TextView) dialog.findViewById(R.id.forgotpassword_cancel_tv);
		  // dialog_ok = (TextView) dialog.findViewById(R.id.forgotpassword_ok_tv);
		  
		  
		username = (EditText) findViewById(R.id.login_uname_et);
		password = (EditText) findViewById(R.id.login_password_et);
		
		login = (ImageView) findViewById(R.id.login_login_iv);
		cancel = (ImageView) findViewById(R.id.login_cancel_iv);
		forgot_password = (ImageView) findViewById(R.id.login_forgotPassword_iv);
		
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new login().execute("");
				
			}
		});
		forgot_password.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						fpclick(v);
						//showPreConfirmationDialog();
			}
		});
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				login_Intent = new Intent(LoginMarketPlace.this,Welcome.class);
				startActivity(login_Intent);
	}
});
	}
	public class login extends AsyncTask<String, Void, Void>{

		ProgressDialog pd;
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			try {
				jobj = new JSONObject(login_details);
				
				 status = jobj.getString("status");
				 message = jobj.getString("message");
				 
				 Log.i("status", "status :"+status);
				 Log.i("message", "message :"+message);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			if(status.equals("success")){
				try {
					uid = jobj.getString("uid");
					LoginDetails.setUser_id(uid);
					login_Intent = new Intent(LoginMarketPlace.this,BrowseAdsPlacedtoday.class);
					startActivity(login_Intent);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			else{
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					LoginMarketPlace.this);
			
				// set title
				//alertDialogBuilder.setTitle("Your Title");
			
				// set dialog message
				alertDialogBuilder
					.setCancelable(false)
					.setMessage(""+message)
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
			pd = new ProgressDialog(LoginMarketPlace.this);
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.setMessage("Processing...");
			pd.show();
		}
		@Override
		protected Void doInBackground(String... params) {

			l_uname = username.getText().toString();
			l_password = password.getText().toString();
			
			login_details = Services.getLoginDetails(l_uname,l_password);
			
			
			return null;
		}
		
	}
	/*
	 * Forgot Password
	 */
	
	public void fpclick(View v) {
		// get prompts.xml view
		LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.forgotpassword_dialog, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
		
		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.forgotpassword_et);
		TextView dialog_cancel = (TextView)promptsView.findViewById(R.id.forgotpassword_cancel_tv);
		TextView dialog_ok = (TextView)promptsView.findViewById(R.id.forgotpassword_ok_tv);
		
		// create alert dialog
	final	AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		// show it
		
     dialog_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialog.dismiss();
			}
		});
		dialog_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialog.dismiss();
				
				email = userInput.getText().toString();
				Log.i("email id", "email is " + email);
				//LoginDetails.setForgotpwd_email(email);

				new forgotpassword().execute("");
			}
		});
		alertDialog.show();
		
		Button bq = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);  
		//bq.setBackgroundColor(R.color.fbdialogbtn);
		//bq.setBackgroundResource(R.drawable.fp_bg);
		Button bqq = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);  
		//bqq.setBackgroundResource(R.drawable.fp_bg);
		//bqq.setBackgroundColor(R.color.fbdialogbtn);

	}
	// start: forgot password
		public class forgotpassword extends AsyncTask<String, Void, Void> {
			ProgressDialog pd;

			@Override
			protected Void doInBackground(String... params) {
				// TODO Auto-generated method stub
				forgot_password_resultdata = Services.getForgotPasswordStatus(email);
				return null;

			}

			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				pd.dismiss();
				try {
					fpobj = new JSONObject(forgot_password_resultdata);
					fp_status = fpobj.getString("status");
					fp_message = fpobj.getString("message");
					if (fp_status.equals("error")) {
						
						LayoutInflater li = LayoutInflater.from(context);
						View promptsView = li.inflate(R.layout.alertdialog_message, null);
						
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
								LoginMarketPlace.this);
						
						alertDialogBuilder.setView(promptsView);
						
						TextView fp_status = (TextView)promptsView.findViewById(R.id.alertdialog_message_tv);
						TextView alertdialog_ok = (TextView)promptsView.findViewById(R.id.alertdialog_message_ok_tv);
						// set title
						// alertDialogBuilder.setTitle("Your Title");
						// create alert dialog
						final AlertDialog alertDialog = alertDialogBuilder.create();
						fp_status.setText(fp_message);
						alertdialog_ok.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								alertDialog.dismiss();
							}
						});
						// show it
						alertDialog.show();
					} else {
						
						LayoutInflater li = LayoutInflater.from(context);
						View promptsView = li.inflate(R.layout.alertdialog_message, null);
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
								LoginMarketPlace.this);
						
						alertDialogBuilder.setView(promptsView);
						
						TextView fp_status = (TextView)promptsView.findViewById(R.id.alertdialog_message_tv);
						TextView alertdialog_ok = (TextView)promptsView.findViewById(R.id.alertdialog_message_ok_tv);
						// set title
						// alertDialogBuilder.setTitle("Your Title");

						// set dialog message
						/*alertDialogBuilder
								.setMessage(
										"Your password has sent to your email,please check it & login again.")
								.setCancelable(false)
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog, int id) {
												// if this button is clicked, close
												// current activity
												dialog.cancel();

											}
										});*/

						// create alert dialog
						final AlertDialog alertDialog = alertDialogBuilder.create();
						fp_status.setText("Your password has sent to your email, please check it & login again.");
						alertdialog_ok.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								alertDialog.dismiss();
							}
						});
						// show it
						alertDialog.show();

					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				pd = new ProgressDialog(LoginMarketPlace.this);
				pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				pd.setMessage("loading...");
				pd.show();
			}

		}

		// end: forgotpassword
		
	/*	private void showPreConfirmationDialog() {
			final Dialog dialog= new Dialog(context);
			dialog.setContentView(R.layout.forgotpassword_dialog);  
			//final Dialog dialog= new Dialog(context,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
			 // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			//dialog.setTitle("Loading...");
			dialog.show(); 
			 dialog_email = (EditText)dialog.findViewById(R.id.forgotpassword_et);
			 dialog_cancel = (TextView) dialog.findViewById(R.id.forgotpassword_cancel_tv);
			   dialog_ok = (TextView) dialog.findViewById(R.id.forgotpassword_ok_tv);
			  dialog_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.cancel();
					
				}
			});
			  dialog_ok.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.cancel();
						email = dialog_email.getText().toString();
						Log.i("email id", "email is " + email);
						//LoginDetails.setForgotpwd_email(email);

						new forgotpassword().execute("");
					}
				});
			
			
			
			}
*/
}
