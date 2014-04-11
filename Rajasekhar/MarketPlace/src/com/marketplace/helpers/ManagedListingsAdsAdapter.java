package com.marketplace.helpers;

import java.util.HashMap;
import java.util.List;

import com.marketplace.BrowseAdsIndividualPlacedToday;
import com.marketplace.EditPost;
import com.marketplace.ManageListingsTab;
import com.marketplace.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ManagedListingsAdsAdapter extends SimpleAdapter{
	
	 List<HashMap<String, Object>> map;
	    String[] from;
	    int layout;
	    int[] to;
	   // Context context;
	   // ImageDownloder2xsize imagedownloader;
	    Activity activity;
	    LayoutInflater inflater;
		private String cookie;
		private ImageLoader imageLoader;
	    public ManagedListingsAdsAdapter(Activity activity, List<HashMap<String, Object>> data,
	            int resource, String[] from, int[] to) {
	        super(activity, data, resource, from, to);
	        layout = resource;
	        map = data;
	        this.from = from;
	        this.to = to;
	        this.activity = activity;
	        
	        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        imageLoader=new ImageLoader(activity.getApplicationContext());
	    }
	    @Override
	    public int getCount() {
	        return map.size();
	    }
	 
	    @Override
	    public Object getItem(int arg0) {
	        return arg0;
	    }
	 
	    @Override
	    public long getItemId(int arg0) {
	        return arg0;
	    }
	    
	   
	    class ViewHolder {
	       
	        TextView Product_id1;
	        TextView Product_title1;
	        TextView Product_location1;
	        TextView Product_currency1;
	        TextView Product_price1;
	        TextView Product_phone1;
	        ImageView Product_View;
	        ImageView Product_Delete;
	        ImageView Product_Edit;
	        
	        
	        ImageView Product_image1;
	        
	    }
	    
	    @Override
		 public View getView(int position, View convertView, ViewGroup parent) {

	    	ViewHolder holder = null;
	    	if(convertView==null){
	        	convertView = inflater.inflate(layout, null);
	        	holder = new ViewHolder();
	        	
	        	holder.Product_id1 = (TextView) convertView.findViewById(R.id.manage_listings_individual_add_product_id_tv);
	        	holder.Product_title1 = (TextView) convertView.findViewById(R.id.manage_listings_individual_add_item_product_title_tv);
	        	holder.Product_location1 = (TextView) convertView.findViewById(R.id.manage_listings_individual_add_product_location_tv);
	        	holder.Product_currency1 = (TextView) convertView.findViewById(R.id.manage_listings_individual_add_product_currency_tv);
	        	holder.Product_price1 = (TextView) convertView.findViewById(R.id.manage_listings_individual_add_product_price_tv);
	        	holder.Product_image1 = (ImageView) convertView.findViewById(R.id.manage_listings_individual_add_product_image_iv);
	        	holder.Product_phone1 = (TextView) convertView.findViewById(R.id.manage_listings_individual_add_product_phone_tv);
	    
	        	holder.Product_View = (ImageView) convertView.findViewById(R.id.manage_listings_individual_view_iv);
	        	holder.Product_Delete = (ImageView) convertView.findViewById(R.id.manage_listings_delete_iv);
	        	holder.Product_Edit = (ImageView) convertView.findViewById(R.id.manage_listings_edit_iv);
	    	/*holder.timeago = (TextView) convertView.findViewById(R.id.myfeeds_lv_timeago);
            holder.postid = (TextView) convertView.findViewById(R.id.myfeeds_lv_postid);
            holder.message = (TextView) convertView.findViewById(R.id.myfeeds_lv_message);
            holder.name = (TextView) convertView.findViewById(R.id.myfeeds_lv_name);
			 holder.userpic=(ImageView)convertView.findViewById(R.id.myfeeds_lv_profilepic); 
			 
			 holder.propic=(ImageView)convertView.findViewById(R.id.myfeeds_lv_postpic);*/
            convertView.setTag(holder);
        
	    }else {
            /* get the View from the existing Tag */
            holder = (ViewHolder) convertView.getTag();
        }
	 final  TextView Product_id = (TextView)convertView.findViewById(R.id.manage_listings_individual_add_product_id_tv);
	    	ImageView individual_view  = (ImageView) convertView.findViewById(R.id.manage_listings_individual_view_iv);
        	individual_view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					  String pid = Product_id.getText().toString();
					     //Toast.makeText(getApplicationContext(), "productID is :"+pid, Toast.LENGTH_LONG).show();
					  Intent manage_listings_intent = new Intent(activity,BrowseAdsIndividualPlacedToday.class);
					     manage_listings_intent.putExtra("Product_id",pid );
					     manage_listings_intent.putExtra("activity", "manage_listing");
					   activity. startActivity(manage_listings_intent);
				}
			});
        	 final TextView del_Product_id = (TextView)convertView.findViewById(R.id.manage_listings_individual_add_product_id_tv);
 	    	ImageView delete_post  = (ImageView) convertView.findViewById(R.id.manage_listings_delete_iv);
 	    	delete_post.setOnClickListener(new OnClickListener() {
 				
 				@Override
 				public void onClick(View v) {
 					// TODO Auto-generated method stub
 					String id = del_Product_id.getText().toString();
 					Log.i("del_Product_id", "del_Product_id is :"+id);
 					((ManageListingsTab)activity).deletePost(id);
 					 
 				}
 			});
 	    	
 	    	final TextView edit_Product_id = (TextView)convertView.findViewById(R.id.manage_listings_individual_add_product_id_tv);
 	    	ImageView edit_post  = (ImageView) convertView.findViewById(R.id.manage_listings_edit_iv);
 	    	edit_post.setOnClickListener(new OnClickListener() {
 				
 				@Override
 				public void onClick(View v) {
 					// TODO Auto-generated method stub
 					String edit_id = edit_Product_id.getText().toString();
 					((ManageListingsTab)activity).editPost(edit_id);
 					 
 				}
 			});
	      //  View view = convertView;
	    	
	    	
	    	
	    	   /*holder.message.setText((CharSequence) map.get(position).get("message"));
		        holder.name.setText((CharSequence) map.get(position).get("name"));
		        holder.postid.setText((CharSequence) map.get(position).get("post_id"));
		        holder.timeago.setText((CharSequence) map.get(position).get("posted_on"));*/
	    	
	    	holder.Product_id1.setText((CharSequence) map.get(position).get("Product_id"));
	        holder.Product_title1.setText((CharSequence) map.get(position).get("Product_title"));
	        holder.Product_location1.setText((CharSequence) map.get(position).get("Product_location"));
	        holder.Product_currency1.setText((CharSequence) map.get(position).get("Product_currency"));
	        holder.Product_price1.setText((CharSequence) map.get(position).get("Product_price"));
	        holder.Product_phone1.setText((CharSequence) map.get(position).get("Product_phone"));
		      
		        
		        
	        	
	        	try {
					 imageLoader.DisplayImage(map.get(position).get("Product_image11").toString(),  holder.Product_image1);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


	   try{
	 // ImageView iv = (ImageView)convertView.findViewById(R.id.myfeeds_lv_delete)  ;
	   
	//  final TextView tv = (TextView)convertView.findViewById(R.id.myfeeds_lv_postid);
	    
	  /*  iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
			
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setMessage("Do you want to delete the comment");
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					String post_id =	tv.getText().toString();
					
					((MyFeeds)activity).Deletepost(post_id);
				}
			});
			
			builder.setNegativeButton("No", null);
			builder.create();
			builder.show();
			}
		});*/
	    
	 //   ImageView im1 = (ImageView)convertView.findViewById(R.id.myfeeds_lv_postpic);
	   
	  //  final TextView tv1 = (TextView)convertView.findViewById(R.id.myfeeds_lv_postid);
	     /* im1.setOnClickListener(new OnClickListener() {
	   				
	   				@Override
	   				public void onClick(View v) {
	   					// TODO Auto-generated method stub
	   					
	   					String post_id= tv1.getText().toString();
	   					Intent intent = new Intent(activity,   IndiviPostDetails.class);
	   					intent.putExtra("post_id",post_id );
	   					activity.startActivity(intent);
	   					
	   					String post_id= tv1.getText().toString();
	   					Intent intent = new Intent(activity,  Comments.class);
	   					intent.putExtra("post_id",post_id );
	   					activity.startActivity(intent);
	   					
	   				}
	   			});*/
	   		} catch (Exception e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   		}
	       
	        return convertView;
	    }
	

	  


	

	
	
}
