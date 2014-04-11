/*package com.marketplace.helpers;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.marketplace.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TakeSnap extends Activity{
	
	LinearLayout tab1, tab2,tab3 ,tab4,tab5;
	Button progress,takesnap ;
	
	
	Uri output1;
	File imageFile;
	Bitmap bitmap1;
	File file;
	FileOutputStream fOut;
	 Intent intent;
	 String pathin;
	 
	 private static final int GALLERY_REQUEST_CODE = 0;
		private static final int CAMERA_REQUEST_CODE = 1;
		Bitmap bmp1 =null;
		
		ImageView profile_pic;
		TextView user_name,user_type,user_posts,user_following,user_follower;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.takesnap);
		
		
		tab1 = (LinearLayout)findViewById(R.id.tab1);
		tab2 = (LinearLayout)findViewById(R.id.tab2);
		tab3 = (LinearLayout)findViewById(R.id.tab3);
		tab4 = (LinearLayout)findViewById(R.id.tab4);
		tab5 = (LinearLayout)findViewById(R.id.tab5);
		
		tab3.setBackgroundResource(R.color.select);
		
		progress = (Button)findViewById(R.id.takesnap_progress);
		
		takesnap =(Button)findViewById(R.id.takesnap_takesnap);
		
		progress.setTypeface(Typeface.createFromAsset(getAssets(), "segoepr.ttf"));
		
		takesnap.setTypeface(Typeface.createFromAsset(getAssets(), "segoepr.ttf"));
		
		profile_pic =(ImageView)findViewById(R.id.takesnap_profile_pic);
		user_name =(TextView)findViewById(R.id.takesnap_user_name);
		user_posts=(TextView)findViewById(R.id.takesnap_count_posts);
		user_following=(TextView)findViewById(R.id.takesnap_count_following);
		user_follower=(TextView)findViewById(R.id.takesnap_count_follower);
		
		user_name.setText(UserDetails.getName());
		profile_pic.setImageBitmap(UserDetails.getProfile_picture());
		user_posts.setText(UserDetails.getPosts());
		user_follower.setText(UserDetails.getFollowers());
		user_following.setText(UserDetails.getFollowing());
		
		
	}
	
	public void Following(View v){
		 intent = new Intent(TakeSnap.this ,Follow.class);
		 intent.putExtra("follow", "following");
		 intent.putExtra("userid", UserDetails.getUser_id());
		 startActivity(intent);
}
public void Follower(View v){
intent = new Intent(TakeSnap.this ,Follow.class);
intent.putExtra("follow", "follower");
intent.putExtra("userid",  UserDetails.getUser_id());
startActivity(intent);
}
	
	
	public void Progress(View v){
		deleteDirectory(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GridViewDemo/"));
		intent = new Intent(TakeSnap.this ,Progresspics.class);
		
		startActivity(intent);
		
	}
public void Gallery(View v){
	onBtnPickGallery();
	}
public void Facebook(View v){
	
}
public void Takesnap(View v){
	
	
	
	AlertDialog.Builder alert = new AlertDialog.Builder(TakeSnap.this);
	alert.setMessage("Choose One");
	alert.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			onBtnTakePicture();
		}
	});
	
	alert.setNegativeButton("Gallary", new DialogInterface.OnClickListener() {
		
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
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		
		startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
		
		//startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),  CAMERA_REQUEST_CODE);
		
		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		cameraIntent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 
	   
		pathin =Environment.getExternalStorageDirectory()+"/Takesnap_pro_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
		output1 = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                "Takesnap_pro" + String.valueOf(System.currentTimeMillis()) + ".jpg"));
		
		output1 = Uri.fromFile(new File(pathin));
	    
	                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, output1);
		
	                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
		
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
						bitmap1 = getImage1(	getRealPathFromURI(selectedImageUri));
						
						
                        file = new File(Environment.getExternalStorageDirectory() + "/camera_snap.jpeg");
						
						fOut = new FileOutputStream(file);
						
						
						
			            
						bitmap1.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
	
			            //profile_pic.setImageBitmap(bitmap1);
			            
			            imageFile =file;
			            Utils.profile_pic =file;
			            
			            ByteArrayOutputStream stream = new ByteArrayOutputStream();
			             
						 bitmap1.compress(Bitmap.CompressFormat.PNG, 100, stream);
	                         
				            byte[] byteArray = stream.toByteArray();
			            
			            
			            intent = new Intent(TakeSnap.this,MySnap.class );
			            intent.putExtra("Bitmap", byteArray);
			            startActivity(intent);
						
					}
					else{
						
						//bitmap1 =decodeUri1(output1);
						Log.i("camera", "else");
						//bitmap1 = getImage1(	getRealPathFromURI(output1));
						Log.i("camera", "else"+pathin);
						bitmap1 = getImage1(pathin);
						//
						 file = new File(Environment.getExternalStorageDirectory() + "/camera_snap.jpeg");
							
							fOut = new FileOutputStream(file);

							bitmap1.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
							
							imageFile =file;
							Utils.profile_pic =file;
		
				           // profile_pic.setImageBitmap(bitmap1);
							
							 ByteArrayOutputStream stream = new ByteArrayOutputStream();
					             
							 bitmap1.compress(Bitmap.CompressFormat.PNG, 100, stream);
		                         
					            byte[] byteArray = stream.toByteArray();
				            
				            
				            intent = new Intent(TakeSnap.this,MySnap.class );
				            intent.putExtra("Bitmap", byteArray);
				            startActivity(intent);
						
						
						
						//Toast.makeText(getApplicationContext(), "data is null", Toast.LENGTH_SHORT).show();
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
						Uri selectedImage = data.getData();
						 try {
							 Log.i("Gallary code","Gallery1");
							 bmp1 = getImage1(	getRealPathFromURI(selectedImage));
							  // bmp1 =decodeUri1(selectedImage);
							   Log.i("bmp1", "image :"+bmp1);
			                	//
			                } catch (FileNotFoundException e) {
			                    e.printStackTrace();
			                }
						
						
						
						Uri  picture = data.getData();

					

						

						File file = new File(Environment.getExternalStorageDirectory() + "/gallery_snap.jpeg");
						
						FileOutputStream fOut = new FileOutputStream(file);
						//Bitmap bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

			            ByteArrayOutputStream stream = new ByteArrayOutputStream();
			            
			            bmp1.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
			            
			            bmp1.compress(Bitmap.CompressFormat.PNG, 100, stream);
                         
			            byte[] byteArray = stream.toByteArray();
			          
						
			           // image.setImageBitmap(bmp);
			            //imageFile =file;
			            
			            Utils.profile_pic =file;
			            Log.i("imageFile", "imageFile :"+imageFile);
			           
							
							
								
								intent = new Intent(TakeSnap.this,MySnap.class );
					            intent.putExtra("Bitmap", byteArray);
					            startActivity(intent);
							
			            
			          
					}
					else{

						Toast.makeText(getApplicationContext(), "data is null", Toast.LENGTH_SHORT).show();
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






private Bitmap decodeUri1(Uri selectedImage) throws FileNotFoundException {
    BitmapFactory.Options o = new BitmapFactory.Options();
    o.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(
            getContentResolver().openInputStream(selectedImage), null, o);
    
    //BitmapFactory.dec

    final int REQUIRED_SIZE = 240;

    int width_tmp = o.outWidth, height_tmp = o.outHeight;
    int scale = 1;
    while (true) {
        if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
            break;
        }
        width_tmp /= 2;
        height_tmp /= 2;
        scale *= 2;
    }

    BitmapFactory.Options o2 = new BitmapFactory.Options();
    o2.inSampleSize = scale;
    return BitmapFactory.decodeStream(
            getContentResolver().openInputStream(selectedImage), null, o2);
}	




	
	
	
	public void Tab(View v){
		switch (v.getId()) {
		case R.id.tab1:
			tab1.setBackgroundResource(R.color.select);
        	tab2.setBackgroundResource(R.color.unselect);
        	tab3.setBackgroundResource(R.color.unselect);
        	tab4.setBackgroundResource(R.color.unselect);
        	tab5.setBackgroundResource(R.color.unselect);
        	
        	intent = new Intent(TakeSnap.this, Friends.class);
        	startActivity(intent);
        	
			break;
        case R.id.tab2:
        	tab1.setBackgroundResource(R.color.unselect);
        	tab2.setBackgroundResource(R.color.select);
        	tab3.setBackgroundResource(R.color.unselect);
        	tab4.setBackgroundResource(R.color.unselect);
        	tab5.setBackgroundResource(R.color.unselect);
        	
        	intent = new Intent(TakeSnap.this, PublicPosts.class);
        	startActivity(intent);
			
			break;
       case R.id.tab3:
    	   tab1.setBackgroundResource(R.color.unselect);
       	tab2.setBackgroundResource(R.color.unselect);
       	tab3.setBackgroundResource(R.color.select);
       	tab4.setBackgroundResource(R.color.unselect);
       	tab5.setBackgroundResource(R.color.unselect);
       
	       break;
        case R.id.tab4:
        	tab1.setBackgroundResource(R.color.unselect);
        	tab2.setBackgroundResource(R.color.unselect);
        	tab3.setBackgroundResource(R.color.unselect);
        	tab4.setBackgroundResource(R.color.select);
        	tab5.setBackgroundResource(R.color.unselect);
        	intent = new Intent(TakeSnap.this, Feeds.class);
        	startActivity(intent);
	
	        break;
         case R.id.tab5:
        	 tab1.setBackgroundResource(R.color.unselect);
         	tab2.setBackgroundResource(R.color.unselect);
         	tab3.setBackgroundResource(R.color.unselect);
         	tab4.setBackgroundResource(R.color.unselect);
         	tab5.setBackgroundResource(R.color.select);
	
         	intent = new Intent(TakeSnap.this, MyprofileNav.class);
        	startActivity(intent);
	       break;

		default:
			break;
		}
	}
	
 public void Alert(){
	 Dialog dialog = new Dialog(TakeSnap.this);
	 dialog.setContentView(R.layout.select_picfrom);
	 dialog.show();
 }

 @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	      
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	} 

 public static boolean deleteDirectory(File path) {
	    if( path.exists() ) {
	      File[] files = path.listFiles();
	      if (files == null) {
	          return true;
	      }
	      for(int i=0; i<files.length; i++) {
	         if(files[i].isDirectory()) {
	           deleteDirectory(files[i]);
	         }
	         else {
	           
	           Log.i("file", "delete"+files[i]);
	           files[i].delete();
	         }
	      }
	    }
	    return( path.delete() );
	  }
 
 // rotate image
 
 
//rotate image 
	
	
	public Bitmap getImage1(String path) throws IOException
	{
	    BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(path, options);
	    int srcWidth = options.outWidth;
	    int srcHeight = options.outHeight;
	    final int REQUIRED_SIZE = 180;
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
	    float rotation = rotationForImage(TakeSnap.this, Uri.fromFile(new File(path)));
	    if (rotation != 0f) {
	        matrix.preRotate(rotation);
	    }

	    Bitmap pqr=Bitmap.createBitmap(
	            sampledSrcBitmap, 0, 0, sampledSrcBitmap.getWidth(), sampledSrcBitmap.getHeight(), matrix, true);


	    return pqr;
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
	
	
	// get uri path
	
	public String getRealPathFromURI (Uri contentUri) {
	    String path = null;
	    String[] proj = { MediaStore.MediaColumns.DATA };
	    Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
	    if (cursor.moveToFirst()) {
	       int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
	       path = cursor.getString(column_index);
	    }
	    cursor.close();
	    return path;
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
	
	
	/// image rotation exampple...
	
	public  void rotateimage(){
		try {
	       // File f = new File(imagePath);
	        ExifInterface exif = new ExifInterface(f.getPath());
	        int orientation = exif.getAttributeInt(
	                ExifInterface.TAG_ORIENTATION,
	                ExifInterface.ORIENTATION_NORMAL);

	        int angle = 0;

	        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
	            angle = 90;
	        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
	            angle = 180;
	        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
	            angle = 270;
	        }

	        Matrix mat = new Matrix();
	        mat.postRotate(angle);
	        BitmapFactory.Options options = new BitmapFactory.Options();
	        options.inSampleSize = 2;

	        Bitmap bmp = BitmapFactory.decodeStream(new FileInputStream(f),
	                null, options);
	        bitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
	                bmp.getHeight(), mat, true);
	        ByteArrayOutputStream outstudentstreamOutputStream = new ByteArrayOutputStream();
	        bitmap.compress(Bitmap.CompressFormat.PNG, 100,
	                outstudentstreamOutputStream);
	        imageView.setImageBitmap(bitmap);

	    } catch (IOException e) {
	        Log.w("TAG", "-- Error in setting image");
	    } catch (OutOfMemoryError oom) {
	        Log.w("TAG", "-- OOM Error in setting image");
	    }
	}
}*/