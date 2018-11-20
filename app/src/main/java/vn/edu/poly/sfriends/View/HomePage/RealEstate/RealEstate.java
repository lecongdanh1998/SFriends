package vn.edu.poly.sfriends.View.HomePage.RealEstate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Adapter.SeveraltyAdapter;
import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.Model.SeveralModel;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.View.MainActivity;

public class RealEstate extends BaseActivity implements View.OnClickListener, AdapterView
        .OnItemClickListener {
    private View view;
    private ListView listView_severalty;
    private ArrayList<SeveralModel> listSeveral;
    private SeveraltyAdapter severaltyAdapter;
    private FloatingActionButton fb_real_estate;
    private ImageView toolBar_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_estate);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        listView_severalty = findViewById(R.id.listView_severalty);
        fb_real_estate = findViewById(R.id.fb_real_estate);
        toolBar_back = findViewById(R.id.toolBar_back);
        toolBar_back.setOnClickListener(this);
    }

    private void initData() {
        listSeveral = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listSeveral.add(new SeveralModel(String.valueOf(i), "Entire apartment - 1 bed",
                    "champs-ElysEes Golden triangle flat with mezzanine", "đ1,607,619 Per night -" +
                    " Free cancellation", "80"));
        }
        severaltyAdapter = new SeveraltyAdapter(listSeveral, this);
        listView_severalty.setAdapter(severaltyAdapter);
    }

    private void initEvent() {
        fb_real_estate.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RealEstate.this, MainActivity.class);
        intent.putExtra("screen", "Homepage");
        startActivity(intent);
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fb_real_estate:
                Intent intent = new Intent(RealEstate.this, SearchMap.class);
                startActivity(intent);
                break;
            case R.id.toolBar_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DetailsEstimate.class);
        startActivity(intent);
    }
}
