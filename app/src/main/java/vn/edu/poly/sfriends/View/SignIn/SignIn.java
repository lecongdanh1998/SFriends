package vn.edu.poly.sfriends.View.SignIn;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.poly.sfriends.Adapter.LoginAdapterRycer;
import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.Model.LoginContrucstor;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.Server.ApiConnect;
import vn.edu.poly.sfriends.Util.ValidateForm;
import vn.edu.poly.sfriends.View.Intro.Intro;
import vn.edu.poly.sfriends.View.MainActivity;
import vn.edu.poly.sfriends.View.SplashScreen;

public class SignIn extends BaseActivity implements View.OnClickListener {
    TextView txt_login_SignIn, txt_NameUser_SignIn, txt_signinwith_SignIn;
    CircleImageView img_ImagesUser_signup;
    LinearLayout LNL_LOGIN_SIGNIN, LNL_3_SignIn;
    TextInputLayout textInput_username_signIn,textInput_username_signIn123,textInput_username_signIn123456, textInput_password_signIn,textInput_password_signIn123,textInput_password_signIn123456;
    AppCompatEditText edt_user_signIn, edt_password__signIn,edt_user_signIn123,edt_user_signIn123456;
    Button btn_Login_SignUp;
    private ProgressDialog progressDialog;
    int NumberKeyBoard = 1;
    int NumberTrangThai = 1;
    int NumberStatus = 1;
    private RecyclerView mRecyclerView_details;
    private RecyclerView.Adapter mAdapter_details;
    private RecyclerView.LayoutManager mLayoutManager_details;
    ArrayList<LoginContrucstor> arrayList;
    String URL_SIGNIN = "";
    String URL_USER = "";
    private String useremail, userpassword;
    String useremail2;
    View rootView;
    int keyboardHeight;
    RelativeLayout RelativeLayout_text;
    ImageView imgEmail,imgEmail123,imgEmail123456;
    int numBerOne = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initControl();
        initData();
        initOnClick();
        checkDataLogin();
        JsonUserInfo(URL_USER);
    }
    private void initOnClick() {
        rootView = getWindow().getDecorView().getRootView();
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                rootView.getWindowVisibleDisplayFrame(rect);
                int screenHeight = rootView.getHeight();
                keyboardHeight = screenHeight - (rect.bottom - rect.top);
                if(keyboardHeight > screenHeight / 3){
                    if(numBerOne == 1) {
                        KeyBoardShow();
                        numBerOne = 0;
                        Log.d("hihi","1");
                    }
                } else{
                    if(numBerOne == 0) {
                        KeyBoardHide();
                        numBerOne = 1;
                        Log.d("hihi","2");
                    }
                }
            }
        });
        txt_signinwith_SignIn.setOnClickListener(this);
        btn_Login_SignUp.setOnClickListener(this);
    }

    private boolean keyboardShown(View rootView) {
        final int softKeyboardHeight = 100;
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
        int heightDiff = rootView.getBottom() - r.bottom;
        return heightDiff > softKeyboardHeight * dm.density;
    }


    private void setContentDialog(String title, String messager) {
        progressDialog.setTitle(title);
        progressDialog.setMessage(messager);
    }


    private void intentView(Class c) {
        Intent intent = new Intent(SignIn.this, c);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }

    private void initData() {
        progressDialog = new ProgressDialog(this);
        URL_SIGNIN = ApiConnect.URL_SIGNIN(HTTP);
        URL_USER = ApiConnect.URL_GET_USER_INFOR(HTTP);
        arrayList = new ArrayList<>();
        arrayList.add(new LoginContrucstor("https://png.icons8.com/ios/70/B382C7/exit.png"));
        arrayList.add(new LoginContrucstor("https://png.icons8.com/metro/70/2ecc71/qr-code.png"));
        arrayList.add(new LoginContrucstor("https://png.icons8.com/ios/40/EC0890/gift.png"));
        arrayList.add(new LoginContrucstor("https://png.icons8.com/ios/40/EBA4EF/scales-filled.png"));
        arrayList.add(new LoginContrucstor("https://png.icons8.com/ios/70/B382C7/exit.png"));
        arrayList.add(new LoginContrucstor("https://png.icons8.com/metro/70/2ecc71/qr-code.png"));
        arrayList.add(new LoginContrucstor("https://png.icons8.com/ios/40/EC0890/gift.png"));
        arrayList.add(new LoginContrucstor("https://png.icons8.com/ios/40/EBA4EF/scales-filled.png"));
        mRecyclerView_details.setHasFixedSize(true);
        mLayoutManager_details = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView_details.setLayoutManager(mLayoutManager_details);
        mAdapter_details = new LoginAdapterRycer(arrayList, this);
        mRecyclerView_details.setAdapter(mAdapter_details);
        mAdapter_details.notifyDataSetChanged();
    }

    private void initControl() {
        textInput_username_signIn123 = findViewById(R.id.textInput_username_signIn123);
        textInput_username_signIn123456 = findViewById(R.id.textInput_username_signIn123456);
        edt_user_signIn123= findViewById(R.id.edt_user_signIn123);
        edt_user_signIn123456 = findViewById(R.id.edt_user_signIn123456);
        imgEmail123 = findViewById(R.id.imgEmail123);
        imgEmail123456 = findViewById(R.id.imgEmail123456);
        txt_NameUser_SignIn = findViewById(R.id.txt_NameUser_SignIn);
        txt_signinwith_SignIn = findViewById(R.id.txt_signinwith_SignIn);
        img_ImagesUser_signup = (CircleImageView) findViewById(R.id.img_ImagesUser_signup);
        LNL_LOGIN_SIGNIN = findViewById(R.id.LNL_LOGIN_SIGNIN);
        LNL_3_SignIn = findViewById(R.id.LNL_3_SignIn);
        textInput_username_signIn = findViewById(R.id.textInput_username_signIn);
        textInput_password_signIn = findViewById(R.id.textInput_password_signIn);
        textInput_password_signIn123 = findViewById(R.id.textInput_password_signIn123);
        textInput_password_signIn123456 = findViewById(R.id.textInput_password_signIn123456);
        edt_user_signIn = findViewById(R.id.edt_user_signIn);
        edt_password__signIn = findViewById(R.id.edt_password__signIn);
        btn_Login_SignUp = findViewById(R.id.btn_Login_SignUp);
        mRecyclerView_details = (RecyclerView) findViewById(R.id.RecyclerViewSignIn);
        RelativeLayout_text = findViewById(R.id.RelativeLayout_text);
        imgEmail = findViewById(R.id.imgEmail);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Login_SignUp:
                SignIn(URL_SIGNIN);
                break;
            case R.id.txt_signinwith_SignIn:
                LogOut();
                break;

        }
    }

    private void LogOut() {
        editorUser = dataLoginUser.edit();
        editorUser.putString("useremail", "");
        editorUser.putString("userpassword", "");
        editorUser.commit();
        if (NumberStatus == 0) {
            edt_user_signIn.setVisibility(View.VISIBLE);
            edt_user_signIn.startAnimation(AnimationUtils.loadAnimation(
                    SignIn.this, R.anim.lefthide));
            imgEmail.setVisibility(View.VISIBLE);
            imgEmail.startAnimation(AnimationUtils.loadAnimation(
                    SignIn.this, R.anim.lefthide));
            txt_signinwith_SignIn.setVisibility(View.INVISIBLE);
            txt_signinwith_SignIn.startAnimation(AnimationUtils.loadAnimation(
                    SignIn.this, R.anim.right));
            img_ImagesUser_signup.setVisibility(View.INVISIBLE);
            img_ImagesUser_signup.startAnimation(AnimationUtils.loadAnimation(
                    SignIn.this, R.anim.right));
            LNL_LOGIN_SIGNIN.setVisibility(View.INVISIBLE);
            LNL_LOGIN_SIGNIN.startAnimation(AnimationUtils.loadAnimation(
                    SignIn.this, R.anim.left));
            edt_user_signIn123.setVisibility(View.INVISIBLE);
            edt_user_signIn123456.setVisibility(View.INVISIBLE);
            imgEmail123.setVisibility(View.INVISIBLE);
            imgEmail123456.setVisibility(View.INVISIBLE);
            NumberTrangThai = 3;

        }
        if (NumberStatus == 1) {
            imgEmail.setVisibility(View.VISIBLE);
            imgEmail.startAnimation(AnimationUtils.loadAnimation(
                    SignIn.this, R.anim.lefthide));
            edt_user_signIn.setVisibility(View.VISIBLE);
            edt_user_signIn.startAnimation(AnimationUtils.loadAnimation(
                    SignIn.this, R.anim.lefthide));
            txt_signinwith_SignIn.setVisibility(View.INVISIBLE);
            txt_signinwith_SignIn.startAnimation(AnimationUtils.loadAnimation(
                    SignIn.this, R.anim.right));
            edt_user_signIn123.setVisibility(View.INVISIBLE);
            edt_user_signIn123456.setVisibility(View.INVISIBLE);
            imgEmail123.setVisibility(View.INVISIBLE);
            imgEmail123456.setVisibility(View.INVISIBLE);
            NumberTrangThai = 3;

        }
    }

    private void JsonUserInfo(String url) {
        final String token = dataLoginUser.getString("usertoken","");
        final String email = dataLoginUser.getString("useremail","");
        RequestQueue requestUser = Volley.newRequestQueue(this);
        StringRequest UserRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject1 = new JSONObject(response);
                    JSONObject jsonObject = jsonObject1.getJSONObject("data");
                    String idUser = jsonObject.getString("id");
                    String nameUser = jsonObject.getString("name");
                    String emailUser = jsonObject.getString("email");
                    String avatarUser = jsonObject.getString("avatar");
                    Picasso.get()
                            .load(avatarUser)
                            .placeholder(R.drawable.account)
                            .error(R.drawable.account)
                            .into(img_ImagesUser_signup);
                    txt_NameUser_SignIn.setText(nameUser);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR_USER", error + "");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
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

    private void KeyBoardShow() {

        if (NumberKeyBoard == 1) {
            if (NumberTrangThai == 2) {
                imgEmail.setVisibility(View.GONE);
                edt_user_signIn.setVisibility(View.GONE);
                mRecyclerView_details.setVisibility(View.GONE);
                img_ImagesUser_signup.setVisibility(View.INVISIBLE);
                img_ImagesUser_signup.startAnimation(AnimationUtils.loadAnimation(
                        SignIn.this, R.anim.right));
                LNL_3_SignIn.startAnimation(AnimationUtils.loadAnimation(
                        SignIn.this, R.anim.top));
                LNL_LOGIN_SIGNIN.setVisibility(View.INVISIBLE);
                LNL_LOGIN_SIGNIN.startAnimation(AnimationUtils.loadAnimation(
                        SignIn.this, R.anim.left));
                RelativeLayout_text.setVisibility(View.GONE);
            }
            if (NumberTrangThai == 3) {
                imgEmail.setVisibility(View.VISIBLE);
                edt_user_signIn.setVisibility(View.VISIBLE);
                mRecyclerView_details.setVisibility(View.GONE);
                LNL_3_SignIn.startAnimation(AnimationUtils.loadAnimation(
                        SignIn.this, R.anim.top));
                RelativeLayout_text.setVisibility(View.GONE);
            }

            NumberKeyBoard = 0;
            NumberStatus = 1;
        }


    }

    private void KeyBoardHide() {
        if (NumberKeyBoard == 0) {
            if (NumberTrangThai == 2) {
                RelativeLayout_text.setVisibility(View.VISIBLE);
                edt_user_signIn.setVisibility(View.GONE);
                imgEmail.setVisibility(View.GONE);
                mRecyclerView_details.setVisibility(View.VISIBLE);
                LNL_LOGIN_SIGNIN.setVisibility(View.VISIBLE);
                LNL_LOGIN_SIGNIN.startAnimation(AnimationUtils.loadAnimation(
                        SignIn.this, R.anim.lefthide));
                img_ImagesUser_signup.setVisibility(View.VISIBLE);
                img_ImagesUser_signup.startAnimation(AnimationUtils.loadAnimation(
                        SignIn.this, R.anim.righthide));
                LNL_3_SignIn.startAnimation(AnimationUtils.loadAnimation(
                        SignIn.this, R.anim.bottom));
            }
            if (NumberTrangThai == 3) {
                LNL_LOGIN_SIGNIN.setVisibility(View.INVISIBLE);
                img_ImagesUser_signup.setVisibility(View.INVISIBLE);
                RelativeLayout_text.setVisibility(View.VISIBLE);
                edt_user_signIn.setVisibility(View.VISIBLE);
                imgEmail.setVisibility(View.VISIBLE);
                mRecyclerView_details.setVisibility(View.VISIBLE);
                LNL_3_SignIn.startAnimation(AnimationUtils.loadAnimation(
                        SignIn.this, R.anim.bottom));


            }

            NumberKeyBoard = 1;

        }
        NumberStatus = 0;

    }

    private void checkDataLogin() {
        useremail2 = dataLoginUser.getString("useremail", "");
        if (new ValidateForm().validateTextEmpty(useremail2) == false) {
            txt_signinwith_SignIn.setVisibility(View.VISIBLE);
            img_ImagesUser_signup.setVisibility(View.VISIBLE);
            LNL_LOGIN_SIGNIN.setVisibility(View.VISIBLE);
            edt_user_signIn.setVisibility(View.GONE);
            imgEmail.setVisibility(View.GONE);
            edt_user_signIn123.setVisibility(View.GONE);
            edt_user_signIn123456.setVisibility(View.GONE);
            imgEmail123.setVisibility(View.GONE);
            imgEmail123456.setVisibility(View.GONE);
            NumberTrangThai = 2;
        } else {
            txt_signinwith_SignIn.setVisibility(View.INVISIBLE);
            img_ImagesUser_signup.setVisibility(View.INVISIBLE);
            LNL_LOGIN_SIGNIN.setVisibility(View.INVISIBLE);
            edt_user_signIn.setVisibility(View.VISIBLE);
            imgEmail.setVisibility(View.VISIBLE);
            edt_user_signIn123.setVisibility(View.INVISIBLE);
            edt_user_signIn123456.setVisibility(View.INVISIBLE);
            imgEmail123.setVisibility(View.INVISIBLE);
            imgEmail123456.setVisibility(View.INVISIBLE);
            NumberTrangThai = 3;
        }

    }


    private void SignIn(String URL_SIGNIN) {
        setContentDialog("Sign in", "Please wait...");
        useremail2 = dataLoginUser.getString("useremail", "");
        useremail = edt_user_signIn.getText().toString().trim();
        userpassword = edt_password__signIn.getText().toString().trim();
        RequestQueue requestSignIn = Volley.newRequestQueue(this);
        StringRequest signInRequest = new StringRequest(Request.Method.POST, URL_SIGNIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.d("SUCCESS_SIGNIN", response + "");
                try {
                    editorUser = dataLoginUser.edit();
                    JSONObject jsonObject = new JSONObject(response);
                    token = jsonObject.getString("token_type") + " " +
                            jsonObject.getString("access_token");
                    if (new ValidateForm().validateTextEmpty(useremail2) == false) {
                        editorUser.putString("useremail", useremail2);
                        editorUser.putString("userpassword", userpassword);
                        JsonUserInfo(URL_USER);
                    } else {
                        editorUser.putString("useremail", useremail);
                        editorUser.putString("userpassword", userpassword);
                        JsonUserInfo(URL_USER);
                    }
                    editorUser.putString("usertoken", token);
                    editorUser.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(SignIn.this, "Login success!", Toast.LENGTH_SHORT).show();
                editorURL = dataLoginURL.edit();
                editorURL.putString("URLConnect", HTTP);
                editorURL.commit();

                intentView(MainActivity.class);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.d("ERROR_SIGNIN", error + "");
                Toast.makeText(SignIn.this, "Vui lòng nhập đúng email và password \n Hoặc URL bạn không đúng", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                if (new ValidateForm().validateTextEmpty(useremail2) == false) {
                    params.put("username", useremail2);
                } else {
                    params.put("username", useremail);
                }
                params.put("password", userpassword);
                params.put("grant_type", "password");
                params.put("client_id", "2");
                params.put("client_secret", "rOU4FPWpj36XDlWvNZBn1S39BZaxFpGyAEtBHBLH");
                return params;
            }
        };

        int errorCode = 0;


        if (new ValidateForm().validateTextEmpty(useremail2) == false) {


        } else {
            if (new ValidateForm().validateTextEmpty(useremail)) {
                textInput_username_signIn.setHint("Enter your email!");
                textInput_username_signIn.setError("Please enter your email!");
                textInput_username_signIn123.setHint("Enter your email!");
                textInput_username_signIn123.setError("Please enter your email!");
                textInput_username_signIn123456.setHint("Enter your email!");
                textInput_username_signIn123456.setError("Please enter your email!");
                errorCode++;
            }
        }


        if (new ValidateForm().validateTextEmpty(userpassword)) {
            textInput_password_signIn.setHint("Enter your password!");
            textInput_password_signIn.setError("Please enter your password!");
            textInput_password_signIn123.setHint("Enter your password!");
            textInput_password_signIn123.setError("Please enter your password!");
            textInput_password_signIn123456.setHint("Enter your password!");
            textInput_password_signIn123456.setError("Please enter your password!");

            errorCode++;
        }

        if (errorCode == 0) {
            requestSignIn.add(signInRequest);
            progressDialog.show();
        }
//        intentView(MainActivity.class);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignIn.this, Intro.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }
}
