package com.marketplace.beans;

import java.io.File;

public class AddNewPostDetails {
	
	public static String title,description,location,price,category,currency,category_id,currency_code;
	public static String image_one,image_two,image_three;
	static File upload_one,upload_two,upload_three;
	public static String currency_code_id;
	public static String getCurrency_code_id() {
		return currency_code_id;
	}

	public static void setCurrency_code_id(String currency_code_id) {
		AddNewPostDetails.currency_code_id = currency_code_id;
	}

	public static File getUpload_one() {
		return upload_one;
	}

	public static File getUpload_two() {
		return upload_two;
	}

	public static void setUpload_two(File upload_two) {
		AddNewPostDetails.upload_two = upload_two;
	}

	public static File getUpload_three() {
		return upload_three;
	}

	public static void setUpload_three(File upload_three) {
		AddNewPostDetails.upload_three = upload_three;
	}

	public static void setUpload_one(File upload_one) {
		AddNewPostDetails.upload_one = upload_one;
	}

	public static String getImage_one() {
		return image_one;
	}

	public static void setImage_one(String image_one) {
		AddNewPostDetails.image_one = image_one;
	}

	public static String getImage_two() {
		return image_two;
	}

	public static void setImage_two(String image_two) {
		AddNewPostDetails.image_two = image_two;
	}

	public static String getImage_three() {
		return image_three;
	}

	public static void setImage_three(String image_three) {
		AddNewPostDetails.image_three = image_three;
	}

	public static String getCurrency_code() {
		return currency_code;
	}

	public static void setCurrency_code(String currency_code) {
		AddNewPostDetails.currency_code = currency_code;
	}

	public static String getCategory_id() {
		return category_id;
	}

	public static void setCategory_id(String category_id) {
		AddNewPostDetails.category_id = category_id;
	}

	public static String upload_image_one,upload_image_two,upload_image_three;

	public static String getUpload_image_one() {
		return upload_image_one;
	}

	public static void setUpload_image_one(String upload_image_one) {
		AddNewPostDetails.upload_image_one = upload_image_one;
	}

	public static String getUpload_image_two() {
		return upload_image_two;
	}

	public static void setUpload_image_two(String upload_image_two) {
		AddNewPostDetails.upload_image_two = upload_image_two;
	}

	public static String getUpload_image_three() {
		return upload_image_three;
	}

	public static void setUpload_image_three(String upload_image_three) {
		AddNewPostDetails.upload_image_three = upload_image_three;
	}

	public static String getTitle() {
		return title;
	}

	public static void setTitle(String title) {
		AddNewPostDetails.title = title;
	}

	public static String getDescription() {
		return description;
	}

	public static void setDescription(String description) {
		AddNewPostDetails.description = description;
	}

	public static String getLocation() {
		return location;
	}

	public static void setLocation(String location) {
		AddNewPostDetails.location = location;
	}

	public static String getPrice() {
		return price;
	}

	public static void setPrice(String price) {
		AddNewPostDetails.price = price;
	}

	public static String getCategory() {
		return category;
	}

	public static void setCategory(String category) {
		AddNewPostDetails.category = category;
	}

	public static String getCurrency() {
		return currency;
	}

	public static void setCurrency(String currency) {
		AddNewPostDetails.currency = currency;
	}

}
