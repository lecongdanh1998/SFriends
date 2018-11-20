package vn.edu.poly.sfriends.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Model.SlideImageModel;
import vn.edu.poly.sfriends.R;

public class CustomInfoMarkerAdapter implements GoogleMap.InfoWindowAdapter {

    private final View view;
    private final Context context;
    private RatingBar ratingBar_severalty;
    private ImageView img_favorite_severalty;
    private TextView txt_address_severalty, txt_price_severalty, txt_description_severalty,
            ratingBar_count_severalty;
    private ViewPager viewPager_severalty;
    private LinearLayout layout_dots_severalty;
    private SlideImageAdapter slideIntroAdapter;
    private ArrayList<SlideImageModel> arrayListIntro;
    private TextView[] txt_dots;
    private ArrayList<SlideImageModel> listSlide;
    private SlideImageAdapter slideImageAdapter;

    public CustomInfoMarkerAdapter(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.custom_info_windown, null);
        this.context = context;
    }

    private void displayData(Marker marker,View view){
        listSlide = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            listSlide.add(new SlideImageModel("https://media.nbcphiladelphia.com/images/1200*675/watcher+house.jpg"));
        }
        ratingBar_severalty = view.findViewById(R.id.ratingBar_severalty);
        txt_address_severalty = view.findViewById(R.id.txt_address_severalty);
        txt_price_severalty = view.findViewById(R.id.txt_price_severalty);
        txt_description_severalty = view.findViewById(R.id
                    .txt_description_severalty);
            ratingBar_count_severalty = view.findViewById(R.id
                    .ratingBar_count_severalty);
            viewPager_severalty = view.findViewById(R.id.viewPager_severalty);
            img_favorite_severalty = view.findViewById(R.id
                    .img_favorite_severalty);
            layout_dots_severalty = view.findViewById(R.id.layout_dots_severalty);
            addDotIndicator(0);
            viewPager_severalty.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    addDotIndicator(i);
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });
        ratingBar_severalty.setRating(4);
        txt_address_severalty.setText(marker.getTitle());
        try {
            JSONObject object = new JSONObject(marker.getSnippet());
            txt_price_severalty.setText(object.getString("price"));
            txt_description_severalty.setText(object.getString("description"));
            ratingBar_count_severalty.setText(object.getString("rating"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
       //slide
        slideImageAdapter = new SlideImageAdapter(context, listSlide);
        viewPager_severalty.setAdapter(slideImageAdapter);
    }

    private void addDotIndicator(int position) {
        txt_dots = new TextView[listSlide.size()];
        layout_dots_severalty.removeAllViews();
        for (int i = 0; i < txt_dots.length; i++) {
            txt_dots[i] = new TextView(context);
            txt_dots[i].setText(Html.fromHtml("&#8226;"));
            txt_dots[i].setTextSize(35);
            txt_dots[i].setTextColor(Color.WHITE);
            layout_dots_severalty.addView(txt_dots[i]);
        }

        if (txt_dots.length > 0) {
            txt_dots[position].setTextColor(Color.BLUE);
        }
    }

    @Override
    public View getInfoWindow(Marker marker) {
        displayData(marker, view);
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        displayData(marker, view);
        return view;
    }
}
