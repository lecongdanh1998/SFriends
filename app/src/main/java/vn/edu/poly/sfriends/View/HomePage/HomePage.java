package vn.edu.poly.sfriends.View.HomePage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.edu.poly.sfriends.Adapter.AdapterTabLayoutFrangment;
import vn.edu.poly.sfriends.Adapter.CustomViewPagerAdapter;
import vn.edu.poly.sfriends.Adapter.GirdViewAdapter_1_Homepage;
import vn.edu.poly.sfriends.Adapter.GirdViewAdapter_2_Homepage;
import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.Model.GirdViewContrucTor_1_Homepage;
import vn.edu.poly.sfriends.Model.GirdViewContrucTor_2_Homepage;
import vn.edu.poly.sfriends.Model.HotDealObject;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.Server.ApiConnect;
import vn.edu.poly.sfriends.View.HomePage.Cuisine.Cuisine;
import vn.edu.poly.sfriends.View.HomePage.Education.Education;
import vn.edu.poly.sfriends.View.HomePage.Entertainment.Entertainment;
import vn.edu.poly.sfriends.View.HomePage.Other.Other;
import vn.edu.poly.sfriends.View.HomePage.Shopping.Shopping;
import vn.edu.poly.sfriends.View.HomePage.Travel.Travel;
import vn.edu.poly.sfriends.View.MainActivity;

public class HomePage extends Fragment {
    private static final String TAG = HomePage.class.getSimpleName();
    private View view;
    public ViewPager mViewPager,viewPager;
    private CustomViewPagerAdapter mAdapter;
    private Handler handler;
    private final int delay = 2000;
    private int page = 0;
    public TabLayout tab_layout;
    ArrayList listFragment;
    ArrayList<String> listTitle;
    AdapterTabLayoutFrangment adapterTabLayoutFrangment;
    public TextView tabOne, tabTwo, tabThree, tabFour, tabFive,tabSix;
    public int index_change;
    Context context;
    ArrayList<GirdViewContrucTor_1_Homepage> arrayList;
    GridView gridView;
    GirdViewAdapter_1_Homepage adapter;
    ArrayList<GirdViewContrucTor_2_Homepage> arrayList2;
    GridView gridView2;
    String emailUser;
    String URL_USER = "";
    GirdViewAdapter_2_Homepage adapter2;
    TextView txt_Name_postdetails;
    Runnable runnable = new Runnable() {
        public void run() {
            if (mAdapter.getCount() == page) {
                page = 0;
            } else {
                page++;
            }
            viewPager.setCurrentItem(page, true);
            handler.postDelayed(this, delay);
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_homepage, container, false);
        initControl();
        initData();
        initEvent();
        initOnClick();
        createTabIcons();
        return view;
    }

    private void initData() {
        BaseActivity.editorUser = BaseActivity.dataLoginUser.edit();
        emailUser = BaseActivity.dataLoginUser.getString("useremail", "");
        BaseActivity.token = BaseActivity.dataLoginUser.getString("usertoken", "");
        URL_USER = ApiConnect.URL_GET_USER_INFOR(BaseActivity.HTTP);
        getJsonInfo(URL_USER,emailUser,BaseActivity.token);
    }

    private void getJsonInfo(String url, final String email, final String token){
        RequestQueue requestUser = Volley.newRequestQueue(getContext());
        StringRequest UserRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject1 = new JSONObject(response);
                    JSONObject jsonObject = jsonObject1.getJSONObject("data");
                    String  nameUser = jsonObject.getString("name");
                    txt_Name_postdetails.setText(nameUser);
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



    private void initControl() {
        viewPager = (ViewPager)view.findViewById(R.id.hot_deal_view_pager);
        tab_layout = (TabLayout) view.findViewById(R.id.tabs);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager_content);
        tab_layout.setupWithViewPager(mViewPager);
        gridView = view.findViewById(R.id.gridView1);
        gridView2 = view.findViewById(R.id.gridView2);
        txt_Name_postdetails = view.findViewById(R.id.txt_Name_postdetails);

    }

