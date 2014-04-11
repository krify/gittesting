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
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;


import com.marketplace.beans.LoginDetails;
import com.marketplace.beans.ProductCurrencyDetails;
import com.marketplace.helpers.AlertDialogManager;
import com.marketplace.helpers.ConnectionDetector;
import com.marketplace.services.Services;
import com.marketplace.views.HorizontalPager;

public class BrowseAdsIndividualPlacedToday extends Activity{
	ImageView back;
	
	TextView title,description,category,price,post,postby,sendmail,mobile,location;
	
	ScrollView sv;
	
String BrowseAdsIndividualResult;
	
ListView browse_ads_individual_listview;
	JSONObject jobj,browse_ads_individual_obj;
	JSONArray browse_ads_individual_jary;
	ArrayList<HashMap<String, Object>> browse_ads_individual_Arraylist;
	
	Intent browse_ads_individual_intent;
	
	String productID = null;
	String activtystatus = null;
	
	String status1,product_id1,product_title1,product_description1,product_location1;
	
	String product_currency1,product_price1,product_date1,product_email1,product_phone1,product_category1;
	
	String p_username,p_user_phone;
	String pimage1,pimage2,pimage3;
	
	String sendtoemailid = null;
	
	ArrayList<Bitmap> imageslist;
	
	ArrayList<String> image_url;
	
	HorizontalPager image_slider;
	
