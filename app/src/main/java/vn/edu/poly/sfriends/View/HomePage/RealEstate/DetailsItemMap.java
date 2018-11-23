package vn.edu.poly.sfriends.View.HomePage.RealEstate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.poly.sfriends.Model.ResultSearchModel;
import vn.edu.poly.sfriends.R;

public class DetailsItemMap extends AppCompatActivity implements View.OnClickListener {

    private TextView txt_address_details_map, txt_time_open_details_map, txt_phone_details_map,
            txt_address2_details_map, txt_direction_details,
            txt_description_details_map, txt_website_details_map;
    private ResultSearchModel resultSearchModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_item_map);
        try {
            resultSearchModel = (ResultSearchModel) getIntent().getBundleExtra("data")
                    .getSerializable("object");
        } catch (Exception e) {
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
        initView();
        initEventButton();
        initData();
    }

    private void initView() {
        txt_address_details_map = findViewById(R.id.txt_address_details_map);
        txt_address2_details_map = findViewById(R.id.txt_address2_details_map);
        txt_description_details_map = findViewById(R.id.txt_description_details_map);
        txt_phone_details_map = findViewById(R.id.txt_phone_details_map);
        txt_time_open_details_map = findViewById(R.id.txt_time_open_details_map);
        txt_website_details_map = findViewById(R.id.txt_website_details_map);
        txt_direction_details = findViewById(R.id.txt_direction_details);
    }

    private void initEventButton() {
        txt_direction_details.setOnClickListener(this);
    }

    private void initData() {
        if (resultSearchModel.getDescription().length() > 3) {
            txt_description_details_map.setText(resultSearchModel.getDescription());
        } else {
            txt_description_details_map.setText("Pizza &amp; buffet chain offering several crust options, plus " +
                    "sandwiches, pastas &amp; more");
        }
        txt_address_details_map.setText(resultSearchModel.getAddress() + ", " + resultSearchModel.getAdd_ward() + ", "
                + resultSearchModel.getAdd_city());
        txt_address2_details_map.setText(resultSearchModel.getAddress() + ", " + resultSearchModel.getAdd_ward() + ", "
                + resultSearchModel.getAdd_city());
        txt_phone_details_map.setText(resultSearchModel.getPhone());
        txt_time_open_details_map.setText("Open until 10:30 AM");
        txt_website_details_map.setText(resultSearchModel.getWebsite());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, SearchMap.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_direction_details:
                Intent intent = new Intent(this, SearchMap.class);
                intent.putExtra("direction", resultSearchModel.getGeolocation());
                startActivity(intent);
                finish();
                break;
        }
    }
}
