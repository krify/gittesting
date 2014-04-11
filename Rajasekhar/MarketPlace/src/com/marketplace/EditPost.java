package com.marketplace;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.marketplace.beans.EditPostDetails;
import com.marketplace.beans.ProductCurrencyDetails;
import com.marketplace.helpers.AlertDialogManager;
import com.marketplace.helpers.ConnectionDetector;
import com.marketplace.services.Services;
import com.marketplace.views.HorizontalPager;

public class EditPost extends Activity{
TextView title,category,post,postby,sendmail,mobile,location;
	EditText description,price;
	ScrollView sv;
	
String BrowseAdsIndividualResult,edit_product_id;
	
ListView browse_ads_individual_listview;
	JSONObject jobj,browse_ads_individual_obj,epjobj,epbrowse_ads_obj;
	JSONArray browse_ads_individual_jary,ep_browse_ads_jary;
	ArrayList<HashMap<String, Object>> browse_ads_individual_Arraylist,epbrowse_ads_individual_Arraylist;
	
	Intent browse_ads_individual_intent;
	
	String productID = null;
	String activtystatus = null;
	
	String status1,product_id1,product_title1,product_description1,product_location1;
	
	String product_currency1,product_price1,product_date1,product_email1,product_phone1,product_category1;
	
	String p_username,p_user_phone;
	String pimage1,pimage2,pimage3;
	
	String sendtoemailid = null;
	
	ArrayList<Bitmap> imageslist;
	
	HorizontalPager image_slider;
	
	ImageView dot1,dot2,dot3;
	
	ImageView save;
	
	String edit_pid,editPostResult;
	protected ConnectionDetector cd;
	protected AlertDialogManager alert = new AlertDialogManager();
	
