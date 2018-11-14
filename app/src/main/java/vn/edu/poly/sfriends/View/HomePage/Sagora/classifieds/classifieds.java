package vn.edu.poly.sfriends.View.HomePage.Sagora.classifieds;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Adapter.AdapterTabLayoutFrangment;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.View.HomePage.Sagora.classifieds.other.other;
import vn.edu.poly.sfriends.View.HomePage.Sagora.classifieds.rent.rent;
import vn.edu.poly.sfriends.View.HomePage.Sagora.classifieds.sell.sell;
import vn.edu.poly.sfriends.View.HomePage.Sagora.classifieds.wanted.wanted;
import vn.edu.poly.sfriends.View.HomePage.Sagora.freelancers.freelancers;
import vn.edu.poly.sfriends.View.HomePage.Sagora.services.services;

public class classifieds extends Fragment {
    private View view;
    public TabLayout tab_layout;
    public ViewPager mViewPager;
    ArrayList listFragment;
    AdapterTabLayoutFrangment adapterTabLayoutFrangment;
    ArrayList<String> listTitle;
    public int index_change;
    public TextView tabOne, tabTwo, tabThree,tabFour;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_classifieds, container, false);
        initControl();
        initData();
        initEvent();
        initOnClick();
        createTabIcons();
        return view;
    }

    private void initEvent() {
    }

    private void initOnClick() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint({"ResourceAsColor", "ResourceType"})
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tabOne.setText(getResources().getString(R.string.txt_sell));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_rent));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_wanted));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(2).setCustomView(tabThree);

                        tabFour.setText(getResources().getString(R.string.txt_other));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon Tìm kiếm
                        // icon giỏ hàng
                        break;
                    case 1:

                        tabOne.setText(getResources().getString(R.string.txt_sell));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(0).setCustomView(tabOne);

                        tabTwo.setText(getResources().getString(R.string.txt_rent));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_wanted));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(2).setCustomView(tabThree);

                        tabFour.setText(getResources().getString(R.string.txt_other));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon Tìm kiếm
                        // icon giỏ hàng
                        break;
                    case 2:

                        tabOne.setText(getResources().getString(R.string.txt_sell));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_rent));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_wanted));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayout));
                        tab_layout.getTabAt(2).setCustomView(tabThree);

                        tabFour.setText(getResources().getString(R.string.txt_other));
                        tabFour.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(3).setCustomView(tabFour);
                        // icon giỏ hàng
                        break;
                    case 3:

                        tabOne.setText(getResources().getString(R.string.txt_sell));
                        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(0).setCustomView(tabOne);
                        tabTwo.setText(getResources().getString(R.string.txt_rent));
                        tabTwo.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(1).setCustomView(tabTwo);
                        // icon Tham quan
                        tabThree.setText(getResources().getString(R.string.txt_wanted));
                        tabThree.setTextColor(getResources().getColor(R.color.color_text_tablayoutactivity));
                        tab_layout.getTabAt(2).setCustomView(tabThree);

                        tabFour.setText(getResources().getString(R.string.txt_other));
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

    private void initControl() {
        tab_layout = (TabLayout) view.findViewById(R.id.tabLayoutClassifieds);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPagerClassifieds);
        tab_layout.setupWithViewPager(mViewPager);
    }

    private void initData() {
        listFragment = new ArrayList();
        listFragment.add(new sell());
        listFragment.add(new rent());
        listFragment.add(new wanted());
        listFragment.add(new other());
        listTitle = new ArrayList<>();

        listTitle.add(getResources().getString(R.string.txt_sell));
        listTitle.add(getResources().getString(R.string.txt_rent));
        listTitle.add(getResources().getString(R.string.txt_wanted));
        listTitle.add(getResources().getString(R.string.txt_other));
        adapterTabLayoutFrangment = new AdapterTabLayoutFrangment(
                getChildFragmentManager(),getContext(), listTitle, listFragment
        );

        mViewPager.setAdapter(adapterTabLayoutFrangment);
    }
    private void createTabIcons() {
        tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tabsarena, null);
        tabOne.setText(getResources().getString(R.string.txt_sell));
        tabOne.setTextColor(getResources().getColor(R.color.color_text_tablayout));
        tab_layout.getTabAt(0).setCustomView(tabOne);
        // icon Hôm nay
        tabTwo = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tabsarena, null);
        tabTwo.setText(getResources().getString(R.string.txt_rent));
        tab_layout.getTabAt(1).setCustomView(tabTwo);
        // icon Tham quan
        tabThree = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tabsarena, null);
        tabThree.setText(getResources().getString(R.string.txt_wanted));
        tab_layout.getTabAt(2).setCustomView(tabThree);
        // icon Tìm kiếm
        // icon giỏ hàng
        // icon Tài khoản
        tabFour = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tabsarena, null);
        tabFour.setText(getResources().getString(R.string.txt_other));
        tab_layout.getTabAt(3).setCustomView(tabFour);
        mViewPager.setCurrentItem(index_change);
    }
}

