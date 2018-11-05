package vn.edu.poly.sfriends.View;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.Util.ValidateForm;
import vn.edu.poly.sfriends.View.SignIn.SignIn;

public class SplashScreen extends BaseActivity {
    private int SPLASH_DISPLAY_LENGTH = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreen.this,SignIn.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    private void checkDataLogin() {
        String useremail = dataLoginUser.getString("useremail", "");
        if (new ValidateForm().validateTextEmpty(useremail) == false){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Intent mainIntent = new Intent(SplashScreen.this,SignIn.class);
            startActivity(mainIntent);
            finish();
        }

    }
}
