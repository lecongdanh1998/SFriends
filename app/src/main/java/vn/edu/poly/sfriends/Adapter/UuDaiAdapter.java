package vn.edu.poly.sfriends.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Model.UuDaiContructor;
import vn.edu.poly.sfriends.R;

public class UuDaiAdapter extends BaseAdapter {
    ArrayList<UuDaiContructor> arrayList;
    LayoutInflater inflater;
    Context context;

    public UuDaiAdapter(ArrayList<UuDaiContructor> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_gridviewmember,null);
            viewHolder.images = (ImageView) convertView.findViewById(R.id.imagesMainGridview_Member);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        UuDaiContructor contructorMain = arrayList.get(position);
        Picasso.get()
                .load(contructorMain.getImages())
                .into(viewHolder.images);
        return convertView;
    }


    class ViewHolder{
        ImageView images;
    }
}
