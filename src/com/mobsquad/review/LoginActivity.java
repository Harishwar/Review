package com.mobsquad.review;

import com.mobsquad.review.DashboardActivity;
import com.mobsquad.review.InterestListActivity;
import com.mobsquad.review.R.color;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity {
	/**
	 * A dummy authentication store containing known user names and passwords.
	 * TODO: remove after connecting to a real authentication system.
	 */
	private static final String[] DUMMY_CREDENTIALS = new String[] {
			"foo@example.com:hello", "bar@example.com:world" };

	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	//private UserLoginTask mAuthTask = null;

	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	// UI references.
	private EditText mEmailView;
	private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;
	
	Button loginButton;
	
	AlertDialogManager alert = new AlertDialogManager();
	
	SessionManagement session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ccff")));
		//setupActionBar();
		
		session = new SessionManagement(getApplicationContext());

		// Set up the login form.
		mEmail = getIntent().getStringExtra(EXTRA_EMAIL);
		mEmailView = (EditText) findViewById(R.id.email);
		mEmailView.setText(mEmail);

		mPasswordView = (EditText) findViewById(R.id.password);

		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		loginButton = (Button) findViewById(R.id.sign_in_button);
		loginButton.setOnClickListener(new View.OnClickListener() {
            
           @Override
           public void onClick(View arg0) {
               // Get username, password from EditText
               String username = mEmailView.getText().toString();
               String password = mPasswordView.getText().toString();
               session.getUserDetails(); 
               // Check if username, password is filled                
               if(username.trim().length() > 0 && password.trim().length() > 0){
                   // For testing puspose username, password is checked with sample data
                   // username = test
                   // password = test
                   if(username.equals("test") && password.equals("test")){
                        
                       // Creating user login session
                       // For testing i am string name, email as follow
                       // Use user real data
                       session.createLoginSession("test", "test");
                        
                       // Staring MainActivity
                       Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
                       startActivity(i);
                       finish();
                        
                   }else{
                       // username / password doesn't match
                       alert.showAlertDialog(LoginActivity.this, "Login Failed", "Username/Password is incorrect", false);
                   }               
               }else{
                   // user didn't entered username or password
                   // Show alert asking him to enter the details
                   alert.showAlertDialog(LoginActivity.this, "Login Failed", "Please enter username and password", false);
               }
                
           }
       });
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
        case android.R.id.home:
            // Home or UpButton
			//NavUtils.navigateUpFromSameTask(this);
            return true;
        case R.id.action_forgot_password:
            Intent intentForgotPassword = new Intent(this, ForgotPasswordActivity.class);
            startActivity(intentForgotPassword);
            finish();
            //return true;
        default:
            return super.onOptionsItemSelected(item);
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

    public void signUpClick(View v) {
        Intent intentSignUp = new Intent(this, SignUpActivity.class);
        startActivity(intentSignUp);
        finish();
    }
	
}