    private void initEvent() {
        listFragment = new ArrayList();
        listFragment.add(new Cuisine());
        listFragment.add(new Entertainment());
        listFragment.add(new Shopping());
        listFragment.add(new Travel());
        listFragment.add(new Education());
        listFragment.add(new Other());
        listTitle = new ArrayList<>();

        listTitle.add(getResources().getString(R.string.txt_Cuisine));
        listTitle.add(getResources().getString(R.string.txt_entertainment));
        listTitle.add(getResources().getString(R.string.txt_Shopping));
        listTitle.add(getResources().getString(R.string.txt_Travel));
        listTitle.add(getResources().getString(R.string.txt_Education));
        listTitle.add(getResources().getString(R.string.txt_Other));
        adapterTabLayoutFrangment = new AdapterTabLayoutFrangment(
                getChildFragmentManager(), getContext(), listTitle, listFragment
        );

        mViewPager.setAdapter(adapterTabLayoutFrangment);


        arrayList = new ArrayList<>();
        arrayList.add(new GirdViewContrucTor_1_Homepage("https://png.icons8.com/ios/70/B382C7/exit.png","Khuyến mãi"));
        arrayList.add(new GirdViewContrucTor_1_Homepage("https://png.icons8.com/metro/70/2ecc71/qr-code.png","Chuyển tiền"));
        arrayList.add(new GirdViewContrucTor_1_Homepage("https://png.icons8.com/ios/40/EC0890/gift.png","Thanh toán dịch vụ"));
        adapter = new GirdViewAdapter_1_Homepage(arrayList,getContext());
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        arrayList2 = new ArrayList<>();
        arrayList2.add(new GirdViewContrucTor_2_Homepage("https://png.icons8.com/ios/70/B382C7/exit.png","Tài khoản"));
        arrayList2.add(new GirdViewContrucTor_2_Homepage("https://png.icons8.com/metro/70/2ecc71/qr-code.png","Máy tính"));
        arrayList2.add(new GirdViewContrucTor_2_Homepage("https://png.icons8.com/ios/40/EC0890/gift.png","Thời khóa biểu"));
        adapter2 = new GirdViewAdapter_2_Homepage(arrayList2,getContext());
        gridView2.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();

        handler = new Handler();
        mAdapter = new CustomViewPagerAdapter(getContext(), getTestData());
        viewPager.setAdapter(mAdapter);
    }
    public List<HotDealObject> getTestData() {
        List<HotDealObject> mTestData = new ArrayList<HotDealObject>();
        mTestData.add(new HotDealObject("https://www.xemgame.com/data/pictures/xemgame/2014/12/30/BCVS-1.png"));
        mTestData.add(new HotDealObject("https://www.xemgame.com/data/pictures/xemgame/2014/12/30/BCVS-1.png"));
        mTestData.add(new HotDealObject("https://www.xemgame.com/data/pictures/xemgame/2014/12/30/BCVS-1.png"));
        mTestData.add(new HotDealObject("https://www.xemgame.com/data/pictures/xemgame/2014/12/30/BCVS-1.png"));
        mTestData.add(new HotDealObject("https://www.xemgame.com/data/pictures/xemgame/2014/12/30/BCVS-1.png"));
        return mTestData;
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, delay);
    }
    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
    private void intentView(Class c) {
        Intent intent = new Intent(getContext(), c);
        startActivity(intent);
        getActivity().finish();
    }

    private void initOnClick() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        intentView(WebViewMain.class);
                        break;

                }
            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint({"ResourceAsColor", "ResourceType"})
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
//                        tabOne = (TextView) LayoutInflater.from(TabLayOutActivity.this).inflate(R.layout.custom_tab_select, null);
                        tabOne.setText(getResources().getString(R.string.txt_Cuisine));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayout));
