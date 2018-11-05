package vn.edu.poly.sfriends.Component;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import vn.edu.poly.sfriends.Networking.NetworkStateMonitor;
import vn.edu.poly.sfriends.R;

public class BaseActivity extends AppCompatActivity {
    BroadcastReceiver broadcastReceiver;
    private final int MY_PERMISSIONS_REQUEST_INTERNET = 10;
    View view;
    public Snackbar snackbar;
    public static SharedPreferences dataLoginURL;
    public static SharedPreferences.Editor editorURL;
    public static SharedPreferences dataLoginUser;
    public static SharedPreferences.Editor editorUser;
    public static SharedPreferences dataLoginTab;
    public static SharedPreferences.Editor editorTab;
    public static String URL = "";
    public static String HTTP = "http://demo.vnlead.webstarterz.com";
    public static String token = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataLoginURL  = getSharedPreferences("data_login", MODE_PRIVATE);
        dataLoginUser  = getSharedPreferences("data_User", MODE_PRIVATE);
        dataLoginTab = getSharedPreferences("data_Tab", MODE_PRIVATE);
        view = getWindow().getDecorView().getRootView();
        InterNet();
    }

    private void InterNet() {
        snackbar = Snackbar.make(view, "Not connect internet", Snackbar.LENGTH_INDEFINITE)
                .setAction("Setting", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                    }
                })
                .setActionTextColor(Color.RED);
        View viewSnackBar = snackbar.getView();
        TextView txt_snack = viewSnackBar.findViewById(android.support.design.R.id.snackbar_text);
        TextView txt_snack_action = viewSnackBar.findViewById(android.support.design.R.id.snackbar_action);
        txt_snack.setTypeface(Typeface.createFromAsset(getAssets(),
                "roboto_regular.ttf"));
        txt_snack_action.setTypeface(Typeface.createFromAsset(getAssets(),
                "roboto_regular.ttf"));
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (new NetworkStateMonitor().checkInterNet(context)){
                    snackbar.dismiss();
                } else {
                    snackbar.show();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(broadcastReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        try{
            unregisterReceiver(broadcastReceiver);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void checkInternetPermission(Activity thisActivity) {

        if (ContextCompat.checkSelfPermission(thisActivity,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
                    Manifest.permission.INTERNET)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                ActivityCompat.requestPermissions(thisActivity,
                        new String[]{Manifest.permission.INTERNET},
                        MY_PERMISSIONS_REQUEST_INTERNET);
            }
        } else {
            // Permission has already been granted
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_INTERNET: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    finish();
                    System.exit(0);
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

}
