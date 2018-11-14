package vn.edu.poly.sfriends.Component.CustomViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Model.HotDealObject;
import vn.edu.poly.sfriends.R;

public class CardFragment extends Fragment {
    private CardView cardView;
    public static Fragment getInstance(int position,String image) {
        CardFragment f = new CardFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("images",image);
        f.setArguments(args);
        return f;
    }

    public CardFragment() {
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_deal_layout, container, false);
        cardView = (CardView) view.findViewById(R.id.direction_card_view);
        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);
        ImageView images = (ImageView) view.findViewById(R.id.hot_deal_food_image);
        Picasso.get()
                .load(getArguments().getString("images"))
                .into(images);

        return view;
    }

    public CardView getCardView() {
        return cardView;
    }
}
