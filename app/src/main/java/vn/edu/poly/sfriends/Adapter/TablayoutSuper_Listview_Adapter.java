package vn.edu.poly.sfriends.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.poly.sfriends.Model.TabLayoutSuper_listiew;
import vn.edu.poly.sfriends.R;

public class TablayoutSuper_Listview_Adapter extends BaseAdapter {
    ArrayList<TabLayoutSuper_listiew> arrayList;
    Context context;
    LayoutInflater inflater;

    public TablayoutSuper_Listview_Adapter(ArrayList<TabLayoutSuper_listiew> arrayList, Context context) {
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
            convertView = inflater.inflate(R.layout.custom_lst_homepagetoobarsub,null);
            viewHolder.images = convertView.findViewById(R.id.avatar_user_main_listview);
            viewHolder.title = convertView.findViewById(R.id.txt_title_lisivew_custom);
            viewHolder.time = convertView.findViewById(R.id.txt_time_lisivew_custom);
            viewHolder.day = convertView.findViewById(R.id.txt_day_lisivew_custom);
            viewHolder.cmt = convertView.findViewById(R.id.txt_cmt_lisivew_custom);
            viewHolder.like = convertView.findViewById(R.id.txt_like_lisivew_custom);
            viewHolder.star = convertView.findViewById(R.id.txt_star_lisivew_custom);
            viewHolder.imagesBackground = convertView.findViewById(R.id.img_imagesBackgound_listview);
            viewHolder.khuyenmai = convertView.findViewById(R.id.btn_khuyenmai_listview);
            viewHolder.NameShop = convertView.findViewById(R.id.txt_name_listview_custom);
            viewHolder.RelativeLayoutClick = convertView.findViewById(R.id.RelativeLayoutClick);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TabLayoutSuper_listiew contructor = arrayList.get(position);
        viewHolder.title.setText(contructor.getTitle());
        viewHolder.time.setText(contructor.getTime());
        viewHolder.day.setText(contructor.getDay());
        viewHolder.cmt.setText(contructor.getCmt());
        viewHolder.like.setText(contructor.getLike());
        viewHolder.star.setText(contructor.getStar());
        viewHolder.khuyenmai.setText(contructor.getKhuyenmai());
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
        viewHolder.RelativeLayoutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    class ViewHolder{
        CircleImageView images;
        TextView NameShop,title,time, day,cmt,like,star;
        ImageView imagesBackground;
        Button khuyenmai;
        RelativeLayout RelativeLayoutClick;
    }


}
