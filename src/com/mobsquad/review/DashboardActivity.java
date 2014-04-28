package com.mobsquad.review;

import java.util.ArrayList;
import java.util.List;

import com.mobsquad.review.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class DashboardActivity extends Activity implements OnQueryTextListener {
	
	TextView mSearchText;
	private List<Post> myPosts = new ArrayList<Post>();
	
	/**
	 * Whether or not the system UI should be auto-hidden after
	 * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	//private static final boolean AUTO_HIDE = true;

	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
	 * user interaction before hiding the system UI.
	 */
	//private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

	/**
	 * If set, will toggle the system UI visibility upon interaction. Otherwise,
	 * will show the system UI visibility upon interaction.
	 */
	//private static final boolean TOGGLE_ON_CLICK = true;

	/**
	 * The flags to pass to {@link SystemUiHider#getInstance}.
	 */
	//private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */
	//private SystemUiHider mSystemUiHider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ccff")));

        mSearchText = new TextView(this);
        mSearchText.setPadding(10, 10, 10, 10);
        mSearchText.setText("Action Bar Usage");
		setContentView(R.layout.activity_dashboard);

        //setContentView(mSearchText);
		
		populatePostList();
        populateListView();
        

	}

    private void populatePostList() {

        myPosts.add(new Post("Ford",R.drawable.ford));
        myPosts.add(new Post("Chevy",R.drawable.chevy));
        myPosts.add(new Post("Buick",R.drawable.buick));
        myPosts.add(new Post("Lamborgini",R.drawable.lamborgini));

    }
    
    public void usernameClick(View v) {
    	Intent intent = new Intent(this, UserProfileActivity.class);
    	startActivity(intent);
    }


    private void populateListView() {

        ArrayAdapter<Post> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.postsListView);
        list.setAdapter(adapter);

    }
	 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.actions, menu);
    	//SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
    	//searchView.setOnQueryTextListener(this);
    	
        MenuItem menuItem = menu.findItem(R.id.action_search);

        MenuItemCompat.setOnActionExpandListener(menuItem, new OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }
        });
        
    	return super.onCreateOptionsMenu(menu);
    }
    
 
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
 
    @Override
    public boolean    onOptionsItemSelected       (MenuItem item) {
        //Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intentSignUp = new Intent(this, MyProfileActivity.class);
        startActivity(intentSignUp);
        finish();
    	return true;
    }
 
    // The following callbacks are called for the SearchView.OnQueryChangeListener
    public boolean onQueryTextChange(String newText) {
        newText = newText.isEmpty() ? "" : "Query so far: " + newText;
        mSearchText.setText(newText);
        mSearchText.setTextColor(Color.GREEN);
        return true;
    }
 
    public boolean      onQueryTextSubmit      (String query) {
        Toast.makeText(this, "Searching for: " + query + "...", Toast.LENGTH_SHORT).show();
        mSearchText.setText("Searching for: " + query + "...");
        mSearchText.setTextColor(Color.RED);
        return true;
    }
    
    private class MyListAdapter extends ArrayAdapter<Post>{

        public MyListAdapter(){
            super(DashboardActivity.this,R.layout.dashboard_listview,myPosts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            //Making sure we have a view to work with
            View itemView = convertView;

            if(itemView == null){

                itemView = getLayoutInflater().inflate(R.layout.dashboard_listview,parent,false);

            }

            Post currentPost = myPosts.get(position);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.listview_review_image);
            imageView.setImageResource(currentPost.getIconId());

            TextView makeText = (TextView) itemView.findViewById(R.id.listview_review_content);
            makeText.setText(currentPost.getDescription());

            return itemView;
        }

    }
    
    public void newReviewButtonClick(View v) {
        Intent intent = new Intent(this, NewReviewActivity.class);
        startActivity(intent);
    }
}
