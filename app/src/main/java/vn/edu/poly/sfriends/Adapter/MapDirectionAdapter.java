package vn.edu.poly.sfriends.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.edu.poly.sfriends.Model.MapDirectionModel;
import vn.edu.poly.sfriends.R;

public class MapDirectionAdapter extends BaseAdapter {

    List<MapDirectionModel> listMapDirection;
    Context context;

    public MapDirectionAdapter(List<MapDirectionModel> listMapDirection, Context context) {
        this.listMapDirection = listMapDirection;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listMapDirection.size();
    }

    @Override
    public Object getItem(int position) {
        return listMapDirection.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        ImageView ic_map_direction;
        TextView txt_direction;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_map_direction, null);
            viewHolder.ic_map_direction = convertView.findViewById(R.id.ic_map_direction);
            viewHolder.txt_direction = convertView.findViewById(R.id.txt_direction);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        try{
            viewHolder.ic_map_direction.setImageResource(listMapDirection.get(position).getIcon());
            viewHolder.txt_direction.setText(Html.fromHtml(listMapDirection.get(position).getDirection()));
        } catch (Exception e){
        }
        return convertView;
    }
}
