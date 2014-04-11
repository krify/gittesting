package com.marketplace;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;

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
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.marketplace.helpers.WelcomeScreenAdapter;
import com.marketplace.services.Services;

public class Welcome extends Activity {

	ImageView login, signup;
	Intent welcome_Intent;
	ListView welcome_listview;

	String WelcomeScreenActivityResult;

	JSONObject jobj, productobj;
	JSONArray productsjary;
	ArrayList<HashMap<String, Object>> productArraylist;

	TextView titlebar;

	String currencyCode;
	Utils s = new Utils();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);

		login = (ImageView) findViewById(R.id.welcome_login_iv);
		signup = (ImageView) findViewById(R.id.welcome_signup);
		welcome_listview = (ListView) findViewById(R.id.welcome_listview);

		// TextView titlebar;
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/ASENST.ttf");
		titlebar = (TextView) findViewById(R.id.welcome_titlebar_tv);
		titlebar.setTypeface(tf);
		titlebar.setTextSize(40);

		productArraylist = new ArrayList<HashMap<String, Object>>();

		new welcomeScreenActivity().execute("");

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				welcome_Intent = new Intent(Welcome.this,
						LoginMarketPlace.class);
				startActivity(welcome_Intent);
			}
		});
		signup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				welcome_Intent = new Intent(Welcome.this, SignUp.class);
				startActivity(welcome_Intent);
			}
		});

		welcome_listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {

				// String name =
				// tickets_listView.getItemAtPosition(position).toString();
				// Toast.makeText(getApplicationContext(), "ticket webview",
				// Toast.LENGTH_SHORT).show();
				// HashMap<String, Object> lotteryHashmap = (HashMap<String,
				// Object>) tickets_array_list.get(position);

				try {

					TextView Product_id = (TextView) v
							.findViewById(R.id.welcome_individual_add_item_id_tv);
					String pid = Product_id.getText().toString();
					// Toast.makeText(getApplicationContext(),
					// "productID is :"+pid, Toast.LENGTH_LONG).show();
					welcome_Intent = new Intent(Welcome.this,
							BrowseAdsIndividualPlacedToday.class);
					welcome_Intent.putExtra("activity", "welcome_screen");
					welcome_Intent.putExtra("Product_id", pid);

					startActivity(welcome_Intent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	public class welcomeScreenActivity extends AsyncTask<String, Void, Void> {
		ProgressDialog pd;

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			WelcomeScreenActivityResult = Services
					.getWelcomeScreenActivityResultDetails();
			Log.i("WelcomeScreenActivityResult",
					"WelcomeScreenActivityResult : "
							+ WelcomeScreenActivityResult);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			try {
				jobj = new JSONObject(WelcomeScreenActivityResult);
				String status = jobj.getString("status");
				productsjary = jobj.getJSONArray("Products");

				for (int i = 0; i < productsjary.length(); i++) {
					HashMap<String, Object> productsMap = new HashMap<String, Object>();
					productobj = productsjary.getJSONObject(i);
					productsMap.put("Product_id", productobj.get("product_id"));
					productsMap.put("Product_title",
							productobj.get("product_title"));
					productsMap.put("Product_location",""+
							productobj.get("product_location"));
					productsMap.put("Product_currency",  productobj.getString("product_currency"));
					String Currency_code = productobj
							.getString("product_currency");

					/*if (Currency_code.length() == 0) {
						productsMap.put("Product_currency", "");
					} else {
						// Currency curr = Currency.getInstance(Currency_code);
						// String curr_symbol = curr.getSymbol();
						// CurrencyExample symbol = new CurrencyExample();

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
						/*
						 * Locale.setDefault(Locale.UK); Currency c =
						 * Currency.getInstance(Currency_code); String
						 * curr_symbol = c.getSymbol();
						 * //System.out.println(c.getSymbol());
						 */

						// String curr_symbol = Currency(Currency_code);

						/*
						 * Currency curr = Currency.getInstance(Currency_code);
						 * String curr_symbol = printCurrency(curr);
						 */

						// Log.i("Currency_code",
						// "Currency symbol is :"+curr_symbol);
						// Log.i("Currency_code", "Currency symbol is :"+curr);

						// curr_symbol = "";

						productsMap.put("Product_price",
								productobj.get("product_price"));
						productsMap.put("Product_image",
								productobj.get("product_image"));
						productsMap.put("Phone", productobj.get("phone"));

						productArraylist.add(productsMap);

						String from[] = { "Product_id", "Product_title",
								"Product_location", "Product_currency",
								"Product_price", "Product_image", "Phone" };
						int to[] = {
								R.id.welcome_individual_add_item_id_tv,
								R.id.welcome_individual_add_item_product_title_tv,
								R.id.welcome_individual_add_location_tv,
								R.id.welcome_individual_add_currency_tv,
								R.id.welcome_individual_add_price_tv,
								R.id.welcome_individual_add_iv,
								R.id.welcome_individual_add_phno_tv

						};

						WelcomeScreenAdapter welcomeAdapter = new WelcomeScreenAdapter(
								Welcome.this, productArraylist,
								R.layout.welcome_individual_add, from, to);
						welcome_listview.setAdapter(welcomeAdapter);

					
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
			pd = new ProgressDialog(Welcome.this);
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.setMessage("loading...");
			pd.show();
		}

	}

	private String printCurrency(Currency c) {

		String curr_symbol = c.getSymbol(Locale.getDefault());
		Log.i("curr_symbol", "curr_symbol is :" + curr_symbol);
		/*
		 * System.out.println(); System.out.println(c.getCurrencyCode());
		 * System.out.println(c.getSymbol());
		 * System.out.println(c.getDefaultFractionDigits());
		 */
		return curr_symbol;
	}

}
