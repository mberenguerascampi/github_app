package mbc.com.githubapp.utils;

import org.json.JSONObject;

public interface GitHubApiListener {
	public void onProcessSuccessful(JSONObject result);
	public void onProcessError(String error);
}
