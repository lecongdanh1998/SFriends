package vn.edu.poly.sfriends.View.HomePage.Sarena.ALBUMS;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Adapter.AdapterAlbums;
import vn.edu.poly.sfriends.Adapter.AdapterAllConverSation;
import vn.edu.poly.sfriends.Model.AlbumsContructor;
import vn.edu.poly.sfriends.Model.AllConverSationContructor;
import vn.edu.poly.sfriends.R;

public class albums extends Fragment {
    private View view;
    AdapterAlbums adapter;
    ArrayList<AlbumsContructor> arrayList;
    GridView girdview_albums;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_albums, container, false);
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
        girdview_albums = view.findViewById(R.id.girdview_albums);
    }

    private void initData() {
        arrayList = new ArrayList<>();
        arrayList.add(new AlbumsContructor("https://viajeaorlando.files.wordpress.com/2012/11/disney-christmas-parade-santa-m.jpg"
        ,"Christmas Celebration"));
        arrayList.add(new AlbumsContructor("https://images.financialexpress.com/2018/08/independence-day-pti.jpg"
                ,"Independence Day"));
        arrayList.add(new AlbumsContructor("https://www.johornow.com/wp-content/uploads/sites/2/2017/02/Satisfying-Food-Spots-You-Should-Check-Out-in-Adda-Heights.jpg"
                ,"Food around our ADDA"));
        arrayList.add(new AlbumsContructor("http://www.azoneevents.com/wp-content/uploads/2018/07/rwa-activities-agency-in-patna-bihar.jpg"
                ,"Society Activities"));
        arrayList.add(new AlbumsContructor("https://cdn.makeupandbeauty.com/wp-content/uploads/2014/03/Happy_Holi_2014.jpg"
                ,"Holi 2014"));
        arrayList.add(new AlbumsContructor("https://i.pinimg.com/736x/8e/e1/39/8ee1392054043177aea57147e89a1f5d--cristano-ronaldo-cristiano.jpg"
                ,"Football in our Campus"));
        adapter = new AdapterAlbums(arrayList, getContext());
        girdview_albums.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