	ImageView dot1,dot2,dot3;
	TextView titlebar;
	int count = 0;
	
	
	protected ConnectionDetector cd;
	protected AlertDialogManager alert = new AlertDialogManager();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.placed_today_product_details_individual);
		
		//TextView titlebar;
				Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/ASENST.ttf");
				titlebar = (TextView) findViewById(R.id.placedtoday_pdetails_titlebar_tv);
				titlebar.setTypeface(tf);
				titlebar.setTextSize(40);
		
		 Bundle extras = getIntent().getExtras(); 

			if (extras != null) {
				activtystatus = extras.getString("activity");
				Log.i("activtystatus", "activtystatus is :"+activtystatus);
				//Toast.makeText(getApplicationContext(), "activtystatus is :"+activtystatus, Toast.LENGTH_SHORT).show();
			    
			}
			
			sv = (ScrollView) findViewById(R.id.placedtoday_pdetails_scrollview);
		
		imageslist = new ArrayList<Bitmap>();
		image_url = new ArrayList<String>();
		
		image_slider = (HorizontalPager) findViewById(R.id.horizontal_pager1);
		
		back = (ImageView) findViewById(R.id.placedtoday_pdetails_back_iv);
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(activtystatus.contentEquals("welcome_screen")){
				browse_ads_individual_intent = new Intent(BrowseAdsIndividualPlacedToday.this,Welcome.class);
				startActivity(browse_ads_individual_intent);
				}
				else if( activtystatus.contentEquals("placed_today")){
					browse_ads_individual_intent = new Intent(BrowseAdsIndividualPlacedToday.this,BrowseAdsPlacedtoday.class);
					startActivity(browse_ads_individual_intent);
				}
					else if(activtystatus.contentEquals("category") ){
						browse_ads_individual_intent = new Intent(BrowseAdsIndividualPlacedToday.this,BrowseAdsCategory.class);
						startActivity(browse_ads_individual_intent);
					}
					else if(activtystatus.contentEquals("manage_listing") ){
						browse_ads_individual_intent = new Intent(BrowseAdsIndividualPlacedToday.this,ManageListingsTab.class);
						startActivity(browse_ads_individual_intent);
					}
			}
		});
		
		sv.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				
				findViewById(R.id.horizontal_pager1).getParent()
						.requestDisallowInterceptTouchEvent(false);
				// findViewById(R.id.grid).getParent().requestDisallowInterceptTouchEvent(false);
				return false;
			}

		});
		image_slider.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				
				// Disallow the touch request for parent scroll on touch of
				// child view
				v.getParent().requestDisallowInterceptTouchEvent(true);
				return false;
			}

		});
		
		title = (TextView) findViewById(R.id.placedtoday_pdetails_placedtoday_pdetails_title_status_tv);
		description = (TextView) findViewById(R.id.placedtoday_pdetails_placedtoday_pdetails_description_status_tv);
		category = (TextView) findViewById(R.id.placedtoday_pdetails_placedtoday_pdetails_category_status_tv);
		price = (TextView) findViewById(R.id.placedtoday_pdetails_placedtoday_pdetails_price_status_tv);
		post = (TextView) findViewById(R.id.placedtoday_pdetails_placedtoday_pdetails_post_status_tv);
		postby = (TextView) findViewById(R.id.placedtoday_pdetails_placedtoday_pdetails_postby_status_tv);
		sendmail = (TextView) findViewById(R.id.placedtoday_pdetails_placedtoday_pdetails_sendemail_status_tv);
		mobile = (TextView) findViewById(R.id.placedtoday_pdetails_placedtoday_pdetails_mobile_status_tv);
		location = (TextView) findViewById(R.id.placedtoday_pdetails_placedtoday_pdetails_locations_status_tv);
		
		dot1 = (ImageView) findViewById(R.id.placedtoday_pdetails_placedtoday_pdetails_slider_dot1_iv);
		dot2 = (ImageView) findViewById(R.id.placedtoday_pdetails_placedtoday_pdetails_slider_dot2_iv);
		dot3 = (ImageView) findViewById(R.id.placedtoday_pdetails_placedtoday_pdetails_slider_dot3_iv);
		
		sendmail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cd = new ConnectionDetector(getApplicationContext());
				 if (!cd.isConnectingToInternet()) {
						// Internet Connection is not present
						alert.showAlertDialog(BrowseAdsIndividualPlacedToday.this,
								"Connection Error",
								"Unable to connect with the server. Please check your internet connection and try again.", false);
						// stop executing code by return
						return;
					}
				 else if(activtystatus.contentEquals("welcome_screen")){
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
								BrowseAdsIndividualPlacedToday.this);
				 
							// set title
							//alertDialogBuilder.setTitle("Your Title");
				 
							// set dialog message
							alertDialogBuilder
								.setMessage("Please login to send an email to seller")
								.setCancelable(false)
								.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										// if this button is clicked, close
										// current activity
										dialog.cancel();
										browse_ads_individual_intent = new Intent(BrowseAdsIndividualPlacedToday.this,LoginMarketPlace.class);
										startActivity(browse_ads_individual_intent);
										
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
					alertDialog.show();
							
					}
				 
				 else if(activtystatus.contentEquals("category") || activtystatus.contentEquals("placed_today")|| activtystatus.contentEquals("manage_listing")){
						
						sendEmail();
						/*Intent email = new Intent(Intent.ACTION_SEND);
						sendtoemailid = postby.getText().toString();
						browse_ads_individual_intent.putExtra("Product_id", sendtoemailid);
						Toast.makeText(getApplicationContext(), "email iid is :"+ postby.getText().toString(), Toast.LENGTH_LONG).show();
						email.putExtra(Intent.EXTRA_EMAIL, new String[] { sendtoemailid });
						// email.putExtra(Intent.EXTRA_CC, new String[]{ to});
						// email.putExtra(Intent.EXTRA_BCC, new String[]{to});
						// email.putExtra(Intent.EXTRA_SUBJECT, subject);
						//email.putExtra(Intent.EXTRA_TEXT, message);

						// need this to prompts email client only
						email.setType("message/rfc822");

						startActivity(Intent.createChooser(email,
								"Choose an Email client :"));*/
					}else{
						
							
					}
				 
			}
		});
		/*
		 * Intent extra data
		 */
		 Bundle extras1 = getIntent().getExtras(); 

			if (extras1 != null) {
				productID = extras1.getString("Product_id");
				Log.i("productID", "productID is :"+productID);
				//Toast.makeText(getApplicationContext(), "productID is :"+productID, Toast.LENGTH_LONG).show();
				
			}
			
			
			new BrowseAdsIndividualDetail().execute("");
		
		
		
	}
	public class BrowseAdsIndividualDetail extends AsyncTask<String, Void, Void>{
		ProgressDialog pd;
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			BrowseAdsIndividualResult = Services.getBrowseAdsIndividualResultDetails(productID);
			Log.i("BrowseAdsIndividualResult","BrowseAdsIndividualResult : "+BrowseAdsIndividualResult);
			try {
				jobj = new JSONObject(BrowseAdsIndividualResult);
				 status1 = jobj.getString("status");
				 product_id1 = jobj.getString("product_id");
				 product_title1 = jobj.getString("product_title");
				 product_description1 = jobj.getString("product_description");
				 product_location1 = jobj.getString("product_location");
				 product_currency1 = jobj.getString("product_currency")+"";
				 String Currency_code =  jobj.getString("product_currency");

				
				 product_price1 = jobj.getString("product_price");
				 product_date1 = jobj.getString("product_date");
				
				
				 product_email1 = jobj.getString("product_email");
				 
				 sendtoemailid = product_email1;
				 product_phone1 = jobj.getString("product_phone");
				 product_category1 = jobj.getString("product_category");
				 
				 pimage1 = jobj.getString("product_image1");
				 pimage2 = jobj.getString("product_image2");
				 pimage3 = jobj.getString("product_image3");
				 
				 Log.i("pimage1", "pimage1"+pimage1);
				 Log.i("pimage2", "pimage2"+pimage2);
				 Log.i("pimage3", "pimage3"+pimage3);
				 
				 p_username = jobj.getString("product_username");
				 p_user_phone = jobj.getString("product_phone");
				 
				 if(pimage1.length() == 0 && pimage2.length() == 0 && pimage3.length() == 0 ){
					 
				 }
				 if(pimage1.length() != 0 && pimage2.length() == 0 && pimage3.length() == 0 ){
					 
					imageslist.add(Services.getBitmapFromURLs(pimage1));
					//image_url.add(pimage1);
					count =1;
				 }
				 
				 if(pimage1.length() != 0 && pimage2.length() != 0 && pimage3.length() == 0 ){
					 
					 imageslist.add(Services.getBitmapFromURLs(pimage1));
					 imageslist.add(Services.getBitmapFromURLs(pimage2));
					// image_url.add(pimage1);
					 //image_url.add(pimage2);
					 count =2;
					
				 }
				 if(pimage1.length() != 0 && pimage2.length() != 0 && pimage3.length() != 0 ){
					 
					 imageslist.add(Services.getBitmapFromURLs(pimage1));
					 imageslist.add(Services.getBitmapFromURLs(pimage2));
					 imageslist.add(Services.getBitmapFromURLs(pimage3));
					 //image_url.add(pimage1);
					// image_url.add(pimage2);
					// image_url.add(pimage3);
					 count=3;
				 }
				 
			
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
			
			try {
				title.setText(product_title1);
				description.setText(product_description1);
				category.setText(product_category1);
				price.setText(product_currency1+""+product_price1);
				post.setText(product_date1);

				postby.setText(product_email1);
				sendmail.setText("Send email to the seller");
				
				mobile.setText(product_phone1);
				location.setText(product_location1);
				
				if(count==0){
					dot1.setVisibility(View.GONE);
					dot2.setVisibility(View.GONE);
					dot3.setVisibility(View.GONE);
					
				}else if(count==1){
					dot2.setVisibility(View.GONE);
					dot3.setVisibility(View.GONE);
				}else if(count==2){
					dot3.setVisibility(View.GONE);
					
				}else {
					
					
				}
				
				count=0;
				
				for (int i = 0; i < imageslist.size(); i++) {
					
					ImageView img = new ImageView(BrowseAdsIndividualPlacedToday.this);
					img.setScaleType(ImageView.ScaleType.FIT_CENTER);
					img.setLayoutParams(new LayoutParams(20,
					                LayoutParams.MATCH_PARENT));
				
					/* img.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								ImageView image = (ImageView)v.findViewById(v.getId());
								loadPhoto(image, image.getWidth(), image.getHeight());
								
								intent = new Intent(Comments.this, Imagepopup.class);
								intent.putExtra("post_id", post_id);
								startActivity(intent);
							}
						});*/
					 
					img.setId(i);
					LinearLayout.LayoutParams vp = 
						    new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 
						    		LayoutParams.WRAP_CONTENT);
						img.setLayoutParams(vp); 
						//img.setAdjustViewBounds(false);
						
						//img.getLayoutParams().width = 320;
						//Toast.makeText(getApplicationContext(), "width "+ img.getLayoutParams().width, Toast.LENGTH_SHORT).show();
						//img.getLayoutParams().
						
			
						//img.setScaleType(ScaleType.CENTER_CROP);
						img.setAdjustViewBounds(true);
						img.setImageBitmap(imageslist.get(i));
						//new ImageLoader(Comments.this).DisplayImage(imageshare_url.get(j), img);
						//cover_pic.setImageBitmap(scoralimages.get(0));
					
					image_slider.addView(img);
					
					
					
				}
				
/*				
for (int i = 0; i < image_url.size(); i++) {
					
					WebView img = new WebView(BrowseAdsIndividualPlacedToday.this);
					
				 img.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
				 img.getSettings().setBuiltInZoomControls(false);
					 
					img.setId(i);
					LinearLayout.LayoutParams vp = 
						    new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 
						    		LayoutParams.WRAP_CONTENT);
						img.setLayoutParams(vp); 
						img.loadUrl(image_url.get(i));
					
					image_slider.addView(img);
					
					
					
				}*/
				pd.dismiss();
				
				
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
			pd = new ProgressDialog(BrowseAdsIndividualPlacedToday.this);
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.setMessage("loading...");
			pd.show();
		}
		
	}
	
	 protected void sendEmail() {
	      Log.i("Send email", ""+sendtoemailid);
	      //Toast.makeText(BrowseAdsIndividualPlacedToday.this,"email is:"+sendtoemailid, Toast.LENGTH_SHORT).show();
	      String[] TO = {sendtoemailid};
	     // String[] CC = {"mcmohd@gmail.com"};
	      Intent emailIntent = new Intent(Intent.ACTION_SEND);
	      emailIntent.setData(Uri.parse("mailto:"));
	      emailIntent.setType("text/plain");

         // String data = Html.fromHtml(getResources().getString(R.string.email_content)).toString();
	     // data.replace("email_title", title.getText().toString());
	     // String data = Html.fromHtml( getString(R.string.email_content). replace("email_title", title.getText().toString())).toString();
	     // sp.replace("email_title", title.getText().toString());
	      emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
	   //   emailIntent.putExtra(Intent.EXTRA_CC, CC);
	      emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Laxani Market Place - Enquiry...");
	      /*emailIntent.putExtra(Intent.EXTRA_TEXT, "Title :"+""+title.getText().toString());
	      emailIntent.putExtra(Intent.EXTRA_TEXT, "Description :"+""+description.getText().toString());
	      emailIntent.putExtra(Intent.EXTRA_TEXT, "Category :"+""+category.getText().toString());
	      emailIntent.putExtra(Intent.EXTRA_TEXT, "Price :"+""+price.getText().toString());
	      emailIntent.putExtra(Intent.EXTRA_TEXT, "Posted On :"+""+post.getText().toString());
	      emailIntent.putExtra(Intent.EXTRA_TEXT, "User Comment :"+""+title.getText().toString());
	      emailIntent.putExtra(Intent.EXTRA_TEXT, "User Name :"+"");
	      emailIntent.putExtra(Intent.EXTRA_TEXT, "User Phone Number :"+"");*/
	     // emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, data );
	      emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
	      Html.fromHtml("Hi " +"<b>"+""+"</b>"+"<br><br>"+
	    		   "You got a enquiry from one of the laxani market place user for the following Ad:"+"<br><br>"+
	    		  "<b>"+"Title"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp"+":"+"</b>"+" "+title.getText().toString()+"<br><br>"+
	    		  "<b>"+"Description"+"&nbsp;&nbsp;"+":"+"</b>"+" "+description.getText().toString()+"<br><br>"+
	    		  "<b>"+"Category"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+":"+"</b>"+" "+category.getText().toString()+"<br><br>"+
	    		  "<b>"+"Price"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+":"+""+"</b>"+" "+price.getText().toString()+"<br><br>"+
	    		  "<b>"+"Posted On"+"&nbsp;&nbsp;&nbsp;&nbsp;"+":"+"</b>"+" "+post.getText().toString()+"<br><br>"+"<br><br>"+"<br><br>"+
	    		 "Here is the message from the user:"+"<br><br>"+
	    		  ""+"User Comment"+"&nbsp;&nbsp;&nbsp;&nbsp;"+":"+"</b>"+" "+""+"<br><br>"+
	    		  ""+"User Name"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+":"+"</b>"+" "+p_username+"<br><br>"+
	    		  ""+"User Phone Number"+"&nbsp;&nbsp;"+":"+"</b>"+" "+p_user_phone+"</b>"+""));
	    		  
	      
	     /* emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
	    	      Html.fromHtml("<!DOCTYPE html><html><head></head><body><div style='border:5px solid #018DAE;margin:0 auto;padding:10px;width:auto;'><p>Hi <b>%@,</b></p><p>You got a enquiry from one of the laxani market place user for the following Ad:</p><div style='background-color:#CCCCCC;padding:5px;margin:5px 0 20px;'><table><tbody><tr><td>Title</td><td>%@</td></tr><tr><td>Description</td><td>%@</td></tr><tr><td>Category</td><td>%@</td></tr><tr><td>Price</td><td>%@</td></tr><tr><td>Posted On</td><td>%@</td></tr></tbody></table></div><p>Here is the message from the user:</p><div style='background-color:#ffffff;padding:5px;margin:5px 0 20px;'><table><tbody><tr><td>User Comment</td><td></td></tr><tr><td>User Name</td><td>%@</td></tr><tr><td>User Phone Number</td><td>%@</td></tr></tbody></table></div></div></body></html>"));
	    		  */
	      emailIntent.setType("text/html");
	      

	      try {
	         startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	         finish();
	         Log.i("Finished sending email...", "");
	      } catch (android.content.ActivityNotFoundException ex) {
	        /* Toast.makeText(BrowseAdsIndividualPlacedToday.this, 
	         "There is no email client installed.", Toast.LENGTH_SHORT).show();*/
	      }
	   }

}
