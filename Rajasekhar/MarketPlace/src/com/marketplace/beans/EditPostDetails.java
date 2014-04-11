package com.marketplace.beans;

public class EditPostDetails {

	public static String description,price,delete_pid;

	public static String getDelete_pid() {
		return delete_pid;
	}

	public static void setDelete_pid(String delete_pid) {
		EditPostDetails.delete_pid = delete_pid;
	}

	public static String getDescription() {
		return description;
	}

	public static void setDescription(String description) {
		EditPostDetails.description = description;
	}

	public static String getPrice() {
		return price;
	}

	public static void setPrice(String price) {
		EditPostDetails.price = price;
	}
}
