package vn.edu.poly.sfriends.View.User.Medal.HistoryPoint;

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
import vn.edu.poly.sfriends.View.User.Medal.HistoryPoint.Points_received.Points_received;
import vn.edu.poly.sfriends.View.User.Medal.HistoryPoint.Points_used.Points_used;
import vn.edu.poly.sfriends.View.User.Medal.Member.Member;
import vn.edu.poly.sfriends.View.User.Medal.MemberActivity;
import vn.edu.poly.sfriends.View.User.Medal.SVIP.SVIP;
import vn.edu.poly.sfriends.View.User.Medal.Silver.Silver;
import vn.edu.poly.sfriends.View.User.Medal.Standard.Standard;

public class HistoryPointActivity extends BaseActivity implements View.OnClickListener {
    public TabLayout tab_layout;
    public ViewPager mViewPager;
    ArrayList listFragment;
    ArrayList<String> listTitle;
    AdapterTabLayoutFrangment adapterTabLayoutFrangment;
    public TextView tabOne, tabTwo;
    public int index_change;
    ImageView img_back_historypoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_point);
        initControl();
        initData();
        initOnClick();
        createTabIcons();
    }

    private void initOnClick() {
        img_back_historypoint.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint({"ResourceAsColor", "ResourceType"})
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tabOne.setText(getResources().getString(R.string.txt_Pointsreceived));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_Pointsused));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        break;
                    case 1:

                        tabOne.setText(getResources().getString(R.string.txt_Pointsreceived));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(0).setCustomView(tabOne);

                        tabTwo.setText(getResources().getString(R.string.txt_Pointsused));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void intentView(Class c) {
        Intent intent = new Intent(HistoryPointActivity.this, c);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    private void initData() {
        listFragment = new ArrayList();
        listFragment.add(new Points_received());
        listFragment.add(new Points_used());
        listTitle = new ArrayList<>();

        listTitle.add(getResources().getString(R.string.txt_Pointsreceived));
        listTitle.add(getResources().getString(R.string.txt_Pointsused));
        adapterTabLayoutFrangment = new AdapterTabLayoutFrangment(
                getSupportFragmentManager(),this, listTitle, listFragment
        );

        mViewPager.setAdapter(adapterTabLayoutFrangment);
    }

    private void initControl() {
        tab_layout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        tab_layout.setupWithViewPager(mViewPager);
        img_back_historypoint = findViewById(R.id.img_back_historypoint);
    }

    private void createTabIcons() {
        tabOne = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tabsarena, null);
        tabOne.setText(getResources().getString(R.string.txt_Pointsreceived));
        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayout));
        tab_layout.getTabAt(0).setCustomView(tabOne);
        // icon Hôm nay
        tabTwo = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tabsarena, null);
        tabTwo.setText(getResources().getString(R.string.txt_Pointsused));
        tab_layout.getTabAt(1).setCustomView(tabTwo);
        // icon Tham quan
        mViewPager.setCurrentItem(index_change);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_historypoint:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        intentView(MemberActivity.class);
        super.onBackPressed();
    }
}
