package com.community.local;

public class LocalValue{
	
	private static int UserID;
	private static String url="http://192.168.0.103:8080/";

	public static int getUserID() {
		return UserID;
	}

	public static void setUserID(int userID) {
		UserID = userID;
	}
	public static String getUrl(){
		return url;
	}
	
}
