package vn.edu.poly.sfriends.View.HomePage.Sagora.freelancers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import vn.edu.poly.sfriends.R;

public class freelancers extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_freelancers, container, false);
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

    }

    private void initData() {
    }
}

