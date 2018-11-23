package vn.edu.poly.sfriends.View.User.Medal;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.View.MainActivity;
import vn.edu.poly.sfriends.View.User.UserActivity;

public class MemberActivity extends BaseActivity implements View.OnClickListener {
    AppBarLayout appBar;
    TextView txt_titlestatus_member;
    ImageView img_back_member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        initControl();
        initData();
        initOnClick();
    }

    private void initControl() {
        appBar = findViewById(R.id.appBar);
        img_back_member = findViewById(R.id.img_back_member);
        txt_titlestatus_member = findViewById(R.id.txt_titlestatus_member);
    }

    private void initData() {
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    txt_titlestatus_member.setText(getResources().getString(R.string.txt_GOLD)+" : 2379"+" "+getResources().getString(R.string.txt_point));
                    // Collapsed
                } else if (verticalOffset == 0) {
                    // Expanded
                    txt_titlestatus_member.setText(getResources().getString(R.string.txt_redeempoints));
                } else {
                    // Somewhere in between
                }
            }
        });
    }

    private void initOnClick() {
        img_back_member.setOnClickListener(this);
    }


    private void intentView(Class c) {
        Intent intent = new Intent(MemberActivity.this, c);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }


    @Override
    public void onBackPressed() {
        intentView(UserActivity.class);
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_member:
                onBackPressed();
                break;
        }
    }
}
