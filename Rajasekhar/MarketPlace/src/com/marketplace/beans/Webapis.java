package com.marketplace.beans;

public class Webapis {

	public static String BaseUrl = "http://devserver.krify.com/marketprice/mobapp/";

	public static String Signup = BaseUrl + "signup.php?";

	public static String login = BaseUrl + "login.php?";

	public static String welcomescreen = BaseUrl + "home.php";

	public static String forgot_password = BaseUrl + "forpwd.php?";

	public static String placed_today = BaseUrl + "today.php";

	public static String individual_product_details = BaseUrl + "products.php?";

	public static String category_list = BaseUrl + "categories.php";

	public static String category_related_items = BaseUrl + "catproduct.php?";
	// http://devserver.krify.com/salesnoffers/mobapp/userdetails.php?user_id=16
	// public static String user_login_details = BaseUrl + "userdetails.php?";

	// http://devserver.krify.com/marketprice/mobapp/users.php?uid=2
	public static String user_login_details = BaseUrl + "users.php?";

	public static String categories_url = BaseUrl + "categories.php";
	public static String currency_list_url = BaseUrl + "currency.php";

	// http://devserver.krify.com/marketprice/mobapp/placead.php?
	public static String post_ad_url = BaseUrl + "placead.php?";

	public static String manage_listings_url = BaseUrl + "userproducts.php?";
	// http://devserver.krify.com/marketprice/mobapp/pro_del.php?pid=31

	public static String delete_post_url = BaseUrl + "pro_del.php?";
	
/*	http://devserver.krify.com/marketprice/mobapp/editpost.php?
		price=12345&description=this%20is%20a%20description%20for%20my%20post&pid=3*/
		
		public static String edit_post_url = BaseUrl + "editpost.php?";

	/*	http://devserver.krify.com/marketprice/mobapp/chnagepassword.php?
			oldpass=absc&newpass=divya&confirmpass=divya&uid=4*/
			
			public static String change_password_url = BaseUrl + "chnagepassword.php?";
			
			public static String update_account_url = BaseUrl + "updateuser.php?";
			
			
			
			
			
		
}
