package vn.edu.poly.sfriends.View.HomePage.Cuisine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Adapter.TablayoutSuper_Listview_Adapter;
import vn.edu.poly.sfriends.Model.TabLayoutSuper_listiew;
import vn.edu.poly.sfriends.R;

public class Cuisine extends Fragment {
    private View view;
    ArrayList<TabLayoutSuper_listiew> arrayList;
    TablayoutSuper_Listview_Adapter adapter;
    ListView listView;
    NestedScrollView nestedScrollView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cuisine, container, false);
        initControl();
        initData();
        return view;
    }

    private void initData() {
        arrayList = new ArrayList<>();
        arrayList.add(new TabLayoutSuper_listiew("http://kientructhegioi.com/wp-content/uploads/2015/12/logo_archi_l%E1%BA%A7n_6-06.png",
                "ToCoToCo","https://xaydungthuonghieu.org/wp-content/uploads/2017/08/thuong-hieu-tra-sua-noi-tieng-hien-nay-1.png",
                "Đang có đợt khuyến mãi vô cùng sốc dành cho các bạn trẻ",
                "16:30",
                "11/12/2018",
                "190",
                "198",
                "4.7",
                "- 50 %"
                ));
        arrayList.add(new TabLayoutSuper_listiew("http://kientructhegioi.com/wp-content/uploads/2015/12/logo_archi_l%E1%BA%A7n_6-06.png",
                "ToCoToCo","https://xaydungthuonghieu.org/wp-content/uploads/2017/08/thuong-hieu-tra-sua-noi-tieng-hien-nay-1.png",
                "Đang có đợt khuyến mãi vô cùng sốc dành cho các bạn trẻ",
                "16:30",
                "11/12/2018",
                "190",
                "198",
                "4.7",
                "- 50 %"
        ));
        arrayList.add(new TabLayoutSuper_listiew("http://kientructhegioi.com/wp-content/uploads/2015/12/logo_archi_l%E1%BA%A7n_6-06.png",
                "ToCoToCo","https://xaydungthuonghieu.org/wp-content/uploads/2017/08/thuong-hieu-tra-sua-noi-tieng-hien-nay-1.png",
                "Đang có đợt khuyến mãi vô cùng sốc dành cho các bạn trẻ",
                "16:30",
                "11/12/2018",
                "190",
                "198",
                "4.7",
                "- 50 %"
        ));
        arrayList.add(new TabLayoutSuper_listiew("http://kientructhegioi.com/wp-content/uploads/2015/12/logo_archi_l%E1%BA%A7n_6-06.png",
                "ToCoToCo","https://xaydungthuonghieu.org/wp-content/uploads/2017/08/thuong-hieu-tra-sua-noi-tieng-hien-nay-1.png",
                "Đang có đợt khuyến mãi vô cùng sốc dành cho các bạn trẻ",
                "16:30",
                "11/12/2018",
                "190",
                "198",
                "4.7",
                "- 50 %"
        ));
        arrayList.add(new TabLayoutSuper_listiew("http://kientructhegioi.com/wp-content/uploads/2015/12/logo_archi_l%E1%BA%A7n_6-06.png",
                "ToCoToCo","https://xaydungthuonghieu.org/wp-content/uploads/2017/08/thuong-hieu-tra-sua-noi-tieng-hien-nay-1.png",
                "Đang có đợt khuyến mãi vô cùng sốc dành cho các bạn trẻ",
                "16:30",
                "11/12/2018",
                "190",
                "198",
                "4.7",
                "- 50 %"
        ));

        adapter = new TablayoutSuper_Listview_Adapter(arrayList,getActivity());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void initControl() {
        listView = (ListView) view.findViewById(R.id.lisview_Cuisine);
        nestedScrollView = (NestedScrollView) view.findViewById(R.id.NestedScrollView);

    }
}
