package com.mobsquad.review;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MyPostsActivity extends Activity {

    private List<Post> myPosts = new ArrayList<Post>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_posts);
        populatePostList();
        populateListView();

        Intent intent = getIntent();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_posts, menu);
		return true;
	}
	
    private void populatePostList() {

        myPosts.add(new Post("Ford",R.drawable.ford));
        myPosts.add(new Post("Chevy",R.drawable.chevy));
        myPosts.add(new Post("Buick",R.drawable.buick));
        myPosts.add(new Post("Lamborgini",R.drawable.lamborgini));

    }

    private void populateListView() {

        ArrayAdapter<Post> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.postsListView);
        list.setAdapter(adapter);

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

    private class MyListAdapter extends ArrayAdapter<Post>{

        public MyListAdapter(){
            super(MyPostsActivity.this,R.layout.postlayout,myPosts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            //Making sure we have a view to work with
            View itemView = convertView;

            if(itemView == null){

                itemView = getLayoutInflater().inflate(R.layout.postlayout,parent,false);

            }


            Post currentPost = myPosts.get(position);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.postImageView);
            imageView.setImageResource(currentPost.getIconId());

            TextView makeText = (TextView) itemView.findViewById(R.id.postDescriptionView);
            makeText.setText(currentPost.getDescription());

            return itemView;
        }

    }
	
}
