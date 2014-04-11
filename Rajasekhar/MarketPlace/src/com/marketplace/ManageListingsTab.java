package com.marketplace;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.marketplace.BrowseAdsPlacedtoday.BrowseAdsActivity;
import com.marketplace.beans.EditPostDetails;
import com.marketplace.helpers.ManagedListingsAdsAdapter;
import com.marketplace.helpers.PlacedTodayAdsAdapter;
import com.marketplace.services.Services;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ManageListingsTab extends Activity{
	
	ImageView settings;
	TextView titlebar,post_ads_status;
	
	ImageView activity_tab,post_tab,manage_listing_tab;
	ListView manage_listing_listview;
	
	Intent manage_listings_intent;
	
String manage_listings_Result,delete_post_Result,edit_product_id,edit_post_Result;
String delete_product_id = null,delete_postmessage;
	
	JSONObject jobj,manage_listings_obj,djobj,ejobj;
	JSONArray manage_listings_jary;
	ArrayList<HashMap<String, Object>> manage_listings_Arraylist;
	
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.managelistingstab);
		

		Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/ASENST.ttf");
		titlebar = (TextView) findViewById(R.id.manage_listings_titlebar_tv);
		titlebar.setTypeface(tf);
		titlebar.setTextSize(40);
		
		post_ads_status = (TextView)findViewById(R.id.manage_listing_post_ads_status_tv);
		settings = (ImageView)findViewById(R.id.manage_listings_settings_iv);
		
		settings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				manage_listings_intent = new Intent(ManageListingsTab.this,Settings.class);
				startActivity(manage_listings_intent);
			}
		});
		
		activity_tab = (ImageView) findViewById(R.id.botombar_browse_ads_tab_iv);
		post_tab = (ImageView) findViewById(R.id.botombar_post_ads_tab_iv);
		manage_listing_tab = (ImageView) findViewById(R.id.botombar_manage_listing_tab_iv);
		manage_listing_tab.setBackgroundColor(0xff000000);
		activity_tab.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				manage_listings_intent = new Intent(ManageListingsTab.this,BrowseAdsPlacedtoday.class);
				startActivity(manage_listings_intent);
			}
		});
		post_tab.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						manage_listings_intent = new Intent(ManageListingsTab.this,PostAd.class);
						startActivity(manage_listings_intent);
					}
				});
		manage_listing_tab.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*manage_listings_intent = new Intent(BrowseAdsPlacedtoday.this,PostAd.class);
				startActivity(manage_listings_intent);*/
			}
		});
		
		//placed_today.setEnabled(true);
		
		manage_listing_listview = (ListView) findViewById(R.id.manage_listings_listview);
		manage_listings_Arraylist = new ArrayList<HashMap<String,Object>>();
		
		new BrowseAdsActivity().execute("");
		
		/*manage_listing_listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				
				//String name = tickets_listView.getItemAtPosition(position).toString();
				//Toast.makeText(getApplicationContext(), "ticket webview", Toast.LENGTH_SHORT).show(); 
				//HashMap<String, Object> lotteryHashmap = (HashMap<String, Object>) tickets_array_list.get(position);                   

				
				   try {
					
					   TextView Product_id = (TextView)v.findViewById(R.id.manage_listings_individual_add_product_id_tv);
					     String pid = Product_id.getText().toString();
					     //Toast.makeText(getApplicationContext(), "productID is :"+pid, Toast.LENGTH_LONG).show();
					   manage_listings_intent = new Intent(ManageListingsTab.this,BrowseAdsIndividualPlacedToday.class);
					     manage_listings_intent.putExtra("Product_id",pid );
					     manage_listings_intent.putExtra("activity", "manage_listing");
					    startActivity(manage_listings_intent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});*/
	}
	public class BrowseAdsActivity extends AsyncTask<String, Void, Void>{
		ProgressDialog pd;
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			manage_listings_Result = Services.getManageListingsResultDetails();
			Log.i("manage_listings_Result","manage_listings_Result : "+manage_listings_Result);
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			try {
				jobj = new JSONObject(manage_listings_Result);
				String status = jobj.getString("status");
				String ml_posts_message = jobj.getString("message");
				
				if(status.equals("success")){
				manage_listings_jary = jobj.getJSONArray("posts");
				
				for(int i=0;i<manage_listings_jary.length();i++){
					HashMap<String, Object> productsMap = new HashMap<String, Object>();
					manage_listings_obj = manage_listings_jary.getJSONObject(i);
					productsMap.put("Product_id", manage_listings_obj.get("product_id"));
					productsMap.put("Product_title", manage_listings_obj.get("product_title"));
					//productsMap.put("Product_description", manage_listings_obj.get("product_description"));
					
					productsMap.put("Product_location", manage_listings_obj.get("product_location"));
					productsMap.put("Product_currency", manage_listings_obj.get("product_currency").toString());
					
					/*
					String curr_symbol = null;
					try {
						
						curr_symbol = Utils.getCurrencySymbol(manage_listings_obj.get("product_currency").toString());
						Log.i("Currency_code", "Currency_code :"+manage_listings_obj.get("product_currency").toString());
						Log.i("curr_symbol", "curr_symbol :"+curr_symbol);
						productsMap.put("Product_currency", curr_symbol);
					
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
						try {
							Currency c  = Currency.getInstance(manage_listings_obj.get("product_currency").toString());
							String curr_symbol1 = c.getSymbol();
							Log.i("Currency_code1", "Currency_code1 :"+manage_listings_obj.get("product_currency").toString());
							Log.i("curr_symbol1", "curr_symbol1 :"+curr_symbol1);
							productsMap.put("Product_currency", curr_symbol1);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					
					}*/
					
					
					
					
					productsMap.put("Product_price", manage_listings_obj.get("product_price"));
					//productsMap.put("Product_date", manage_listings_obj.get("product_date"));
					productsMap.put("Product_phone", manage_listings_obj.get("product_phone"));
					productsMap.put("Product_image11", manage_listings_obj.get("product_image1"));
					//productsMap.put("Product_image2", manage_listings_obj.get("product_image2"));
					//productsMap.put("Product_image3", manage_listings_obj.get("product_image3"));
					
					manage_listings_Arraylist.add(productsMap);
					
					String from[] = {"Product_id","Product_title","Product_location",
							"Product_currency","Product_price","Product_phone","Product_image1"};
					int to[] = {R.id.manage_listings_individual_add_product_id_tv,
							R.id.manage_listings_individual_add_item_product_title_tv,
							R.id.manage_listings_individual_add_product_location_tv,
							R.id.manage_listings_individual_add_product_location_tv,
							R.id.manage_listings_individual_add_product_currency_tv,
							R.id.manage_listings_individual_add_product_price_tv,
							R.id.manage_listings_individual_add_product_phone_tv,
							R.id.manage_listings_individual_add_product_image_iv
							
					};
					
					ManagedListingsAdsAdapter placedTodayAdapter = new ManagedListingsAdsAdapter(ManageListingsTab.this, manage_listings_Arraylist,
							R.layout.manage_listings_individual_item, from, to);
					manage_listing_listview.setAdapter(placedTodayAdapter);
					
				}
				}else{
					post_ads_status.setVisibility(View.VISIBLE);
					post_ads_status.setText(ml_posts_message);
				}
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
			pd = new ProgressDialog(ManageListingsTab.this);
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.setMessage("loading...");
			pd.show();
		}
		
	}
	
	public void deletePost(String del_pid){
	String dpid=del_pid;
	EditPostDetails.setDelete_pid(dpid);
		//Toast.makeText(ManageListingsTab.this, ""+delete_product_id, Toast.LENGTH_SHORT).show();
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ManageListingsTab.this);
 
			// set title
			//alertDialogBuilder.setTitle("Your Title");
 
			// set dialog message
			alertDialogBuilder
				.setMessage("Are you sure to delete the post")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
						dialog.cancel();
						//Toast.makeText(getApplicationContext(), "delete_product_id is "+delete_product_id, Toast.LENGTH_LONG).show();
						new delete_post().execute("");
					}
				  })
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
		
	} 
	public class delete_post extends AsyncTask<String, Void, Void>{
		ProgressDialog pd1;
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			Log.i("del product id ","del product id is :"+EditPostDetails.getDelete_pid());
			delete_post_Result = Services.getDeletePostResultDetails(EditPostDetails.getDelete_pid());
			Log.i("delete_post_Result","delete_post_Result : "+delete_post_Result);
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd1.dismiss();
			try {
				djobj = new JSONObject(manage_listings_Result);
				String delete_post_status = djobj.getString("status");
				 delete_postmessage = djobj.getString("message");
				manage_listings_jary = djobj.getJSONArray("posts");
				
				if(delete_post_status.equals("success")){
					manage_listings_intent = new Intent(getApplicationContext(),ManageListingsTab.class);
					startActivity(manage_listings_intent);
					
				}else{
					
				}
					
				
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
			pd1 = new ProgressDialog(ManageListingsTab.this);
			pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd1.setMessage("processing...");
			pd1.show();
		}

}
	public void editPost(String del_product_id){
		this.edit_product_id=del_product_id;
	//	Toast.makeText(getApplicationContext(), "edit_product_id is "+edit_product_id, Toast.LENGTH_LONG).show();
		new editPostDetail().execute("");
	} 
	public class editPostDetail extends AsyncTask<String, Void, Void>{
		ProgressDialog pd1;
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			/*edit_post_Result = Services.getEditPostResultDetails(edit_product_id);
			Log.i("edit_post_Result","edit_post_Result : "+edit_post_Result);
			*/
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd1.dismiss();
			try {
				/*ejobj = new JSONObject(edit_post_Result);
				String edit_post_status = ejobj.getString("status");
				 delete_postmessage = ejobj.getString("message");
				manage_listings_jary = ejobj.getJSONArray("posts");
				
				if(edit_post_status.equals("success")){*/
					manage_listings_intent = new Intent(ManageListingsTab.this,EditPost.class);
					manage_listings_intent.putExtra("edit", ""+edit_product_id);
					startActivity(manage_listings_intent);
					
				/*}else{
					
				}
					*/
				
			
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd1 = new ProgressDialog(ManageListingsTab.this);
			pd1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd1.setMessage("loading...");
			pd1.show();
		}

}
}
