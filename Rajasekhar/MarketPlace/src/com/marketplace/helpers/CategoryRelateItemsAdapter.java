package com.marketplace.helpers;


import java.util.HashMap;
import java.util.List;
import com.marketplace.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class CategoryRelateItemsAdapter extends SimpleAdapter{
	
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
	    public CategoryRelateItemsAdapter(Activity activity, List<HashMap<String, Object>> data,
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
	       
	        TextView Product_id22;
	        TextView Product_title22;
	        TextView Product_location22;
	        TextView Product_currency22;
	        TextView Product_price22;
	        TextView Product_phone22;
	        
	        ImageView Product_image22;
	        
	    }
	    
	    @Override
		 public View getView(int position, View convertView, ViewGroup parent) {

	    	ViewHolder holder = null;
	    	if(convertView==null){
	        	convertView = inflater.inflate(layout, null);
	        	holder = new ViewHolder();
	        	
	        	holder.Product_id22 = (TextView) convertView.findViewById(R.id.category_related_individual_add_product_id_tv);
	        	holder.Product_title22 = (TextView) convertView.findViewById(R.id.category_related_individual_add_item_product_title_tv);
	        	holder.Product_location22 = (TextView) convertView.findViewById(R.id.category_related_individual_add_product_location_tv);
	        	holder.Product_currency22 = (TextView) convertView.findViewById(R.id.category_related_individual_add_product_currency_tv);
	        	holder.Product_price22 = (TextView) convertView.findViewById(R.id.category_related_individual_add_product_price_tv);
	        	holder.Product_image22 = (ImageView) convertView.findViewById(R.id.category_related_individual_add_product_image_iv);
	        	holder.Product_phone22 = (TextView) convertView.findViewById(R.id.category_related_individual_add_product_phone_tv);
	    
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
        	
	      //  View view = convertView;
	    	
	    	
	    	
	    	   /*holder.message.setText((CharSequence) map.get(position).get("message"));
		        holder.name.setText((CharSequence) map.get(position).get("name"));
		        holder.postid.setText((CharSequence) map.get(position).get("post_id"));
		        holder.timeago.setText((CharSequence) map.get(position).get("posted_on"));*/
	    	
	    	holder.Product_id22.setText((CharSequence) map.get(position).get("product_id2"));
	        holder.Product_title22.setText((CharSequence) map.get(position).get("product_title2"));
	        holder.Product_location22.setText((CharSequence) map.get(position).get("product_location2"));
	        holder.Product_currency22.setText((String) map.get(position).get("product_currency2"));
	        holder.Product_price22.setText((CharSequence) map.get(position).get("product_price2"));
	        holder.Product_phone22.setText((CharSequence) map.get(position).get("product_phone2"));
		      
		        
		        
	        	
	        	try {
					 imageLoader.DisplayImage(map.get(position).get("product_image2").toString().replace(" ","%20"),  holder.Product_image22);
					
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
