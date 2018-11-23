package vn.edu.poly.sfriends.View.Borrow;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.poly.sfriends.R;

public class Borrow extends Fragment implements View.OnClickListener, RadioGroup
        .OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    private View view;
    private TextView txt_value_money_estimate, txt_value_borrow, txt_value_duration_borrow,
            txt_value_inter_rest;
    private SeekBar seekBar_value_estimate, seekBar_value_borrow, seekBar_duration_borrow;
    private Spinner spinner_inter_rest;
    private RadioGroup radioGroup;
    private RadioButton radio_button;
    private Button btn_check_borrow;
    private int radio_button_select = 0;
    private ArrayAdapter<CharSequence> listSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_borrow, container, false);
        initView();
        initEventButton();
        initData();
        return view;
    }

    private void initView() {
        seekBar_value_estimate = view.findViewById(R.id.seekBar_value_estimate);
        seekBar_value_borrow = view.findViewById(R.id.seekBar_value_borrow);
        seekBar_duration_borrow = view.findViewById(R.id.seekBar_duration_borrow);
        txt_value_money_estimate = view.findViewById(R.id.txt_value_money_estimate);
        txt_value_borrow = view.findViewById(R.id.txt_value_borrow);
        txt_value_duration_borrow = view.findViewById(R.id.txt_value_duration_borrow);
        txt_value_inter_rest = view.findViewById(R.id.txt_value_inter_rest);
        spinner_inter_rest = view.findViewById(R.id.spinner_inter_rest);
        radioGroup = view.findViewById(R.id.radio_group);
        btn_check_borrow = view.findViewById(R.id.btn_check_borrow);
    }

    private void initEventButton() {
        btn_check_borrow.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        seekBar_value_estimate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                DecimalFormat format = new DecimalFormat("#.##");
                double price = Double.valueOf((i * 0.1) + 0.1);
                txt_value_money_estimate.setText(String.valueOf(format.format(price)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar_duration_borrow.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txt_value_duration_borrow.setText(String.valueOf(i + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar_value_borrow.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txt_value_borrow.setText(String.valueOf(i + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initData() {
        listSpinner = ArrayAdapter.createFromResource(getContext(), R.array.spinner, android.R
                .layout.simple_spinner_item);
        listSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_inter_rest.setAdapter(listSpinner);
        spinner_inter_rest.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_check_borrow:
                checkSelect();
                break;
        }
    }

    private void checkSelect() {
        if (radio_button_select == 1) {
            Toast.makeText(getContext(), "Du no", Toast.LENGTH_SHORT).show();
        } else if (radio_button_select == 2) {
            Toast.makeText(getContext(), "Hang thang", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Vui long chon 1 trong 2", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.radio_button_1:
                radio_button_select = 1;
                break;
            case R.id.radio_button_2:
                radio_button_select = 2;
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0) {
            txt_value_inter_rest.setText("7.6");
        } else {
            txt_value_inter_rest.setText(adapterView.getItemAtPosition(i).toString());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
