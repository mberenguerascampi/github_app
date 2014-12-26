package mbc.com.githubapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import mbc.com.githubapp.R;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends Activity {

	/**
	 * Create/show the activity and after some time hide the activity and show the main activity 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		new Handler().postDelayed(new Runnable() {
			@Override
            public void run() {
                // Start main activity
                Intent i = new Intent(SplashActivity.this, MainActivity_.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, getResources().getInteger(R.integer.SPLASH_TIME));
	}
}
