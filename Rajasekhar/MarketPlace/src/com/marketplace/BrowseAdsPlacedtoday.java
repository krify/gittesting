package com.marketplace;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.marketplace.beans.LoginDetails;
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
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class BrowseAdsPlacedtoday extends Activity {

	ImageView placed_today, category;
	ImageView settings;
	TextView titlebar;

	ImageView activity_tab, post_tab, manage_listing_tab;
	ListView browse_ads_placed_today_listview;

	String BrowseAdsPlacedtodayResult, loginDetailsResult;

	JSONObject jobj, browse_ads_obj;
	JSONArray browse_ads_jary;
	ArrayList<HashMap<String, Object>> browse_ads_placed_today_Arraylist;

	JSONObject log_details_jobj;

	Intent browse_ads_intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.browse_ads);

		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/ASENST.ttf");
		titlebar = (TextView) findViewById(R.id.browse_ads_titlebar_tv);
		titlebar.setTypeface(tf);
		titlebar.setTextSize(40);

		placed_today = (ImageView) findViewById(R.id.browse_ads_placed_today_tab_iv);
		category = (ImageView) findViewById(R.id.browse_ads_category_tab_iv);

		// browse_ads_tab = (ImageView)
		// findViewById(R.id.botombar_browse_ads_tab_iv);

		settings = (ImageView) findViewById(R.id.browse_ads_settings_iv);

		settings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				browse_ads_intent = new Intent(BrowseAdsPlacedtoday.this,
						Settings.class);
				startActivity(browse_ads_intent);
			}
		});

		activity_tab = (ImageView) findViewById(R.id.botombar_browse_ads_tab_iv);
		post_tab = (ImageView) findViewById(R.id.botombar_post_ads_tab_iv);
		manage_listing_tab = (ImageView) findViewById(R.id.botombar_manage_listing_tab_iv);
		activity_tab.setBackgroundColor(0xff000000);
		activity_tab.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * browse_ads_intent = new
				 * Intent(BrowseAdsPlacedtoday.this,PostAd.class);
				 * startActivity(browse_ads_intent);
				 */
			}
		});
		post_tab.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				browse_ads_intent = new Intent(BrowseAdsPlacedtoday.this,
						PostAd.class);
				startActivity(browse_ads_intent);
			}
		});
		manage_listing_tab.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				browse_ads_intent = new Intent(BrowseAdsPlacedtoday.this,
						ManageListingsTab.class);
				startActivity(browse_ads_intent);
			}
		});

		// placed_today.setEnabled(true);

		browse_ads_placed_today_listview = (ListView) findViewById(R.id.browse_ads_listview);

		browse_ads_placed_today_Arraylist = new ArrayList<HashMap<String, Object>>();

		new BrowseAdsActivity().execute("");

		category.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				browse_ads_intent = new Intent(BrowseAdsPlacedtoday.this,
						BrowseAdsCategory.class);
				startActivity(browse_ads_intent);
			}
		});

		browse_ads_placed_today_listview
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
									.findViewById(R.id.browse_ads_individual_add_product_id_tv);
							String pid = Product_id.getText().toString();
							// Toast.makeText(getApplicationContext(),
							// "productID is :"+pid, Toast.LENGTH_LONG).show();
							browse_ads_intent = new Intent(
									BrowseAdsPlacedtoday.this,
									BrowseAdsIndividualPlacedToday.class);
							browse_ads_intent.putExtra("Product_id", pid);
							browse_ads_intent.putExtra("activity",
									"placed_today");
							startActivity(browse_ads_intent);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

		new loginDetails().execute("");

	}

	public class BrowseAdsActivity extends AsyncTask<String, Void, Void> {
		ProgressDialog pd;

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			BrowseAdsPlacedtodayResult = Services
					.getBrowseAdsPlacedtodayResultDetails();
			Log.i("BrowseAdsPlacedtodayResult", "BrowseAdsPlacedtodayResult : "
					+ BrowseAdsPlacedtodayResult);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			try {
				jobj = new JSONObject(BrowseAdsPlacedtodayResult);
				String status = jobj.getString("status");
				browse_ads_jary = jobj.getJSONArray("Products");

				for (int i = 0; i < browse_ads_jary.length(); i++) {
					HashMap<String, Object> productsMap = new HashMap<String, Object>();
					browse_ads_obj = browse_ads_jary.getJSONObject(i);
					productsMap.put("Product_id",
							browse_ads_obj.get("product_id"));
					productsMap.put("Product_title",
							browse_ads_obj.get("product_title"));
					productsMap.put("Product_location",
							browse_ads_obj.get("product_location"));

					// productsMap.put("Product_currency",
					// browse_ads_obj.get("product_currency"));
					String Currency_code = browse_ads_obj
							.getString("product_currency");

					/*if (Currency_code.length() == 0) {
						productsMap.put("Product_currency", "");
					} else {

						String curr_symbol = null;
						try {

							curr_symbol = Utils
									.getCurrencySymbol(Currency_code);
							Log.i("Currency_code", "Currency_code :"
									+ Currency_code);
							Log.i("curr_symbol", "curr_symbol :" + curr_symbol);
							productsMap.put("Product_currency", curr_symbol);

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();

							try {
								Currency c = Currency
										.getInstance(Currency_code);
								String curr_symbol1 = c.getSymbol();
								Log.i("Currency_code1", "Currency_code1 :"
										+ Currency_code);
								Log.i("curr_symbol1", "curr_symbol1 :"
										+ curr_symbol1);
								productsMap.put("Product_currency",
										curr_symbol1);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					}
*/
					productsMap.put("Product_currency",
							"" + browse_ads_obj.getString("product_currency"));
					productsMap.put("Product_price",
							browse_ads_obj.get("product_price"));
					productsMap.put("Product_phone",
							browse_ads_obj.get("product_phone"));
					productsMap.put("Product_image",
							browse_ads_obj.get("product_image"));

					browse_ads_placed_today_Arraylist.add(productsMap);

					String from[] = { "Product_id", "Product_title",
							"Product_location", "Product_currency",
							"Product_price", "Product_phone", "Product_image" };
					int to[] = {
							R.id.browse_ads_individual_add_product_id_tv,
							R.id.browse_ads_individual_add_item_product_title_tv,
							R.id.browse_ads_individual_add_product_location_tv,
							R.id.browse_ads_individual_add_product_currency_tv,
							R.id.browse_ads_individual_add_product_price_tv,
							R.id.browse_ads_individual_add_product_phone_tv,
							R.id.browse_ads_individual_add_product_image_iv

					};

					PlacedTodayAdsAdapter placedTodayAdapter = new PlacedTodayAdsAdapter(
							BrowseAdsPlacedtoday.this,
							browse_ads_placed_today_Arraylist,
							R.layout.browse_ads_individual_placed_today, from,
							to);
					browse_ads_placed_today_listview
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
			pd = new ProgressDialog(BrowseAdsPlacedtoday.this);
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.setMessage("loading...");
			pd.show();
		}

	}

	/*
	 * Login Details
	 */
	public class loginDetails extends AsyncTask<String, Void, Void> {
		ProgressDialog pd;

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			loginDetailsResult = Services
					.getloginDetailsResultDetails(LoginDetails.getUser_id());
			Log.i("loginDetailsResult", "loginDetailsResult : "
					+ loginDetailsResult);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			try {
				log_details_jobj = new JSONObject(loginDetailsResult);
				String status, user_id, user_name, user_email, user_password, user_phone;
				String login_details_status = log_details_jobj
						.getString("status");
				if (login_details_status.equals("success")) {

					Log.i("", "");
					status = log_details_jobj.getString("status");
					user_id = log_details_jobj.getString("user_id");
					user_name = log_details_jobj.getString("user_name");
					user_email = log_details_jobj.getString("user_email");
					user_password = log_details_jobj.getString("user_password");
					user_phone = log_details_jobj.getString("user_phone");

					Log.i("status", "status:" + status);
					Log.i("user_id", "user_id :" + user_id);
					Log.i("user_name", "user_name :" + user_name);
					Log.i("user_email", "user_email :" + user_email);
					Log.i("user_password", "user_password :" + user_password);
					Log.i("user_phone", "user_phone :" + user_phone);

					LoginDetails.setStatus(status);
					LoginDetails.setLog_userid(user_id);
					LoginDetails.setUser_name(user_name);
					LoginDetails.setUser_email(user_email);
					LoginDetails.setUser_password(user_password);
					LoginDetails.setUser_phone(user_phone);

				} else {
					LoginDetails.setStatus(" ");
					LoginDetails.setLog_userid(" ");
					LoginDetails.setUser_name(" ");
					LoginDetails.setUser_email(" ");
					LoginDetails.setUser_password(" ");
					LoginDetails.setUser_phone(" ");
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
			pd = new ProgressDialog(BrowseAdsPlacedtoday.this);
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.setMessage("loading...");
			pd.show();
		}

	}

}
