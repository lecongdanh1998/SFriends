package vn.edu.poly.sfriends.View.User.QR;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.View.User.UserActivity;

import static android.Manifest.permission_group.CAMERA;

public class QRUserActivity extends BaseActivity implements View.OnClickListener {
    ImageView img_back_ToobarQRuser, img_QR_user;
    Button btn_share_QRuser, btn_save_QRuser;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    public static final int REQUEST_CAMERA = 1;
    String images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qruser);
        initControl();
        initData();
        initOnClick();
    }

    private void initOnClick() {
        img_back_ToobarQRuser.setOnClickListener(this);
        btn_share_QRuser.setOnClickListener(this);
        btn_save_QRuser.setOnClickListener(this);
    }

    private void initData() {
        String text = "https://news.zing.vn";
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            images = decodeImage(bitmap);
            img_QR_user.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public String decodeImage(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
        byte[] byteImage = outputStream.toByteArray();
        String encodeImage = Base64.encodeToString(byteImage, Base64.NO_WRAP);
        return encodeImage;
    }


    private void initControl() {
        img_back_ToobarQRuser = findViewById(R.id.img_back_ToobarQRuser);
        img_QR_user = findViewById(R.id.img_QR_user);
        btn_save_QRuser = findViewById(R.id.btn_save_QRuser);
        btn_share_QRuser = findViewById(R.id.btn_share_QRuser);
    }

    private void intentView(Class c) {
        Intent intent = new Intent(QRUserActivity.this, c);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_ToobarQRuser:
                onBackPressed();
                break;
            case R.id.btn_share_QRuser:
                break;
            case R.id.btn_save_QRuser:
                Save();
                break;
        }
    }

    private void Save() {
        try {
            java.net.URL url = new java.net.URL(images);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        intentView(UserActivity.class);
        super.onBackPressed();
    }
}
