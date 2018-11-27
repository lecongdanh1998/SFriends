package vn.edu.poly.sfriends.View.User;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
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

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import vn.edu.poly.sfriends.Adapter.AdapterUserIssuedBy;
import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.Model.UserIssuedByContructor;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.Server.ApiConnect;
import vn.edu.poly.sfriends.View.MainActivity;
import vn.edu.poly.sfriends.View.User.Medal.MemberActivity;
import vn.edu.poly.sfriends.View.User.QR.QRUserActivity;

public class UserActivity extends BaseActivity implements View.OnClickListener {
    Toolbar toolbar;
    LinearLayout LNL_fullname, LNL_gender_user, LNL_chagepassword_user, LNL_birthday_user,
            LNL_phone_user, LNL_email_user, LNL_IDnumber_user, LNL_address_user;
    CollapsingToolbarLayout collapsingToolbar;
    int numberWard = 0;
    int numberDistrict = 0;
    int numberCity = 0;
    int MAX_CHAR = 8;
    ImageView img_QRuser, img_back_postdetail,img_avatar_user,img_logo_user,img_medal_postdetail;
    String URL_USER = "";
    private ProgressDialog progressDialog;
    String emailUser;
    String idUser, nameUser, avatarUser;
    TextView txt_title_user, txt_username_User, txt_email_User;
    private int PIC_CROP = 3;
    private Uri picUri;
    private int CAMERA_REQUEST = 1;
    private int CAMERA_REQUEST_MAX = 1999;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private static final int PERMISSION_REQUEST_CAMERA = 0;
    int NumberCamera = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initControl();
        initData();
        initOnClick();
    }

    private void initOnClick() {
        img_logo_user.setOnClickListener(this);
        LNL_fullname.setOnClickListener(this);
        LNL_gender_user.setOnClickListener(this);
        LNL_chagepassword_user.setOnClickListener(this);
        LNL_birthday_user.setOnClickListener(this);
        LNL_phone_user.setOnClickListener(this);
        LNL_email_user.setOnClickListener(this);
        LNL_IDnumber_user.setOnClickListener(this);
        LNL_address_user.setOnClickListener(this);
        img_QRuser.setOnClickListener(this);
        img_back_postdetail.setOnClickListener(this);
        img_medal_postdetail.setOnClickListener(this);
        img_avatar_user.setOnClickListener(this);
    }

    private void initControl() {
        img_medal_postdetail = findViewById(R.id.img_medal_postdetail);
        img_logo_user = findViewById(R.id.img_logo_user);
        img_avatar_user = findViewById(R.id.img_avatar_user);
        img_back_postdetail = findViewById(R.id.img_back_postdetail);
        img_QRuser = findViewById(R.id.img_qr_user);
        LNL_fullname = findViewById(R.id.LNL_fullname_User);
        LNL_gender_user = findViewById(R.id.LNL_gender_user);
        LNL_chagepassword_user = findViewById(R.id.LNL_chagepassword_user);
        LNL_birthday_user = findViewById(R.id.LNL_birthday_user);
        LNL_phone_user = findViewById(R.id.LNL_phone_user);
        txt_username_User = findViewById(R.id.txt_username_User);
        txt_email_User = findViewById(R.id.txt_email_User);
        LNL_email_user = findViewById(R.id.LNL_email_user);
        LNL_IDnumber_user = findViewById(R.id.LNL_IDnumber_user);
        LNL_address_user = findViewById(R.id.LNL_address_user);
        toolbar = findViewById(R.id.toolbar);
        collapsingToolbar = findViewById(R.id.collapsingToolbar);
        txt_title_user = findViewById(R.id.txt_title_user);

    }

    private void initData() {
        progressDialog = new ProgressDialog(this);
        URL_USER = ApiConnect.URL_GET_USER_INFOR(HTTP);
        emailUser = dataLoginUser.getString("useremail", "");
        token = dataLoginUser.getString("usertoken", "");
        UserInfo(URL_USER);
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
                    Picasso.get()
                            .load(avatarUser)
                            .into(img_avatar_user);
                    txt_title_user.setText(nameUser);
                    txt_username_User.setText(nameUser);
                    txt_email_User.setText(emailUser);
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


    private void initDiagLogFullName() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.diaglog_fullname_user);
        dialog.setTitle(getResources().getString(R.string.txt_Fullname));
        dialog.show();
    }


    private void initDiagLogPhone() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.diaglog_phone_user);
        dialog.setTitle(getResources().getString(R.string.txt_phone));
        dialog.show();
    }

    private void initDiagLogEmail() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.diaglog_email_user);
        dialog.setTitle(getResources().getString(R.string.txt_emailUser));
        final EditText edt_user_email = dialog.findViewById(R.id.edt_user_email);
        final Button btn_finish_user = dialog.findViewById(R.id.btn_finish_user);
        edt_user_email.setText(emailUser);
        if(edt_user_email.getText().toString().equals("")){
            btn_finish_user.setEnabled(false);
            btn_finish_user.setAlpha((float) 0.3);
        }else {
            if(edt_user_email.getText().toString().equals(emailUser)){
                btn_finish_user.setEnabled(false);
                btn_finish_user.setAlpha((float) 0.3);
            }else {
                btn_finish_user.setEnabled(true);
                btn_finish_user.setAlpha(1);
            }
        }
        edt_user_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(edt_user_email.getText().toString().equals("")){
                        btn_finish_user.setEnabled(false);
                        btn_finish_user.setAlpha((float) 0.3);
                    }else {
                        if(edt_user_email.getText().toString().equals(emailUser)){
                            btn_finish_user.setEnabled(false);
                            btn_finish_user.setAlpha((float) 0.3);
                        }else {
                            btn_finish_user.setEnabled(true);
                            btn_finish_user.setAlpha(1);
                        }
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dialog.show();
    }

    private void initDiagLogGender() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.diaglog_gender_user);
        dialog.setTitle(getResources().getString(R.string.txt_gender));
        dialog.show();
    }

    private void initDiagLogPasswordRandom() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.diaglog_password_user);
        dialog.setTitle(getResources().getString(R.string.txt_Changepassworduser));
        TextView txt_self = dialog.findViewById(R.id.txt_self_user);
        TextView txt_random = dialog.findViewById(R.id.txt_random_user);
        txt_self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                final Dialog dialog1 = new Dialog(UserActivity.this);
                dialog1.setContentView(R.layout.diaglog_seft_user);
                dialog1.setTitle(getResources().getString(R.string.txt_selfchange));
                dialog1.show();
            }
        });
        txt_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                final Dialog dialog2 = new Dialog(UserActivity.this);
                dialog2.setContentView(R.layout.dialogpasswordrandom);
                dialog2.setTitle(getResources().getString(R.string.txt_chooserandompassword));
                final TextView txt_PasswordRandom = (TextView) dialog2.findViewById(R.id.ext_PasswordRandom);
                final CheckBox checkBoxSymbols = (CheckBox) dialog2.findViewById(R.id.checkbox_Symbols);
                final CheckBox checkBoxNumbers = (CheckBox) dialog2.findViewById(R.id.checkbox_Numbers);
                final CheckBox checkBoxLowercase = (CheckBox) dialog2.findViewById(R.id.checkbox_Lowercase);
                final CheckBox checkBoxAmbiguous = (CheckBox) dialog2.findViewById(R.id.checkbox_Ambiguous);
                final CheckBox checkBoxUppercase = (CheckBox) dialog2.findViewById(R.id.checkbox_Uppercase);
                final RelativeLayout RLT_passwordGenare = (RelativeLayout) dialog2.findViewById(R.id.RLT_passwordGenare);
                final CheckBox check_save_password = (CheckBox) dialog2.findViewById(R.id.check_save_password);
                Button btn_save_passwordRandom = (Button) dialog2.findViewById(R.id.btn_save_passwordRandom);
                TextView txt_advance = (TextView) dialog2.findViewById(R.id.txt_advance);
                txt_advance.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RLT_passwordGenare.setVisibility(View.VISIBLE);
                    }
                });
                SeekBar seekBar = (SeekBar) dialog2.findViewById(R.id.SB_length_random);
                checkBoxSymbols.setChecked(true);
                try {
                    String allowedCharacters = "";
                    if (checkBoxUppercase.isChecked()) {
                        allowedCharacters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                    }
                    if (checkBoxLowercase.isChecked()) {
                        allowedCharacters += "abcdefghijklmnopqrstuvwxyz";
                    }
                    if (checkBoxNumbers.isChecked()) {
                        allowedCharacters += "0123456789";
                    }
                    if (checkBoxSymbols.isChecked()) {
                        allowedCharacters += "!@#$%^&*()_-+=<>?/{}~|";
                    }
                    if (checkBoxAmbiguous.isChecked()) {
                        allowedCharacters += "{[',;:.<\"";
                    }
                    final Random random = new Random();
                    final StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < MAX_CHAR; ++i) {
                        sb.append(allowedCharacters.charAt(random.nextInt(allowedCharacters.length())));
                    }
                    txt_PasswordRandom.setText(sb);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                seekBar.setProgress(MAX_CHAR);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        Log.d("Length: ", "" + progress);
                        MAX_CHAR = seekBar.getProgress();
                        Log.d("MAX_CHAR ", "" + MAX_CHAR);
                        if (progress >= 8) {
                            try {
                                String allowedCharacters = "";
                                if (checkBoxUppercase.isChecked()) {
                                    allowedCharacters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                                }
                                if (checkBoxLowercase.isChecked()) {
                                    allowedCharacters += "abcdefghijklmnopqrstuvwxyz";
                                }
                                if (checkBoxNumbers.isChecked()) {
                                    allowedCharacters += "0123456789";
                                }
                                if (checkBoxSymbols.isChecked()) {
                                    allowedCharacters += "!@#$%^&*()_-+=<>?/{}~|";
                                }
                                if (checkBoxAmbiguous.isChecked()) {
                                    allowedCharacters += "{[',;:.<\"";
                                }
                                final Random random = new Random();
                                final StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < MAX_CHAR; ++i) {
                                    sb.append(allowedCharacters.charAt(random.nextInt(allowedCharacters.length())));
                                }
                                txt_PasswordRandom.setText(sb);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        if (seekBar.getProgress() == 0)
                            MAX_CHAR = 8;
                        seekBar.setProgress(MAX_CHAR);
                    }
                });

                checkBoxUppercase.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            String allowedCharacters = "";
                            if (checkBoxUppercase.isChecked()) {
                                allowedCharacters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                            }
                            if (checkBoxLowercase.isChecked()) {
                                allowedCharacters += "abcdefghijklmnopqrstuvwxyz";
                            }
                            if (checkBoxNumbers.isChecked()) {
                                allowedCharacters += "0123456789";
                            }
                            if (checkBoxSymbols.isChecked()) {
                                allowedCharacters += "!@#$%^&*()_-+=<>?/{}~|";
                            }
                            if (checkBoxAmbiguous.isChecked()) {
                                allowedCharacters += "{[',;:.<\"";
                            }
                            final Random random = new Random();
                            final StringBuilder sb = new StringBuilder();

                            for (int i = 0; i < MAX_CHAR; ++i) {
                                sb.append(allowedCharacters.charAt(random.nextInt(allowedCharacters.length())));
                            }
                            txt_PasswordRandom.setText(sb);
                        } else {
                            try {
                                String allowedCharacters = "";
                                if (checkBoxUppercase.isChecked()) {
                                    allowedCharacters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                                }
                                if (checkBoxLowercase.isChecked()) {
                                    allowedCharacters += "abcdefghijklmnopqrstuvwxyz";
                                }
                                if (checkBoxNumbers.isChecked()) {
                                    allowedCharacters += "0123456789";
                                }
                                if (checkBoxSymbols.isChecked()) {
                                    allowedCharacters += "!@#$%^&*()_-+=<>?/{}~|";
                                }
                                if (checkBoxAmbiguous.isChecked()) {
                                    allowedCharacters += "{[',;:.<\"";
                                }
                                final Random random = new Random();
                                final StringBuilder sb = new StringBuilder();

                                for (int i = 0; i < MAX_CHAR; ++i) {
                                    sb.append(allowedCharacters.charAt(random.nextInt(allowedCharacters.length())));
                                }
                                txt_PasswordRandom.setText(sb);
                            } catch (Exception e) {
                                txt_PasswordRandom.setText("Your password");
                            }
                        }
                    }
                });
                checkBoxLowercase.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            String allowedCharacters = "";
                            if (checkBoxUppercase.isChecked()) {
                                allowedCharacters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                            }
                            if (checkBoxLowercase.isChecked()) {
                                allowedCharacters += "abcdefghijklmnopqrstuvwxyz";
                            }
                            if (checkBoxNumbers.isChecked()) {
                                allowedCharacters += "0123456789";
                            }
                            if (checkBoxSymbols.isChecked()) {
                                allowedCharacters += "!@#$%^&*()_-+=<>?/{}~|";
                            }
                            if (checkBoxAmbiguous.isChecked()) {
                                allowedCharacters += "{[',;:.<\"";
                            }
                            final Random random = new Random();
                            final StringBuilder sb = new StringBuilder();

                            for (int i = 0; i < MAX_CHAR; ++i) {
                                sb.append(allowedCharacters.charAt(random.nextInt(allowedCharacters.length())));
                            }
                            txt_PasswordRandom.setText(sb);
                        } else {
                            try {
                                String allowedCharacters = "";
                                if (checkBoxUppercase.isChecked()) {
                                    allowedCharacters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                                }
                                if (checkBoxLowercase.isChecked()) {
                                    allowedCharacters += "abcdefghijklmnopqrstuvwxyz";
                                }
                                if (checkBoxNumbers.isChecked()) {
                                    allowedCharacters += "0123456789";
                                }
                                if (checkBoxSymbols.isChecked()) {
                                    allowedCharacters += "!@#$%^&*()_-+=<>?/{}~|";
                                }
                                if (checkBoxAmbiguous.isChecked()) {
                                    allowedCharacters += "{[',;:.<\"";
                                }
                                final Random random = new Random();
                                final StringBuilder sb = new StringBuilder();

                                for (int i = 0; i < MAX_CHAR; ++i) {
                                    sb.append(allowedCharacters.charAt(random.nextInt(allowedCharacters.length())));
                                }
                                txt_PasswordRandom.setText(sb);
                            } catch (Exception e) {
                                txt_PasswordRandom.setText("Your password");
                            }
                        }

                    }
                });
                checkBoxNumbers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            String allowedCharacters = "";
                            if (checkBoxUppercase.isChecked()) {
                                allowedCharacters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                            }
                            if (checkBoxLowercase.isChecked()) {
                                allowedCharacters += "abcdefghijklmnopqrstuvwxyz";
                            }
                            if (checkBoxNumbers.isChecked()) {
                                allowedCharacters += "0123456789";
                            }
                            if (checkBoxSymbols.isChecked()) {
                                allowedCharacters += "!@#$%^&*()_-+=<>?/{}~|";
                            }
                            if (checkBoxAmbiguous.isChecked()) {
                                allowedCharacters += "{[',;:.<\"";
                            }
                            final Random random = new Random();
                            final StringBuilder sb = new StringBuilder();

                            for (int i = 0; i < MAX_CHAR; ++i) {
                                sb.append(allowedCharacters.charAt(random.nextInt(allowedCharacters.length())));
                            }
                            txt_PasswordRandom.setText(sb);
                        } else {
                            try {
                                String allowedCharacters = "";
                                if (checkBoxUppercase.isChecked()) {
                                    allowedCharacters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                                }
                                if (checkBoxLowercase.isChecked()) {
                                    allowedCharacters += "abcdefghijklmnopqrstuvwxyz";
                                }
                                if (checkBoxNumbers.isChecked()) {
                                    allowedCharacters += "0123456789";
                                }
                                if (checkBoxSymbols.isChecked()) {
                                    allowedCharacters += "!@#$%^&*()_-+=<>?/{}~|";
                                }
                                if (checkBoxAmbiguous.isChecked()) {
                                    allowedCharacters += "{[',;:.<\"";
                                }

                                final Random random = new Random();
                                final StringBuilder sb = new StringBuilder();

                                for (int i = 0; i < MAX_CHAR; ++i) {
                                    sb.append(allowedCharacters.charAt(random.nextInt(allowedCharacters.length())));
                                }
                                txt_PasswordRandom.setText(sb);
                            } catch (Exception e) {
                                txt_PasswordRandom.setText("Your password");
                            }
                        }

                    }
                });
                checkBoxSymbols.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            String allowedCharacters = "";
                            if (checkBoxUppercase.isChecked()) {
                                allowedCharacters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                            }
                            if (checkBoxLowercase.isChecked()) {
                                allowedCharacters += "abcdefghijklmnopqrstuvwxyz";
                            }
                            if (checkBoxNumbers.isChecked()) {
                                allowedCharacters += "0123456789";
                            }
                            if (checkBoxSymbols.isChecked()) {
                                allowedCharacters += "!@#$%^&*()_-+=<>?/{}~|";
                            }
                            if (checkBoxAmbiguous.isChecked()) {
                                allowedCharacters += "{[',;:.<\"";
                            }
                            final Random random = new Random();
                            final StringBuilder sb = new StringBuilder();

                            for (int i = 0; i < MAX_CHAR; ++i) {
                                sb.append(allowedCharacters.charAt(random.nextInt(allowedCharacters.length())));
                            }
                            txt_PasswordRandom.setText(sb);
                        } else {
                            try {
                                String allowedCharacters = "";
                                if (checkBoxUppercase.isChecked()) {
                                    allowedCharacters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                                }
                                if (checkBoxLowercase.isChecked()) {
                                    allowedCharacters += "abcdefghijklmnopqrstuvwxyz";
                                }
                                if (checkBoxNumbers.isChecked()) {
                                    allowedCharacters += "0123456789";
                                }
                                if (checkBoxSymbols.isChecked()) {
                                    allowedCharacters += "!@#$%^&*()_-+=<>?/{}~|";
                                }
                                if (checkBoxAmbiguous.isChecked()) {
                                    allowedCharacters += "{[',;:.<\"";
                                }

                                final Random random = new Random();
                                final StringBuilder sb = new StringBuilder();

                                for (int i = 0; i < MAX_CHAR; ++i) {
                                    sb.append(allowedCharacters.charAt(random.nextInt(allowedCharacters.length())));
                                }
                                txt_PasswordRandom.setText(sb);
                            } catch (Exception e) {
                                txt_PasswordRandom.setText("Your password");
                            }
                        }
                    }
                });
                checkBoxAmbiguous.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            String allowedCharacters = "";
                            if (checkBoxUppercase.isChecked()) {
                                allowedCharacters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                            }
                            if (checkBoxLowercase.isChecked()) {
                                allowedCharacters += "abcdefghijklmnopqrstuvwxyz";
                            }
                            if (checkBoxNumbers.isChecked()) {
                                allowedCharacters += "0123456789";
                            }
                            if (checkBoxSymbols.isChecked()) {
                                allowedCharacters += "!@#$%^&*()_-+=<>?/{}~|";
                            }
                            if (checkBoxAmbiguous.isChecked()) {
                                allowedCharacters += "{[',;:.<\"";
                            }
                            final Random random = new Random();
                            final StringBuilder sb = new StringBuilder();

                            for (int i = 0; i < MAX_CHAR; ++i) {
                                sb.append(allowedCharacters.charAt(random.nextInt(allowedCharacters.length())));
                            }
                            txt_PasswordRandom.setText(sb);
                        } else {
                            try {
                                String allowedCharacters = "";
                                if (checkBoxUppercase.isChecked()) {
                                    allowedCharacters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                                }
                                if (checkBoxLowercase.isChecked()) {
                                    allowedCharacters += "abcdefghijklmnopqrstuvwxyz";
                                }
                                if (checkBoxNumbers.isChecked()) {
                                    allowedCharacters += "0123456789";
                                }
                                if (checkBoxSymbols.isChecked()) {
                                    allowedCharacters += "!@#$%^&*()_-+=<>?/{}~|";
                                }
                                if (checkBoxAmbiguous.isChecked()) {
                                    allowedCharacters += "{[',;:.<\"";
                                }
                                final Random random = new Random();
                                final StringBuilder sb = new StringBuilder();

                                for (int i = 0; i < MAX_CHAR; ++i) {
                                    sb.append(allowedCharacters.charAt(random.nextInt(allowedCharacters.length())));
                                }
                                txt_PasswordRandom.setText(sb);
                            } catch (Exception e) {
                                txt_PasswordRandom.setText("Your password");
                            }
                        }

                    }
                });
                btn_save_passwordRandom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (check_save_password.isChecked()) {
                            String passrandom = txt_PasswordRandom.getText().toString();
//                            edt_password_account.setText(passrandom);
                            dialog2.dismiss();
                        } else {
                            Toast.makeText(UserActivity.this, "Vui lòng đồng ý điều khoản", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog2.show();
            }
        });
        dialog.show();
    }

    private void initDiagLogAddress() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.diaglog_address_user);
        dialog.setTitle(getResources().getString(R.string.txt_address));
        final EditText edt_user_numberhouse = dialog.findViewById(R.id.edt_user_numberhouse);
        final TextView txt_address = dialog.findViewById(R.id.txt_address);
        Spinner spin_Wards = (Spinner) dialog.findViewById(R.id.pioedittxt5_Wards);
        Spinner spin_District_ = (Spinner) dialog.findViewById(R.id.pioedittxt5_District);
        Spinner spin_city = (Spinner) dialog.findViewById(R.id.pioedittxt5_city);
        final ArrayList<UserIssuedByContructor> arrayList_Wards, arrayList_District, arrayList_city;
        final AdapterUserIssuedBy arrayAdapter_Wards, arrayAdapter_District, arrayAdapter_city;
        arrayList_Wards = new ArrayList<>();
        arrayList_District = new ArrayList<>();
        arrayList_city = new ArrayList<>();
        arrayList_Wards.add(new UserIssuedByContructor("Phường 25"));
        arrayList_Wards.add(new UserIssuedByContructor("Phường Phạm Ngũ Lão"));
        arrayList_District.add(new UserIssuedByContructor("Quận Cầu Giấy"));
        arrayList_District.add(new UserIssuedByContructor("Quận 1"));
        arrayList_city.add(new UserIssuedByContructor("TP. Hồ Chí Minh"));
        arrayList_city.add(new UserIssuedByContructor("TP. Hà Nội"));
        arrayAdapter_Wards = new AdapterUserIssuedBy(this, arrayList_Wards);
        arrayAdapter_Wards.notifyDataSetChanged();
        arrayAdapter_District = new AdapterUserIssuedBy(this, arrayList_District);
        arrayAdapter_District.notifyDataSetChanged();
        arrayAdapter_city = new AdapterUserIssuedBy(this, arrayList_city);
        arrayAdapter_city.notifyDataSetChanged();
        spin_Wards.setAdapter(arrayAdapter_Wards);
        spin_Wards.setDropDownVerticalOffset(100);
        spin_Wards.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                numberWard = position;
                txt_address.setText(edt_user_numberhouse.getText().toString().trim()
                        + ", " + arrayList_Wards.get(position).getTitle()
                        + ", " + arrayList_District.get(numberDistrict).getTitle()
                        + ", " + arrayList_city.get(numberCity).getTitle());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spin_District_.setAdapter(arrayAdapter_District);
        spin_District_.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                numberDistrict = position;
                txt_address.setText(edt_user_numberhouse.getText().toString().trim()
                        + ", " + arrayList_Wards.get(numberWard).getTitle()
                        + ", " + arrayList_District.get(position).getTitle()
                        + ", " + arrayList_city.get(numberCity).getTitle());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spin_city.setAdapter(arrayAdapter_city);
        spin_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                numberCity = position;
                txt_address.setText(edt_user_numberhouse.getText().toString().trim()
                        + ", " + arrayList_Wards.get(numberWard).getTitle()
                        + ", " + arrayList_District.get(numberDistrict).getTitle()
                        + ", " + arrayList_city.get(position).getTitle());
                ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edt_user_numberhouse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txt_address.setText(edt_user_numberhouse.getText().toString().trim()
                        + ", " + arrayList_Wards.get(numberWard).getTitle()
                        + ", " + arrayList_District.get(numberDistrict).getTitle()
                        + ", " + arrayList_city.get(numberCity).getTitle());
                ;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txt_address.setText(edt_user_numberhouse.getText().toString().trim()
                + ", " + arrayList_Wards.get(numberWard).getTitle()
                + ", " + arrayList_District.get(numberDistrict).getTitle()
                + ", " + arrayList_city.get(numberCity).getTitle());
        ;
        dialog.show();
    }

    private void initDiagLogIDNumber() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.diaglog_idnumber_user);
        dialog.setTitle(getResources().getString(R.string.txt_IDnumber));
        final EditText edt_user_ngaycap = dialog.findViewById(R.id.edt_user_ngaycap);
        edt_user_ngaycap.setText("20/11/2018");
        final Calendar newDate = Calendar.getInstance();
        final DatePickerDialog StartTime = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                newDate.set(year, monthOfYear, dayOfMonth);
                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                edt_user_ngaycap.setText(sdf.format(newDate.getTime()));
            }

        }, newDate.get(Calendar.YEAR), newDate.get(Calendar.MONTH), newDate.get(Calendar.DAY_OF_MONTH));
        edt_user_ngaycap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartTime.show();
            }
        });
        Spinner spin = (Spinner) dialog.findViewById(R.id.pioedittxt5);
        ArrayList<UserIssuedByContructor> arrayList;
        AdapterUserIssuedBy arrayAdapter;
        arrayList = new ArrayList<>();
        arrayList.add(new UserIssuedByContructor("Ninh Thuận"));
        arrayList.add(new UserIssuedByContructor("Lâm Đồng"));
        arrayList.add(new UserIssuedByContructor("Đà Nẵng"));
        arrayList.add(new UserIssuedByContructor("Khánh Hòa"));
        arrayList.add(new UserIssuedByContructor("Trà Vinh"));
        arrayList.add(new UserIssuedByContructor("Ninh Bình"));
        arrayList.add(new UserIssuedByContructor("Quảng Nam"));
        arrayAdapter = new AdapterUserIssuedBy(this, arrayList);
        arrayAdapter.notifyDataSetChanged();
        spin.setAdapter(arrayAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initDiagLogBirthday() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.diaglog_birthday_user);
        dialog.setTitle(getResources().getString(R.string.txt_birthday));
        final Calendar myCalendar = Calendar.getInstance();
        DatePicker datePicker = dialog.findViewById(R.id.DatePicker);
        final AppCompatEditText edittext = dialog.findViewById(R.id.edt_birthday_fristname);
        final TextInputLayout textInputLayout = dialog.findViewById(R.id.textInput_birthday);
        datePicker.init(myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                edittext.setText(sdf.format(myCalendar.getTime()));
            }
        });


        dialog.show();
    }

    private void intentView(Class c) {
        Intent intent = new Intent(UserActivity.this, c);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }


    @Override
    public void onBackPressed() {
        intentView(MainActivity.class);
        super.onBackPressed();
    }



    private void setContentDialog(String title, String messager) {
        progressDialog.setTitle(title);
        progressDialog.setMessage(messager);
    }


    private void alertDiaLogUploadImages() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_uploadimages_gallery);
        dialog.setTitle(getResources().getString(R.string.txt_Thewaytodoit));
        final TextView txt_camera_Upload = (TextView) dialog.findViewById(R.id.txt_camera_user);
        final TextView txt_gallery_Upload = (TextView) dialog.findViewById(R.id.txt_Getphotos_user);
        txt_camera_Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                SDK_Camera();
            }
        });
        txt_gallery_Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                NumberCamera = 2;
                galleryUpload();
            }
        });
        dialog.show();


    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LNL_fullname_User:
                initDiagLogFullName();
                break;
            case R.id.LNL_gender_user:
                initDiagLogGender();
                break;
            case R.id.LNL_chagepassword_user:
                initDiagLogPasswordRandom();
                break;
            case R.id.LNL_birthday_user:
                initDiagLogBirthday();
                break;
            case R.id.LNL_phone_user:
                initDiagLogPhone();
                break;
            case R.id.LNL_email_user:
                initDiagLogEmail();
                break;
            case R.id.LNL_IDnumber_user:
                initDiagLogIDNumber();
                break;
            case R.id.LNL_address_user:
                initDiagLogAddress();
                break;
            case R.id.img_qr_user:
                intentView(QRUserActivity.class);
                break;
            case R.id.img_back_postdetail:
                onBackPressed();
                break;
            case R.id.img_avatar_user:
                alertDiaLogUploadImages();
                break;
            case R.id.img_logo_user:
                NumberCamera = 1;
                galleryUpload();
                break;
            case R.id.img_medal_postdetail:
                intentView(MemberActivity.class);
                break;

        }
    }

    private void SDK_Camera() {
        if (Build.VERSION.SDK_INT > 21) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                // Permission is already available, start camera preview
                Toast.makeText(this, "Starting preview.", Toast.LENGTH_SHORT).show();
//                            Snackbar.make(view_Account,
//                                    "Camera permission is available (API: " + Build.VERSION.SDK_INT + "). Starting preview.",
//                                    Snackbar.LENGTH_SHORT).show();

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {

                    startActivityForResult(takePictureIntent, CAMERA_REQUEST_MAX);
                }
            } else {
                // Permission is missing and must be requested.
                requestCameraPermission();
            }
        } else {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent, CAMERA_REQUEST);

        }
    }
    private void requestCameraPermission() {
        // Permission has not been granted and must be requested.
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
//            // Display a SnackBar with a button to request the missing permission.
            Toast.makeText(this, "Camera access is required to display the camera preview.", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Permission is not available. Requesting camera permission.", Toast.LENGTH_SHORT).show();
            // Request the permission. The result will be received in onRequestPermissionResult().
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CAMERA);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == this.RESULT_OK && data != null) {
            if(NumberCamera == 1){
                Bundle extras = data.getExtras();
                Bitmap image = extras.getParcelable("data");
                img_logo_user.setImageBitmap(image);
                final String image1 = decodeImage(image);
            }else if(NumberCamera == 2){
                Bundle extras = data.getExtras();
                Bitmap image = extras.getParcelable("data");
                img_avatar_user.setImageBitmap(image);
                final String image1 = decodeImage(image);
            }


        }
        if (resultCode == this.RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                // get the Uri for the captured image
                picUri = data.getData();
                performCrop();
            } else if (requestCode == CAMERA_REQUEST_MAX) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                img_avatar_user.setImageBitmap(imageBitmap);
                Bitmap bitmap = ((BitmapDrawable) img_avatar_user.getDrawable()).getBitmap();
                final String image = decodeImage(bitmap);

            } else if (requestCode == PIC_CROP) {
                // get the returned data
                Bundle extras = data.getExtras();
                // get the cropped bitmap
                Bitmap thePic = extras.getParcelable("data");
                img_avatar_user.setImageBitmap(thePic);
            }
        }
    }

    public String decodeImage(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
        byte[] byteImage = outputStream.toByteArray();
        String encodeImage = Base64.encodeToString(byteImage, Base64.NO_WRAP);
        return encodeImage;
    }

    private void performCrop() {
        // take care of exceptions
        try {
            // call the standard crop action intent (the user device may not
            // support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 200);
            cropIntent.putExtra("aspectY", 200);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 200);
            cropIntent.putExtra("outputY", 200);
            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {

        }
    }

    private void galleryUpload() {
        Intent imageDownload = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if(NumberCamera == 1){
            imageDownload.putExtra("crop", "true");
            imageDownload.putExtra("aspectX", 0);
            imageDownload.putExtra("aspectY", 0);
            imageDownload.putExtra("outputX", 200);
            imageDownload.putExtra("outputY", 150);
        }else if(NumberCamera == 2){
            imageDownload.putExtra("crop", "true");
            imageDownload.putExtra("aspectX", 200);
            imageDownload.putExtra("aspectY", 200);
            imageDownload.putExtra("outputX", 200);
            imageDownload.putExtra("outputY", 200);
        }
        imageDownload.putExtra("return-data", true);
        startActivityForResult(imageDownload, 2);
    }


}
