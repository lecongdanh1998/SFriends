package vn.edu.poly.sfriends.View;

import android.accounts.Account;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
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
import vn.edu.poly.sfriends.Adapter.MenuAdapter;
import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.Model.MenuModel;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.Server.ApiConnect;
import vn.edu.poly.sfriends.View.Borrow.Borrow;
import vn.edu.poly.sfriends.View.HomePage.HomePage;
import vn.edu.poly.sfriends.View.SignIn.SignIn;
import vn.edu.poly.sfriends.View.User.UserActivity;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private DrawerLayout drawer_layout;
    private NavigationView nav_view;
    private Toolbar toolbar;
    private ListView listview_menu;
    private TextView toolbar_title, txt_name_header_navigation_main, txt_role_header_navigation_main;
    private ImageView img_back_MysiteToobar;
    private MenuAdapter menuAdapter;
    private ArrayList<MenuModel> menuModelArrayList;
    RelativeLayout layout_header_navigation_main;
    private RelativeLayout btn_setting, btn_logout_main;
    String URL_USER = "";
    private ProgressDialog progressDialog;
    String emailUser, passwordUser;
    CircleImageView avatar_user_header_navigation_main;
    String idUser, nameUser,avatarUser;
    String screen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEventButton();
        UserInfo(URL_USER);
        try{
            Intent intent = getIntent();
            screen = intent.getStringExtra("screen");
            if(screen.equalsIgnoreCase("Homepage")){
                transactionFrangment(new HomePage(), getResources().getString(R.string.txt_homepage));
            }
            else{
                transactionFrangment(new HomePage(), getResources().getString(R.string.txt_homepage));
            }
        }catch (Exception e){
            if (savedInstanceState == null) {
                transactionFrangment(new HomePage(), getResources().getString(R.string.txt_homepage));
            }
            e.printStackTrace();
        }


    }

    private void initEventButton() {
        listview_menu.setOnItemClickListener(this);
        img_back_MysiteToobar.setOnClickListener(this);
        btn_logout_main.setOnClickListener(this);
        layout_header_navigation_main.setOnClickListener(this);
    }

    private void initView() {
        drawer_layout = findViewById(R.id.drawer_layout);
        nav_view = findViewById(R.id.nav_view);
        toolbar_title = findViewById(R.id.txt_name_Toobar);
        listview_menu = findViewById(R.id.listview_menu);
        img_back_MysiteToobar = findViewById(R.id.img_back_Toobar);
        btn_setting = findViewById(R.id.btn_setting_main);
        btn_logout_main = findViewById(R.id.btn_logout_main);
        layout_header_navigation_main = findViewById(R.id.layout_header_navigation_main);
        img_back_MysiteToobar.setImageResource(R.drawable.menu);
        txt_name_header_navigation_main = findViewById(R.id.txt_name_header_navigation_main);
        txt_role_header_navigation_main = findViewById(R.id.txt_role_header_navigation_main);
        avatar_user_header_navigation_main = findViewById(R.id.avatar_user_header_navigation_main);
    }

    private void initData() {
        progressDialog = new ProgressDialog(this);
        URL_USER = ApiConnect.URL_GET_USER_INFOR(HTTP);
        menuModelArrayList = new ArrayList<>();
        menuModelArrayList.add(new MenuModel(getResources().getString(R.string.txt_homepage)));
        menuModelArrayList.add(new MenuModel(getResources().getString(R.string.txt_Notifications)));
        menuModelArrayList.add(new MenuModel("Borrow"));
        menuModelArrayList.add(new MenuModel("Post"));
        menuModelArrayList.add(new MenuModel("Gallery"));
        menuModelArrayList.add(new MenuModel("Contact"));
        menuModelArrayList.add(new MenuModel("Help"));
        /*
         * create menu adapter
         * */
        menuAdapter = new MenuAdapter(this, menuModelArrayList, "3");
        listview_menu.setAdapter(menuAdapter);
        editorUser = dataLoginUser.edit();
        emailUser = dataLoginUser.getString("useremail", "");
        passwordUser = dataLoginUser.getString("userpassword", "");
        token = dataLoginUser.getString("usertoken", "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    /*
     * drawer menu open when click ic_menu
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void transactionFrangment(Fragment f, String s) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                f, s).commit();
        toolbar_title.setText(s);
        drawer_layout.closeDrawers();
    }

    private void setContentDialog(String title, String messager) {
        progressDialog.setTitle(title);
        progressDialog.setMessage(messager);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        Fragment fragment;
        switch (id) {
            case R.id.img_back_Toobar:
                drawer_layout.openDrawer(GravityCompat.START);
                break;
            case R.id.btn_logout_main:
                AlertDialogLogOut();
                break;
            case R.id.layout_header_navigation_main:
                intentView(UserActivity.class);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        AlertDialogLogOutFinish();
    }

    private void AlertDialogLogOutFinish() {
        android.app.AlertDialog.Builder alertDialog2 = new android.app.AlertDialog.Builder(
                MainActivity.this);
        alertDialog2.setTitle("Thoát...");
        alertDialog2.setMessage("Bạn có chắc chắn muốn thoát không?");
        alertDialog2.setIcon(R.drawable.ic_exit_to_app_black_24dp);
        alertDialog2.setPositiveButton("Có",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        alertDialog2.setNegativeButton("Không",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog2.show();
    }


    private void AlertDialogLogOut() {
        android.app.AlertDialog.Builder alertDialog2 = new android.app.AlertDialog.Builder(
                MainActivity.this);
        alertDialog2.setTitle("Đăng xuất...");
        alertDialog2.setMessage("Bạn có chắc chắn muốn đăng xuất không?");
        alertDialog2.setIcon(R.drawable.ic_exit_to_app_black_24dp);
        alertDialog2.setPositiveButton("Có",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        editorUser = dataLoginUser.edit();
                        editorUser.putString("useremail", "");
                        editorUser.putString("userpassword", "");
                        editorUser.commit();
                        intentView(SignIn.class);
                    }
                });
        alertDialog2.setNegativeButton("Không",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog2.show();
    }

    private void intentView(Class c) {
        Intent intent = new Intent(MainActivity.this, c);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new HomePage();
                transactionFrangment(fragment, getResources().getString(R.string.txt_homepage));
                break;
//            case 1:
//                //Notifications
//                break;
            case 2:
                fragment = new Borrow();
                transactionFrangment(fragment, "Borrow");
                break;
//            case 3:
//                fragment = new Gallery();
//                transactionFrangment(fragment, "My Gallery");
//                break;
//            case 4:
//                fragment = new Contact();
//                transactionFrangment(fragment, "Contact");
//                break;
//            case 5:
//                fragment = new Help();
//                transactionFrangment(fragment, "Help");
//                break;
//            case 7:
//                break;
            default:
                break;
        }
    }

    private void UserInfo(String url) {
        setContentDialog("Loading", "Please wait...");
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
                            .placeholder(R.drawable.account)
                            .error(R.drawable.account)
                            .into(avatar_user_header_navigation_main);
                    txt_name_header_navigation_main.setText(nameUser);
                    txt_role_header_navigation_main.setText(emailUser);
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


}
