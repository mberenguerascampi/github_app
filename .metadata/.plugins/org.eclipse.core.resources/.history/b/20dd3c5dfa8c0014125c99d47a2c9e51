package mbc.com.githubapp.activities;

import mbc.com.githubapp.R;
import mbc.com.githubapp.R.id;
import mbc.com.githubapp.R.layout;
import mbc.com.githubapp.R.menu;
import mbc.com.githubapp.models.User;

import org.androidannotations.annotations.AfterExtras;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

@EActivity(R.layout.activity_user_detail)
public class UserDetailActivity extends ActionBarActivity {
	@Extra
	User user;
	
	@ViewById
	TextView score_textview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@AfterViews
	void initView() {
		score_textview.setText(user.getScore());
	}
	
	//EVENTS
	
	@Click(R.id.open_button)
	void openButtonClicked() {
		Uri uri = Uri.parse(user.getHtmlURL());
		startActivity(new Intent(Intent.ACTION_VIEW, uri));
	}
}
