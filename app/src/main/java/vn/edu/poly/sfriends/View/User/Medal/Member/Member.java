package vn.edu.poly.sfriends.View.User.Medal.Member;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.edu.poly.sfriends.R;

public class Member extends Fragment {
    private View view;
    TextView txt_tichluydiem_member;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_member, container, false);
        initControl();
        initData();
        return view;
    }

    private void initData() {
        txt_tichluydiem_member.setText(getResources().getString(R.string.txt_accumulation)
                +" 600 "
                +getResources().getString(R.string.txt_previouspoint)
                +" 31/12/2018 "
                +getResources().getString(R.string.txt_toopenthestandard));
    }

    private void initControl() {
        txt_tichluydiem_member = view.findViewById(R.id.txt_tichluydiem_member);
    }
}
