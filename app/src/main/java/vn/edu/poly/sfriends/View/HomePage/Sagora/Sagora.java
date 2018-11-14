package vn.edu.poly.sfriends.View.HomePage.Sagora;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Adapter.AdapterTabLayoutFrangment;
import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.View.HomePage.Sagora.classifieds.classifieds;
import vn.edu.poly.sfriends.View.HomePage.Sagora.freelancers.freelancers;
import vn.edu.poly.sfriends.View.HomePage.Sagora.services.services;
import vn.edu.poly.sfriends.View.HomePage.Sarena.ALBUMS.albums;
import vn.edu.poly.sfriends.View.HomePage.Sarena.ALL_CONVERSATION.all_conversation;
import vn.edu.poly.sfriends.View.HomePage.Sarena.GROUPS.groups;
import vn.edu.poly.sfriends.View.HomePage.Sarena.POLL.poll;
import vn.edu.poly.sfriends.View.HomePage.Sarena.Sarena;
import vn.edu.poly.sfriends.View.MainActivity;

public class Sagora extends BaseActivity implements View.OnClickListener {
    public TabLayout tab_layout;
    public ViewPager mViewPager;
    ArrayList listFragment;
    ArrayList<String> listTitle;
    AdapterTabLayoutFrangment adapterTabLayoutFrangment;
    public TextView tabOne, tabTwo, tabThree;
    public int index_change;
    ImageView img_back_sagora;
    ImageView img_filter_ToobarSagora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sagora);
        initControl();
        initData();
        initOnClick();
        createTabIcons();
    }
    private void initOnClick() {
        img_back_sagora.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint({"ResourceAsColor", "ResourceType"})
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        img_filter_ToobarSagora.setVisibility(View.GONE);
                        tabOne.setText(getResources().getString(R.string.txt_services));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_freelancers));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_classifieds));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        // icon giỏ hàng
                        break;
                    case 1:
                        img_filter_ToobarSagora.setVisibility(View.GONE);

                        tabOne.setText(getResources().getString(R.string.txt_services));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(0).setCustomView(tabOne);

                        tabTwo.setText(getResources().getString(R.string.txt_freelancers));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_classifieds));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        // icon giỏ hàng
                        break;
                    case 2:

                        img_filter_ToobarSagora.setVisibility(View.VISIBLE);

                        tabOne.setText(getResources().getString(R.string.txt_services));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_freelancers));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_classifieds));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon giỏ hàng
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Sagora.this, MainActivity.class);
        intent.putExtra("screen", "Homepage");
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }

    private void initData() {
        listFragment = new ArrayList();
        listFragment.add(new services());
        listFragment.add(new freelancers());
        listFragment.add(new classifieds());
        listTitle = new ArrayList<>();

        listTitle.add(getResources().getString(R.string.txt_services));
        listTitle.add(getResources().getString(R.string.txt_freelancers));
        listTitle.add(getResources().getString(R.string.txt_classifieds));
        adapterTabLayoutFrangment = new AdapterTabLayoutFrangment(
                getSupportFragmentManager(),this, listTitle, listFragment
        );

        mViewPager.setAdapter(adapterTabLayoutFrangment);
    }

    private void initControl() {
        img_filter_ToobarSagora = findViewById(R.id.img_filter_ToobarSagora);
        tab_layout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        img_back_sagora = findViewById(R.id.img_back_ToobarSagora);
        tab_layout.setupWithViewPager(mViewPager);
    }


    private void intentView(Class c) {
        Intent intent = new Intent(Sagora.this, c);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }
    private void createTabIcons() {
        img_filter_ToobarSagora.setVisibility(View.GONE);
        tabOne = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tabsarena, null);
        tabOne.setText(getResources().getString(R.string.txt_services));
        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayout));
        tab_layout.getTabAt(0).setCustomView(tabOne);
        // icon Hôm nay
        tabTwo = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tabsarena, null);
        tabTwo.setText(getResources().getString(R.string.txt_freelancers));
        tab_layout.getTabAt(1).setCustomView(tabTwo);
        // icon Tham quan
        tabThree = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tabsarena, null);
        tabThree.setText(getResources().getString(R.string.txt_classifieds));
        tab_layout.getTabAt(2).setCustomView(tabThree);
        // icon Tìm kiếm
        // icon giỏ hàng
        // icon Tài khoản
        mViewPager.setCurrentItem(index_change);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_ToobarSagora:
                onBackPressed();
                break;
        }
    }
}
