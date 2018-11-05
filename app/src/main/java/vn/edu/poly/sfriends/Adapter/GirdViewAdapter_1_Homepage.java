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

import vn.edu.poly.sfriends.Model.GirdViewContrucTor_1_Homepage;
import vn.edu.poly.sfriends.R;

public class GirdViewAdapter_1_Homepage extends BaseAdapter {
    ArrayList<GirdViewContrucTor_1_Homepage> arrayList;
    LayoutInflater inflater;
    Context context;

    public GirdViewAdapter_1_Homepage(ArrayList<GirdViewContrucTor_1_Homepage> arrayList, Context context) {
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
            convertView = inflater.inflate(R.layout.custom_gridviewhomepage1,null);
            viewHolder.images = (ImageView) convertView.findViewById(R.id.imagesMainGridview);
            viewHolder.title = (TextView) convertView.findViewById(R.id.txtMainGridview);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GirdViewContrucTor_1_Homepage contructorMain = arrayList.get(position);
        viewHolder.title.setText(contructorMain.getTitle());
        Picasso.get()
                .load(contructorMain.getImages())
                .into(viewHolder.images);
        return convertView;
    }


    class ViewHolder{
        ImageView images;
        TextView title;
    }

}
