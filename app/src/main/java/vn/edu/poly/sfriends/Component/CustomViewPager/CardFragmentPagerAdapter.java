package vn.edu.poly.sfriends.Component.CustomViewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.sfriends.Model.HotDealObject;

public class CardFragmentPagerAdapter extends FragmentStatePagerAdapter implements CardAdapter {
    private List<CardFragment> fragments;
    private float baseElevation;
    private ArrayList<HotDealObject> arrayList;

    public CardFragmentPagerAdapter(FragmentManager fm,float baseElevation,ArrayList<HotDealObject> arrayList) {
        super(fm);
        fragments = new ArrayList<>();
        this.baseElevation = baseElevation;
        this.arrayList = arrayList;

        for(int i = 0; i< arrayList.size(); i++){
            addCardFragment(new CardFragment());
        }
    }

    @Override
    public Fragment getItem(int i) {
        return CardFragment.getInstance(i,arrayList.get(i).getImages());
    }

    @Override
    public float getBaseElevation() {
        return baseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return fragments.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addCardFragment(CardFragment fragment) {
        fragments.add(fragment);
    }
}
