package vn.edu.poly.sfriends.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vn.edu.poly.sfriends.Model.CalculatorBorrowModel;
import vn.edu.poly.sfriends.R;

public class CalculatorBorrowAdapter extends BaseAdapter {

    private Context context;
    private List<CalculatorBorrowModel> listBorrow;
    private LayoutInflater layoutInflater;

    public CalculatorBorrowAdapter(Context context, List<CalculatorBorrowModel> listBorrow) {
        this.context = context;
        this.listBorrow = listBorrow;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listBorrow.size();
    }

    @Override
    public Object getItem(int position) {
        return listBorrow.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView txt_ki_thanh_toan, txt_du_no_dau_ki, txt_lai_thanh_toan, txt_goc_thanh_toan,
                txt_du_no, txt_tien_hang_thang;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.header_list_borrow2, null);
            viewHolder.txt_ki_thanh_toan = convertView.findViewById(R.id.txt_ki_thanh_toan);
            viewHolder.txt_du_no_dau_ki = convertView.findViewById(R.id.txt_du_no_dau_ki);
            viewHolder.txt_lai_thanh_toan = convertView.findViewById(R.id.txt_lai_thanh_toan);
            viewHolder.txt_goc_thanh_toan = convertView.findViewById(R.id.txt_goc_thanh_toan);
            viewHolder.txt_du_no = convertView.findViewById(R.id.txt_du_no);
            viewHolder.txt_tien_hang_thang = convertView.findViewById(R.id.txt_tien_hang_thang);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CalculatorBorrowModel model = listBorrow.get(position);
        viewHolder.txt_ki_thanh_toan.setText(model.getKiHanThanhToan());
        viewHolder.txt_du_no_dau_ki.setText(model.getDuNoDauKi());
        viewHolder.txt_lai_thanh_toan.setText(model.getLaiThanhToanHangThang());
        viewHolder.txt_goc_thanh_toan.setText(model.getGocThanhToanHangThang());
        viewHolder.txt_du_no.setText(model.getDuNoCuoiKi());
        viewHolder.txt_tien_hang_thang.setText(model.getKhoanTienTraHangThang());

        return convertView;
    }
}
