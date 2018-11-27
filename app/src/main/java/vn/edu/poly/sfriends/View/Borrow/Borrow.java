package vn.edu.poly.sfriends.View.Borrow;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.poly.sfriends.R;

public class Borrow extends Fragment implements View.OnClickListener, RadioGroup
        .OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    private View view;
    private EditText txt_value_money_estimate, txt_value_borrow, txt_value_duration_borrow;
    private TextView txt_value_inter_rest;
    private SeekBar seekBar_value_estimate, seekBar_value_borrow, seekBar_duration_borrow;
    private Spinner spinner_inter_rest;
    private RadioGroup radioGroup;
    private RadioButton radio_button;
    private Button btn_check_borrow;
    private int radio_button_select = 0;
    private ArrayAdapter<CharSequence> listSpinner;
    private double value_estimate, value_borrow, duration_borrow, inter_rest;
    private int type_select;
    private PieChart pieChart;

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
                DecimalFormat format = new DecimalFormat("#.###");
                double price = Double.valueOf((i * 0.01) + 0.01);
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
        txt_value_money_estimate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    int p = (int) ((Double.valueOf(txt_value_money_estimate.getText().toString()
                            .trim()) - 0.01) * 100);
                    seekBar_value_estimate.setProgress(p);
                }
                return handled;
            }
        });
        txt_value_borrow.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    int p = (int) (Double.valueOf(txt_value_borrow.getText().toString()
                            .trim()) - 1);
                    seekBar_value_borrow.setProgress(p);
                }
                return handled;
            }
        });
        txt_value_duration_borrow.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    txt_value_duration_borrow.setCursorVisible(false);
                    int p = (int) (Double.valueOf(txt_value_duration_borrow.getText().toString()
                            .trim()) - 1);
                    seekBar_duration_borrow.setProgress(p);
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService
                            (Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(txt_value_duration_borrow
                            .getApplicationWindowToken(), 0);
                }
                return handled;
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
        value_borrow = Double.valueOf(txt_value_borrow.getText().toString().trim());
        duration_borrow = Double.valueOf(txt_value_duration_borrow.getText().toString()
                .trim());
        value_estimate = Double.valueOf(txt_value_money_estimate.getText().toString()
                .trim().replaceAll(",", "."));
        if (radio_button_select == 1) {
            Intent intent = new Intent(getContext(), CalculatorBorrow2.class);
            Bundle bundle = new Bundle();
            bundle.putDouble("value_estimate", value_estimate);
            bundle.putDouble("value_borrow", value_borrow);
            bundle.putDouble("duration_borrow", duration_borrow);
            bundle.putDouble("inter_rest", inter_rest);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (radio_button_select == 2) {
            Intent intent = new Intent(getContext(), CalculatorBorrow.class);
            Bundle bundle = new Bundle();
            bundle.putDouble("value_estimate", value_estimate);
            bundle.putDouble("value_borrow", value_borrow);
            bundle.putDouble("duration_borrow", duration_borrow);
            bundle.putDouble("inter_rest", inter_rest);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "Vui lòng chọn hình thức thanh toán", Toast.LENGTH_SHORT)
                    .show();
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
            inter_rest = 7.6;
        } else {
            txt_value_inter_rest.setText(adapterView.getItemAtPosition(i).toString());
            inter_rest = Double.valueOf(adapterView.getItemAtPosition(i).toString());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
