package mbc.com.githubapp.api;

import mbc.com.githubapp.R;
import mbc.com.githubapp.utils.GitHubApiListener;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

import com.google.gson.Gson;

import android.util.Log;

@EBean
public class GitHubApi {
	@RestService
    GitHubClient githubClient; //Inject it
	
	private GitHubApiListener listener;
	
	/**
	 * Make a search and get all the result users 
	 * @param query User to search
	 */
	public void getUser(String query) {
		this.getUserByRestApi(query);
	}
	
	@Background
	void getUserByRestApi(String query) {
	     String results = githubClient.getUsers(query);
	     Log.i("Result", results);
	     try {
			JSONObject json = new JSONObject(results);
			listener.onProcessSuccessful(json);
		} catch (JSONException e) {
			e.printStackTrace();
			listener.onProcessError(e.getMessage());
		}
	 }
	
	
	//Getters & Setters

	public GitHubApiListener getListener() {
		return listener;
	}

	public void setListener(GitHubApiListener listener) {
		this.listener = listener;
	}
}
