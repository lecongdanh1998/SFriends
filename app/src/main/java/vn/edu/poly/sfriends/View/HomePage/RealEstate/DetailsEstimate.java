package vn.edu.poly.sfriends.View.HomePage.RealEstate;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Adapter.SuggestAdapter;
import vn.edu.poly.sfriends.Model.SeveralModel;
import vn.edu.poly.sfriends.R;

public class DetailsEstimate extends AppCompatActivity implements View.OnClickListener {
    private TextView txt_contents_details, txt_des_address_details, txt_comment_details,
            txt_description_rules_details;
    private Button btn_tour_this_home;
    private RelativeLayout menu_back_details_estimate;
    private RecyclerView recyclerView;
    private ArrayList<SeveralModel> listSeveral;
    private SuggestAdapter suggestAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_estimate);
        initView();
        initEventButton();
        initData();
    }

    private void initView() {
        txt_contents_details = findViewById(R.id.txt_contents_details);
        txt_des_address_details = findViewById(R.id.txt_des_address_details);
        txt_comment_details = findViewById(R.id.txt_comment_details);
        txt_description_rules_details = findViewById(R.id.txt_description_rules_details);

        txt_contents_details.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular" +
                ".ttf"));
        txt_des_address_details.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular" +
                ".ttf"));
        txt_comment_details.setTypeface(Typeface.createFromAsset(getAssets(), "roboto_regular" +
                ".ttf"));
        txt_description_rules_details.setTypeface(Typeface.createFromAsset(getAssets(),
                "roboto_regular.ttf"));

        btn_tour_this_home = findViewById(R.id.btn_tour_this_home);
        menu_back_details_estimate = findViewById(R.id.menu_back_details_estimate);
        recyclerView = findViewById(R.id.recycler_suggest_details);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initEventButton() {
        btn_tour_this_home.setOnClickListener(this);
        menu_back_details_estimate.setOnClickListener(this);
    }

    private void initData() {
        listSeveral = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listSeveral.add(new SeveralModel(String.valueOf(i), getResources().getString(R.string
                    .title_suggest),
                    "champs-ElysEes Golden triangle flat with mezzanine", "đ 1,607,619 per " +
                    "night", "80"));
        }
        suggestAdapter = new SuggestAdapter(listSeveral, this);
        recyclerView.setAdapter(suggestAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tour_this_home:
                Intent intent = new Intent(DetailsEstimate.this, DetailsListImage.class);
                startActivity(intent);
                break;
            case R.id.menu_back_details_estimate:
                onBackPressed();
                break;
        }
    }
}
