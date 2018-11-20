package vn.edu.poly.sfriends.View.HomePage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
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
import vn.edu.poly.sfriends.Adapter.GirdViewAdapter_sagora_Homepage;
import vn.edu.poly.sfriends.Adapter.LoginAdapterRycer;
import vn.edu.poly.sfriends.Adapter.RealestateAdapter;
import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.Component.CustomViewPager.CardFragmentPagerAdapter;
import vn.edu.poly.sfriends.Component.CustomViewPager.ShadowTransformer;
import vn.edu.poly.sfriends.Model.GirdViewContrucTor_1_Homepage;
import vn.edu.poly.sfriends.Model.GirdViewContrucTor_2_Homepage;
import vn.edu.poly.sfriends.Model.GirdViewContructor_sagora_homepage;
import vn.edu.poly.sfriends.Model.HotDealObject;
import vn.edu.poly.sfriends.Model.LoginContrucstor;
import vn.edu.poly.sfriends.Model.RealestateContructor;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.Server.ApiConnect;
import vn.edu.poly.sfriends.View.HomePage.Cuisine.Cuisine;
import vn.edu.poly.sfriends.View.HomePage.Education.Education;
import vn.edu.poly.sfriends.View.HomePage.Entertainment.Entertainment;
import vn.edu.poly.sfriends.View.HomePage.Other.Other;
import vn.edu.poly.sfriends.View.HomePage.RealEstate.RealEstate;
import vn.edu.poly.sfriends.View.HomePage.Sagora.Sagora;
import vn.edu.poly.sfriends.View.HomePage.Sarena.Sarena;
import vn.edu.poly.sfriends.View.HomePage.Senate.Senate;
import vn.edu.poly.sfriends.View.HomePage.Shopping.Shopping;
import vn.edu.poly.sfriends.View.HomePage.Travel.Travel;

public class HomePage extends Fragment implements View.OnClickListener {
    private static final String TAG = HomePage.class.getSimpleName();
    private View view;
    public ViewPager mViewPager, viewPager,mViewPageQuangCao;
    private CustomViewPagerAdapter mAdapter,mAdapterQuangcao;
    private Handler handler,handlerQuangcao;
    private final int delay = 5000;
    private int page = 0;
    private int pageQuang = 0;
    public TabLayout tab_layout;
    ArrayList listFragment;
    ArrayList<String> listTitle;
    AdapterTabLayoutFrangment adapterTabLayoutFrangment;
    public TextView tabOne, tabTwo, tabThree, tabFour, tabFive, tabSix;
    public int index_change;
    Context context;
    ArrayList<GirdViewContrucTor_1_Homepage> arrayList;
    GridView gridView,gridViewSagora;
    GirdViewAdapter_1_Homepage adapter;
    ArrayList<GirdViewContructor_sagora_homepage> arrayListSagora;
    GirdViewAdapter_sagora_Homepage adapter_sagora_homepage;
    ArrayList<GirdViewContrucTor_2_Homepage> arrayList2;
    GridView gridView2;
    String emailUser;
    String URL_USER = "";
    GirdViewAdapter_2_Homepage adapter2;
    ArrayList<RealestateContructor> arrayListRealestate;
    private RecyclerView mRecyclerView_details;
    private RecyclerView.Adapter mAdapter_details;
    private RecyclerView.LayoutManager mLayoutManager_details;
    TextView txt_Name_postdetails;
    RelativeLayout RLTlayout;
    CardFragmentPagerAdapter pagerAdapter;
    AppBarLayout appBar;
    TextView txt_name_Toobar,toolbar_title;
    ImageView img_logoMain, img_btn_batdongsan_homepage;
    int NumberOne = 0;
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
    Runnable runnable1 = new Runnable() {
        public void run() {
            if (mAdapterQuangcao.getCount() == pageQuang) {
                pageQuang = 0;
            } else {
                pageQuang++;
            }
            mViewPageQuangCao.setCurrentItem(pageQuang, true);
            handlerQuangcao.postDelayed(this, delay);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentimg_btn_batdongsan_homepage

        view = inflater.inflate(R.layout.fragment_homepage, container, false);
        initControl();
        initData();
        initEvent();
        initOnClick();
        createTabIcons();
        initRealestate();
        return view;
    }


    private void initRealestate() {
        arrayListRealestate = new ArrayList<>();
        arrayListRealestate.add(new RealestateContructor("https://image.thanhnien.vn/665/uploaded/congthang/2018_01_05/batdongsan_ngocduong_eduv.jpg", "Category", "Title"));
        arrayListRealestate.add(new RealestateContructor("https://dantricdn.com/k:62b6ce166f/2016/04/19/du-an-opal-1-1461058457676/bat-dong-san-khu-dong-tphcm-hap-dan-nho-loi-the-ha-tang.jpg", "Category", "Title"));
        arrayListRealestate.add(new RealestateContructor("http://image.vietstock.vn/2012/10/01/Canho-de299.jpg", "Category", "Title"));
        arrayListRealestate.add(new RealestateContructor("https://vneconomy.mediacdn.vn/zoom/710_400/2017/quan-2-2-1509625269988-6-80-819-1527-crop-1509625280172.jpg", "Category", "Title"));
        arrayListRealestate.add(new RealestateContructor("http://phamcongtam.com/upload/images/Richstar%20-%20Gioi%20thieu%20du%20an%20-%2018112015_20.jpg", "Category", "Title"));
        mRecyclerView_details.setHasFixedSize(true);
        mLayoutManager_details = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView_details.setLayoutManager(mLayoutManager_details);
        mAdapter_details = new RealestateAdapter(arrayListRealestate, getContext());
        mRecyclerView_details.setAdapter(mAdapter_details);
        mAdapter_details.notifyDataSetChanged();
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView_details);

    }

