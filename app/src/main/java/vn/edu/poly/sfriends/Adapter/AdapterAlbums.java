package vn.edu.poly.sfriends.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.poly.sfriends.Model.AlbumsContructor;
import vn.edu.poly.sfriends.Model.AllConverSationContructor;
import vn.edu.poly.sfriends.R;

public class AdapterAlbums extends BaseAdapter {
    ArrayList<AlbumsContructor> arrayList;
    Context context;
    LayoutInflater inflater;

    public AdapterAlbums(ArrayList<AlbumsContructor> arrayList, Context context) {
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
            convertView = inflater.inflate(R.layout.gridview_albums,null);
            viewHolder.imageView = convertView.findViewById(R.id.imagesMainGridview);
            viewHolder.title = convertView.findViewById(R.id.txt_titl_albums);
            convertView.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder) convertView.getTag();
        }
        AlbumsContructor contructor = arrayList.get(position);
        Picasso.get()
                .load(contructor.getImages())
                .into(viewHolder.imageView);
        viewHolder.title.setText(contructor.getTitle());
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView title;

    }
}
