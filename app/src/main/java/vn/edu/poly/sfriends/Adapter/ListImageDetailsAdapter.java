package vn.edu.poly.sfriends.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vn.edu.poly.sfriends.R;

public class ListImageDetailsAdapter extends BaseAdapter {

    private Context context;
    private List<String> stringList;

    public ListImageDetailsAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        ImageView img_item_list_details;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_item_image, null);
            viewHolder.img_item_list_details = convertView.findViewById(R.id.img_item_list_details);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (stringList.get(position).length() > 3){
            Picasso.get().load(stringList.get(position)).error(R.drawable.restaurant).into(viewHolder
                    .img_item_list_details);
        } else {
            Picasso.get().load(R.drawable.restaurant).into(viewHolder.img_item_list_details);
        }
        return convertView;
    }
}
