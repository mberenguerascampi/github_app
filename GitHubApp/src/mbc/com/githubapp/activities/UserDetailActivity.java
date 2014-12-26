package mbc.com.githubapp.activities;


import mbc.com.githubapp.R;
import mbc.com.githubapp.models.User;
import mbc.com.githubapp.utils.FavoriteUsersManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

@EActivity(R.layout.activity_user_detail)
public class UserDetailActivity extends ActionBarActivity {
	@Extra
	User user;
	
	@ViewById
	ImageButton fav_button;
	
	@ViewById
	TextView id_textview, admin_textview, score_textview;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@AfterViews
	void initView() {
		setTitle(user.getLoginName());
		score_textview.setText(user.getScore());
		id_textview.setText(user.getUserID());
		String adminText = user.isSiteAdmin() ? "YES" : "NO";
		admin_textview.setText(adminText);
		
		//Set fav image 
		FavoriteUsersManager favUserManager = new FavoriteUsersManager(this);
		if (favUserManager.isFavoriteUser(this.user.getUserID())){
			fav_button.setImageResource(R.drawable.ic_favorita_on);
		}
		else{
			fav_button.setImageResource(R.drawable.ic_favorita);
		}
	}
	
	//EVENTS
	
	@Click(R.id.open_button)
	void openButtonClick() {
		Uri uri = Uri.parse(user.getHtmlURL());
		startActivity(new Intent(Intent.ACTION_VIEW, uri));
	}
	
	@Click(R.id.fav_button)
	void favButtonClick() {
		FavoriteUsersManager favUserManager = new FavoriteUsersManager(this);
		if (favUserManager.isFavoriteUser(this.user.getUserID())){
			favUserManager.removeFavoriteUser(this.user.getUserID());
			fav_button.setImageResource(R.drawable.ic_favorita);
		}
		else {
			favUserManager.addFavoriteUser(this.user.getUserID());
			fav_button.setImageResource(R.drawable.ic_favorita_on);
		}
	}
}
