package com.marketplace;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Currency;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.marketplace.beans.AddNewPostDetails;
import com.marketplace.beans.Webapis;
import com.marketplace.helpers.AlertDialogManager;
import com.marketplace.helpers.ConnectionDetector;
import com.marketplace.helpers.JSONParser;
import com.marketplace.helpers.RotateOrientation;
import com.marketplace.services.Services;




import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class PostAd extends Activity{
	
	 JSONArray jsarr = null;
	JSONArray jsarr1 = null;
	JSONObject jsobj = null;
	JSONObject jsobj1 = null;;
	JSONArray jsar2 = null;
	JSONParser jparse;
	JSONParser myparse;
	
	ArrayList<String> categories_list;
	ArrayList<String> currency_list,currency_code;
	
	Spinner category_spinner,currency_spinner;
	
	String category_item,currency_item,currency_code_item;
	
	ImageView activity_tab,post_tab,manage_listing_tab;
	
	ImageView upload_image_one,upload_image_two,upload_image_three;
	Intent postad_Intent;
	

	Uri output1,output12,output13;
	File imageFile,imageFile2,imageFile3;
	Bitmap bitmap1,bitmap12,bitmap13;
	File file,file2,file3;
	FileOutputStream fOut,fOut2,fOut3;
	Intent intent;
	String pathin,pathin2,pathin3;
	 
	private static final int GALLERY_REQUEST_CODE = 0;
	private static final int CAMERA_REQUEST_CODE = 1;
	private static final int GALLERY_REQUEST_CODE_TWO = 2;
	private static final int CAMERA_REQUEST_CODE_TWO = 3;
	private static final int GALLERY_REQUEST_CODE_THREE = 4;
	private static final int CAMERA_REQUEST_CODE_THREE = 5;
	Bitmap bmp1 =null,bmp12 = null,bmp13 = null;
		
	protected ConnectionDetector cd; protected AlertDialogManager
	alert = new AlertDialogManager();
	
	
	EditText title_status,description_status,price_status,location_status;
	ImageView postad_icon;
	
	String post_new_ad_result;
	JSONObject post_ad_jobj;
	
	TextView category_id_tv;
		
	ImageView middlebarone,middlebartwo;
	String currency_symbol= null,currcode;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.postad_tab);
		
		activity_tab = (ImageView) findViewById(R.id.botombar_browse_ads_tab_iv);
		post_tab = (ImageView) findViewById(R.id.botombar_post_ads_tab_iv);
		manage_listing_tab = (ImageView) findViewById(R.id.botombar_manage_listing_tab_iv);
		
		upload_image_one  = (ImageView) findViewById(R.id.post_ad_image_upload_one);
		upload_image_two  = (ImageView) findViewById(R.id.post_ad_image_upload_two);
		upload_image_three  = (ImageView) findViewById(R.id.post_ad_image_upload_three);
		
		category_spinner = (Spinner)findViewById(R.id.post_ad_content_category_spinner);
		currency_spinner = (Spinner)findViewById(R.id.post_ad_content_select_currency_spinner);
		
		categories_list = new ArrayList<String>();
		currency_list = new ArrayList<String>();
		currency_code = new ArrayList<String>();
		
		title_status =(EditText) findViewById(R.id.post_ad_content_title_status_et);
		description_status =(EditText) findViewById(R.id.post_ad_content_description_status_et);
		location_status =(EditText) findViewById(R.id.post_ad_content_location_status_et);
		price_status =(EditText) findViewById(R.id.post_ad_content_price_status_et);
		
		postad_icon = (ImageView)findViewById(R.id.post_ad_postad_icon_iv);
		
		middlebarone = (ImageView)findViewById(R.id.post_ad_image_upload_middlebar_one);
		middlebartwo = (ImageView)findViewById(R.id.post_ad_image_upload_middlebar_two);
		
		activity_tab.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				postad_Intent = new Intent(PostAd.this,BrowseAdsPlacedtoday.class);
				startActivity(postad_Intent);
			}
		});
		post_tab.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
					}
				});
		manage_listing_tab.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				postad_Intent = new Intent(PostAd.this,ManageListingsTab.class);
				startActivity(postad_Intent);
			}
		});
		postad_icon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				/*AddNewPostDetails.setTitle(title_status.getText().toString());
				AddNewPostDetails.setDescription(description_status.getText().toString());
				AddNewPostDetails.setLocation(location_status.getText().toString());
				AddNewPostDetails.setPrice(price_status.getText().toString());*/
				
				AddNewPostDetails.title=title_status.getText().toString();
				AddNewPostDetails.description = description_status.getText().toString();
				AddNewPostDetails.location = location_status.getText().toString();
				AddNewPostDetails.price= price_status.getText().toString();
				
				
			/*	String curr_symbol = null;
				try {
					
					curr_symbol = Utils.getCurrencySymbol(currency_symbol);
					Log.i("Currency_code", "Currency_code :"+currency_symbol);
					Log.i("curr_symbol", "curr_symbol :"+curr_symbol);
					//productsMap.put("Product_currency", curr_symbol);
					AddNewPostDetails.setCurrency_code(curr_symbol);
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					try {
						Currency c  = Currency.getInstance(currency_symbol);
						String curr_symbol1 = c.getSymbol();
						Log.i("Currency_code1", "Currency_code1 :"+currency_symbol);
						Log.i("curr_symbol1", "curr_symbol1 :"+curr_symbol1);
						AddNewPostDetails.setCurrency_code(curr_symbol);
						//productsMap.put("Product_currency", curr_symbol1);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				
				}*/
				
			new postNewAd().execute("");
			}
		});
		
		upload_image_one.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					//	Takesnap(v);
						AlertDialog.Builder alert = new AlertDialog.Builder(PostAd.this);
						alert.setMessage("Choose One");
						alert.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								onBtnTakePicture();
							}
						});
						
						alert.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								onBtnPickGallery();
							}
						});
						
						alert.create();
						alert.show();
					}
				});
		upload_image_two.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v1) {
				// TODO Auto-generated method stub
			//	Takesnap(v1);
				AlertDialog.Builder alert = new AlertDialog.Builder(PostAd.this);
				alert.setMessage("Choose One");
				alert.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						onBtnTakePicture2();
					}
				});
				
				alert.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						onBtnPickGallery2();
					}
				});
				
				alert.create();
				alert.show();
			}
		});
		upload_image_three.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v2) {
			//	Takesnap(v2);
				AlertDialog.Builder alert = new AlertDialog.Builder(PostAd.this);
				alert.setMessage("Choose One");
				alert.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						onBtnTakePicture3();
					}
				});
				
				alert.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						onBtnPickGallery3();
					}
				});
				
				alert.create();
				alert.show();
			}
		});
		post_tab.setBackgroundColor(0xff000000);
		
		cd = new ConnectionDetector(getApplicationContext());

		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(PostAd.this,
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}
		
		/*AddNewPostDetails.setTitle(" ");
		AddNewPostDetails.setDescription(" ");
		AddNewPostDetails.setLocation(" ");
		AddNewPostDetails.setPrice(" ");
		
		AddNewPostDetails.setUpload_image_one(" ");
		AddNewPostDetails.setUpload_image_two(" ");
		AddNewPostDetails.setUpload_image_three(" ");*/
		//AddNewPostDetails.setCurrency_code(" ");

		new categories().execute("");
	}
	public class postNewAd extends AsyncTask<String, Void, Void>{
		ProgressDialog pd;
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			try {
				post_ad_jobj = new JSONObject(post_new_ad_result);
				String post_ad_status = post_ad_jobj.getString("status");
				String post_ad_message = post_ad_jobj.getString("message");
				
				if(post_ad_status.equals("Error")){
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							PostAd.this);
					
						// set title
						//alertDialogBuilder.setTitle("Your Title");
					
						// set dialog message
						alertDialogBuilder
							.setCancelable(false)
							.setMessage(""+post_ad_message)
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
				}else{
					//Toast.makeText(PostAd.this, "Successfully posted", Toast.LENGTH_SHORT).show();
					postad_Intent = new Intent(PostAd.this,ManageListingsTab.class);
					startActivity(postad_Intent);
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = new ProgressDialog(PostAd.this);
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.setMessage("processing...");
			pd.show();
		}
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			post_new_ad_result = Services.getPostNewAdDetails(imageFile,imageFile2,imageFile3);
			Log.i("post_new_ad_result","post_new_ad_result : "+post_new_ad_result);
			return null;
		}
		
	}
	public class categories extends AsyncTask<String, Void, Void> {

		ProgressDialog pd;

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub

			jparse = new JSONParser();
			jsobj = jparse.getJSONFromUrl(Webapis.categories_url);
			try {
				jsarr = jsobj.getJSONArray("Categories");
				for (int i = 0; i < jsarr.length(); i++) {
					categories_list.add(jsarr.getJSONObject(i).getString("catname"));
					
					
					//categories_id_list.add(jsarr.getJSONObject(i).getString("cat_id"));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			myparse = new JSONParser();
			jsobj1 = myparse.getJSONFromUrl(Webapis.currency_list_url);
			try {
				jsarr1 = jsobj1.getJSONArray("currency_list");
				for (int i = 0; i < jsarr1.length(); i++) {
					currency_list.add(jsarr1.getJSONObject(i).getString("currency_cname"));
					currency_code.add(jsarr1.getJSONObject(i).getString("currency_code"));
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			pd = ProgressDialog.show(PostAd.this, "", "processing...");

			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			try {
				pd.dismiss();

				ArrayAdapter<String> country_adapter = new ArrayAdapter<String>(
						PostAd.this, android.R.layout.simple_spinner_item,categories_list);
				category_spinner.setAdapter(country_adapter);
				/*for(int k=0;k<jsarr.length();k++){
					category_id_tv.setText(jsarr.getJSONObject(k).getString("cat_id"));
				}*/
				
				category_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						category_item = (String) category_spinner
								.getItemAtPosition(category_spinner
										.getSelectedItemPosition());
						AddNewPostDetails.setCategory(category_item);
						
						String index;
						index = ""+category_spinner.getSelectedItemPosition();
						//index = category_spinner.getItemAtPosition((category_spinner.getSelectedItemPosition().);
						
						/*String myString = "some value"; //the value you want the position for

						ArrayAdapter myAdap = (ArrayAdapter) category_spinner.getAdapter(); //cast to an ArrayAdapter

						int spinnerPosition = myAdap.getPosition(myString);

						//set the default according to value
						category_spinner.setSelection(spinnerPosition);*/
						//AddNewPostDetails id = new AddNewPostDetails();
						AddNewPostDetails.setCategory_id(index);
						Log.i("Category id onclick", "Category id onclick :"+index);
						//Toast.makeText(getApplicationContext(), "cat_id is :"+index, Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

				ArrayAdapter<String> club_adapter = new ArrayAdapter<String>(
						PostAd.this, android.R.layout.simple_spinner_item,
						currency_list);
				currency_spinner.setAdapter(club_adapter);
				currency_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						currency_item = (String) currency_spinner.getItemAtPosition(currency_spinner
								.getSelectedItemPosition());
						currency_code_item = ( String) currency_spinner.getItemAtPosition(currency_spinner
								.getSelectedItemPosition());
						/*Toast.makeText(getApplicationContext(),
								"currency_item is :"+currency_item+ "currency_code_item"+currency_code_item, Toast.LENGTH_LONG).show();*/
						AddNewPostDetails.setCurrency(currency_item);
						
						String index1;
						index1 = ""+currency_spinner.getSelectedItemPosition();
						int index2 = Integer.parseInt(index1);
						currency_code_item = currency_code.get(index2);
						currency_symbol = currency_code_item;
						AddNewPostDetails.setCurrency_code_id(index1);
						AddNewPostDetails.setCurrency_code(""+currency_code_item);
						Log.i("currency_code_item", "currency_code_item"+currency_code_item);
						Log.i("currency code id onclick", "currency code onclick :"+index1);
						Log.i("AddNewPostDetails Currency_code", "AddNewPostDetails Currency_code :"+AddNewPostDetails.getCurrency_code());
						//Toast.makeText(getApplicationContext(), "currency_code_item is :"+AddNewPostDetails.getCurrency_code(), Toast.LENGTH_SHORT).show();
						//Toast.makeText(getApplicationContext(), "cat_id is :"+index1+""+ "currency_code_item is :"+currency_code_item, Toast.LENGTH_SHORT).show();
						/*
						 * Toast.makeText(getBaseContext(), clubitem,
						 * Toast.LENGTH_LONG).show();
						 */
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.onPostExecute(result);

		}
	}

	

	public void Takesnap(View v){
		
		
		
		AlertDialog.Builder alert = new AlertDialog.Builder(PostAd.this);
		alert.setMessage("Choose One");
		alert.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				onBtnTakePicture();
			}
		});
		
		alert.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				onBtnPickGallery();
			}
		});
		
		alert.create();
		alert.show();
		
	}

