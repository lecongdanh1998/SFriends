package vn.edu.poly.sfriends.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import vn.edu.poly.sfriends.Model.ResultSearchModel;
import vn.edu.poly.sfriends.R;

public class ResultSearchAdapter extends BaseAdapter {

    Context context;
    List<ResultSearchModel> listResultSearch;

    public ResultSearchAdapter(Context context, List<ResultSearchModel> listResultSearch) {
        this.context = context;
        this.listResultSearch = listResultSearch;
    }

    @Override
    public int getCount() {
        return listResultSearch.size();
    }

    @Override
    public Object getItem(int position) {
        return listResultSearch.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView txt_time_open_address_result, txt_address_result, txt_type_country_result,
                txt_review_result, txt_name_result;
        ImageView img_result;
        RatingBar ratingBar_result;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_search_item, null);
            viewHolder.txt_name_result = convertView.findViewById(R.id.txt_name_result);
            viewHolder.txt_review_result = convertView.findViewById(R.id.txt_review_result);
            viewHolder.txt_type_country_result = convertView.findViewById(R.id.txt_type_country_result);
            viewHolder.txt_address_result = convertView.findViewById(R.id.txt_address_result);
            viewHolder.txt_time_open_address_result = convertView.findViewById(R.id.txt_time_open_address_result);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ResultSearchModel model = listResultSearch.get(position);
        viewHolder.txt_name_result.setText(model.getName());
        viewHolder.txt_review_result.setText(model.getReviewCount() + " reviews");
        viewHolder.txt_type_country_result.setText(model.getTypeCountry());
        viewHolder.txt_address_result.setText(model.getAddress());
        viewHolder.txt_time_open_address_result.setText(model.getTimeOpen());
        return convertView;
    }
}
