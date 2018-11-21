package deepdive.cnm.edu.trips;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookButtonBase;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import java.util.Arrays;

public class Login extends AppCompatActivity {

  private CallbackManager callbackManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    callbackManager = Factory.create();


    final String EMAIL = "email";

    LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
    loginButton.setReadPermissions(Arrays.asList(EMAIL));

    // Callback registration
    loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
      @Override
      public void onSuccess(LoginResult loginResult) {
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
      }

      @Override
      public void onCancel() {
        Toast.makeText(Login.this,
            "Please login to continue.",
            Toast.LENGTH_LONG).show();
      }

      @Override
      public void onError(FacebookException exception) {
        Toast.makeText(Login.this,
            "An Error Occurred",
            Toast.LENGTH_LONG).show();
      }
    });

    AccessToken token;
    token = AccessToken.getCurrentAccessToken();

    if (token != null && !token.isExpired()) {
      Intent intent = new Intent(Login.this, MainActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
    }
  }


  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      callbackManager.onActivityResult(requestCode, resultCode, data);
      super.onActivityResult(requestCode, resultCode, data);
  }

//  private void signInFb(){
//    AccessToken token = AccessToken.getCurrentAccessToken();
//    if (token==null){
//      ().logInWithReadPermissions(Login.this));
//    }else{
//      LoginManager.getInstance().logOut();
//      Toast.makeText(this, Toast.LENGTH_LONG).show();
//
//    }
//  }



}
