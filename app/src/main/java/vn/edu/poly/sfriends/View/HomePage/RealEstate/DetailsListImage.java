package vn.edu.poly.sfriends.View.HomePage.RealEstate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Adapter.ListImageDetailsAdapter;
import vn.edu.poly.sfriends.R;

public class DetailsListImage extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<String> stringArrayList;
    private ListImageDetailsAdapter listImageDetailsAdapter;
    private ListView listView;
    private ImageView img_menu_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_list_image);
        initView();
        initEventButton();
        initData();
    }

    private void initView() {
        listView = findViewById(R.id.listView_image_details);
        img_menu_back = findViewById(R.id.menu_back);
    }

    private void initEventButton() {
        img_menu_back.setOnClickListener(this);
    }

    private void initData() {
        stringArrayList = new ArrayList<>();
        stringArrayList.add("https://www.latimes" +
                ".com/resizer/Wbbgl9uoxJADv7zL40FwOzU7fAQ=/1400x0/arc-anglerfish-arc2-prod-tronc" +
                ".s3.amazonaws.com/public/KPQYJJMCRFAXHID643QYEFMQVQ.jpg");
        stringArrayList.add("https://www.latimes" +
                ".com/resizer/2gn1BWEnG3S6Jj7RgsitkovUWrg=/fit-in/800x600/filters:fill(black)" +
                "/arc-anglerfish-arc2-prod-tronc.s3.amazonaws" +
                ".com/public/DOPD5PVYBNAUVIU4IHQBEY23X4.jpg");
        stringArrayList.add("https://www.latimes" +
                ".com/resizer/dVFS6vSUDG4GgszD7kGbdUhDsqY=/fit-in/800x600/filters:fill(black)" +
                "/arc-anglerfish-arc2-prod-tronc.s3.amazonaws" +
                ".com/public/GTDVGSNUHJCWNE2P4HAF6WQCMU.jpg");
        stringArrayList.add("https://www.latimes" +
                ".com/resizer/E4Q8aFMBiun3F3wiGTerurQ6dKQ=/fit-in/800x600/filters:fill(black)" +
                "/arc-anglerfish-arc2-prod-tronc.s3.amazonaws" +
                ".com/public/YOORY4DDLNEY5GDBDB7RA2WWFQ.jpg");
        stringArrayList.add("https://www.latimes" +
                ".com/resizer/6ivcUMD4733u3z18yxhcxXLXpF0=/fit-in/800x600/filters:fill(black)" +
                "/arc-anglerfish-arc2-prod-tronc.s3.amazonaws" +
                ".com/public/A6B26HE5LFCWDMWFMBZDQUB74Y.jpg");
        listImageDetailsAdapter = new ListImageDetailsAdapter(this, stringArrayList);
        listView.setAdapter(listImageDetailsAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_back:
                onBackPressed();
                break;
        }
    }
}
