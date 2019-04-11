package com.cognizant.app.util;

import java.util.ArrayList;
import java.util.List;

import com.cognizant.app.modal.User;

public class CollectionUtility {
	
	private static List<User> listOfUsersRegistered=new ArrayList<User>();

	public static List<User> getListOfUsersRegistered() {
		return listOfUsersRegistered;
	}

	public static void setListOfUsersRegistered(List<User> listOfUsersRegistered) {
		CollectionUtility.listOfUsersRegistered = listOfUsersRegistered;
	}

}
