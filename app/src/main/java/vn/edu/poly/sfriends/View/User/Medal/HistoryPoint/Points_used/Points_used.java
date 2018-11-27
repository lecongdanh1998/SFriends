package vn.edu.poly.sfriends.View.User.Medal.HistoryPoint.Points_used;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Adapter.PointAdapter;
import vn.edu.poly.sfriends.Model.PointContructor;
import vn.edu.poly.sfriends.R;

public class Points_used extends Fragment {
    private View view;
    PointAdapter adapter;
    ArrayList<PointContructor> arrayList;
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_point_user, container, false);
        initControl();
        initData();
        return view;
    }

    private void initData() {
        arrayList = new ArrayList<>();
        arrayList.add(new PointContructor("Giao Hàng Chuyến Đi"
                ,"21 Th11 2018, 04:05PM"
                ,"14"
        ));
        arrayList.add(new PointContructor("Giao Hàng Chuyến Đi"
                ,"1 Th6 2018, 02:58PM"
                ,"16"
        ));
        arrayList.add(new PointContructor("Grabcar 4 Chỗ Chuyến Đi"
                ,"17 Th6 2018, 02:53PM"
                ,"20"
        ));
        arrayList.add(new PointContructor("Grabcar 4 Chỗ Chuyến Đi"
                ,"17 Th6 2018, 02:11PM"
                ,"9"
        ));
        arrayList.add(new PointContructor("Grabcar 4 Chỗ Chuyến Đi"
                ,"17 Th6 2018, 02:11PM"
                ,"30"
        ));
        arrayList.add(new PointContructor("Grabcar 4 Chỗ Chuyến Đi"
                ,"17 Th6 2018, 02:11PM"
                ,"30"
        ));
        arrayList.add(new PointContructor("Giao Hàng Chuyến Đi"
                ,"1 Th6 2018, 02:58PM"
                ,"16"
        ));
        adapter = new PointAdapter(arrayList, getContext());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initControl() {
        listView = view.findViewById(R.id.listview_point_used);
    }
}