	TextView titlebar;
	ImageView back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.edit_post);
		
		Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/ASENST.ttf");
		titlebar = (TextView) findViewById(R.id.edit_post_pdetails_titlebar_tv);
		titlebar.setTypeface(tf);
		titlebar.setTextSize(40);
		
		back = (ImageView)findViewById(R.id.edit_post_pdetails_back_iv);
		title = (TextView) findViewById(R.id.edit_post_pdetails_title_status_tv);
		description = (EditText) findViewById(R.id.edit_post_pdetails_description_status_et);
		category = (TextView) findViewById(R.id.edit_post_pdetails_category_status_tv);
		price = (EditText) findViewById(R.id.edit_post_pdetails_price_status_et);
		post = (TextView) findViewById(R.id.edit_post_pdetails_post_status_tv);
		postby = (TextView) findViewById(R.id.edit_post_pdetails_postby_status_tv);
		sendmail = (TextView) findViewById(R.id.edit_post_pdetails_sendemail_status_tv);
		mobile = (TextView) findViewById(R.id.edit_post_pdetails_mobile_status_tv);
		location = (TextView) findViewById(R.id.edit_post_pdetails_locations_status_tv);
		
		dot1 = (ImageView) findViewById(R.id.edit_post_pdetails_slider_dot1_iv);
		dot2 = (ImageView) findViewById(R.id.edit_post_pdetails_slider_dot2_iv);
		dot3 = (ImageView) findViewById(R.id.edit_post_pdetails_slider_dot3_iv);
		
		save = (ImageView) findViewById(R.id.edit_post_ads_save_iv);
		image_slider = (HorizontalPager) findViewById(R.id.edit_post_horizontal_pager1);
		imageslist = new ArrayList<Bitmap>();
		
		epbrowse_ads_individual_Arraylist = new ArrayList<HashMap<String,Object>>();
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				EditPostDetails.setDescription(description.getText().toString().replace(" ", "%20"));
				EditPostDetails.setPrice(price.getText().toString());
				new saveEditPost().execute("");
			}
		});
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		Bundle extras = getIntent().getExtras(); 

		if (extras != null) {
			activtystatus = extras.getString("edit");
			Log.i("activtystatus", "activtystatus is :"+activtystatus);
			//Toast.makeText(getApplicationContext(), "activtystatus is :"+activtystatus, Toast.LENGTH_SHORT).show();
			edit_pid = activtystatus;
		}
		new BrowseAdsIndividualDetail().execute("");
	}
	public class BrowseAdsIndividualDetail extends AsyncTask<String, Void, Void>{
		ProgressDialog pd;
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			BrowseAdsIndividualResult = Services.getBrowseAdsIndividualResultDetails(edit_pid);
			Log.i("BrowseAdsIndividualResult","BrowseAdsIndividualResult : "+BrowseAdsIndividualResult);
			try {
				jobj = new JSONObject(BrowseAdsIndividualResult);
				 status1 = jobj.getString("status");
				 product_id1 = jobj.getString("product_id");
				 product_title1 = jobj.getString("product_title");
				 product_description1 = jobj.getString("product_description");
				 product_location1 = jobj.getString("product_location");
				// product_currency1 = jobj.getString("product_currency");
				 String Currency_code =  jobj.getString("product_currency");

					//Currency curr = Currency.getInstance(Currency_code);
					//String curr_symbol = curr.getSymbol();
					
					/*Utils s = new Utils();
					
					String curr_symbol = s.getCurrencySymbol(Currency_code);
					product_currency1 = curr_symbol;
					Log.i("Currency_code", "Currency symbol is :"+curr_symbol);
					//Log.i("Currency_code", "Currency symbol is :"+curr);
*/				 
				 
				 String curr_symbol = null;
					try {
						
						curr_symbol = Utils.getCurrencySymbol(Currency_code);
						Log.i("Currency_code", "Currency_code :"+Currency_code);
						Log.i("curr_symbol", "curr_symbol :"+curr_symbol);
						
						ProductCurrencyDetails.setCurrency_symbol(curr_symbol);
						//productsMap.put("Product_currency", curr_symbol);
					
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
						try {
							Currency c  = Currency.getInstance(Currency_code);
							String curr_symbol1 = c.getSymbol();
							Log.i("Currency_code1", "Currency_code1 :"+Currency_code);
							Log.i("curr_symbol1", "curr_symbol1 :"+curr_symbol1);
							ProductCurrencyDetails.setCurrency_symbol(curr_symbol1);
							//productsMap.put("Product_currency", curr_symbol1);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					}
				 product_price1 = jobj.getString("product_price");
				 product_date1 = jobj.getString("product_date");
				
				
				 product_email1 = jobj.getString("product_email");
				 
				 sendtoemailid = product_email1;
				 product_phone1 = jobj.getString("product_phone");
				 product_category1 = jobj.getString("product_category");
				 
				 pimage1 = jobj.getString("product_image1");
				 pimage2 = jobj.getString("product_image2");
				 pimage3 = jobj.getString("product_image3");
				 
				 p_username = jobj.getString("product_username");
				 p_user_phone = jobj.getString("product_phone");
				 
				 if(pimage1.length() == 0 && pimage2.length() == 0 && pimage3.length() == 0 ){
					 
				 }
				 if(pimage1.length() != 0 && pimage2.length() == 0 && pimage3.length() == 0 ){
					 
					imageslist.add(Services.getBitmapFromURLs(pimage1));
					
				 }
				 
				 if(pimage1.length() != 0 && pimage2.length() != 0 && pimage3.length() == 0 ){
					 
					 imageslist.add(Services.getBitmapFromURLs(pimage1));
					 imageslist.add(Services.getBitmapFromURLs(pimage2));
					 
					
				 }
				 if(pimage1.length() != 0 && pimage2.length() != 0 && pimage3.length() != 0 ){
					 
					 imageslist.add(Services.getBitmapFromURLs(pimage1));
					 imageslist.add(Services.getBitmapFromURLs(pimage2));
					 imageslist.add(Services.getBitmapFromURLs(pimage3));
					 
				 }
				 
				 /*
				 imageslist.add(Services.getBitmapFromURLs(pimage1));
				 imageslist.add(Services.getBitmapFromURLs(pimage2));
				 imageslist.add(Services.getBitmapFromURLs(pimage3));*/
				
			//	imageslist.add(browse_ads_individual_obj.get("product_image1"));
				
			/*		productsMap.put("product_image1", browse_ads_individual_obj.get("product_image1"));
					productsMap.put("product_image2", browse_ads_individual_obj.get("product_image2"));
					productsMap.put("product_image3", browse_ads_individual_obj.get("product_image3"));
					*/
					
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			try {
				title.setText(product_title1);
				description.setText(product_description1);
				category.setText(product_category1);
				price.setText(product_price1);
				post.setText(product_date1);

				postby.setText(product_email1);
				sendmail.setText("Send email to the seller");
				
				mobile.setText(product_phone1);
				location.setText(product_location1);
				
				
				
				for (int i = 0; i < imageslist.size(); i++) {
					
					ImageView img = new ImageView(EditPost.this);
					
					 img.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								/*ImageView image = (ImageView)v.findViewById(v.getId());
								loadPhoto(image, image.getWidth(), image.getHeight());
								*/
								/*intent = new Intent(Comments.this, Imagepopup.class);
								intent.putExtra("post_id", post_id);
								startActivity(intent);*/
							}
						});
					 
					img.setId(i);
					LinearLayout.LayoutParams vp = 
						    new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 
						    		LayoutParams.MATCH_PARENT);
						img.setLayoutParams(vp); 
						img.setScaleType(ScaleType.CENTER_INSIDE);
						img.setImageBitmap(imageslist.get(i));
						//new ImageLoader(Comments.this).DisplayImage(imageshare_url.get(j), img);
						//cover_pic.setImageBitmap(scoralimages.get(0));
					image_slider.addView(img);
					
					
					
				}
				
				
				
				
			}
				catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = new ProgressDialog(EditPost.this);
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.setMessage("loading...");
			pd.show();
		}
		
	}
	
	
	public class saveEditPost extends AsyncTask<String, Void, Void>{
		ProgressDialog pd;
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			editPostResult = Services.getEditPostResultDetails(edit_pid);
			Log.i("editPostResult","editPostResult : "+editPostResult);
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			try {
				jobj = new JSONObject(editPostResult);
				String ep_status = jobj.getString("status");
				String ep_message = jobj.getString("message");
						
				if(ep_status.equals("Success")){
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							EditPost.this);
			 
						// set title
						//alertDialogBuilder.setTitle("Your Title");
			 
						// set dialog message
						alertDialogBuilder
							.setMessage(""+ep_message)
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
			pd = new ProgressDialog(EditPost.this);
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.setMessage("updating...");
			pd.show();
		}
		
	}
	public void editPost(String del_product_id){
		this.edit_product_id=del_product_id;
		
		new saveEditPost().execute("");
	} 

}