public void onBtnPickGallery() {

	try {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"),
				GALLERY_REQUEST_CODE);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		//showAlert("During camera capture : "+e.toString());
	}
}

public void onBtnTakePicture() {

	try {
	
		
		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		cameraIntent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 
	   
		pathin =Environment.getExternalStorageDirectory()+"/Takesnap_pro_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
		
		
		output1 = Uri.fromFile(new File(pathin));
	    
	                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, output1);
		
	                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		//showAlert("During camera capture : "+e.toString());
	}
}

public void onBtnPickGallery2() {

	try {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"),
				GALLERY_REQUEST_CODE_TWO);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		//showAlert("During camera capture : "+e.toString());
	}
}

public void onBtnTakePicture2() {

	try {
	
		
		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		cameraIntent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 
	   
		pathin2 =Environment.getExternalStorageDirectory()+"/Takesnap_pro_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
		
		
		output12 = Uri.fromFile(new File(pathin2));
	    
	                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, output12);
		
	                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE_TWO);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		//showAlert("During camera capture : "+e.toString());
	}
}

public void onBtnPickGallery3() {

	try {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"),
				GALLERY_REQUEST_CODE_THREE);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		//showAlert("During camera capture : "+e.toString());
	}
}

public void onBtnTakePicture3() {

	try {
	
		
		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		cameraIntent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 
	   
		pathin3 =Environment.getExternalStorageDirectory()+"/Takesnap_pro_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
		
		
		output13 = Uri.fromFile(new File(pathin3));
	    
	                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, output13);
		
	                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE_THREE);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		//showAlert("During camera capture : "+e.toString());
	}
}
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	try {
		switch (requestCode) {
		case CAMERA_REQUEST_CODE:
			try {
				if (resultCode == Activity.RESULT_OK) {
					if(data != null)
					{

						Uri selectedImageUri = data.getData();
						
						//bitmap1 =decodeUri1(selectedImageUri);
						Log.i("camera", "if");
						bitmap1 = getImage1(getRealPathFromURI(selectedImageUri));
						
						
                        file = new File(Environment.getExternalStorageDirectory() + "/camera_snap"+System.currentTimeMillis()+".jpeg");
						fOut = new FileOutputStream(file);		            
						
						bitmap1.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
	
			            //profile_pic.setImageBitmap(bitmap1);
			            
			            imageFile =file;
			            Log.i("Image url ", ""+file.toString());
			            Uri outputFileUri = Uri.fromFile(file);
			            AddNewPostDetails.setImage_one(outputFileUri.toString());
			            AddNewPostDetails.setUpload_one(file);
			           //Utils.profile_pic =file;
			            
			            ByteArrayOutputStream stream = new ByteArrayOutputStream();
			             
						 bitmap1.compress(Bitmap.CompressFormat.PNG, 100, stream);
	                         
				            byte[] byteArray = stream.toByteArray();
				       
				            
				                //img.setImageBitmap(bitmap);
					           middlebarone.setVisibility(View.GONE);
					           upload_image_one.setScaleType(ScaleType.FIT_XY);
			            upload_image_one.setImageBitmap(bitmap1);
			    
			       
					}
					else{
						
						//bitmap1 =decodeUri1(output1);
						Log.i("camera", "else");
						//bitmap1 = getImage1(	getRealPathFromURI(output1));
						Log.i("camera", "else"+pathin);
						bitmap1 = getImage1(pathin);
						//
						 file = new File(Environment.getExternalStorageDirectory() + "/camera_snap"+System.currentTimeMillis()+".jpeg");
							
							fOut = new FileOutputStream(file);

							bitmap1.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
							
							imageFile =file;
				            AddNewPostDetails.setUpload_one(file);
							//Utils.profile_pic =file;
		
				           // profile_pic.setImageBitmap(bitmap1);
							
							 ByteArrayOutputStream stream = new ByteArrayOutputStream();
					             
							 bitmap1.compress(Bitmap.CompressFormat.PNG, 100, stream);
		                         
					            byte[] byteArray = stream.toByteArray();
					            
					            
				            
					            middlebarone.setVisibility(View.GONE);
					            upload_image_one.setScaleType(ScaleType.FIT_XY);
					            upload_image_one.setImageBitmap(bitmap1);
					}

				} else if (resultCode == Activity.RESULT_CANCELED) {
					Log.e("pic cancel", "Selecting picture cancelled");
				}
			} catch (OutOfMemoryError e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				//showAlert("During camera capture : "+e2.toString());
			}catch(Exception e1){
				e1.printStackTrace();
				//showAlert("During camera capture : "+e1.toString());
				
			}
			break;

		case GALLERY_REQUEST_CODE:
			try {
				if (resultCode == Activity.RESULT_OK) { 
					if(data != null)
					{
                        
						
						  bmp1 =null;
						Uri selectedImage1 = data.getData();
						file = new File(getRealPathFromURI(selectedImage1));
						 try {
							 Log.i("Gallery code","Gallery1");
							 bmp1 = getImage1(getRealPathFromURI(selectedImage1));
							  // bmp1 =decodeUri1(selectedImage);
							   Log.i("bmp1", "image :"+bmp1);
			                	//
			                } catch (FileNotFoundException e) {
			                    e.printStackTrace();
			                }
						Uri  picture = data.getData();
						

						// file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/gallery_snap"+System.currentTimeMillis()+".jpeg");
						
						FileOutputStream fOut = new FileOutputStream(file);
						//Bitmap bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

			            ByteArrayOutputStream stream = new ByteArrayOutputStream();
			            
			            bmp1.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
			            
			            bmp1.compress(Bitmap.CompressFormat.PNG, 100, stream);
			            
			            imageFile =file;
			            AddNewPostDetails.setUpload_one(file);
                         
			            byte[] byteArray = stream.toByteArray();
			          
						
			           // image.setImageBitmap(bmp);
			            //imageFile =file;
			            
			          //  Utils.profile_pic =file;
			            Log.i("imageFile", "imageFile :"+imageFile);
			            
			            middlebarone.setVisibility(View.GONE);
			            upload_image_one.setScaleType(ScaleType.FIT_XY);
			            upload_image_one.setImageBitmap(bmp1);
					}
					else{

						//Toast.makeText(getApplicationContext(), "data is null", Toast.LENGTH_SHORT).show();
					}


				} else if (resultCode == Activity.RESULT_CANCELED) {
					//Log.e("pic cancel", "Selecting picture cancelled");
				}
			} catch (OutOfMemoryError e1) {
				// TODO Auto-generated catch block
		e1.printStackTrace();
				//showAlert("During camera capture : "+e1.toString());
			}catch(Exception e1){
				e1.printStackTrace();
				//showAlert("During camera capture : "+e1.toString());
			}

			break;
		case CAMERA_REQUEST_CODE_TWO:
			try {
				if (resultCode == Activity.RESULT_OK) {
					if(data != null)
					{

						Uri selectedImageUri2 = data.getData();
						
						
						//bitmap1 =decodeUri1(selectedImageUri);
						Log.i("camera", "if");
						bitmap12 = getImage1(getRealPathFromURI(selectedImageUri2));
						
						
                        file2 = new File(Environment.getExternalStorageDirectory() + "/camera_snap2"+System.currentTimeMillis()+".jpeg");
						fOut2 = new FileOutputStream(file2);		            
						
						bitmap12.compress(Bitmap.CompressFormat.JPEG, 70, fOut2);
	
			            //profile_pic.setImageBitmap(bitmap1);
			            
			            imageFile2 =file2;
			            Log.i("Image url ", ""+file2.toString());
			            Uri outputFileUri = Uri.fromFile(file2);
			            
			          //  AddNewPostDetails.setImage_one(outputFileUri.toString());
			            AddNewPostDetails.setUpload_two(file2);
			           //Utils.profile_pic =file;
			            
			            ByteArrayOutputStream stream = new ByteArrayOutputStream();
			             
						 bitmap12.compress(Bitmap.CompressFormat.PNG, 100, stream);
	                         
				            byte[] byteArray = stream.toByteArray();
				            middlebarone.setVisibility(View.GONE);
				            middlebartwo.setVisibility(View.GONE);
				            upload_image_two.setScaleType(ScaleType.FIT_XY);
			            upload_image_two.setImageBitmap(bitmap12);
			       
					}
					else{
						
						//bitmap1 =decodeUri1(output1);
						Log.i("camera", "else");
						//bitmap1 = getImage1(	getRealPathFromURI(output1));
						Log.i("camera", "else"+pathin2);
						bitmap12 = getImage1(pathin2);
						//
						 file2 = new File(Environment.getExternalStorageDirectory() + "/camera_snap2"+System.currentTimeMillis()+".jpeg");
							
							fOut2 = new FileOutputStream(file2);

							bitmap12.compress(Bitmap.CompressFormat.JPEG, 70, fOut2);
							
							imageFile2 =file2;
							  AddNewPostDetails.setUpload_two(file2);
							//Utils.profile_pic =file;
		
				           // profile_pic.setImageBitmap(bitmap1);
							
							 ByteArrayOutputStream stream = new ByteArrayOutputStream();
					             
							 bitmap12.compress(Bitmap.CompressFormat.PNG, 100, stream);
		                         
					            byte[] byteArray = stream.toByteArray();
				            
					            middlebarone.setVisibility(View.GONE);
					            middlebartwo.setVisibility(View.GONE);
					            upload_image_two.setScaleType(ScaleType.FIT_XY);
					            upload_image_two.setImageBitmap(bitmap12);
					}

				} else if (resultCode == Activity.RESULT_CANCELED) {
					Log.e("pic cancel", "Selecting picture cancelled");
				}
			} catch (OutOfMemoryError e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				//showAlert("During camera capture : "+e2.toString());
			}catch(Exception e1){
				e1.printStackTrace();
				//showAlert("During camera capture : "+e1.toString());
				
			}
			break;

		case GALLERY_REQUEST_CODE_TWO:
			try {
				if (resultCode == Activity.RESULT_OK) { 
					if(data != null)
					{
                        
						
						  bmp12 =null;
						Uri selectedImage2 = data.getData();
						file2 = new File(getRealPathFromURI(selectedImage2));
						 try {
							 Log.i("Gallery code2","Gallery12");
							 bmp12 = getImage1(getRealPathFromURI(selectedImage2));
							  // bmp1 =decodeUri1(selectedImage);
							   Log.i("bmp12", "image :"+bmp12);
			                	//
			                } catch (FileNotFoundException e) {
			                    e.printStackTrace();
			                }
						Uri  picture = data.getData();

						// file2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/gallery_snap2"+System.currentTimeMillis()+".jpeg");
						
						FileOutputStream fOut = new FileOutputStream(file2);
						//Bitmap bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

			            ByteArrayOutputStream stream = new ByteArrayOutputStream();
			            
			            bmp12.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
			            
			            bmp12.compress(Bitmap.CompressFormat.PNG, 100, stream);
			            
			            imageFile2 =file2;
			            AddNewPostDetails.setUpload_two(file2);
			            byte[] byteArray = stream.toByteArray();
			          
						
			           // image.setImageBitmap(bmp);
			            //imageFile =file;
			            
			          //  Utils.profile_pic =file;
			            Log.i("imageFile2", "imageFile2 :"+imageFile2);
			           
			            middlebarone.setVisibility(View.GONE);
			            middlebartwo.setVisibility(View.GONE);
			            upload_image_two.setScaleType(ScaleType.FIT_XY);
			            upload_image_two.setImageBitmap(bmp12);
							
							/*	
								intent = new Intent(TakeSnap.this,MySnap.class );
					            intent.putExtra("Bitmap", byteArray);
					            startActivity(intent);
							*/
			            
			          
					}
					else{

						//Toast.makeText(getApplicationContext(), "data is null", Toast.LENGTH_SHORT).show();
					}


				} else if (resultCode == Activity.RESULT_CANCELED) {
					//Log.e("pic cancel", "Selecting picture cancelled");
				}
			} catch (OutOfMemoryError e1) {
				// TODO Auto-generated catch block
		e1.printStackTrace();
				//showAlert("During camera capture : "+e1.toString());
			}catch(Exception e1){
				e1.printStackTrace();
				//showAlert("During camera capture : "+e1.toString());
			}

			break;
		case CAMERA_REQUEST_CODE_THREE:
			try {
				if (resultCode == Activity.RESULT_OK) {
					if(data != null)
					{

						Uri selectedImageUri3 = data.getData();
						
						//bitmap1 =decodeUri1(selectedImageUri);
						Log.i("camera", "if");
						bitmap13 = getImage1(getRealPathFromURI(selectedImageUri3));
						
						
                        file3 = new File(Environment.getExternalStorageDirectory() + "/camera_snap3"+System.currentTimeMillis()+".jpeg");
						fOut3 = new FileOutputStream(file3);		            
						
						bitmap13.compress(Bitmap.CompressFormat.JPEG, 70, fOut3);
	
			            //profile_pic.setImageBitmap(bitmap1);
			            
			            imageFile3 =file3;
			            Log.i("Image url 3 ", ""+file.toString());
			            Uri outputFileUri = Uri.fromFile(file3);
			           //Utils.profile_pic =file;
			            AddNewPostDetails.setUpload_three(file3);
			            
			            ByteArrayOutputStream stream = new ByteArrayOutputStream();
			             
						 bitmap13.compress(Bitmap.CompressFormat.PNG, 100, stream);
	                         
				            byte[] byteArray = stream.toByteArray();
				            
				            middlebarone.setVisibility(View.GONE);
				            middlebartwo.setVisibility(View.GONE);
				            upload_image_three.setScaleType(ScaleType.FIT_XY);
			            upload_image_three.setImageBitmap(bitmap13);
			       
					}
					else{
						
						//bitmap1 =decodeUri1(output1);
						Log.i("camera3", "else");
						//bitmap1 = getImage1(	getRealPathFromURI(output1));
						Log.i("camera3", "else"+pathin3);
						bitmap13 = getImage1(pathin3);
						//
						 file3 = new File(Environment.getExternalStorageDirectory() + "/camera_snap3"+System.currentTimeMillis()+".jpeg");
							
							fOut3 = new FileOutputStream(file3);

							bitmap13.compress(Bitmap.CompressFormat.JPEG, 70, fOut3);
							
							imageFile3 =file3;
							//Utils.profile_pic =file;
							 AddNewPostDetails.setUpload_three(file3);
				           // profile_pic.setImageBitmap(bitmap1);
							
							 ByteArrayOutputStream stream = new ByteArrayOutputStream();
					             
							 bitmap13.compress(Bitmap.CompressFormat.PNG, 100, stream);
		                         
					            byte[] byteArray = stream.toByteArray();
					            middlebarone.setVisibility(View.GONE);
					            middlebartwo.setVisibility(View.GONE);
					            upload_image_three.setScaleType(ScaleType.FIT_XY);
					            upload_image_three.setImageBitmap(bitmap13);
					}

				} else if (resultCode == Activity.RESULT_CANCELED) {
					Log.e("pic cancel", "Selecting picture cancelled");
				}
			} catch (OutOfMemoryError e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				//showAlert("During camera capture : "+e2.toString());
			}catch(Exception e1){
				e1.printStackTrace();
				//showAlert("During camera capture : "+e1.toString());
				
			}
			break;

		case GALLERY_REQUEST_CODE_THREE:
			try {
				if (resultCode == Activity.RESULT_OK) { 
					if(data != null)
					{
                        
						
						  bmp13 =null;
						Uri selectedImage3 = data.getData();
						file3 = new File(getRealPathFromURI(selectedImage3));
						
						 try {
							 Log.i("Gallery code","Gallery13");
							 bmp13 = getImage1(getRealPathFromURI(selectedImage3));
							  // bmp1 =decodeUri1(selectedImage);
							   Log.i("bmp1", "image :"+bmp13);
			                	//
			                } catch (FileNotFoundException e) {
			                    e.printStackTrace();
			                }
						Uri  picture = data.getData();

						 file3 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/gallery_snap3"+System.currentTimeMillis()+".jpeg");
						
						FileOutputStream fOut = new FileOutputStream(file3);
						//Bitmap bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

			            ByteArrayOutputStream stream = new ByteArrayOutputStream();
			            
			            bmp13.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
			            
			            bmp13.compress(Bitmap.CompressFormat.PNG, 100, stream);
			            
			            imageFile3 =file3;
			            AddNewPostDetails.setUpload_three(file3);
                         
			            byte[] byteArray = stream.toByteArray();
			          
						
			           // image.setImageBitmap(bmp);
			            //imageFile =file;
			            
			          //  Utils.profile_pic =file;
			            Log.i("imageFile", "imageFile :"+imageFile3);
			            middlebarone.setVisibility(View.GONE);
			            middlebartwo.setVisibility(View.GONE);
			            upload_image_three.setScaleType(ScaleType.FIT_XY);
			            upload_image_three.setImageBitmap(bmp13);
							
							/*	
								intent = new Intent(TakeSnap.this,MySnap.class );
					            intent.putExtra("Bitmap", byteArray);
					            startActivity(intent);
							*/
			            
			          
					}
					else{

						//Toast.makeText(getApplicationContext(), "data is null", Toast.LENGTH_SHORT).show();
					}


				} else if (resultCode == Activity.RESULT_CANCELED) {
					//Log.e("pic cancel", "Selecting picture cancelled");
				}
			} catch (OutOfMemoryError e1) {
				// TODO Auto-generated catch block
		e1.printStackTrace();
				//showAlert("During camera capture : "+e1.toString());
			}catch(Exception e1){
				e1.printStackTrace();
				//showAlert("During camera capture : "+e1.toString());
			}

			break;
		}
	} catch (Exception e) {
		//Log.e("exception", "Exception in onActivityResult : " + e.getMessage());

	}
}

		
		public String getRealPathFromURI(Uri contentUri) 
		{
		     String[] proj = { MediaStore.Audio.Media.DATA };
		     Cursor cursor = managedQuery(contentUri, proj, null, null, null);
		    // Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null); //Since manageQuery is deprecated
		     int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
		     cursor.moveToFirst();
		     return cursor.getString(column_index);
		}
		public Bitmap getImage1(String path) throws IOException
		{
		    BitmapFactory.Options options = new BitmapFactory.Options();
		    options.inJustDecodeBounds = true;
		    BitmapFactory.decodeFile(path, options);
		    int srcWidth = options.outWidth;
		    int srcHeight = options.outHeight;
		    final int REQUIRED_SIZE = 300;
		    //int[] newWH =  new int[2];
		    //newWH[0] = srcWidth/2;
		    //newWH[1] = (newWH[0]*srcHeight)/srcWidth;
		
		    int inSampleSize = 1;
		    while(srcWidth / 2 >= REQUIRED_SIZE){
		        srcWidth /= 2;
		        srcHeight /= 2;
		        inSampleSize *= 2;
		    }
		
		     options.inJustDecodeBounds = false;
		    options.inDither = false;
		    options.inSampleSize = inSampleSize;
		    options.inScaled = false;
		    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		    Bitmap sampledSrcBitmap = BitmapFactory.decodeFile(path,options);
		    ExifInterface exif = new ExifInterface(path);
		    String s=exif.getAttribute(ExifInterface.TAG_ORIENTATION);
		    System.out.println("Orientation>>>>>>>>>>>>>>>>>>>>"+s);
		    Matrix matrix = new Matrix();
		    float rotation = rotationForImage(PostAd.this, Uri.fromFile(new File(path)));
		    if (rotation != 0f) {
		        matrix.preRotate(rotation);
		    }
		
		    Bitmap pqr=Bitmap.createBitmap(
		            sampledSrcBitmap, 0, 0, sampledSrcBitmap.getWidth(), sampledSrcBitmap.getHeight(), matrix, true);
		
		
		    return pqr;
		}   
		private String saveToInternalSorage(Bitmap bitmapImage){
		    ContextWrapper cw = new ContextWrapper(getApplicationContext());
		     // path to /data/data/yourapp/app_data/imageDir
		    File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
		    // Create imageDir
		    File mypath=new File(directory,"profile.jpg");
		
		    FileOutputStream fos = null;
		    try {           
		
		        fos = new FileOutputStream(mypath);
		
		   // Use the compress method on the BitMap object to write image to the OutputStream
		        bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
		        fos.close();
		       // upload_image_one.setImageBitmap(bitmapImage);
		       imageFile = mypath;
		       file = mypath;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return directory.getAbsolutePath();
		}
		
			public static String encodeTobase64(Bitmap image) {
				Bitmap immagex = image;
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
				byte[] b = baos.toByteArray();
				String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
		
				Log.e("LOOK", imageEncoded);
				return imageEncoded;
			}
		
			public static Bitmap decodeBase64(String input) {
				byte[] decodedByte = Base64.decode(input, 0);
				return BitmapFactory
						.decodeByteArray(decodedByte, 0, decodedByte.length);
			}
			 public static int getImageOrientation(String imagePath){
			     int rotate = 0;
			     try {

			         File imageFile = new File(imagePath);
			         ExifInterface exif = new ExifInterface(
			                 imageFile.getAbsolutePath());
			         int orientation = exif.getAttributeInt(
			                 ExifInterface.TAG_ORIENTATION,
			                 ExifInterface.ORIENTATION_NORMAL);

			         switch (orientation) {
			         case ExifInterface.ORIENTATION_ROTATE_270:
			             rotate = 270;
			             break;
			         case ExifInterface.ORIENTATION_ROTATE_180:
			             rotate = 180;
			             break;
			         case ExifInterface.ORIENTATION_ROTATE_90:
			             rotate = 90;
			             break;
			         }
			     } catch (IOException e) {
			         e.printStackTrace();
			     }
			    return rotate;
			 }

				public  float rotationForImage(Context context, Uri uri) {
				    if (uri.getScheme().equals("content")) {
				        String[] projection = { Images.ImageColumns.ORIENTATION };
				        Cursor c = context.getContentResolver().query(
				                uri, projection, null, null, null);
				        if (c.moveToFirst()) {
				            return c.getInt(0);
				        }
				    } else if (uri.getScheme().equals("file")) {
				        try {
				            ExifInterface exif = new ExifInterface(uri.getPath());
				            int rotation = (int)exifOrientationToDegrees(
				                    exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
				                            ExifInterface.ORIENTATION_NORMAL));
				            return rotation;
				        } catch (IOException e) {
				            e.printStackTrace();
				        }catch (Exception e) {
				            e.printStackTrace();
				         }

				    }
				    return 0f;
				}
				private static float exifOrientationToDegrees(int exifOrientation) {
				    if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
				        return 90;
				    } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
				        return 180;
				    } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
				        return 270;
				    }
				    return 0;
				}
			    private String getRealPathFromURI1(Uri contentURI) {
			        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
			        if (cursor == null) { // Source is Dropbox or other similar local file path
			            return contentURI.getPath();
			        } else { 
			            cursor.moveToFirst(); 
			            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA); 
			            return cursor.getString(idx); 
			        }
			    }
}
