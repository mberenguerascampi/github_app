package mbc.com.githubapp.activities;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;
import org.json.JSONObject;

import mbc.com.githubapp.R;
import mbc.com.githubapp.api.GitHubApi;
import mbc.com.githubapp.api.GitHubClient;
import mbc.com.githubapp.api.Parser;
import mbc.com.githubapp.models.User;
import mbc.com.githubapp.utils.ArrayAdapterUser;
import mbc.com.githubapp.utils.GitHubApiListener;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity implements GitHubApiListener{
	@Bean
	GitHubApi gitHubApi;
	
	@ViewById
	EditText searchUserEditText;
	
	@ViewById
	ListView usersListView;
	
	@ViewById
	Button searchUserButton;
	
	private ArrayList<User> users;
	private ArrayAdapterUser adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.gitHubApi.setListener(this);
	}
	
	//Private Methods
	@AfterViews
	void initListView (){
		this.users = new ArrayList<User>();
		this.adapter = new ArrayAdapterUser(this, R.layout.listview_row, this.users);
		this.usersListView.setAdapter(adapter);
		
		//Set on item click listener
		this.usersListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				view.setBackgroundResource(R.color.user_visited);
				User userSelected = users.get(position);
				userSelected.setVisited(true);
				// Start User Detail activity
                Intent intent = new Intent(MainActivity.this, UserDetailActivity_.class);
                intent.putExtra("user",userSelected);
                
                startActivity(intent);
 
                // close this activity
                //finish();
			}
		});
	}
	
	
	/**
	 * Update the listview with the data changes
	 */
	@UiThread
	void updateListView(){
		this.adapter.notifyDataSetChanged();
	}
	
	//Events
	@Click(R.id.searchUserButton)
	void onSearchButtonClick() {
		searchUserButton.setText(getString(R.string.searching_button_text));
		String query = this.searchUserEditText.getText().toString();
		this.gitHubApi.getUser(query);
	}
		

	//GitHubApiListener Methods
	@Override
	public void onProcessSuccessful(JSONObject result) {
		Parser parser = new Parser(this);
		ArrayList<User> usersResult = parser.parseUsers(result);
		this.users.clear();
		this.users.addAll(usersResult);
		//this.updateListView();
		this.usersListView.post(new Runnable() {                  
		    @Override
		    public void run() {
		       searchUserButton.setText(getString(R.string.search_button_text));
		       adapter.notifyDataSetChanged();

		    }
		});
		Log.i("USER PARSER", users.get(0).getLoginName());
	}

	@Override
	public void onProcessError(String error) {
		searchUserButton.setText(getString(R.string.search_button_text));
		Log.i("ERROR", error);
	}
}
