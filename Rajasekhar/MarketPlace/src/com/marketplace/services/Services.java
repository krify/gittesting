package com.marketplace.services;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.marketplace.beans.AddNewPostDetails;
import com.marketplace.beans.EditPostDetails;
import com.marketplace.beans.LoginDetails;
import com.marketplace.beans.Webapis;


import android.R.string;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Services {
	
	/*
	 * getBitmapFromURLs
	 */
	public static Bitmap getBitmapFromURLs(String src) {
		try {
			URL url = new URL(src.replace(" ", "%20"));
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = null;
			myBitmap = 	BitmapFactory.decodeStream(input);
			
			
			
			
			
			
			
			
			
			
			
			
			Bitmap resizedbitmap1=null;
			
			/*
			 * Bitmap resizedbitmap1 = Bitmap.createBitmap(myBitmap, 0, 0,
			 * myBitmap.getHeight(), myBitmap.getHeight());
			 */
			
			
			/*resizedbitmap1 = Bitmap.createScaledBitmap(myBitmap, 400,
			 280,true);*/
			
			

			/*if (myBitmap.getWidth() >= myBitmap.getHeight()) {

				resizedbitmap1 = Bitmap.createBitmap(myBitmap,
						myBitmap.getWidth() / 2 - myBitmap.getHeight() / 2, 0,
						myBitmap.getHeight(), myBitmap.getHeight());

			} else {

				resizedbitmap1 = Bitmap.createBitmap(myBitmap, 0,
						myBitmap.getHeight() / 2 - myBitmap.getWidth() / 2,
						myBitmap.getWidth(), myBitmap.getWidth());
			}*/

			
			return myBitmap;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getDataFromURL(String url) throws JSONException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		String responseString = null;
		try {
			response = httpclient.execute(new HttpGet(url));
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
			} else {
				// Closes the connection.
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		}
		
		catch (ClientProtocolException e) {
			// TODO Handle problems..
		}
		catch (IOException e) {
			// TODO Handle problems..
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return responseString;

	}
	/*
	 * Signup 
	 */
	public static String getSignUpDetails(String uname,String email,String password,String phone){

        String jsonresponce = null;
        InputStream ist;
        StringBuilder s;
        String wresponse = null;
        try {
        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(Webapis.Signup);

        MultipartEntity reqEntity = new MultipartEntity(
        HttpMultipartMode.BROWSER_COMPATIBLE);
        HttpResponse response = null;
        
        reqEntity.addPart("username",new StringBody(uname));
        reqEntity.addPart("email",new StringBody(email));
        reqEntity.addPart("password",new StringBody(password));
        reqEntity.addPart("phone",new StringBody(phone));
    
        Log.w("username","username :"+uname);
        Log.w("email","email :"+email);
        Log.w("password","password :"+password);
        Log.w("phone","phone :"+phone);
      
        post.setEntity(reqEntity);
        response = client.execute(post);

        if (response.getStatusLine().getStatusCode() != 200) {
        // Log.d("MyApp", "Server encountered an error");
        }

        HttpEntity resEntity = response.getEntity();
        // Log.d("", "Uploading Response : " + response.getStatusLine());

        ist = resEntity.getContent();

        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(

        ist, "UTF-8"));

        String sResponse;
        s = new StringBuilder();

        while ((sResponse = reader.readLine()) != null) {

        s = s.append(sResponse);

        }
        // Log.d("", "Uploading Response : " + response.getStatusLine());
        wresponse = s.toString();
        Log.i("services getSignUpDetails wresponse", "getSignUpDetails is:"+wresponse);
        
        JSONObject obj = new JSONObject(wresponse);

        String status = obj.getString("status");

        if (status.equals("success")) {

        jsonresponce = "success";

        } else {
        jsonresponce = obj.getString("message");
        ;
        }
        // String url = Webapis.sendsnap_url+"sender=" +
        // senderid+"&receiver="+receiverid+"&text="+text+"&udata="+udata;
        // String data = getDataFromURL(url);

        } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IllegalStateException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }

        return wresponse;
		
	}
	
	/*
	 * Login
	 */
	
	public static String getLoginDetails(String uname,String password){

        String jsonresponce = null;
        InputStream ist;
        StringBuilder s;
        String wresponse = null;
        try {
        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(Webapis.login);

        MultipartEntity reqEntity = new MultipartEntity(
        HttpMultipartMode.BROWSER_COMPATIBLE);
        HttpResponse response = null;
        
        reqEntity.addPart("email",new StringBody(uname));
        reqEntity.addPart("password",new StringBody(password));
    
        Log.w("username","email is  :"+uname);
        Log.w("password","password is :"+password);
      
        post.setEntity(reqEntity);
        response = client.execute(post);

        if (response.getStatusLine().getStatusCode() != 200) {
        // Log.d("MyApp", "Server encountered an error");
        }

        HttpEntity resEntity = response.getEntity();
        // Log.d("", "Uploading Response : " + response.getStatusLine());

        ist = resEntity.getContent();

        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(

        ist, "UTF-8"));

        String sResponse;
        s = new StringBuilder();

        while ((sResponse = reader.readLine()) != null) {

        s = s.append(sResponse);

        }
        // Log.d("", "Uploading Response : " + response.getStatusLine());
        wresponse = s.toString();
        Log.i("services getLoginDetails wresponse", "getLoginDetails is:"+wresponse);
        
        JSONObject obj = new JSONObject(wresponse);

        String status = obj.getString("status");

        if (status.equals("success")) {

        jsonresponce = "success";

        } else {
        jsonresponce = obj.getString("message");
        ;
        }
        // String url = Webapis.sendsnap_url+"sender=" +
        // senderid+"&receiver="+receiverid+"&text="+text+"&udata="+udata;
        // String data = getDataFromURL(url);

        } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IllegalStateException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }

        return wresponse;
		
	}
	
	/*
	 * getWelcomeScreenActivityResultDetails
	 */
	public static String getWelcomeScreenActivityResultDetails(){

        String jsonresponce = null;
        InputStream ist;
        StringBuilder s;
        String wresponse = null;
        try {
        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(Webapis.welcomescreen);

        MultipartEntity reqEntity = new MultipartEntity(
        HttpMultipartMode.BROWSER_COMPATIBLE);
        HttpResponse response = null;
        
     //   reqEntity.addPart("email",new StringBody(uname));
     //   reqEntity.addPart("password",new StringBody(password));
    
     //   Log.w("username","email is  :"+uname);
      //  Log.w("password","password is :"+password);
      
        post.setEntity(reqEntity);
        response = client.execute(post);

        if (response.getStatusLine().getStatusCode() != 200) {
        // Log.d("MyApp", "Server encountered an error");
        }

        HttpEntity resEntity = response.getEntity();
        // Log.d("", "Uploading Response : " + response.getStatusLine());

        ist = resEntity.getContent();

        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(

        ist, "UTF-8"));

        String sResponse;
        s = new StringBuilder();

        while ((sResponse = reader.readLine()) != null) {

        s = s.append(sResponse);

        }
        // Log.d("", "Uploading Response : " + response.getStatusLine());
        wresponse = s.toString();
        Log.i("services getWelcomeScreenActivityResultDetails wresponse", "getWelcomeScreenActivityResultDetails is:"+wresponse);
        
        JSONObject obj = new JSONObject(wresponse);

        String status = obj.getString("status");

        if (status.equals("success")) {

        jsonresponce = "success";

        } else {
        jsonresponce = obj.getString("message");
        ;
        }
        // String url = Webapis.sendsnap_url+"sender=" +
        // senderid+"&receiver="+receiverid+"&text="+text+"&udata="+udata;
        // String data = getDataFromURL(url);

        } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IllegalStateException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }

        return wresponse;
		
	}
	
	
	/*
	 * Forgot password
	 */
	public static String getForgotPasswordStatus(String email){
		String check_forgotpassword_result = null ;
		try {
			String verify_forgotpassword_result = Webapis.forgot_password+"email="+email;
			check_forgotpassword_result = getDataFromURL(verify_forgotpassword_result);
			Log.i(check_forgotpassword_result, "check_forgotpassword_result"+check_forgotpassword_result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return check_forgotpassword_result;
		
	}
	
	/*
	 * getBrowseAdsResultResultDetails
	 */
	public static String getBrowseAdsPlacedtodayResultDetails(){

        String jsonresponce = null;
        InputStream ist;
        StringBuilder s;
        String wresponse = null;
        try {
        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(Webapis.placed_today);

        MultipartEntity reqEntity = new MultipartEntity(
        HttpMultipartMode.BROWSER_COMPATIBLE);
        HttpResponse response = null;
        
     //   reqEntity.addPart("email",new StringBody(uname));
     //   reqEntity.addPart("password",new StringBody(password));
    
     //   Log.w("username","email is  :"+uname);
      //  Log.w("password","password is :"+password);
      
        post.setEntity(reqEntity);
        response = client.execute(post);

        if (response.getStatusLine().getStatusCode() != 200) {
        // Log.d("MyApp", "Server encountered an error");
        }

        HttpEntity resEntity = response.getEntity();
        // Log.d("", "Uploading Response : " + response.getStatusLine());

        ist = resEntity.getContent();

        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(

        ist, "UTF-8"));

        String sResponse;
        s = new StringBuilder();

        while ((sResponse = reader.readLine()) != null) {

        s = s.append(sResponse);

        }
        // Log.d("", "Uploading Response : " + response.getStatusLine());
        wresponse = s.toString();
        Log.i("services getBrowseAdsPlacedtodayResultDetails wresponse", "getBrowseAdsPlacedtodayResultDetails is:"+wresponse);
        
        JSONObject obj = new JSONObject(wresponse);

        String status = obj.getString("status");

        if (status.equals("success")) {

        jsonresponce = "success";

        } else {
        jsonresponce = obj.getString("message");
        ;
        }
        // String url = Webapis.sendsnap_url+"sender=" +
        // senderid+"&receiver="+receiverid+"&text="+text+"&udata="+udata;
        // String data = getDataFromURL(url);

        } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IllegalStateException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }

        return wresponse;
		
	}
	

	/*
	 * getBrowseAdsIndividualResultDetails
	 */
	public static String getBrowseAdsIndividualResultDetails(String productid){

        String jsonresponce = null;
        InputStream ist;
        StringBuilder s;
        String wresponse = null;
        try {
        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(Webapis.individual_product_details);

        MultipartEntity reqEntity = new MultipartEntity(
        HttpMultipartMode.BROWSER_COMPATIBLE);
        HttpResponse response = null;
        
       reqEntity.addPart("pid",new StringBody(productid));
     //   reqEntity.addPart("password",new StringBody(password));
    
     //   Log.w("username","email is  :"+uname);
      //  Log.w("password","password is :"+password);
      
        post.setEntity(reqEntity);
        response = client.execute(post);

        if (response.getStatusLine().getStatusCode() != 200) {
        // Log.d("MyApp", "Server encountered an error");
        }

        HttpEntity resEntity = response.getEntity();
        // Log.d("", "Uploading Response : " + response.getStatusLine());

        ist = resEntity.getContent();

        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(

        ist, "UTF-8"));

        String sResponse;
        s = new StringBuilder();

        while ((sResponse = reader.readLine()) != null) {

        s = s.append(sResponse);

        }
        // Log.d("", "Uploading Response : " + response.getStatusLine());
        wresponse = s.toString();
        Log.i("services getBrowseAdsIndividualResultDetails wresponse", "wresponse is:"+wresponse);
        
        JSONObject obj = new JSONObject(wresponse);

        String status = obj.getString("status");

        if (status.equals("success")) {

        jsonresponce = "success";

        } else {
        jsonresponce = obj.getString("message");
        ;
        }
        // String url = Webapis.sendsnap_url+"sender=" +
        // senderid+"&receiver="+receiverid+"&text="+text+"&udata="+udata;
        // String data = getDataFromURL(url);

        } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IllegalStateException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }

        return wresponse;
		
	}
	
	/*
	 * getBrowseAdsIndividualResultDetails
	 */
	public static String getBrowseAdsCategoryResultDetails(){

        String jsonresponce = null;
        InputStream ist;
        StringBuilder s;
        String wresponse = null;
        try {
        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(Webapis.category_list);

        MultipartEntity reqEntity = new MultipartEntity(
        HttpMultipartMode.BROWSER_COMPATIBLE);
        HttpResponse response = null;
        
     //  reqEntity.addPart("pid",new StringBody(productid));
     //   reqEntity.addPart("password",new StringBody(password));
    
     //   Log.w("username","email is  :"+uname);
      //  Log.w("password","password is :"+password);
      
        post.setEntity(reqEntity);
        response = client.execute(post);

        if (response.getStatusLine().getStatusCode() != 200) {
        // Log.d("MyApp", "Server encountered an error");
        }

        HttpEntity resEntity = response.getEntity();
        // Log.d("", "Uploading Response : " + response.getStatusLine());

        ist = resEntity.getContent();

        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(

        ist, "UTF-8"));

        String sResponse;
        s = new StringBuilder();

        while ((sResponse = reader.readLine()) != null) {

        s = s.append(sResponse);

        }
        // Log.d("", "Uploading Response : " + response.getStatusLine());
        wresponse = s.toString();
        Log.i("services getBrowseAdsIndividualResultDetails wresponse", "wresponse is:"+wresponse);
        
        JSONObject obj = new JSONObject(wresponse);

        String status = obj.getString("status");

        if (status.equals("success")) {

        jsonresponce = "success";

        } else {
        jsonresponce = obj.getString("message");
        ;
        }
        // String url = Webapis.sendsnap_url+"sender=" +
        // senderid+"&receiver="+receiverid+"&text="+text+"&udata="+udata;
        // String data = getDataFromURL(url);

        } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IllegalStateException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }

        return wresponse;
		
	}
	
	/*
	 * getCategoryRelatedItemsResultDetails
	 */
	public static String getCategoryRelatedItemsResultDetails(String cid){

        String jsonresponce = null;
        InputStream ist;
        StringBuilder s;
        String wresponse = null;
        try {
        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(Webapis.category_related_items);

        MultipartEntity reqEntity = new MultipartEntity(
        HttpMultipartMode.BROWSER_COMPATIBLE);
        HttpResponse response = null;
        
        reqEntity.addPart("cid",new StringBody(cid));
     //   reqEntity.addPart("password",new StringBody(password));
    
     //   Log.w("username","email is  :"+uname);
      //  Log.w("password","password is :"+password);
      
        post.setEntity(reqEntity);
        response = client.execute(post);

        if (response.getStatusLine().getStatusCode() != 200) {
        // Log.d("MyApp", "Server encountered an error");
        }

        HttpEntity resEntity = response.getEntity();
        // Log.d("", "Uploading Response : " + response.getStatusLine());

        ist = resEntity.getContent();

        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(

        ist, "UTF-8"));

        String sResponse;
        s = new StringBuilder();

        while ((sResponse = reader.readLine()) != null) {

        s = s.append(sResponse);

        }
        // Log.d("", "Uploading Response : " + response.getStatusLine());
        wresponse = s.toString();
        Log.i("services getCategoryRelatedItemsResultDetails wresponse", "getCategoryRelatedItemsResultDetails is:"+wresponse);
        
        JSONObject obj = new JSONObject(wresponse);

        String status = obj.getString("status");

        if (status.equals("success")) {

        jsonresponce = "success";

        } else {
        jsonresponce = obj.getString("message");
        ;
        }
        // String url = Webapis.sendsnap_url+"sender=" +
        // senderid+"&receiver="+receiverid+"&text="+text+"&udata="+udata;
        // String data = getDataFromURL(url);

        } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IllegalStateException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }

        return wresponse;
		
	}
	
	/*
	 * getloginDetailsResultDetails
	 */
	public static String getloginDetailsResultDetails(String uid){

        String jsonresponce = null;
        InputStream ist;
        StringBuilder s;
        String wresponse = null;
        try {
        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(Webapis.user_login_details);

        MultipartEntity reqEntity = new MultipartEntity(
        HttpMultipartMode.BROWSER_COMPATIBLE);
        HttpResponse response = null;
        
        reqEntity.addPart("uid",new StringBody(uid));
     //   reqEntity.addPart("password",new StringBody(password));
    
     //   Log.w("username","email is  :"+uname);
      //  Log.w("password","password is :"+password);
      
        post.setEntity(reqEntity);
        response = client.execute(post);

        if (response.getStatusLine().getStatusCode() != 200) {
        // Log.d("MyApp", "Server encountered an error");
        }

        HttpEntity resEntity = response.getEntity();
        // Log.d("", "Uploading Response : " + response.getStatusLine());

        ist = resEntity.getContent();

        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(

        ist, "UTF-8"));

        String sResponse;
        s = new StringBuilder();

        while ((sResponse = reader.readLine()) != null) {

        s = s.append(sResponse);

        }
        // Log.d("", "Uploading Response : " + response.getStatusLine());
        wresponse = s.toString();
        Log.i("services getloginDetailsResultDetails wresponse", "getloginDetailsResultDetails is:"+wresponse);
        
        JSONObject obj = new JSONObject(wresponse);

        String status = obj.getString("status");

        if (status.equals("success")) {

        jsonresponce = "success";

        } else {
        jsonresponce = obj.getString("message");
        ;
        }
        // String url = Webapis.sendsnap_url+"sender=" +
        // senderid+"&receiver="+receiverid+"&text="+text+"&udata="+udata;
        // String data = getDataFromURL(url);

        } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IllegalStateException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }

        return wresponse;
		
	}
	
	/*
	 * getPostNewAdDetails
	 */
	public static String getPostNewAdDetails(File imageFile, File imageFile2, File imageFile3){

        String jsonresponce = null;
        InputStream ist;
        StringBuilder s;
        String wresponse = null;
        try {
        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(Webapis.post_ad_url);

        MultipartEntity reqEntity = new MultipartEntity(
        HttpMultipartMode.BROWSER_COMPATIBLE);
        HttpResponse response = null;
        
        reqEntity.addPart("title",new StringBody(AddNewPostDetails.getTitle()));
        reqEntity.addPart("description",new StringBody(AddNewPostDetails.getDescription()));
        
        
       
        if(imageFile!=null){
       	 reqEntity.addPart("img1",new FileBody(imageFile));
       	Log.i("img1", "img1 is "+AddNewPostDetails.getUpload_one().toString());
       }
		if(imageFile2!=null){
			reqEntity.addPart("img2",new FileBody(imageFile2));
			  Log.i("img2", "img2"+AddNewPostDetails.getUpload_two().toString());
		        }
		if(imageFile3!=null){
			reqEntity.addPart("img3",new FileBody(imageFile3));
			Log.i("img3", "img3"+AddNewPostDetails.getUpload_three().toString());
		}
        
        
       /* if(!(AddNewPostDetails.getUpload_image_one().isEmpty())){
        	 reqEntity.addPart("img1",new FileBody(AddNewPostDetails.getUpload_one()));
        }
		if(!(AddNewPostDetails.getUpload_image_one().isEmpty())){
			reqEntity.addPart("img2",new FileBody(AddNewPostDetails.getUpload_two()));
		        }
		if(!(AddNewPostDetails.getUpload_image_one().isEmpty())){
			reqEntity.addPart("img3",new FileBody(AddNewPostDetails.getUpload_three()));
		}*/
	   	// reqEntity.addPart("img1",new FileBody(AddNewPostDetails.getUpload_one()));
        //  reqEntity.addPart("img2",new FileBody(AddNewPostDetails.getUpload_two()));
       //  reqEntity.addPart("img3",new FileBody(AddNewPostDetails.getUpload_three()));
        reqEntity.addPart("cat_id",new StringBody(AddNewPostDetails.getCategory_id()));
        reqEntity.addPart("location",new StringBody(AddNewPostDetails.getLocation()));
        reqEntity.addPart("currency",new StringBody(AddNewPostDetails.getCurrency_code_id()));
        reqEntity.addPart("price",new StringBody(AddNewPostDetails.getPrice()));
        reqEntity.addPart("uid",new StringBody(LoginDetails.getLog_userid()));
        
        Log.i("title", "title :"+AddNewPostDetails.getTitle());
        Log.i("description", "description :"+AddNewPostDetails.getDescription());
        Log.i("cat_id", "cat_id :"+AddNewPostDetails.getCategory_id());
        Log.i("location", "location :"+AddNewPostDetails.getLocation());
        Log.i("currency", "currency :"+AddNewPostDetails.getCurrency_code_id());
        Log.i("price", "price :"+AddNewPostDetails.getPrice());
        Log.i("uid", "uid :"+LoginDetails.getLog_userid());
  
      
        post.setEntity(reqEntity);
        response = client.execute(post);

        if (response.getStatusLine().getStatusCode() != 200) {
        // Log.d("MyApp", "Server encountered an error");
        }

        HttpEntity resEntity = response.getEntity();
        // Log.d("", "Uploading Response : " + response.getStatusLine());

        ist = resEntity.getContent();

        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(

        ist, "UTF-8"));

        String sResponse;
        s = new StringBuilder();

        while ((sResponse = reader.readLine()) != null) {

        s = s.append(sResponse);

        }
        // Log.d("", "Uploading Response : " + response.getStatusLine());
        wresponse = s.toString();
        Log.i("services getPostNewAdDetails wresponse", "getPostNewAdDetails is:"+wresponse);
        
        JSONObject obj = new JSONObject(wresponse);

        String status = obj.getString("status");

        if (status.equals("success")) {

        jsonresponce = "success";

        } else {
        jsonresponce = obj.getString("message");
        ;
        }
        // String url = Webapis.sendsnap_url+"sender=" +
        // senderid+"&receiver="+receiverid+"&text="+text+"&udata="+udata;
        // String data = getDataFromURL(url);

        } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IllegalStateException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }

        return wresponse;
		
	}
	/*
	 * getManageListingsResultDetails
	 */
	public static String getManageListingsResultDetails(){

        String jsonresponce = null;
        InputStream ist;
        StringBuilder s;
        String wresponse = null;
        try {
        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(Webapis.manage_listings_url);

        MultipartEntity reqEntity = new MultipartEntity(
        HttpMultipartMode.BROWSER_COMPATIBLE);
        HttpResponse response = null;
        
        reqEntity.addPart("uid",new StringBody(LoginDetails.getLog_userid()));
      
        
        Log.i("uid", "uid :"+LoginDetails.getLog_userid());
       
        
     //   reqEntity.addPart("password",new StringBody(password));
    
     //   Log.w("username","email is  :"+uname);
      //  Log.w("password","password is :"+password);
      
        post.setEntity(reqEntity);
        response = client.execute(post);

        if (response.getStatusLine().getStatusCode() != 200) {
        // Log.d("MyApp", "Server encountered an error");
        }

        HttpEntity resEntity = response.getEntity();
        // Log.d("", "Uploading Response : " + response.getStatusLine());

        ist = resEntity.getContent();

        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(

        ist, "UTF-8"));

        String sResponse;
        s = new StringBuilder();

        while ((sResponse = reader.readLine()) != null) {

        s = s.append(sResponse);

        }
        // Log.d("", "Uploading Response : " + response.getStatusLine());
        wresponse = s.toString();
        Log.i("services getManageListingsResultDetails wresponse", "getManageListingsResultDetails is:"+wresponse);
        
        JSONObject obj = new JSONObject(wresponse);

        String status = obj.getString("status");

        if (status.equals("success")) {

        jsonresponce = "success";

        } else {
        jsonresponce = obj.getString("message");
        ;
        }
        // String url = Webapis.sendsnap_url+"sender=" +
        // senderid+"&receiver="+receiverid+"&text="+text+"&udata="+udata;
        // String data = getDataFromURL(url);

        } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IllegalStateException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }

        return wresponse;
		
	}
	
	/*
	 * getDeletePostResultDetails
	 */
	public static String getDeletePostResultDetails(String id){

        String jsonresponce = null;
        InputStream ist;
        StringBuilder s;
        String wresponse = null;
        try {
        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(Webapis.delete_post_url);

        MultipartEntity reqEntity = new MultipartEntity(
        HttpMultipartMode.BROWSER_COMPATIBLE);
        HttpResponse response = null;
        
        reqEntity.addPart("pid",new StringBody(id));
      
        
        Log.i("pid", "pid :"+id);
       
        
     //   reqEntity.addPart("password",new StringBody(password));
    
     //   Log.w("username","email is  :"+uname);
      //  Log.w("password","password is :"+password);
      
        post.setEntity(reqEntity);
        response = client.execute(post);

        if (response.getStatusLine().getStatusCode() != 200) {
        // Log.d("MyApp", "Server encountered an error");
        }

        HttpEntity resEntity = response.getEntity();
        // Log.d("", "Uploading Response : " + response.getStatusLine());

        ist = resEntity.getContent();

        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(

        ist, "UTF-8"));

        String sResponse;
        s = new StringBuilder();

        while ((sResponse = reader.readLine()) != null) {

        s = s.append(sResponse);

        }
        // Log.d("", "Uploading Response : " + response.getStatusLine());
        wresponse = s.toString();
        Log.i("services getDeletePostResultDetails wresponse", "getDeletePostResultDetails is:"+wresponse);
        
        JSONObject obj = new JSONObject(wresponse);

        String status = obj.getString("status");

        if (status.equals("success")) {

        jsonresponce = "success";

        } else {
        jsonresponce = obj.getString("message");
        ;
        }
        // String url = Webapis.sendsnap_url+"sender=" +
        // senderid+"&receiver="+receiverid+"&text="+text+"&udata="+udata;
        // String data = getDataFromURL(url);

        } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IllegalStateException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }

        return wresponse;
		
	}
	/*
	 * getChangePasswordResults
	 */
	public static String getChangePasswordResults(String oldp,String newp,String confirmp){

        String jsonresponce = null;
        InputStream ist;
        StringBuilder s;
        String wresponse = null;
        try {
        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(Webapis.change_password_url);

        MultipartEntity reqEntity = new MultipartEntity(
        HttpMultipartMode.BROWSER_COMPATIBLE);
        HttpResponse response = null;
        
        reqEntity.addPart("oldpass",new StringBody(oldp));
        reqEntity.addPart("newpass",new StringBody(newp));
        reqEntity.addPart("confirmpass",new StringBody(confirmp));
        reqEntity.addPart("uid",new StringBody(LoginDetails.getLog_userid()));
      
        Log.i("oldpass", "oldpass :"+oldp);
        Log.i("newpass", "newpass :"+newp);
        Log.i("confirmpass", "confirmpass :"+confirmp);
        Log.i("uid", "uid :"+LoginDetails.getLog_userid());
       
        
     //   reqEntity.addPart("password",new StringBody(password));
    
     //   Log.w("username","email is  :"+uname);
      //  Log.w("password","password is :"+password);
      
        post.setEntity(reqEntity);
        response = client.execute(post);

        if (response.getStatusLine().getStatusCode() != 200) {
        // Log.d("MyApp", "Server encountered an error");
        }

        HttpEntity resEntity = response.getEntity();
        // Log.d("", "Uploading Response : " + response.getStatusLine());

        ist = resEntity.getContent();

        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(

        ist, "UTF-8"));

        String sResponse;
        s = new StringBuilder();

        while ((sResponse = reader.readLine()) != null) {

        s = s.append(sResponse);

        }
        // Log.d("", "Uploading Response : " + response.getStatusLine());
        wresponse = s.toString();
        Log.i("services getChangePasswordResults wresponse", "getChangePasswordResults is:"+wresponse);
        
        JSONObject obj = new JSONObject(wresponse);

        String status = obj.getString("status");

        if (status.equals("success")) {

        jsonresponce = "success";

        } else {
        jsonresponce = obj.getString("message");
        ;
        }
        // String url = Webapis.sendsnap_url+"sender=" +
        // senderid+"&receiver="+receiverid+"&text="+text+"&udata="+udata;
        // String data = getDataFromURL(url);

        } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IllegalStateException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }

        return wresponse;
		
	}
	/*
	 * getUpdateAccountResults
	 */
	public static String getUpdateAccountResults(String uname,String ph){

        String jsonresponce = null;
        InputStream ist;
        StringBuilder s;
        String wresponse = null;
        try {
        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(Webapis.update_account_url);

        MultipartEntity reqEntity = new MultipartEntity(
        HttpMultipartMode.BROWSER_COMPATIBLE);
        HttpResponse response = null;
        
        reqEntity.addPart("phone",new StringBody(ph));
        reqEntity.addPart("username",new StringBody(uname));
        reqEntity.addPart("uid",new StringBody(LoginDetails.getLog_userid()));
      
        Log.i("phone", "phone :"+ph);
        Log.i("username", "username :"+uname);
        Log.i("uid", "uid :"+LoginDetails.getLog_userid());
       
        
     //   reqEntity.addPart("password",new StringBody(password));
    
     //   Log.w("username","email is  :"+uname);
      //  Log.w("password","password is :"+password);
      
        post.setEntity(reqEntity);
        response = client.execute(post);

        if (response.getStatusLine().getStatusCode() != 200) {
        // Log.d("MyApp", "Server encountered an error");
        }

        HttpEntity resEntity = response.getEntity();
        // Log.d("", "Uploading Response : " + response.getStatusLine());

        ist = resEntity.getContent();

        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(

        ist, "UTF-8"));

        String sResponse;
        s = new StringBuilder();

        while ((sResponse = reader.readLine()) != null) {

        s = s.append(sResponse);

        }
        // Log.d("", "Uploading Response : " + response.getStatusLine());
        wresponse = s.toString();
        Log.i("services getUpdateAccountResults wresponse", "getUpdateAccountResults is:"+wresponse);
        
        JSONObject obj = new JSONObject(wresponse);

        String status = obj.getString("status");

        if (status.equals("success")) {

        jsonresponce = "success";

        } else {
        jsonresponce = obj.getString("message");
        ;
        }
        // String url = Webapis.sendsnap_url+"sender=" +
        // senderid+"&receiver="+receiverid+"&text="+text+"&udata="+udata;
        // String data = getDataFromURL(url);

        } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IllegalStateException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }

        return wresponse;
		
	}
	
	/*
	 * getEditPostResultDetails
	 */
	public static String getEditPostResultDetails(String id){

        String jsonresponce = null;
        InputStream ist;
        StringBuilder s;
        String wresponse = null;
        try {
        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(Webapis.edit_post_url);

        MultipartEntity reqEntity = new MultipartEntity(
        HttpMultipartMode.BROWSER_COMPATIBLE);
        HttpResponse response = null;
        
        reqEntity.addPart("price",new StringBody(EditPostDetails.getPrice()));
        reqEntity.addPart("description",new StringBody(EditPostDetails.getDescription()));
        reqEntity.addPart("pid",new StringBody(id));
        
      
        
        Log.i("price", "price :"+id);
       
        
     //   reqEntity.addPart("password",new StringBody(password));
    
     //   Log.w("username","email is  :"+uname);
      //  Log.w("password","password is :"+password);
      
        post.setEntity(reqEntity);
        response = client.execute(post);

        if (response.getStatusLine().getStatusCode() != 200) {
        // Log.d("MyApp", "Server encountered an error");
        }

        HttpEntity resEntity = response.getEntity();
        // Log.d("", "Uploading Response : " + response.getStatusLine());

        ist = resEntity.getContent();

        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(

        ist, "UTF-8"));

        String sResponse;
        s = new StringBuilder();

        while ((sResponse = reader.readLine()) != null) {

        s = s.append(sResponse);

        }
        // Log.d("", "Uploading Response : " + response.getStatusLine());
        wresponse = s.toString();
        Log.i("services getEditPostResultDetails wresponse", "getEditPostResultDetails is:"+wresponse);
        
        JSONObject obj = new JSONObject(wresponse);

        String status = obj.getString("status");

        if (status.equals("success")) {

        jsonresponce = "success";

        } else {
        jsonresponce = obj.getString("message");
        ;
        }
        // String url = Webapis.sendsnap_url+"sender=" +
        // senderid+"&receiver="+receiverid+"&text="+text+"&udata="+udata;
        // String data = getDataFromURL(url);

        } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IllegalStateException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }

        return wresponse;
		
	}

}
