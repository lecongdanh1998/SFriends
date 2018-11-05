package vn.edu.poly.sfriends.View.HomePage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import vn.edu.poly.sfriends.Model.MyWebViewClient;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.View.MainActivity;

public class WebViewMain extends AppCompatActivity {
    private WebView webView;
    String url = "https://www.tugo.com.vn/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_main);
        initControl();
        initData();
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
    }

    private void intentView(Class c) {
        Intent intent = new Intent(WebViewMain.this, c);
        intent.putExtra("screen", "Homepage");
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        intentView(MainActivity.class);
    }
}
