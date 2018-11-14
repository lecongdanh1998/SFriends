package vn.edu.poly.sfriends.View.HomePage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import vn.edu.poly.sfriends.Model.MyWebViewClient;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.View.MainActivity;
import vn.edu.poly.sfriends.View.SignIn.SignIn;

public class WebViewMain extends AppCompatActivity implements View.OnClickListener {
    private WebView webView;
    String url = "https://www.tugo.com.vn/";
    ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_web_view_main);
        initControl();
        initData();
        initOnClick();
    }

    private void initOnClick() {
        img_back.setOnClickListener(this);
    }

    private void initData() {
        webView.setWebViewClient(new MyWebViewClient(url));
        goUrl();
    }

    private void goUrl()  {
        if(url.isEmpty())  {
            Toast.makeText(this,"Please enter url",Toast.LENGTH_SHORT).show();
            return;
        }
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }

    private void initControl() {
        webView =(WebView)findViewById(R.id.webView);
        img_back = findViewById(R.id.img_back_ToobarWebview);
    }

    private void intentView(Class c) {
        Intent intent = new Intent(WebViewMain.this, c);
        intent.putExtra("screen", "Homepage");
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public void onBackPressed() {
        intentView(MainActivity.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_ToobarWebview:
                onBackPressed();
                break;
        }
    }

}