    private void initData() {
        toolbar_title.setVisibility(View.INVISIBLE);
        img_logoMain.setVisibility(View.VISIBLE);
        img_logoMain.setImageResource(R.drawable.logoapp);
        BaseActivity.editorUser = BaseActivity.dataLoginUser.edit();
        emailUser = BaseActivity.dataLoginUser.getString("useremail", "");
        BaseActivity.token = BaseActivity.dataLoginUser.getString("usertoken", "");
        URL_USER = ApiConnect.URL_GET_USER_INFOR(BaseActivity.HTTP);
        getJsonInfo(URL_USER, emailUser, BaseActivity.token);
        ViewTreeObserver observer = RLTlayout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
//                int a= gridView.getHeight();
                int availableHeight = RLTlayout.getMeasuredHeight();
//                int b = gridView.getWidth();
                if (availableHeight > 0) {
                    Log.d("availableHeight", "" + availableHeight);
                } else {
                    Log.d("availableHeight", "" + availableHeight);

                }
                RLTlayout.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
            }
        });


    }

    private void getJsonInfo(String url, final String email, final String token) {
        RequestQueue requestUser = Volley.newRequestQueue(getContext());
        StringRequest UserRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject1 = new JSONObject(response);
                    JSONObject jsonObject = jsonObject1.getJSONObject("data");
                    String nameUser = jsonObject.getString("name");
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
        txt_name_Toobar = getActivity().findViewById(R.id.txt_name_Toobar);
        appBar = view.findViewById(R.id.appBar);
        RLTlayout = view.findViewById(R.id.RLTlayout);
        viewPager = (ViewPager) view.findViewById(R.id.hot_deal_view_pager);
        tab_layout = (TabLayout) view.findViewById(R.id.tabs);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager_content);
        mViewPageQuangCao = (ViewPager) view.findViewById(R.id.hot_deal_view_pagerQuangCao);
        tab_layout.setupWithViewPager(mViewPager);
        gridView = view.findViewById(R.id.gridView1);
        gridView2 = view.findViewById(R.id.gridView2);
        txt_Name_postdetails = view.findViewById(R.id.txt_Name_postdetails);
        mRecyclerView_details = view.findViewById(R.id.rcy_batdongsan_homepage);
        gridViewSagora = view.findViewById(R.id.gridViewSagora_homepage);
        img_logoMain = getActivity().findViewById(R.id.img_logoMain);
        toolbar_title =  getActivity().findViewById(R.id.txt_name_Toobar);
        img_btn_batdongsan_homepage = view.findViewById(R.id.img_btn_batdongsan_homepage);




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
        arrayList.add(new GirdViewContrucTor_1_Homepage("https://png.icons8.com/ios/70/B382C7/exit.png", "SERENA"));
        arrayList.add(new GirdViewContrucTor_1_Homepage("https://png.icons8.com/metro/70/2ecc71/qr-code.png", "SENATE"));
        arrayList.add(new GirdViewContrucTor_1_Homepage("https://png.icons8.com/ios/40/EC0890/gift.png", "SAGORA"));
        adapter = new GirdViewAdapter_1_Homepage(arrayList, getContext());
        gridView.setAdapter(adapter);
        setGridViewHeightBasedOnChildren(gridView, 3);
        adapter.notifyDataSetChanged();

        arrayList2 = new ArrayList<>();
        arrayList2.add(new GirdViewContrucTor_2_Homepage("https://png.icons8.com/ios/70/B382C7/exit.png", "Tài khoản"));
        arrayList2.add(new GirdViewContrucTor_2_Homepage("https://png.icons8.com/metro/70/2ecc71/qr-code.png", "Máy tính"));
        arrayList2.add(new GirdViewContrucTor_2_Homepage("https://png.icons8.com/ios/40/EC0890/gift.png", "Thời khóa biểu"));

        adapter2 = new GirdViewAdapter_2_Homepage(arrayList2, getContext());
        gridView2.setAdapter(adapter2);
        setGridViewHeightBasedOnChildren(gridView2, 3);
        adapter2.notifyDataSetChanged();

        arrayListSagora = new ArrayList<>();
        arrayListSagora.add(new GirdViewContructor_sagora_homepage("https://i.pinimg.com/originals/48/7e/4d/487e4de42095e0b10cb5eaa77ff4da19.jpg"));
        arrayListSagora.add(new GirdViewContructor_sagora_homepage("https://i.pinimg.com/originals/79/b4/c6/79b4c6a273fbb9a89feba835b6dc21e4.jpg"));
        arrayListSagora.add(new GirdViewContructor_sagora_homepage("https://i.pinimg.com/564x/3c/d5/fd/3cd5fd8cbc9d0055ada0eac30fbb6aac.jpg"));
        arrayListSagora.add(new GirdViewContructor_sagora_homepage("https://i.pinimg.com/564x/64/e1/19/64e119e9a02a86d6b3eb8104ceb388d8.jpg"));
        arrayListSagora.add(new GirdViewContructor_sagora_homepage("https://i.pinimg.com/564x/64/e1/19/64e119e9a02a86d6b3eb8104ceb388d8.jpg"));
        adapter_sagora_homepage = new GirdViewAdapter_sagora_Homepage(arrayListSagora, getContext());
        gridViewSagora.setAdapter(adapter_sagora_homepage);
        setGridViewHeightBasedOnChildren(gridViewSagora, 2);
        adapter_sagora_homepage.notifyDataSetChanged();




