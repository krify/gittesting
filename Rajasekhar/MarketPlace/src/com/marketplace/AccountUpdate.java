package com.marketplace;

import org.json.JSONException;
import org.json.JSONObject;

import com.marketplace.services.Services;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AccountUpdate extends Activity{
	
	EditText username,phonenumber;
	TextView update;
	ImageView cancel;
	String uname,phno;
	
	String updateResults;
	JSONObject jobj;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.account_update);
		
		username = (EditText) findViewById(R.id.account_update_uname_et);
		phonenumber = (EditText) findViewById(R.id.account_update_phone_number_et);
		
		cancel = (ImageView)findViewById(R.id.account_update_cancel_iv);
		update = (TextView)findViewById(R.id.account_update_update_tv);
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 uname = username.getText().toString();
				 phno = phonenumber.getText().toString();
				new updateUser().execute("");
			}
		});
		
		
		
		
	}
	public class updateUser extends AsyncTask<String, Void, Void>{
		ProgressDialog pd;
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			updateResults = Services.getUpdateAccountResults(uname,phno);
			Log.i("updateResults", "updateResults"+updateResults);
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			try {
				jobj = new JSONObject(updateResults);
				
				String status = jobj.getString("status");
				String message = jobj.getString("message");
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						AccountUpdate.this);
		 
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
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd =new ProgressDialog(AccountUpdate.this);
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.setMessage("updating...");
			pd.show();
		}
		
	}


}
