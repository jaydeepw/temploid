package nl.changer.temploid;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;

/****
 * Superclass for holding some commonly used variables.
 * Every Activity class in this application should extend from
 * this class
 ****/
public class BaseActivity extends FragmentActivity {
	
	protected LayoutInflater mLayoutInflator;
	
	protected Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstance) {
		overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
		super.onCreate(savedInstance);
		mContext = BaseActivity.this;
		
		ActionBar actionBar = getActionBar();
		
		if(actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);	
		}
		
		mLayoutInflator = getLayoutInflater();
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// Handle action buttons
    	switch (item.getItemId()) {
	        // Respond to the action bar's Up/Home button
	        case android.R.id.home:
	        	finishWithTransition();
	        	return true;
        }
    	
    	return super.onOptionsItemSelected(item);
    }
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finishWithTransition();
	}
	
	private void finishWithTransition() {
		finish();
		overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
	}
}