//        ArrayList<HotDealObject> mTestData = new ArrayList<HotDealObject>();
//        mTestData.add(new HotDealObject("https://i.ytimg.com/vi/PAuMs3WCd68/maxresdefault.jpg"));
//        mTestData.add(new HotDealObject("https://trungtamnghiencuuthucpham.vn/wp-content/uploads/2013/10/giay-phep-quang-cao-tren-truyen-hinh.jpg"));
//        mTestData.add(new HotDealObject("https://inhongdang.com.vn/images/images/in-bien-quang-cao-gia-re-tai-Ha-Noi.jpg"));
//        mTestData.add(new HotDealObject("https://i.ytimg.com/vi/8mqCk2GevAs/maxresdefault.jpg"));
//        mTestData.add(new HotDealObject("https://cdn-images-1.medium.com/max/700/1*NF6AtRiHrqW5BniRzY30hg.jpeg"));

//        pagerAdapter = new CardFragmentPagerAdapter(getActivity().getSupportFragmentManager(),
//                dpToPixels(2, getContext()),
//                mTestData);
//        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
//        fragmentCardShadowTransformer.enableScaling(true);
//
//        viewPager.setAdapter(pagerAdapter);
//        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
//        viewPager.setOffscreenPageLimit(3);

        handler = new Handler();
        handlerQuangcao = new Handler();
        mAdapter = new CustomViewPagerAdapter(getContext(), getTestData());
        mAdapterQuangcao = new CustomViewPagerAdapter(getContext(), getTestDataQuangCao());
        viewPager.setAdapter(mAdapter);
        mViewPageQuangCao.setAdapter(mAdapterQuangcao);
        img_btn_batdongsan_homepage.setOnClickListener(this);
    }

    public List<HotDealObject> getTestData() {
        List<HotDealObject> mTestData = new ArrayList<HotDealObject>();
        mTestData.add(new HotDealObject("https://i.ytimg.com/vi/PAuMs3WCd68/maxresdefault.jpg"));
        mTestData.add(new HotDealObject("https://trungtamnghiencuuthucpham.vn/wp-content/uploads/2013/10/giay-phep-quang-cao-tren-truyen-hinh.jpg"));
        mTestData.add(new HotDealObject("https://inhongdang.com.vn/images/images/in-bien-quang-cao-gia-re-tai-Ha-Noi.jpg"));
        mTestData.add(new HotDealObject("https://i.ytimg.com/vi/8mqCk2GevAs/maxresdefault.jpg"));
        mTestData.add(new HotDealObject("https://cdn-images-1.medium.com/max/700/1*NF6AtRiHrqW5BniRzY30hg.jpeg"));
        return mTestData;
    }

    public List<HotDealObject> getTestDataQuangCao() {
        List<HotDealObject> mTestData = new ArrayList<HotDealObject>();
        mTestData.add(new HotDealObject("http://sieuthidosukien.com/upload/images/phao-sang-su-kien.jpg"));
        mTestData.add(new HotDealObject("https://onelikestudio.com/assets/2016/04/chup-anh-su-kien-hcm.jpg"));
        mTestData.add(new HotDealObject("https://vienthong24h.com.vn/wp-content/uploads/2017/04/wifi-su-kien.jpg"));
        mTestData.add(new HotDealObject("https://vtv1.mediacdn.vn/thumb_w/650/2018/9/25/1843280-15361368974851948162666-153786834432242500231.jpg"));
        mTestData.add(new HotDealObject("http://starevent.vn/wp-content/uploads/yeu-to-anh-sang-trong-to-chuc-su-kien.jpg"));
        return mTestData;
    }


    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }


    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, delay);
        handlerQuangcao.postDelayed(runnable1, delay);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        handlerQuangcao.removeCallbacks(runnable1);

    }

    private void intentView(Class c) {
        Intent intent = new Intent(getContext(), c);
        startActivity(intent);
        getActivity().finish();
        getActivity().overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }

    private void initOnClick() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intentView(Sarena.class);
                        break;
                    case 1:
                        intentView(Senate.class);
                        break;
                    case 2:
                        intentView(Sagora.class);
                        break;
                }
            }
        });
