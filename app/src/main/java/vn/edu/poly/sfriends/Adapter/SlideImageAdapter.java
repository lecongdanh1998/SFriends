package vn.edu.poly.sfriends.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.sfriends.Model.SlideImageModel;
import vn.edu.poly.sfriends.R;

public class SlideImageAdapter extends PagerAdapter {

    Context context;
    List<SlideImageModel> listSlideImage;

    public SlideImageAdapter(Context context, ArrayList<SlideImageModel> listSlideImage) {
        this.context = context;
        this.listSlideImage = listSlideImage;
    }

    @Override
    public int getCount() {
        return listSlideImage.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (View) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        View v = null;
        v = layoutInflater.inflate(R.layout.item_slie_image, container, false);
        ImageView img_intro = v.findViewById(R.id.img_slide);
        Picasso.get().load(listSlideImage.get(position).getImage()).fit().error(R.drawable
                .places_ic_search).into(img_intro);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
