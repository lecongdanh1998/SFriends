package vn.edu.poly.sfriends.View;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.Util.ValidateForm;
import vn.edu.poly.sfriends.View.Intro.Intro;
import vn.edu.poly.sfriends.View.SignIn.SignIn;

public class SplashScreen extends BaseActivity {
    private int SPLASH_DISPLAY_LENGTH = 3;
    ImageView imagesLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initControl();
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        imagesLogo.startAnimation(animation);
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();


//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run() {
////                /* Create an Intent that will start the Menu-Activity. */
//                Intent mainIntent = new Intent(SplashScreen.this,SignIn.class);
//                startActivity(mainIntent);
//                finish();
//            }
//        }, SPLASH_DISPLAY_LENGTH);
    }

    private void initControl() {
        imagesLogo = findViewById(R.id.imagesLogo);
    }

    private void checkDataLogin() {
        String useremail = dataLoginUser.getString("useremail", "");
        if (new ValidateForm().validateTextEmpty(useremail) == false) {
            Intent intent = new Intent(this,Intro.class);
            startActivity(intent);
            finish();
        } else {
            Intent mainIntent = new Intent(SplashScreen.this, Intro.class);
            startActivity(mainIntent);
            finish();
        }

    }

    private class LogoLauncher extends Thread {
        public void run() {
            try {
                sleep(1000 * SPLASH_DISPLAY_LENGTH);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent mainIntent = new Intent(SplashScreen.this, Intro.class);
            startActivity(mainIntent);
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
            finish();



        }
    }
}
