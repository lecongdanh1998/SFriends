package vn.edu.poly.sfriends.View.User.Medal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Adapter.AdapterTabLayoutFrangment;
import vn.edu.poly.sfriends.Adapter.GirdViewAdapter_1_Homepage;
import vn.edu.poly.sfriends.Adapter.UuDaiAdapter;
import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.Model.GirdViewContrucTor_1_Homepage;
import vn.edu.poly.sfriends.Model.UuDaiContructor;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.View.HomePage.Sarena.ALBUMS.albums;
import vn.edu.poly.sfriends.View.HomePage.Sarena.ALL_CONVERSATION.all_conversation;
import vn.edu.poly.sfriends.View.HomePage.Sarena.GROUPS.groups;
import vn.edu.poly.sfriends.View.HomePage.Sarena.POLL.poll;
import vn.edu.poly.sfriends.View.MainActivity;
import vn.edu.poly.sfriends.View.User.Medal.Gift_store.GiftStoreActivity;
import vn.edu.poly.sfriends.View.User.Medal.HistoryPoint.HistoryPointActivity;
import vn.edu.poly.sfriends.View.User.Medal.Member.Member;
import vn.edu.poly.sfriends.View.User.Medal.SVIP.SVIP;
import vn.edu.poly.sfriends.View.User.Medal.Silver.Silver;
import vn.edu.poly.sfriends.View.User.Medal.Standard.Standard;
import vn.edu.poly.sfriends.View.User.UserActivity;

public class MemberActivity extends BaseActivity implements View.OnClickListener {
    AppBarLayout appBar;
    TextView txt_titlestatus_member,txt_historyPoint_member,txt_viewall_member;
    ImageView img_back_member;
    ArrayList<UuDaiContructor> arrayList;
    GridView gridView;
    UuDaiAdapter adapter;
    public TabLayout tab_layout;
    public ViewPager mViewPager;
    ArrayList listFragment;
    ArrayList<String> listTitle;
    AdapterTabLayoutFrangment adapterTabLayoutFrangment;
    public TextView tabOne, tabTwo, tabThree, tabFour;
    public int index_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        initControl();
        initData();
        initGirdView();
        initOnClick();
        createTabIcons();
        
    }

    private void initGirdView() {
        arrayList = new ArrayList<>();
        arrayList.add(new UuDaiContructor("https://png.icons8.com/ios/70/B382C7/exit.png"));
        arrayList.add(new UuDaiContructor("https://png.icons8.com/metro/70/2ecc71/qr-code.png"));
        arrayList.add(new UuDaiContructor("https://png.icons8.com/ios/40/EC0890/gift.png"));
        arrayList.add(new UuDaiContructor("https://png.icons8.com/ios/70/B382C7/exit.png"));
        adapter = new UuDaiAdapter(arrayList, this);
        gridView.setAdapter(adapter);
        setGridViewHeightBasedOnChildren(gridView, 4);
        adapter.notifyDataSetChanged();
    }

    private void createTabIcons() {
        tabOne = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tabsarena, null);
        tabOne.setText(getResources().getString(R.string.txt_Member));
        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayout));
        tab_layout.getTabAt(0).setCustomView(tabOne);
        // icon Hôm nay
        tabTwo = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tabsarena, null);
        tabTwo.setText(getResources().getString(R.string.txt_standard));
        tab_layout.getTabAt(1).setCustomView(tabTwo);
        // icon Tham quan
        tabThree = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tabsarena, null);
        tabThree.setText(getResources().getString(R.string.txt_Silver));
        tab_layout.getTabAt(2).setCustomView(tabThree);
        // icon Tìm kiếm
        tabFour = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tabsarena, null);
        tabFour.setText(getResources().getString(R.string.txt_vip));
        tab_layout.getTabAt(3).setCustomView(tabFour);
        // icon giỏ hàng
        // icon Tài khoản
        mViewPager.setCurrentItem(index_change);
    }

    private void initControl() {
        txt_viewall_member = findViewById(R.id.txt_viewall_member);
        txt_historyPoint_member = findViewById(R.id.txt_historyPoint_member);
        tab_layout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        tab_layout.setupWithViewPager(mViewPager);
        gridView = findViewById(R.id.gridView_Member);
        appBar = findViewById(R.id.appBar);
        img_back_member = findViewById(R.id.img_back_member);
        txt_titlestatus_member = findViewById(R.id.txt_titlestatus_member);
    }

    private void initData() {
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    txt_titlestatus_member.setText(getResources().getString(R.string.txt_GOLD)+" : 2379"+" "+getResources().getString(R.string.txt_point));
                    // Collapsed
                } else if (verticalOffset == 0) {
                    // Expanded
                    txt_titlestatus_member.setText(getResources().getString(R.string.txt_redeempoints));
                } else {
                    // Somewhere in between
                }
            }
        });

        listFragment = new ArrayList();
        listFragment.add(new Member());
        listFragment.add(new Standard());
        listFragment.add(new Silver());
        listFragment.add(new SVIP());
        listTitle = new ArrayList<>();

        listTitle.add(getResources().getString(R.string.txt_Member));
        listTitle.add(getResources().getString(R.string.txt_standard));
        listTitle.add(getResources().getString(R.string.txt_Silver));
        listTitle.add(getResources().getString(R.string.txt_vip));
        adapterTabLayoutFrangment = new AdapterTabLayoutFrangment(
                getSupportFragmentManager(),this, listTitle, listFragment
        );

        mViewPager.setAdapter(adapterTabLayoutFrangment);





    }

    private void initOnClick() {
        txt_historyPoint_member.setOnClickListener(this);
        txt_viewall_member.setOnClickListener(this);
        img_back_member.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint({"ResourceAsColor", "ResourceType"})
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tabOne.setText(getResources().getString(R.string.txt_Member));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_standard));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_Silver));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_vip));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        break;
                    case 1:

                        tabOne.setText(getResources().getString(R.string.txt_Member));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(0).setCustomView(tabOne);

                        tabTwo.setText(getResources().getString(R.string.txt_standard));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_Silver));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_vip));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        break;
                    case 2:


                        tabOne.setText(getResources().getString(R.string.txt_Member));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_standard));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_Silver));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_vip));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        break;
                    case 3:

                        tabOne.setText(getResources().getString(R.string.txt_Member));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(0).setCustomView(tabOne);

                        tabTwo.setText(getResources().getString(R.string.txt_standard));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_Silver));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(2).setCustomView(tabThree);
                        // icon Tìm kiếm
                        tabFour.setText(getResources().getString(R.string.txt_vip));
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


    private void intentView(Class c) {
        Intent intent = new Intent(MemberActivity.this, c);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }


    @Override
    public void onBackPressed() {
        intentView(UserActivity.class);
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_member:
                onBackPressed();
                break;
            case R.id.txt_historyPoint_member:
                intentView(HistoryPointActivity.class);
                break;
            case R.id.txt_viewall_member:
                intentView(GiftStoreActivity.class);
                break;
        }
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
}
