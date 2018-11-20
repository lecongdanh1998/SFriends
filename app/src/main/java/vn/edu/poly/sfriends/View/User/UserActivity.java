package vn.edu.poly.sfriends.View.User;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import vn.edu.poly.sfriends.Adapter.AdapterUserIssuedBy;
import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.Model.UserIssuedByContructor;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.View.MainActivity;
import vn.edu.poly.sfriends.View.User.QR.QRUserActivity;

public class UserActivity extends BaseActivity implements View.OnClickListener {
    Toolbar toolbar;
    LinearLayout LNL_username, LNL_gender_user, LNL_chagepassword_user, LNL_birthday_user,
            LNL_phone_user, LNL_email_user, LNL_IDnumber_user, LNL_address_user;
    CollapsingToolbarLayout collapsingToolbar;
    int numberWard = 0;
    int numberDistrict = 0;
    int numberCity = 0;
    int MAX_CHAR = 8;
    ImageView img_QRuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initControl();
        initData();
        initOnClick();
    }

    private void initOnClick() {
        LNL_username.setOnClickListener(this);
        LNL_gender_user.setOnClickListener(this);
        LNL_chagepassword_user.setOnClickListener(this);
        LNL_birthday_user.setOnClickListener(this);
        LNL_phone_user.setOnClickListener(this);
        LNL_email_user.setOnClickListener(this);
        LNL_IDnumber_user.setOnClickListener(this);
        LNL_address_user.setOnClickListener(this);
        img_QRuser.setOnClickListener(this);
    }

    private void initControl() {
        img_QRuser = findViewById(R.id.img_qr_user);
        LNL_username = findViewById(R.id.LNL_username);
        LNL_gender_user = findViewById(R.id.LNL_gender_user);
        LNL_chagepassword_user = findViewById(R.id.LNL_chagepassword_user);
        LNL_birthday_user = findViewById(R.id.LNL_birthday_user);
        LNL_phone_user = findViewById(R.id.LNL_phone_user);
        LNL_email_user = findViewById(R.id.LNL_email_user);
        LNL_IDnumber_user = findViewById(R.id.LNL_IDnumber_user);
        LNL_address_user = findViewById(R.id.LNL_address_user);
        toolbar = findViewById(R.id.toolbar);
        collapsingToolbar = findViewById(R.id.collapsingToolbar);
    }

    private void initData() {


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
                        + ", " + arrayList_city.get(position).getTitle());;
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
                        + ", " + arrayList_city.get(numberCity).getTitle());;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txt_address.setText(edt_user_numberhouse.getText().toString().trim()
                + ", " + arrayList_Wards.get(numberWard).getTitle()
                + ", " + arrayList_District.get(numberDistrict).getTitle()
                + ", " + arrayList_city.get(numberCity).getTitle());;
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

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LNL_username:
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
        }
    }
}
