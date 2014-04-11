package com.marketplace;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.marketplace.BrowseAdsPlacedtoday.BrowseAdsActivity;
import com.marketplace.helpers.BrowseAdsCategoryListAdapter;
import com.marketplace.helpers.PlacedTodayAdsAdapter;
import com.marketplace.services.Services;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class BrowseAdsCategory extends Activity{
	
	ImageView placed_today,category;
	ImageView settings;
	
	ListView browse_ads_category_listview;
	
	String BrowseAdscategoryResult;
	
	JSONObject jobj,browse_ads_category_obj;
	JSONArray browse_ads_category_jary;
	ArrayList<HashMap<String, Object>> browse_ads_category_Arraylist;
	
	Intent browse_ads_category_Intent;
	ImageView browse_ads_tab,post_ad_tab,manage_listings_tab;
	TextView titlebar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.browse_ads_category);
		
		
		placed_today = (ImageView) findViewById(R.id.browse_ads_category_placed_today_iv);
		category = (ImageView)findViewById(R.id.browse_ads_category_category_iv);

		browse_ads_tab = (ImageView) findViewById(R.id.botombar_browse_ads_tab_iv);
		post_ad_tab = (ImageView) findViewById(R.id.botombar_post_ads_tab_iv);
		manage_listings_tab = (ImageView) findViewById(R.id.botombar_manage_listing_tab_iv);
		browse_ads_tab.setBackgroundColor(0xff000000);
		
		
		
		settings = (ImageView)findViewById(R.id.browse_ads_category_settings_iv);
		
		//TextView titlebar;
		Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/ASENST.ttf");
		titlebar = (TextView) findViewById(R.id.browse_ads_category_titlebar_tv);
		titlebar.setTypeface(tf);
		titlebar.setTextSize(40);
		
		/*textview=(TextView)findViewById(R.id.textviewone);
		       textview.setTypeface(tf);*/
		
		
		post_ad_tab.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				browse_ads_category_Intent = new Intent(BrowseAdsCategory.this,PostAd.class);
				startActivity(browse_ads_category_Intent);
			}
		});
		manage_listings_tab.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				browse_ads_category_Intent = new Intent(BrowseAdsCategory.this,ManageListingsTab.class);
				startActivity(browse_ads_category_Intent);
			}
		});
		
		settings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				browse_ads_category_Intent = new Intent(BrowseAdsCategory.this,Settings.class);
				startActivity(browse_ads_category_Intent);
			}
		});
		placed_today.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						browse_ads_category_Intent = new Intent(BrowseAdsCategory.this,BrowseAdsPlacedtoday.class);
						startActivity(browse_ads_category_Intent);
						}
				});
		

		category.setEnabled(true);
		
		browse_ads_category_listview = (ListView) findViewById(R.id.browse_ads_category_listview);
		
		browse_ads_category_listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				
				//String name = tickets_listView.getItemAtPosition(position).toString();
				//Toast.makeText(getApplicationContext(), "ticket webview", Toast.LENGTH_SHORT).show(); 
				//  HashMap<String, Object> lotteryHashmap = (HashMap<String, Object>) tickets_array_list.get(position);                   

				
				   try {
					
					   TextView Product_id = (TextView)v.findViewById(R.id.browse_ads_category_item_child_id_tv);
					     String cid = Product_id.getText().toString();
					    // Toast.makeText(getApplicationContext(), "Category_id is :"+cid, Toast.LENGTH_LONG).show();
					     browse_ads_category_Intent = new Intent(BrowseAdsCategory.this,CategoryRelatedItems.class);
					     browse_ads_category_Intent.putExtra("Category_id",cid );

					    startActivity(browse_ads_category_Intent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		browse_ads_category_Arraylist = new ArrayList<HashMap<String,Object>>();
		
		new BrowseAdsCategoryActivity().execute("");
		
	}

	public class BrowseAdsCategoryActivity extends AsyncTask<String, Void, Void>{
		ProgressDialog pd;
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			BrowseAdscategoryResult = Services.getBrowseAdsCategoryResultDetails();
			Log.i("BrowseAdscategoryResult","BrowseAdscategoryResult : "+BrowseAdscategoryResult);
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			try {
				jobj = new JSONObject(BrowseAdscategoryResult);
				String status = jobj.getString("status");
				browse_ads_category_jary = jobj.getJSONArray("Categories");
				
				for(int i=0;i<browse_ads_category_jary.length();i++){
					HashMap<String, Object> productsMap = new HashMap<String, Object>();
					browse_ads_category_obj = browse_ads_category_jary.getJSONObject(i);
					productsMap.put("Cat_id", browse_ads_category_obj.get("cat_id"));
					productsMap.put("Catname", browse_ads_category_obj.get("catname"));
				
					
					
					browse_ads_category_Arraylist.add(productsMap);
					
					String from[] = {"Cat_id","Catname"	};
					int to[] = {
							R.id.browse_ads_category_item_child_id_tv,
							R.id.browse_ads_category_item_child_category_name_tv };
					
					BrowseAdsCategoryListAdapter placedTodayAdapter = new BrowseAdsCategoryListAdapter(BrowseAdsCategory.this, browse_ads_category_Arraylist,
							R.layout.browse_ads_category_item, from, to);
					browse_ads_category_listview.setAdapter(placedTodayAdapter);
					
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
			pd = new ProgressDialog(BrowseAdsCategory.this);
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.setMessage("loading...");
			pd.show();
		}
		
	}


}
