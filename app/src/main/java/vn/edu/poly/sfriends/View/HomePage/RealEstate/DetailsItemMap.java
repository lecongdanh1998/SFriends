package vn.edu.poly.sfriends.View.HomePage.RealEstate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.edu.poly.sfriends.R;

public class DetailsItemMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_item_map);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
