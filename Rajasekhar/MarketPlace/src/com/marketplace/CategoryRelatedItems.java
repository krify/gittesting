package com.marketplace;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.marketplace.BrowseAdsPlacedtoday.BrowseAdsActivity;
import com.marketplace.helpers.CategoryRelateItemsAdapter;
import com.marketplace.helpers.PlacedTodayAdsAdapter;
import com.marketplace.services.Services;

public class CategoryRelatedItems extends Activity {

	ImageView placed_today, category;
	ImageView settings;
	TextView titlebar;

	TextView category_name_tv;

	ListView category_related_category_listview;

	String categoryRelatedItemResult;

	JSONObject jobj, category_related_item_obj;
	JSONArray category_related_item_jary;
	ArrayList<HashMap<String, Object>> category_related_item_Arraylist;

	Intent category_related_item_intent;
	ImageView category_related_item_tab;

	String cid_intent_data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.category_related_items);

		Intent intent = getIntent();

		cid_intent_data = intent.getStringExtra("Category_id");
		
		Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/ASENST.ttf");
		titlebar = (TextView) findViewById(R.id.category_related_item_titlebar_tv);
		titlebar.setTypeface(tf);
		titlebar.setTextSize(40);

		placed_today = (ImageView) findViewById(R.id.category_related_item_placed_today_iv);
		category = (ImageView) findViewById(R.id.category_related_item_category_iv);

		category_name_tv = (TextView) findViewById(R.id.category_related_item_category_name_tv);

		category_related_item_tab = (ImageView) findViewById(R.id.botombar_browse_ads_tab_iv);
		category_related_item_tab.setBackgroundColor(0xff000000);

		settings = (ImageView) findViewById(R.id.category_related_item_settings_iv);

		settings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				category_related_item_intent = new Intent(
						CategoryRelatedItems.this, Settings.class);
				startActivity(category_related_item_intent);
			}
		});

		category_related_category_listview = (ListView) findViewById(R.id.category_related_item_listview);

		category_related_item_Arraylist = new ArrayList<HashMap<String, Object>>();

		new CategoryRelatedItemsActivity().execute("");

		category.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				/*
				 * category_related_item_intent = new
				 * Intent(CategoryRelatedItems.this,BrowseAdsCategory.class);
				 * startActivity(category_related_item_intent);
				 */
			}
		});
		placed_today.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				category_related_item_intent = new Intent(
						CategoryRelatedItems.this, BrowseAdsPlacedtoday.class);
				startActivity(category_related_item_intent);
			}
		});

		category_related_category_listview
				.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0, View v,
							int position, long id) {

						// String name =
						// tickets_listView.getItemAtPosition(position).toString();
						// Toast.makeText(getApplicationContext(),
						// "ticket webview", Toast.LENGTH_SHORT).show();
						// HashMap<String, Object> lotteryHashmap =
						// (HashMap<String, Object>)
						// tickets_array_list.get(position);

						try {

							TextView Product_id = (TextView) v
									.findViewById(R.id.category_related_individual_add_product_id_tv);
							String pid = Product_id.getText().toString();
							/*Toast.makeText(getApplicationContext(),
									"productID is :" + pid, Toast.LENGTH_LONG)
									.show();*/
							category_related_item_intent = new Intent(
									CategoryRelatedItems.this,
									BrowseAdsIndividualPlacedToday.class);
							category_related_item_intent.putExtra("Product_id",
									pid);
							category_related_item_intent.putExtra("activity", "category");

							startActivity(category_related_item_intent);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

	}

	public class CategoryRelatedItemsActivity extends AsyncTask<String, Void, Void> {
		ProgressDialog pd;

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			categoryRelatedItemResult = Services
					.getCategoryRelatedItemsResultDetails(cid_intent_data);
			Log.i("categoryRelatedItemResult", "categoryRelatedItemResult : "
					+ categoryRelatedItemResult);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			try {
				jobj = new JSONObject(categoryRelatedItemResult);
				String status = jobj.getString("status");
				String category_name = jobj.getString("category_name");

				category_name_tv.setText(category_name);

				category_related_item_jary = jobj.getJSONArray("posts");
				//for (int i = category_related_item_jary.length()-1; i >=0; i--) 
				for (int i = 0; i < category_related_item_jary.length(); i++) {
					HashMap<String, Object> categoryRelatedItemsMap = new HashMap<String, Object>();
					category_related_item_obj = category_related_item_jary
							.getJSONObject(i);
					categoryRelatedItemsMap.put("product_id2",
							category_related_item_obj.get("product_id"));
					categoryRelatedItemsMap.put("product_title2",
							category_related_item_obj.get("product_title"));
					categoryRelatedItemsMap.put("product_location2",
							category_related_item_obj.get("product_location"));
					
					String Currency_code = category_related_item_obj.getString("product_currency");

					//Currency curr = Currency.getInstance(Currency_code);
					//String curr_symbol = curr.getSymbol();
					
					/*Utils s = new Utils();
					
					String curr_symbol = s.getCurrencySymbol(Currency_code);
					Log.i("Currency_code", "Currency symbol is :"+curr_symbol);
					//Log.i("Currency_code", "Currency symbol is :"+curr);
				 
					categoryRelatedItemsMap.put("product_currency2",""+curr_symbol);*/
					
/*
					String curr_symbol = null;
					try {
						
						curr_symbol = Utils.getCurrencySymbol(Currency_code);
						
						categoryRelatedItemsMap.put("product_currency2", curr_symbol);
					
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
						try {
							Currency c  = Currency.getInstance(Currency_code);
							String curr_symbol1 = c.getSymbol();
							categoryRelatedItemsMap.put("product_currency2", curr_symbol1);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}*/
					categoryRelatedItemsMap.put("product_currency2", category_related_item_obj.getString("product_currency")+"");
					
					
					
					categoryRelatedItemsMap.put("product_price2",
							category_related_item_obj.get("product_price"));

					categoryRelatedItemsMap.put("product_image2",
							category_related_item_obj.get("product_image"));

					categoryRelatedItemsMap.put("product_phone2",
							category_related_item_obj.get("product_phone"));

					category_related_item_Arraylist
							.add(categoryRelatedItemsMap);

					String from[] = { "product_id2", "product_title2",
							"product_location2", "product_currency2",
							"product_price2", "product_image2",
							"product_phone2" };
					int to[] = {
							R.id.category_related_individual_add_product_id_tv,
							R.id.category_related_individual_add_item_product_title_tv,
							R.id.category_related_individual_add_product_location_tv,
							R.id.category_related_individual_add_product_currency_tv,
							R.id.category_related_individual_add_product_price_tv,
							R.id.category_related_individual_add_product_image_iv,
							R.id.category_related_individual_add_product_phone_tv

					};

					CategoryRelateItemsAdapter placedTodayAdapter = new CategoryRelateItemsAdapter(
							CategoryRelatedItems.this,
							category_related_item_Arraylist,
							R.layout.category_related_individual_item, from, to);
					category_related_category_listview
							.setAdapter(placedTodayAdapter);

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
			pd = new ProgressDialog(CategoryRelatedItems.this);
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.setMessage("loading...");
			pd.show();
		}

	}
}
