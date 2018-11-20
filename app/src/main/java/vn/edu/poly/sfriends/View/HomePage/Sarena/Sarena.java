package vn.edu.poly.sfriends.View.HomePage.Sarena;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Adapter.AdapterTabLayoutFrangment;
import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.View.HomePage.Cuisine.Cuisine;
import vn.edu.poly.sfriends.View.HomePage.Education.Education;
import vn.edu.poly.sfriends.View.HomePage.Entertainment.Entertainment;
import vn.edu.poly.sfriends.View.HomePage.Other.Other;
import vn.edu.poly.sfriends.View.HomePage.Sarena.ALBUMS.albums;
import vn.edu.poly.sfriends.View.HomePage.Sarena.ALL_CONVERSATION.all_conversation;
import vn.edu.poly.sfriends.View.HomePage.Sarena.GROUPS.groups;
import vn.edu.poly.sfriends.View.HomePage.Sarena.POLL.poll;
import vn.edu.poly.sfriends.View.HomePage.Shopping.Shopping;
import vn.edu.poly.sfriends.View.HomePage.Travel.Travel;
import vn.edu.poly.sfriends.View.MainActivity;

public class Sarena extends BaseActivity implements OnClickListener {
    public TabLayout tab_layout;
    public ViewPager mViewPager;
    ArrayList listFragment;
    ArrayList<String> listTitle;
    AdapterTabLayoutFrangment adapterTabLayoutFrangment;
    public TextView tabOne, tabTwo, tabThree, tabFour;
    public int index_change;
    ImageView img_back_serena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sarena);
        initControl();
        initData();
        initOnClick();
        createTabIcons();
    }

    private void initOnClick() {
        img_back_serena.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint({"ResourceAsColor", "ResourceType"})
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tabOne.setText(getResources().getString(R.string.txt_all_conversation));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_groups));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_albums));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_poll));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        break;
                    case 1:

                        tabOne.setText(getResources().getString(R.string.txt_all_conversation));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(0).setCustomView(tabOne);

                        tabTwo.setText(getResources().getString(R.string.txt_groups));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_albums));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_poll));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        break;
                    case 2:


                        tabOne.setText(getResources().getString(R.string.txt_all_conversation));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_groups));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_albums));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_poll));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        break;
                    case 3:

                        tabOne.setText(getResources().getString(R.string.txt_all_conversation));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(0).setCustomView(tabOne);

                        tabTwo.setText(getResources().getString(R.string.txt_groups));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_albums));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_poll));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tab_layout.getTabAt(3).setCustomView(tabFour);
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
        Intent intent = new Intent(Sarena.this, MainActivity.class);
        intent.putExtra("screen", "Homepage");
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }

    private void initData() {
        listFragment = new ArrayList();
        listFragment.add(new all_conversation());
        listFragment.add(new groups());
        listFragment.add(new albums());
        listFragment.add(new poll());
        listTitle = new ArrayList<>();

        listTitle.add(getResources().getString(R.string.txt_all_conversation));
        listTitle.add(getResources().getString(R.string.txt_groups));
        listTitle.add(getResources().getString(R.string.txt_albums));
        listTitle.add(getResources().getString(R.string.txt_poll));
        adapterTabLayoutFrangment = new AdapterTabLayoutFrangment(
                getSupportFragmentManager(),this, listTitle, listFragment
        );

        mViewPager.setAdapter(adapterTabLayoutFrangment);
    }

    private void initControl() {
        tab_layout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        tab_layout.setupWithViewPager(mViewPager);
        img_back_serena = findViewById(R.id.img_back_ToobarSarena);
    }


    private void intentView(Class c) {
        Intent intent = new Intent(Sarena.this, c);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }
    private void createTabIcons() {
        tabOne = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tabsarena, null);
        tabOne.setText(getResources().getString(R.string.txt_all_conversation));
        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayout));
        tab_layout.getTabAt(0).setCustomView(tabOne);
        // icon Hôm nay
        tabTwo = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tabsarena, null);
        tabTwo.setText(getResources().getString(R.string.txt_groups));
        tab_layout.getTabAt(1).setCustomView(tabTwo);
        // icon Tham quan
        tabThree = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tabsarena, null);
        tabThree.setText(getResources().getString(R.string.txt_albums));
        tab_layout.getTabAt(2).setCustomView(tabThree);
        // icon Tìm kiếm
        tabFour = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tabsarena, null);
        tabFour.setText(getResources().getString(R.string.txt_poll));
        tab_layout.getTabAt(3).setCustomView(tabFour);
        // icon giỏ hàng
        // icon Tài khoản
        mViewPager.setCurrentItem(index_change);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_ToobarSarena:
                onBackPressed();
                break;
        }
    }
}
