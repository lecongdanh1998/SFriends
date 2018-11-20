package vn.edu.poly.sfriends.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.sfriends.View.HomePage.RealEstate.DetailsEstimate;
import vn.edu.poly.sfriends.Model.SeveralModel;
import vn.edu.poly.sfriends.Model.SlideImageModel;
import vn.edu.poly.sfriends.R;

//ratingBar_severalty,img_favorite_severalty,txt_address_severalty,txt_price_severalty,
// txt_description_severalty
public class SeveraltyAdapter extends BaseAdapter {
    List<SeveralModel> severalModels;
    Context context;
    private ArrayList<SlideImageModel> listSlide;
    private SlideImageAdapter slideImageAdapter;

    public SeveraltyAdapter(List<SeveralModel> severalModels, Context context) {
        this.severalModels = severalModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return severalModels.size();
    }

    @Override
    public Object getItem(int position) {
        return severalModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        RatingBar ratingBar_severalty;
        ImageView img_favorite_severalty;
        TextView txt_address_severalty, txt_price_severalty, txt_description_severalty,
                ratingBar_count_severalty;
        ViewPager viewPager_severalty;
        LinearLayout layout_dots_severalty;
        RelativeLayout layout_item_estimate;
        SlideImageAdapter slideIntroAdapter;
        ArrayList<SlideImageModel> arrayListIntro;
        TextView[] txt_dots;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        listSlide = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            listSlide.add(new SlideImageModel("https://media.nbcphiladelphia" +
                    ".com/images/1200*675/watcher+house.jpg"));
        }
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.row_item_real_estate, null);

            viewHolder.ratingBar_severalty = convertView.findViewById(R.id.ratingBar_severalty);
            viewHolder.txt_address_severalty = convertView.findViewById(R.id.txt_address_severalty);
            viewHolder.txt_price_severalty = convertView.findViewById(R.id.txt_price_severalty);
            viewHolder.txt_description_severalty = convertView.findViewById(R.id
                    .txt_description_severalty);
            viewHolder.ratingBar_count_severalty = convertView.findViewById(R.id
                    .ratingBar_count_severalty);
            viewHolder.viewPager_severalty = convertView.findViewById(R.id.viewPager_severalty);
            viewHolder.img_favorite_severalty = convertView.findViewById(R.id
                    .img_favorite_severalty);
            viewHolder.layout_dots_severalty = convertView.findViewById(R.id.layout_dots_severalty);
            viewHolder.layout_item_estimate = convertView.findViewById(R.id.layout_item_estimate);
            addDotIndicator(0, viewHolder);
            viewHolder.viewPager_severalty.addOnPageChangeListener(new ViewPager
                    .OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    addDotIndicator(i, viewHolder);
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SeveralModel model = severalModels.get(position);
        viewHolder.ratingBar_severalty.setRating(4);
        viewHolder.txt_address_severalty.setText(model.getName());
        viewHolder.txt_price_severalty.setText(model.getPrice());
        viewHolder.txt_description_severalty.setText(model.getDescription());
        viewHolder.ratingBar_count_severalty.setText(model.getRating());
        viewHolder.layout_item_estimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsEstimate.class);
                context.startActivity(intent);
            }
        });
                //slide
                slideImageAdapter = new SlideImageAdapter(context, listSlide);
        viewHolder.viewPager_severalty.setAdapter(slideImageAdapter);
        return convertView;
    }

    private void addDotIndicator(int position, ViewHolder viewHolder) {
        viewHolder.txt_dots = new TextView[listSlide.size()];
        viewHolder.layout_dots_severalty.removeAllViews();
        for (int i = 0; i < viewHolder.txt_dots.length; i++) {
            viewHolder.txt_dots[i] = new TextView(context);
            viewHolder.txt_dots[i].setText(Html.fromHtml("&#8226;"));
            viewHolder.txt_dots[i].setTextSize(35);
            viewHolder.txt_dots[i].setTextColor(Color.WHITE);
            viewHolder.layout_dots_severalty.addView(viewHolder.txt_dots[i]);
        }

        if (viewHolder.txt_dots.length > 0) {
            viewHolder.txt_dots[position].setTextColor(Color.RED);
        }
    }
}
