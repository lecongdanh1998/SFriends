package vn.edu.poly.sfriends.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.poly.sfriends.Model.GiftStoreContructor;
import vn.edu.poly.sfriends.Model.TabLayoutSuper_listiew;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.View.HomePage.WebView.WebViewMain;

public class GiftStoreAdapter extends BaseAdapter {
    ArrayList<GiftStoreContructor> arrayList;
    Context context;
    LayoutInflater inflater;

    public GiftStoreAdapter(ArrayList<GiftStoreContructor> arrayList, Context context) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_lst_gift_store,null);
            viewHolder.images = convertView.findViewById(R.id.avatar_user_main_listview);
            viewHolder.title = convertView.findViewById(R.id.txt_title_lisivew_custom);
            viewHolder.time = convertView.findViewById(R.id.txt_time_lisivew_custom);;
            viewHolder.imagesBackground = convertView.findViewById(R.id.img_imagesBackgound_listview);
            viewHolder.NameShop = convertView.findViewById(R.id.txt_name_listview_custom);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GiftStoreContructor contructor = arrayList.get(position);
        viewHolder.title.setText(contructor.getTitle()+" "+context.getResources().getString(R.string.txt_point));
        viewHolder.time.setText(contructor.getTime());
        viewHolder.NameShop.setText(contructor.getNameShop());
        Picasso.get()
                .load(contructor.getImages())
                .placeholder(R.color.color_Purple)
                .error(R.color.color_Purple)
                .into(viewHolder.images);
        Picasso.get()
                .load(contructor.getImagesBackground())
                .placeholder(R.color.color_Purple)
                .error(R.color.color_Purple)
                .into(viewHolder.imagesBackground);
        return convertView;
    }
    class ViewHolder{
        CircleImageView images;
        TextView NameShop,title,time;
        ImageView imagesBackground;
    }


}
