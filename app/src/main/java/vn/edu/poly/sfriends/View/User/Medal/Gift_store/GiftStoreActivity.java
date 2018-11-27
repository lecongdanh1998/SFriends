package vn.edu.poly.sfriends.View.User.Medal.Gift_store;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import vn.edu.poly.sfriends.Adapter.CustomViewPagerAdapter;
import vn.edu.poly.sfriends.Adapter.GiftStoreAdapter;
import vn.edu.poly.sfriends.Adapter.TablayoutSuper_Listview_Adapter;
import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.Model.GiftStoreContructor;
import vn.edu.poly.sfriends.Model.HotDealObject;
import vn.edu.poly.sfriends.Model.TabLayoutSuper_listiew;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.View.User.Medal.MemberActivity;

public class GiftStoreActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager mViewPageQuangCao;
    private CustomViewPagerAdapter mAdapterQuangcao;
    private Handler handlerQuangcao;
    private final int delay = 3000;
    private int pageQuang = 0;
    ImageView img_back_gift_store;
    ArrayList<GiftStoreContructor> arrayList;
    GiftStoreAdapter adapter;
    ListView listView;
    Runnable runnable1 = new Runnable() {
        public void run() {
            if (mAdapterQuangcao.getCount() == pageQuang) {
                pageQuang = 0;
            } else {
                pageQuang++;
            }
            mViewPageQuangCao.setCurrentItem(pageQuang, true);
            handlerQuangcao.postDelayed(this, delay);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_store);
        initControl();
        initData();
        initEvent();
        initOnClick();
    }

    private void initControl() {
        mViewPageQuangCao = (ViewPager) findViewById(R.id.viewPager_gift_store);
        listView = (ListView) findViewById(R.id.listview_gift_store);
        img_back_gift_store = findViewById(R.id.img_back_gift_store);
    }

    private void initData() {

        handlerQuangcao = new Handler();
        mAdapterQuangcao = new CustomViewPagerAdapter(this, getTestData());
        mViewPageQuangCao.setAdapter(mAdapterQuangcao);
        mAdapterQuangcao.notifyDataSetChanged();
        mViewPageQuangCao.requestFocus();
        arrayList = new ArrayList<>();
        arrayList.add(new GiftStoreContructor("http://kientructhegioi.com/wp-content/uploads/2015/12/logo_archi_l%E1%BA%A7n_6-06.png",
                "ToCoToCo",
                "https://xaydungthuonghieu.org/wp-content/uploads/2017/08/thuong-hieu-tra-sua-noi-tieng-hien-nay-1.png",
                "600",
                "12:00 12/7/2018"
        ));
        arrayList.add(new GiftStoreContructor("http://kientructhegioi.com/wp-content/uploads/2015/12/logo_archi_l%E1%BA%A7n_6-06.png",
                "ToCoToCo",
                "https://xaydungthuonghieu.org/wp-content/uploads/2017/08/thuong-hieu-tra-sua-noi-tieng-hien-nay-1.png",
                "200",
                "5:00 27/10/2018"
        ));
        arrayList.add(new GiftStoreContructor("http://kientructhegioi.com/wp-content/uploads/2015/12/logo_archi_l%E1%BA%A7n_6-06.png",
                "ToCoToCo",
                "https://xaydungthuonghieu.org/wp-content/uploads/2017/08/thuong-hieu-tra-sua-noi-tieng-hien-nay-1.png",
                "200",
                "16:00 18/11/2018"
        ));
        arrayList.add(new GiftStoreContructor("http://kientructhegioi.com/wp-content/uploads/2015/12/logo_archi_l%E1%BA%A7n_6-06.png",
                "ToCoToCo",
                "https://xaydungthuonghieu.org/wp-content/uploads/2017/08/thuong-hieu-tra-sua-noi-tieng-hien-nay-1.png",
                "700",
                "7:00 19/11/2018"
        ));
        arrayList.add(new GiftStoreContructor("http://kientructhegioi.com/wp-content/uploads/2015/12/logo_archi_l%E1%BA%A7n_6-06.png",
                "ToCoToCo",
                "https://xaydungthuonghieu.org/wp-content/uploads/2017/08/thuong-hieu-tra-sua-noi-tieng-hien-nay-1.png",
                "1000",
                "19:00 20/11/2018"

        ));
        adapter = new GiftStoreAdapter(arrayList,this);
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);
        adapter.notifyDataSetChanged();
        listView.getFirstVisiblePosition();

    }


    @Override
    public void onResume() {
        super.onResume();
        handlerQuangcao.postDelayed(runnable1, delay);
    }

    @Override
    public void onPause() {
        super.onPause();
        handlerQuangcao.removeCallbacks(runnable1);

    }

    private void intentView(Class c) {
        Intent intent = new Intent(GiftStoreActivity.this, c);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }

    @Override
    public void onBackPressed() {
        intentView(MemberActivity.class);
        super.onBackPressed();
    }

    public List<HotDealObject> getTestData() {
        List<HotDealObject> mTestData = new ArrayList<HotDealObject>();
        mTestData.add(new HotDealObject("https://i.ytimg.com/vi/PAuMs3WCd68/maxresdefault.jpg"));
        mTestData.add(new HotDealObject("https://trungtamnghiencuuthucpham.vn/wp-content/uploads/2013/10/giay-phep-quang-cao-tren-truyen-hinh.jpg"));
        mTestData.add(new HotDealObject("https://inhongdang.com.vn/images/images/in-bien-quang-cao-gia-re-tai-Ha-Noi.jpg"));
        mTestData.add(new HotDealObject("https://i.ytimg.com/vi/8mqCk2GevAs/maxresdefault.jpg"));
        mTestData.add(new HotDealObject("https://cdn-images-1.medium.com/max/700/1*NF6AtRiHrqW5BniRzY30hg.jpeg"));
        return mTestData;
    }

    private void initEvent() {
    }

    private void initOnClick() {
        img_back_gift_store.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_gift_store:
                onBackPressed();
                break;
        }
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = (ListAdapter) listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
