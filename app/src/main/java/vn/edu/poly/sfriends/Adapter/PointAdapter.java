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
import vn.edu.poly.sfriends.Model.PointContructor;
import vn.edu.poly.sfriends.R;

public class PointAdapter extends BaseAdapter {
    ArrayList<PointContructor> arrayList;
    Context context;
    LayoutInflater inflater;

    public PointAdapter(ArrayList<PointContructor> arrayList, Context context) {
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
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_listview_point, null);
            viewHolder.title = convertView.findViewById(R.id.title_point);
            viewHolder.time = convertView.findViewById(R.id.time_point);
            viewHolder.point = convertView.findViewById(R.id.point);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PointContructor contructor = arrayList.get(position);
        viewHolder.title.setText(contructor.getTitle());
        viewHolder.time.setText(contructor.getTime());
        viewHolder.point.setText(" + "+contructor.getPoint()+ " " +context.getResources().getString(R.string.txt_point));
        return convertView;
    }

    class ViewHolder {
        TextView title;
        TextView time;
        TextView point;

    }
}
