package vn.edu.poly.sfriends.View.HomePage.Sarena.GROUPS;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Adapter.AdapterAlbums;
import vn.edu.poly.sfriends.Adapter.AdapterGroups;
import vn.edu.poly.sfriends.Model.AlbumsContructor;
import vn.edu.poly.sfriends.Model.GroupsContructor;
import vn.edu.poly.sfriends.R;

public class groups extends Fragment {
    private View view;
    AdapterGroups adapter;
    ArrayList<GroupsContructor> arrayList;
    GridView girdview_groups;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_groups, container, false);
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
        girdview_groups = view.findViewById(R.id.girdview_groups);

    }

    private void initData() {
        arrayList = new ArrayList<>();
        arrayList.add(new GroupsContructor("https://viajeaorlando.files.wordpress.com/2012/11/disney-christmas-parade-santa-m.jpg"
                , "Photography"
                , "86"));
        arrayList.add(new GroupsContructor("https://images.financialexpress.com/2018/08/independence-day-pti.jpg"
                , "Football Fans"
                , "64"));
        arrayList.add(new GroupsContructor("https://www.johornow.com/wp-content/uploads/sites/2/2017/02/Satisfying-Food-Spots-You-Should-Check-Out-in-Adda-Heights.jpg"
                , "Cat Lovers"
                , "52"));
        arrayList.add(new GroupsContructor("http://www.azoneevents.com/wp-content/uploads/2018/07/rwa-activities-agency-in-patna-bihar.jpg"
                , "Adventure"
                , "103"));
        arrayList.add(new GroupsContructor("https://cdn.makeupandbeauty.com/wp-content/uploads/2014/03/Happy_Holi_2014.jpg"
                , "Photography"
                , "86"));
        arrayList.add(new GroupsContructor("https://i.pinimg.com/736x/8e/e1/39/8ee1392054043177aea57147e89a1f5d--cristano-ronaldo-cristiano.jpg"
                , "Photography"
                , "86"));
        adapter = new AdapterGroups(arrayList, getContext());
        girdview_groups.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
