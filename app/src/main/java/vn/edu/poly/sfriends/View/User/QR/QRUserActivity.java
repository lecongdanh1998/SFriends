package vn.edu.poly.sfriends.View.User.QR;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.se.omapi.Session;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.util.Util;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.Server.ApiConnect;
import vn.edu.poly.sfriends.View.User.UserActivity;

import static android.Manifest.permission_group.CAMERA;

public class QRUserActivity extends BaseActivity implements View.OnClickListener {
    ImageView img_back_ToobarQRuser, img_QR_user;
    Button btn_save_QRuser, btn_share_QRuser;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    public static final int REQUEST_CAMERA = 1;
    Bitmap bitmap, bitLogo, bitMerged;
    int numberCanhBao = 0;
    String URL_USER = "";
    private ProgressDialog progressDialog;
    String emailUser;
    String idUser, nameUser, avatarUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qruser);
        initControl();
        getJsonInFo();
        initData();
        initOnClick();
    }

    private void getJsonInFo() {
        progressDialog = new ProgressDialog(this);
        URL_USER = ApiConnect.URL_GET_USER_INFOR(HTTP);
        emailUser = dataLoginUser.getString("useremail", "");
        token = dataLoginUser.getString("usertoken", "");
        UserInfo(URL_USER);
    }

    private void initOnClick() {
        img_back_ToobarQRuser.setOnClickListener(this);
        btn_share_QRuser.setOnClickListener(this);
        btn_save_QRuser.setOnClickListener(this);
    }

    private void setContentDialog(String title, String messager) {
        progressDialog.setTitle(title);
        progressDialog.setMessage(messager);
    }


    private void initData() {

    }


    private void UserInfo(String url) {
        setContentDialog("Loading", "Please wait...");
        progressDialog.show();
        RequestQueue requestUser = Volley.newRequestQueue(this);
        StringRequest UserRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    JSONObject jsonObject1 = new JSONObject(response);
                    JSONObject jsonObject = jsonObject1.getJSONObject("data");
                    idUser = jsonObject.getString("id");
                    nameUser = jsonObject.getString("name");
                    emailUser = jsonObject.getString("email");
                    avatarUser = jsonObject.getString("avatar");
                    String text = "https://news.zing.vn";
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    try {
                        BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        Picasso.get()
                                .load(avatarUser)
                                .into(new Target() {
                                    @Override
                                    public void onBitmapLoaded (final Bitmap bitmapLogo, Picasso.LoadedFrom from){
                                        bitMerged = mergeBitmaps(bitmap, bitmapLogo);
                                        img_QR_user.setImageBitmap(bitMerged);
                                        numberCanhBao = 1;
                                    }

                                    @Override
                                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                                    }

                                    @Override
                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                    }
                                });

                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.d("ERROR_USER", error + "");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", emailUser);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", token);
                return headers;
            }
        };
        requestUser.add(UserRequest);

    }


    public static Bitmap mergeBitmaps(Bitmap qrCode, Bitmap myLogo) {
        Bitmap combined = Bitmap.createBitmap(qrCode.getWidth(), qrCode.getHeight(), qrCode.getConfig());
        Canvas canvas = new Canvas(combined);
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        canvas.drawBitmap(qrCode, new Matrix(), null);

        Bitmap resizeLogo = Bitmap.createScaledBitmap(myLogo, canvasWidth / 5, canvasHeight / 5, true);
        int centreX = (canvasWidth - resizeLogo.getWidth()) / 2;
        int centreY = (canvasHeight - resizeLogo.getHeight()) / 2;
        canvas.drawBitmap(resizeLogo, centreX, centreY, null);
        return combined;
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
                Share();
                break;
            case R.id.btn_save_QRuser:
                Save();
                break;
        }
    }


    private void Share() {
        Intent i = new Intent(Intent.ACTION_SEND);
        Uri bmpUri = getLocalBitmapUri(bitMerged, getApplicationContext());
        i.setType("image/*");
        i.putExtra(Intent.EXTRA_STREAM,
                bmpUri);
        startActivity(Intent.createChooser(i, "Share Image"));
//        if (ShareDialog.canShow(ShareLinkContent.class)) {
//            SharePhoto photo = new SharePhoto.Builder()
//                    .setBitmap(bitMerged)
//                    .build();
//            SharePhotoContent content = new SharePhotoContent.Builder()
//                    .addPhoto(photo)
//                    .build();
//            shareDialog.show(content);
//        }
//        List<String> permissionNeeds = Arrays.asList("publish_actions");
//        loginManager = LoginManager.getInstance();
//        loginManager.logInWithPublishPermissions(this, permissionNeeds);
//        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//
//            }
//
//            @Override
//            public void onCancel() {
//                System.out.println("onCancel");
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                System.out.println("onError");
//            }
//        });

        // this part is optional


    }

    static public Uri getLocalBitmapUri(Bitmap bmp, Context context) {
        Uri bmpUri = null;
        try {
            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void Save() {
        if (numberCanhBao == 1) {
//            MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(),
//                    bitmap,
//                    "demo_image",
//                    "demo_image");

            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "");
            values.put(MediaStore.Images.Media.DISPLAY_NAME, "");
            values.put(MediaStore.Images.Media.DESCRIPTION, "");
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
            Uri url = null;
            String stringUrl = null;    /* value to be returned */
            try {
                url = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                if (bitMerged != null) {
                    OutputStream imageOut = getContentResolver().openOutputStream(url);
                    try {
                        bitMerged.compress(Bitmap.CompressFormat.JPEG, 50, imageOut);
                    } finally {
                        imageOut.close();
                    }
                    long id = ContentUris.parseId(url);
                    Bitmap miniThumb = MediaStore.Images.Thumbnails.getThumbnail(getContentResolver(), id, MediaStore.Images.Thumbnails.MINI_KIND, null);
                    storeThumbnail(getContentResolver(), miniThumb, id, 50F, 50F, MediaStore.Images.Thumbnails.MICRO_KIND);
                } else {
                    getContentResolver().delete(url, null, null);
                    url = null;
                }
            } catch (Exception e) {
                if (url != null) {
                    getContentResolver().delete(url, null, null);
                    url = null;
                }
            }
            if (url != null) {
                stringUrl = url.toString();
            }
            Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show();
            numberCanhBao = 2;
        } else if (numberCanhBao == 2) {
            Toast.makeText(this, "Mã này đã được lưu", Toast.LENGTH_SHORT).show();
        }
    }

    private static final Bitmap storeThumbnail(
            ContentResolver cr,
            Bitmap source,
            long id,
            float width,
            float height,
            int kind) {
        Matrix matrix = new Matrix();
        float scaleX = width / source.getWidth();
        float scaleY = height / source.getHeight();
        matrix.setScale(scaleX, scaleY);
        Bitmap thumb = Bitmap.createBitmap(source, 0, 0,
                source.getWidth(),
                source.getHeight(), matrix,
                true
        );
        ContentValues values = new ContentValues(4);
        values.put(MediaStore.Images.Thumbnails.KIND, kind);
        values.put(MediaStore.Images.Thumbnails.IMAGE_ID, (int) id);
        values.put(MediaStore.Images.Thumbnails.HEIGHT, thumb.getHeight());
        values.put(MediaStore.Images.Thumbnails.WIDTH, thumb.getWidth());
        Uri url = cr.insert(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, values);
        try {
            OutputStream thumbOut = cr.openOutputStream(url);
            thumb.compress(Bitmap.CompressFormat.JPEG, 100, thumbOut);
            thumbOut.close();
            return thumb;
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        intentView(UserActivity.class);
        super.onBackPressed();
    }
}
