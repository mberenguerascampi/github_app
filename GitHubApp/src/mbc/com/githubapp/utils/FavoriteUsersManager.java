package mbc.com.githubapp.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

@SuppressLint("NewApi") public class FavoriteUsersManager {
	Activity activity;
	
	public FavoriteUsersManager(Activity activity){
		this.activity = activity;
	}
	
	public void addFavoriteUser(String userID){
		SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        
        Set<String> favUsers = sharedPreferences.getStringSet("FAV_USERS", new HashSet<String>());
        /*String favUsers = sharedPreferences.getString("FAV_USERS", "");
        favUsers = favUsers + userID + "|";*/
        favUsers.add(userID);
       
        editor.putStringSet("FAV_USERS", favUsers);
        editor.commit();                  
	}
	
	public void removeFavoriteUser(String userID){
		SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        
        Set<String> favUsers = sharedPreferences.getStringSet("FAV_USERS", new HashSet<String>());
        favUsers.remove(userID);
       
        editor.putStringSet("FAV_USERS", favUsers);
        editor.commit();                  
	}
	
	public Set<String> getFavoritesUsers(){
		SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        
        Set<String> favUsers = sharedPreferences.getStringSet("FAV_USERS", new HashSet<String>());
        return favUsers;             
	}
	
	public Boolean isFavoriteUser(String userID){
		SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        Set<String> favUsers = sharedPreferences.getStringSet("FAV_USERS", new HashSet<String>());
        return favUsers.contains(userID);
	}
}