//        appBar1.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
//                if (Math.abs(i) == appBarLayout.getTotalScrollRange()) {
//                    // Collapsed
//                    Log.d("appBarLayout", "Collapsed: ");
//                } else if (i == 0) {
//                    // Expanded
//                    gridView.setVisibility(View.INVISIBLE);
//                    txt_name_Toobar.setVisibility(View.INVISIBLE);
//                    NumberOne = 1;
//                } else {
//                    Log.d("appBarLayout", "between0: " + i);
////                    if (i < -163 && i > -201) {
////                        if (NumberOne == 0) {
////
////
////                        } else if (NumberOne == 1) {
////                            gridView.setVisibility(View.VISIBLE);
////                            txt_name_Toobar.setVisibility(View.VISIBLE);
////                            NumberOne = 0;
////                        }
////                    }
//                }
//            }
//        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint({"ResourceAsColor", "ResourceType"})
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tabOne.setText(getResources().getString(R.string.txt_Cuisine));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.restaurantblue, 0, 0);
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_entertainment));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.documentaryblack, 0, 0);
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_Shopping));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shoppingblack, 0, 0);
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_Travel));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.beachblack, 0, 0);
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        tabFive.setText(getResources().getString(R.string.txt_Education));
                        tabFive.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.schoolblack, 0, 0);
                        tab_layout.getTabAt(4).setCustomView(tabFive);
                        tabSix.setText(getResources().getString(R.string.txt_Other));
                        tabSix.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabSix.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.christmasblack, 0, 0);
                        tab_layout.getTabAt(5).setCustomView(tabSix);
                        break;
                    case 1:

                        tabOne.setText(getResources().getString(R.string.txt_Cuisine));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.restaurantblack, 0, 0);
                        tab_layout.getTabAt(0).setCustomView(tabOne);

                        tabTwo.setText(getResources().getString(R.string.txt_entertainment));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.documentaryblue, 0, 0);
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_Shopping));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shoppingblack, 0, 0);
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_Travel));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.beachblack, 0, 0);
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        tabFive.setText(getResources().getString(R.string.txt_Education));
                        tabFive.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.schoolblack, 0, 0);
                        tab_layout.getTabAt(4).setCustomView(tabFive);
                        tabSix.setText(getResources().getString(R.string.txt_Other));
                        tabSix.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabSix.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.christmasblack, 0, 0);
                        tab_layout.getTabAt(5).setCustomView(tabSix);
                        break;
                    case 2:


                        tabOne.setText(getResources().getString(R.string.txt_Cuisine));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.restaurantblack, 0, 0);
                        tab_layout.getTabAt(0).setCustomView(tabOne);

                        tabTwo.setText(getResources().getString(R.string.txt_entertainment));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));

                        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.documentaryblack, 0, 0);
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_Shopping));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.cartblue, 0, 0);
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_Travel));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.beachblack, 0, 0);
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        tabFive.setText(getResources().getString(R.string.txt_Education));
                        tabFive.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.schoolblack, 0, 0);
                        tab_layout.getTabAt(4).setCustomView(tabFive);
                        tabSix.setText(getResources().getString(R.string.txt_Other));
                        tabSix.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabSix.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.christmasblack, 0, 0);
                        tab_layout.getTabAt(5).setCustomView(tabSix);
                        break;
                    case 3:

                        tabOne.setText(getResources().getString(R.string.txt_Cuisine));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.restaurantblack, 0, 0);
                        tab_layout.getTabAt(0).setCustomView(tabOne);

                        tabTwo.setText(getResources().getString(R.string.txt_entertainment));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.documentaryblack, 0, 0);
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_Shopping));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shoppingblack, 0, 0);
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_Travel));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.beachblue, 0, 0);
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        tabFive.setText(getResources().getString(R.string.txt_Education));
                        tabFive.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.schoolblack, 0, 0);
                        tab_layout.getTabAt(4).setCustomView(tabFive);
                        tabSix.setText(getResources().getString(R.string.txt_Other));
                        tabSix.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabSix.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.christmasblack, 0, 0);
                        tab_layout.getTabAt(5).setCustomView(tabSix);
                        break;
                    case 4:
                        tabOne.setText(getResources().getString(R.string.txt_Cuisine));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.restaurantblack, 0, 0);
                        tab_layout.getTabAt(0).setCustomView(tabOne);

                        tabTwo.setText(getResources().getString(R.string.txt_entertainment));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.documentaryblack, 0, 0);
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_Shopping));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shoppingblack, 0, 0);
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_Travel));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.beachblack, 0, 0);
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        tabFive.setText(getResources().getString(R.string.txt_Education));
                        tabFive.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.schoolblue, 0, 0);
                        tab_layout.getTabAt(4).setCustomView(tabFive);
                        tabSix.setText(getResources().getString(R.string.txt_Other));
                        tabSix.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabSix.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.christmasblack, 0, 0);
                        tab_layout.getTabAt(5).setCustomView(tabSix);
                        break;
                    case 5:
                        tabOne.setText(getResources().getString(R.string.txt_Cuisine));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.restaurantblack, 0, 0);
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_entertainment));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.documentaryblack, 0, 0);
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_Shopping));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shoppingblack, 0, 0);
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_Travel));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.beachblack, 0, 0);
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        tabFive.setText(getResources().getString(R.string.txt_Education));
                        tabFive.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.schoolblack, 0, 0);
                        tab_layout.getTabAt(4).setCustomView(tabFive);
                        tabSix.setText(getResources().getString(R.string.txt_Other));
                        tabSix.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tabSix.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.starblue, 0, 0);
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
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.restaurantblue, 0, 0);
        tab_layout.getTabAt(0).setCustomView(tabOne);
        // icon Hôm nay
        tabTwo = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabTwo.setText(getResources().getString(R.string.txt_entertainment));
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.documentaryblack, 0, 0);
        tab_layout.getTabAt(1).setCustomView(tabTwo);
        // icon Tham quan
        tabThree = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabThree.setText(getResources().getString(R.string.txt_Shopping));
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shoppingblack, 0, 0);
        tab_layout.getTabAt(2).setCustomView(tabThree);
        // icon Tìm kiếm
        tabFour = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabFour.setText(getResources().getString(R.string.txt_Travel));
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.beachblack, 0, 0);
        tab_layout.getTabAt(3).setCustomView(tabFour);
        // icon giỏ hàng
        tabFive = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabFive.setText(getResources().getString(R.string.txt_Education));
        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.schoolblack, 0, 0);
        tab_layout.getTabAt(4).setCustomView(tabFive);
        tabSix = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabSix.setText(getResources().getString(R.string.txt_Other));
        tabSix.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.christmasblack, 0, 0);
        tab_layout.getTabAt(5).setCustomView(tabSix);
        // icon Tài khoản
        ChangeTab();
        mViewPager.setCurrentItem(index_change);
    }

    public void ChangeTab() {
        BaseActivity.editorTab = BaseActivity.dataLoginTab.edit();
        BaseActivity.editorTab.putInt("index", 0);
        BaseActivity.editorTab.commit();
    }
    public void setGridViewHeightBasedOnChildren(GridView gridView, int columns) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int items = listAdapter.getCount();
        int rows = 0;

        View listItem = listAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight();

        float x = 1;
        if (items > columns) {
            x = items / columns;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_btn_batdongsan_homepage:
                intentView(RealEstate.class);
                break;
        }
    }
}