//                        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_homnay_select, 0, 0);
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_entertainment));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_thamquan, 0, 0);
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_Shopping));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_tinkiem, 0, 0);
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_Travel));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_giaohang, 0, 0);
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        tabFive.setText(getResources().getString(R.string.txt_Education));
                        tabFive.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_taikhoan, 0, 0);
                        tab_layout.getTabAt(4).setCustomView(tabFive);
                        tabSix.setText(getResources().getString(R.string.txt_Other));
                        tabSix.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_taikhoan_select, 0, 0);
                        tab_layout.getTabAt(5).setCustomView(tabSix);
                        break;
                    case 1:
                        tabOne.setText(getResources().getString(R.string.txt_Cuisine));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_today, 0, 0);
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_entertainment));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayout));
//                        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_thamquan_select, 0, 0);
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_Shopping));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_tinkiem, 0, 0);
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_Travel));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_giaohang, 0, 0);
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        tabFive.setText(getResources().getString(R.string.txt_Education));
                        tabFive.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_taikhoan, 0, 0);
                        tab_layout.getTabAt(4).setCustomView(tabFive);
                        tabSix.setText(getResources().getString(R.string.txt_Other));
                        tabSix.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_taikhoan_select, 0, 0);
                        tab_layout.getTabAt(5).setCustomView(tabSix);
                        break;
                    case 2:
                        tabOne.setText(getResources().getString(R.string.txt_Cuisine));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_today, 0, 0);
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_entertainment));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_thamquan, 0, 0);
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_Shopping));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayout));
//                        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_tiemkiem_select, 0, 0);
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_Travel));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_giaohang, 0, 0);
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        tabFive.setText(getResources().getString(R.string.txt_Education));
                        tabFive.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_taikhoan, 0, 0);
                        tab_layout.getTabAt(4).setCustomView(tabFive);
                        tabSix.setText(getResources().getString(R.string.txt_Other));
                        tabSix.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_taikhoan_select, 0, 0);
                        tab_layout.getTabAt(5).setCustomView(tabSix);
                        break;
                    case 3:
                        tabOne.setText(getResources().getString(R.string.txt_Cuisine));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_today, 0, 0);
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_entertainment));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_thamquan, 0, 0);
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_Shopping));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_tinkiem, 0, 0);
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_Travel));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayout));
//                        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_giohang_select, 0, 0);
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        tabFive.setText(getResources().getString(R.string.txt_Education));
                        tabFive.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_taikhoan, 0, 0);
                        tab_layout.getTabAt(4).setCustomView(tabFive);
                        tabSix.setText(getResources().getString(R.string.txt_Other));
                        tabSix.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_taikhoan_select, 0, 0);
                        tab_layout.getTabAt(5).setCustomView(tabSix);
                        break;
                    case 4:
                        tabOne.setText(getResources().getString(R.string.txt_Cuisine));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_today, 0, 0);
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_entertainment));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_thamquan, 0, 0);
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_Shopping));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_tinkiem, 0, 0);
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_Travel));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_giaohang, 0, 0);
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        tabFive.setText(getResources().getString(R.string.txt_Education));
                        tabFive.setTextColor(getResources().getColor(R.color.color_text_tablayout));
//                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_taikhoan_select, 0, 0);
                        tab_layout.getTabAt(4).setCustomView(tabFive);
                        tabSix.setText(getResources().getString(R.string.txt_Other));
                        tabSix.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_taikhoan_select, 0, 0);
                        tab_layout.getTabAt(5).setCustomView(tabSix);
                        break;
                    case 5:
                        tabOne.setText(getResources().getString(R.string.txt_Cuisine));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_today, 0, 0);
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_entertainment));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_thamquan, 0, 0);
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_Shopping));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_tinkiem, 0, 0);
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_Travel));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_giaohang, 0, 0);
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        tabFive.setText(getResources().getString(R.string.txt_Education));
                        tabFive.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
//                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_taikhoan_select, 0, 0);
                        tab_layout.getTabAt(4).setCustomView(tabFive);
                        tabSix.setText(getResources().getString(R.string.txt_Other));
                        tabSix.setTextColor(getResources().getColor(R.color.color_text_tablayout));
//                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_taikhoan_select, 0, 0);
                        tab_layout.getTabAt(5).setCustomView(tabSix);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void createTabIcons() {
        tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabOne.setText(getResources().getString(R.string.txt_Cuisine));
        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayout));
//        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_homnay_select, 0, 0);
        tab_layout.getTabAt(0).setCustomView(tabOne);
        // icon Hôm nay
        tabTwo = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabTwo.setText(getResources().getString(R.string.txt_entertainment));
//        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_thamquan, 0, 0);
        tab_layout.getTabAt(1).setCustomView(tabTwo);
        // icon Tham quan

        tabThree = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabThree.setText(getResources().getString(R.string.txt_Shopping));
//        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_tinkiem, 0, 0);
        tab_layout.getTabAt(2).setCustomView(tabThree);
        // icon Tìm kiếm

        tabFour = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabFour.setText(getResources().getString(R.string.txt_Travel));
//        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_giaohang, 0, 0);
        tab_layout.getTabAt(3).setCustomView(tabFour);
        // icon giỏ hàng

        tabFive = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabFive.setText(getResources().getString(R.string.txt_Education));
//        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_taikhoan, 0, 0);
        tab_layout.getTabAt(4).setCustomView(tabFive);

        tabSix = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabSix.setText(getResources().getString(R.string.txt_Other));
//        tabSix.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_taikhoan, 0, 0);
        tab_layout.getTabAt(5).setCustomView(tabSix);
        // icon Tài khoản
        ChangeTab();
        mViewPager.setCurrentItem(index_change);
    }
    public void ChangeTab(){
        BaseActivity.editorTab = BaseActivity.dataLoginTab.edit();
        BaseActivity.editorTab.putInt("index", 0);
        BaseActivity.editorTab.commit();
    }
}
