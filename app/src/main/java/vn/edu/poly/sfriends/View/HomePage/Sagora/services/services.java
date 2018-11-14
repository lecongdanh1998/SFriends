package vn.edu.poly.sfriends.View.HomePage.Sagora.services;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Adapter.AdapterAlbums;
import vn.edu.poly.sfriends.Adapter.AdapterServices;
import vn.edu.poly.sfriends.Model.AlbumsContructor;
import vn.edu.poly.sfriends.Model.ServicesContructor;
import vn.edu.poly.sfriends.R;

public class services extends Fragment {
    private View view;
    AdapterServices adapter;
    ArrayList<ServicesContructor> arrayList;
    GridView girdview_services;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_services, container, false);
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
        girdview_services = view.findViewById(R.id.girdview_services);

    }

    private void initData() {
        arrayList = new ArrayList<>();
        arrayList.add(new ServicesContructor("https://viajeaorlando.files.wordpress.com/2012/11/disney-christmas-parade-santa-m.jpg"
                ,"Courier From Home"));
        arrayList.add(new ServicesContructor("https://images.financialexpress.com/2018/08/independence-day-pti.jpg"
                ,"Electric Meter Transpositon"));
        arrayList.add(new ServicesContructor("https://www.johornow.com/wp-content/uploads/sites/2/2017/02/Satisfying-Food-Spots-You-Should-Check-Out-in-Adda-Heights.jpg"
                ,"Pest Control"));
        arrayList.add(new ServicesContructor("http://www.azoneevents.com/wp-content/uploads/2018/07/rwa-activities-agency-in-patna-bihar.jpg"
                ,"Car Care"));
        arrayList.add(new ServicesContructor("https://cdn.makeupandbeauty.com/wp-content/uploads/2014/03/Happy_Holi_2014.jpg"
                ,"Appliances Care"));
        arrayList.add(new ServicesContructor("https://i.pinimg.com/736x/8e/e1/39/8ee1392054043177aea57147e89a1f5d--cristano-ronaldo-cristiano.jpg"
                ,"Football in our Campus"));
        adapter = new AdapterServices(arrayList, getContext());
        girdview_services.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

