package vn.edu.poly.sfriends.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.poly.sfriends.Model.ClassufiedContructor;
import vn.edu.poly.sfriends.Model.OpenISSUESContructor;
import vn.edu.poly.sfriends.R;

public class AdapterClassified extends BaseAdapter {
    ArrayList<ClassufiedContructor> arrayList;
    Context context;
    LayoutInflater inflater;

    public AdapterClassified(ArrayList<ClassufiedContructor> arrayList, Context context) {
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

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_lst_classified,null);
            viewHolder.images = convertView.findViewById(R.id.img_avatar_classified);
            viewHolder.title = convertView.findViewById(R.id.txt_tile_classified);
            viewHolder.SubTile = convertView.findViewById(R.id.txt_Subtile_classified);
            viewHolder.time = convertView.findViewById(R.id.txt_time_classified);
            viewHolder.Rs = convertView.findViewById(R.id.txt_rs_classified);
            convertView.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder) convertView.getTag();
        }
        ClassufiedContructor contructor = arrayList.get(position);
        Picasso.get()
                .load(contructor.getImages())
                .into(viewHolder.images);
        viewHolder.title.setText(contructor.getTitle());
        viewHolder.SubTile.setText(contructor.getSubTile());
        viewHolder.time.setText(contructor.getTime());
        viewHolder.Rs.setText(context.getResources().getString(R.string.txt_Rs)+" "+contructor.getRs());
        return convertView;
    }
    class ViewHolder{
        CircleImageView images;
        TextView title;
        TextView SubTile;
        TextView time;
        TextView Rs;

    }
}
