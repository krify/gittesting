package com.marketplace.helpers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.marketplace.R;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.SimpleAdapter.ViewBinder;

public class Adapter extends SimpleAdapter{
	
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
	    public Adapter(Activity activity, List<HashMap<String, Object>> data,
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
	       
	        TextView name;
	        TextView message;
	        TextView postid;
	        TextView timeago;
	        ImageView userpic;
	        ImageView propic;
	    }
	    
	    @Override
		 public View getView(int position, View convertView, ViewGroup parent) {

	    	ViewHolder holder = null;
	    	if(convertView==null){
	        	convertView = inflater.inflate(layout, null);
	        	holder = new ViewHolder();
	    
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
	    	
	    	
	    	
	    	   holder.message.setText((CharSequence) map.get(position).get("message"));
		        holder.name.setText((CharSequence) map.get(position).get("name"));
		        holder.postid.setText((CharSequence) map.get(position).get("post_id"));
		        holder.timeago.setText((CharSequence) map.get(position).get("posted_on"));
		        try {
				//	holder.userpic.setImageBitmap(UserDetails.getProfile_picture());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        
	        	
	        	try {
				//	ImageView	img =(ImageView)convertView.findViewById(R.id.myfeeds_lv_postpic);
					if(map.get(position).get("medium_image").toString().equals("image string")){
						 holder.propic.setVisibility(convertView.GONE);
					}else{
				//	 img.setTag(map.get(position).get("medium_image").toString());
					 imageLoader.DisplayImage(map.get(position).get("medium_image").toString().replace(" ","%20"),  holder.propic);
					}
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
