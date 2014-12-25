package mbc.com.githubapp.api;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

@Rest(rootUrl = "https://api.github.com/", converters = { StringHttpMessageConverter.class })
public interface GitHubClient {
	
	  
	  @Get("search/users?q={query}")
	  String getUsers(String query);
	  
	  @Get("users?since={query}")
	  String getUsersSince(String query);

}
