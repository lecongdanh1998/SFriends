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
import vn.edu.poly.sfriends.Model.GirdViewContructor_sagora_homepage;
import vn.edu.poly.sfriends.R;

public class GirdViewAdapter_sagora_Homepage extends BaseAdapter {
    ArrayList<GirdViewContructor_sagora_homepage> arrayList;
    LayoutInflater inflater;
    Context context;

    public GirdViewAdapter_sagora_Homepage(ArrayList<GirdViewContructor_sagora_homepage> arrayList, Context context) {
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
            convertView = inflater.inflate(R.layout.custom_gridviewsagorahomepage,null);
            viewHolder.images = (ImageView) convertView.findViewById(R.id.imagesMainGridviewSagora);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GirdViewContructor_sagora_homepage contructorMain = arrayList.get(position);
        Picasso.get()
                .load(contructorMain.getImage())
                .into(viewHolder.images);
        return convertView;
    }


    class ViewHolder{
        ImageView images;
    }

}
