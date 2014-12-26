package mbc.com.githubapp.api;

import java.util.ArrayList;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;

import mbc.com.githubapp.models.*;
import mbc.com.githubapp.utils.FavoriteUsersManager;

public class Parser {
	private Activity activity;
	
	public Parser() {
		
	}
	
	public Parser(Activity activity) {
		this.activity = activity;
	}
	
	/**
	 * Converts a Json Object to an ArrayList of users
	 * @param json JSON object with all the users
	 * @return ArrayList of User
	 */
	public ArrayList<User> parseUsers(JSONObject json) {
		ArrayList<User> retUsers = new ArrayList<User>();
		
		try {
			JSONArray jsonUsers = json.getJSONArray("items");
			
			for (int i = 0; i < jsonUsers.length(); ++i){
				JSONObject jsonUser = jsonUsers.getJSONObject(i);
				retUsers.add(this.parseUser(jsonUser));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return retUsers;
	}
	
	/**
	 * Converts a Json Object to a User Object
	 * @param json JSON Object with a user
	 * @return User Object
	 */
	public User parseUser(JSONObject json) {
		User user = new User();
		
		try {
			
			user.setLoginName(json.getString("login"));
			user.setUserID(json.getString("id"));
			user.setAvatarURL(json.getString("avatar_url"));
			user.setGravatarID(json.getString("gravatar_id"));
			user.setHtmlURL(json.getString("html_url"));
			user.setSiteAdmin(json.getBoolean("site_admin"));
			user.setScore(String.format("%.2f", json.getDouble("score")));
			user.setVisited(false);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	private Boolean isFavoriteUser(String userID){
		FavoriteUsersManager favUserManager = new FavoriteUsersManager(this.activity);
		return favUserManager.isFavoriteUser(userID);
	}
}
