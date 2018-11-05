package vn.edu.poly.sfriends.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vn.edu.poly.sfriends.Model.HotDealObject;
import vn.edu.poly.sfriends.R;

public class CustomViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<HotDealObject> hotDealList;
    private LayoutInflater layoutInflater;
    public CustomViewPagerAdapter(Context context, List<HotDealObject> hotDealList) {
        this.context = context;
        this.hotDealList = hotDealList;
        this.layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return hotDealList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == ((View)o);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.hot_deal_layout, container, false);
        HotDealObject mHotDealObject = hotDealList.get(position);
        ImageView favoriteIcon = (ImageView)view.findViewById(R.id.hot_deal_food_image);
        Picasso.get()
                .load(mHotDealObject.getImages())
                .into(favoriteIcon);
        container.addView(view);
        return view;
    }
}
