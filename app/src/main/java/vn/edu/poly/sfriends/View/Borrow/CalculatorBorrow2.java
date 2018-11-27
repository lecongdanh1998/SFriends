package vn.edu.poly.sfriends.View.Borrow;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import vn.edu.poly.sfriends.Adapter.CalculatorBorrowAdapter;
import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.Model.CalculatorBorrowModel;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.SQLite.DBHelper;

public class CalculatorBorrow2 extends BaseActivity implements View.OnClickListener {

    private Bundle bundle;
    private TextView txt_interest_calculator, txt_first_month_money_calculator,
            txt_interest_pay_calculator, txt_principal_calculator;
    private PieChart pieChart;
    private ArrayList<PieEntry> yValues;
    private PieDataSet dataSet;
    private PieData pieData;
    private double value_estimate = 0, value_borrow = 0, duration_borrow = 0, inter_rest = 0;
    private double interest_calculator, interest_pay_calculator, first_month_money_calculator,
            principal_calculator;
    private String str_interest_calculator, str_interest_pay_calculator,
            str_first_month_money_calculator, str_principal_calculator, str_du_no_cuoi_ki;
    private double duNoDauKi = 0.0, laiThanhToan = 0.0, gocThanhToan = 0.0, duNoCuoiKi = 0.0,
            tienTraHangThang = 0.0, tongLaiThanhToan = 0.0, tongGocThanhToan = 0.0, tongGocLai =
            0.0, thanhToanThangDau;
    private int billion = 1000000000;
    private DecimalFormat format;
    private DBHelper dbHelper;
    public Cursor cursor;
    private File sd, file, directory;
    private String CSVFile;
    private WorkbookSettings workbookSettings;
    private WritableWorkbook writableWorkbook;
    private WritableSheet sheet;
    private CardView layout_btn_calculator;
    private Button btn_show_excel;
    private String filePath;
    private ImageView img_back_ToobarWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_borrow2);
        initView();
        initData();
        initEventButton();
        configPieChart();
        initDataPieChart();
        writeExcelFile();
    }

    private void initView() {
        txt_interest_calculator = findViewById(R.id.txt_interest_calculator);
        txt_first_month_money_calculator = findViewById(R.id.txt_first_month_money_calculator);
        txt_interest_pay_calculator = findViewById(R.id.txt_interest_pay_calculator);
        txt_principal_calculator = findViewById(R.id.txt_principal_calculator);
        pieChart = findViewById(R.id.pie_chart_calculator_borrow);
        layout_btn_calculator = findViewById(R.id.layout_btn_calculator);
        btn_show_excel = findViewById(R.id.btn_show_excel);
        img_back_ToobarWebview = findViewById(R.id.img_back_ToobarWebview);
    }

    private void initData() {
//        sqlite
        dbHelper = new DBHelper(this);
        cursor = dbHelper.getData();
//        format data
        format = new DecimalFormat("###,###,###");
        try {
            bundle = getIntent().getExtras();
            value_borrow = bundle.getDouble("value_borrow");
            value_estimate = bundle.getDouble("value_estimate");
            duration_borrow = bundle.getDouble("duration_borrow");
            inter_rest = bundle.getDouble("inter_rest");
        } catch (Exception e) {

        }
        calculatorBorrow();
    }

    private void initEventButton(){
        layout_btn_calculator.setOnClickListener(this);
        btn_show_excel.setOnClickListener(this);
        img_back_ToobarWebview.setOnClickListener(this);
    }

    private void configPieChart() {
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setHoleRadius(70f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setDrawEntryLabels(false);
        pieChart.setDrawSliceText(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(70f);
        pieChart.getLegend().setEnabled(false);
        DecimalFormat format = new DecimalFormat("#.## tỷ");
        double price = (interest_calculator + principal_calculator + interest_pay_calculator) / billion;
        String str_center = format.format(price);
        pieChart.setCenterText(str_center.replace(".", ","));
        pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "roboto_medium" +
                ".ttf"));
        pieChart.setCenterTextSize(getResources().getDimension(R.dimen.textLager));
    }

    private void calculatorBorrow() {

        int kiThanhToan = 0;
        value_estimate = value_estimate * billion;
        //interest
        interest_calculator = (value_estimate - (value_estimate * (value_borrow / 100)));
        str_interest_calculator = format.format(interest_calculator);
        txt_interest_calculator.setText(str_interest_calculator.replace(".", ","));
//        principal_calculator
        principal_calculator = value_estimate - interest_calculator;
        str_principal_calculator = format.format(principal_calculator);
        txt_principal_calculator.setText(str_principal_calculator.replace(".", ","));
//        new data calculator
        value_estimate = principal_calculator;
        gocThanhToan = value_estimate / (duration_borrow * 12);
//        insert data to dtb
        dbHelper.insertData(value_estimate, 0.0, 0.0, 0.0);
        for (int i = 1; i > 0; i++) {
            kiThanhToan++;
            duNoDauKi = value_estimate - gocThanhToan;
            laiThanhToan = value_estimate * (inter_rest / 100 / 12);
            value_estimate = value_estimate - gocThanhToan;
            duNoCuoiKi = value_estimate;
            tienTraHangThang = gocThanhToan + laiThanhToan;
            tongLaiThanhToan = tongLaiThanhToan + laiThanhToan;
            tongGocLai = tongGocLai + tienTraHangThang;
            dbHelper.insertData(duNoDauKi, gocThanhToan, laiThanhToan, tienTraHangThang);
            String s = format.format(duNoDauKi);
            if (kiThanhToan == 1){
                thanhToanThangDau = tienTraHangThang;
            }
            if (s.equals("0")) {
                break;
            }
        }
        //        interest_pay_calculator
        interest_pay_calculator = tongLaiThanhToan;
        str_interest_pay_calculator = format.format(interest_pay_calculator);
        txt_interest_pay_calculator.setText(str_interest_pay_calculator.replace(".", ","));
        // interest
        str_first_month_money_calculator = format.format(thanhToanThangDau);
        txt_first_month_money_calculator.setText(str_first_month_money_calculator.replace(".", "," +
                ""));
        tongGocThanhToan = gocThanhToan * duration_borrow * 12;
    }

    private void initDataPieChart() {
        yValues = new ArrayList<>();
        yValues.add(new PieEntry((float) interest_calculator, "Pay first"));
        yValues.add(new PieEntry((float) principal_calculator, "Principal"));
        yValues.add(new PieEntry((float) interest_pay_calculator, "Interest"));

        dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(6f);
        final int[] MY_COLORS = {Color.rgb(0, 119, 187), Color.rgb(20, 168, 149), Color.rgb(182,
                67, 205)};
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : MY_COLORS) colors.add(c);
        dataSet.setColors(colors);

        pieData = new PieData(dataSet);
        pieData.setValueTextColor(Color.TRANSPARENT);

        pieChart.setData(pieData);
    }

    private void writeExcelFile(){
        sd = Environment.getExternalStorageDirectory();
        CSVFile = "dunothang.xls";
        directory = new File(sd.getAbsolutePath());
        //create directory if not exist
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }
        try {
            //file path
            file = new File(directory, CSVFile);
            workbookSettings = new WorkbookSettings();
            workbookSettings.setLocale(new Locale("en", "EN"));
            writableWorkbook = Workbook.createWorkbook(file, workbookSettings);
            sheet = writableWorkbook.createSheet("dunothang", 0);
            sheet.addCell(new Label(0,0, "Tháng"));
            sheet.addCell(new Label(1,0, "Số gốc còn lại"));
            sheet.addCell(new Label(2,0, "Gốc"));
            sheet.addCell(new Label(3,0, "Lãi"));
            sheet.addCell(new Label(4,0,"Tổng gốc + Lãi"));
            int thang = 0;
            if (cursor.moveToFirst()){
                do {
                    double so_goc_con_lai = cursor.getDouble(cursor.getColumnIndex(DBHelper
                            .SO_GOC_CON_LAI));
                    double goc = cursor.getDouble(cursor.getColumnIndex(DBHelper
                            .GOC));
                    double lai = cursor.getDouble(cursor.getColumnIndex(DBHelper
                            .LAI));
                    double tong_goc_lai = cursor.getDouble(cursor.getColumnIndex(DBHelper
                            .TONG_GOC_LAI));

//                    so_goc_con_lai = 1d*(int)(so_goc_con_lai*100)/100;
//                    goc = 1d*(int)(goc*100)/100;
//                    lai = 1d*(int)(lai*100)/100;
//                    tong_goc_lai = 1d*(int)(tong_goc_lai*100)/100;
//                    write data to excel
                    int i = cursor.getPosition() + 1;
                    sheet.addCell(new Label(0, i, String.valueOf(thang)));
                    sheet.addCell(new Label(1, i, format.format(so_goc_con_lai)));
                    sheet.addCell(new Label(2, i, format.format(goc)));
                    sheet.addCell(new Label(3, i, format.format(lai)));
                    sheet.addCell(new Label(4, i, format.format(tong_goc_lai)));
                    thang++;
                } while (cursor.moveToNext());
            }
            cursor.close();
            writableWorkbook.write();
            writableWorkbook.close();
            Toast.makeText(this, "Data Exported in a Excel Sheet", Toast.LENGTH_SHORT).show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.removeAll();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_btn_calculator:
            case R.id.btn_show_excel:
                displayExcelFile();
                Toast.makeText(this, "Open file", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_back_ToobarWebview:
                finish();
                break;
        }
    }

    private void displayExcelFile(){
        MimeTypeMap map = MimeTypeMap.getSingleton();
        String ext = MimeTypeMap.getFileExtensionFromUrl(file.getName());
        String type = map.getMimeTypeFromExtension(ext);
        if (type == null)
            type = "*/*";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.fromFile(file);
        intent.setDataAndType(data, type);
        startActivity(intent);
    }
}
