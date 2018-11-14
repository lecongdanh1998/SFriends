package vn.edu.poly.sfriends.View.HomePage.Senate.OPEN_ISSUES;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Adapter.AdapterAllConverSation;
import vn.edu.poly.sfriends.Adapter.AdapterOpenIssues;
import vn.edu.poly.sfriends.Model.AllConverSationContructor;
import vn.edu.poly.sfriends.Model.OpenISSUESContructor;
import vn.edu.poly.sfriends.R;

public class open_issues extends Fragment {
    private View view;
    AdapterOpenIssues adapter;
    ArrayList<OpenISSUESContructor> arrayList;
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_open_issues, container, false);
        initControl();
        initData();
        initEvent();
        initOnClick();
        return view;
    }

    private void initEvent() {
    }

    private void initOnClick() {
    }

    private void initControl() {
        listView = view.findViewById(R.id.listview_open_issuer);

    }

    private void initData() {
        arrayList = new ArrayList<>();
        arrayList.add(new OpenISSUESContructor("New"
        ,"P363"
        ,"Fed 18, 4:58 PM"
        ,"Plumbing"
        ,"leakage in my kitchen tap"
        ,"Lalita Murthy"
        ,"1"));
        arrayList.add(new OpenISSUESContructor("In Progress"
                ,"P362"
                ,"Fed 18, 4:58 PM"
                ,"Electric"
                ,"Short circuit"
                ,"Lalita Murthy"
                ,"3"));
        arrayList.add(new OpenISSUESContructor("In Progress"
                ,"P362"
                ,"Fed 18, 4:58 PM"
                ,"Electric"
                ,"Short circuit"
                ,"Lalita Murthy"
                ,"3"));
        arrayList.add(new OpenISSUESContructor("In Progress"
                ,"P362"
                ,"Fed 18, 4:58 PM"
                ,"Electric"
                ,"Short circuit"
                ,"Lalita Murthy"
                ,"3"));
        arrayList.add(new OpenISSUESContructor("New"
                ,"P363"
                ,"Fed 18, 4:58 PM"
                ,"Plumbing"
                ,"leakage in my kitchen tap"
                ,"Lalita Murthy"
                ,"1"));
        adapter = new AdapterOpenIssues(arrayList, getContext());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
