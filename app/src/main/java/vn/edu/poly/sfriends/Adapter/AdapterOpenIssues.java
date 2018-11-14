package vn.edu.poly.sfriends.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Model.AlbumsContructor;
import vn.edu.poly.sfriends.Model.OpenISSUESContructor;
import vn.edu.poly.sfriends.R;

public class AdapterOpenIssues extends BaseAdapter {
    ArrayList<OpenISSUESContructor> arrayList;
    Context context;
    LayoutInflater inflater;

    public AdapterOpenIssues(ArrayList<OpenISSUESContructor> arrayList, Context context) {
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
            convertView = inflater.inflate(R.layout.custom_lst_open_issues,null);
            viewHolder.status = convertView.findViewById(R.id.txt_status_open_issues);
            viewHolder.number = convertView.findViewById(R.id.txt_number_open_issues);
            viewHolder.time = convertView.findViewById(R.id.txt_time_open_issues);
            viewHolder.title = convertView.findViewById(R.id.txt_title_lstview);
            viewHolder.subtile = convertView.findViewById(R.id.txt_subtitle_lstview);
            viewHolder.name = convertView.findViewById(R.id.txt_name_open_issues);
            viewHolder.reply = convertView.findViewById(R.id.txt_replies_open_issues);
            convertView.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder) convertView.getTag();
        }
        OpenISSUESContructor contructor = arrayList.get(position);
        viewHolder.status.setText(contructor.getStatus());
        viewHolder.number.setText(context.getResources().getString(R.string.txt_ComplaintNumber) +": "+ contructor.getNumber());
        viewHolder.time.setText(contructor.getTime());
        viewHolder.title.setText(contructor.getTitle());
        viewHolder.subtile.setText(contructor.getSubtile());
        viewHolder.name.setText("By "+contructor.getName());
        viewHolder.reply.setText(contructor.getReply()+" "+context.getResources().getString(R.string.txt_Reply));
        if(viewHolder.status.getText().toString().equals("New")){
            viewHolder.status.setTextColor(context.getResources().getColor(R.color.text_color_Red));
            viewHolder.status.setBackgroundColor(context.getResources().getColor(R.color.textBackground_color_Red));
        }
        if(viewHolder.status.getText().toString().equals("In Progress")){
            viewHolder.status.setTextColor(context.getResources().getColor(R.color.text_color_ori));
            viewHolder.status.setBackgroundColor(context.getResources().getColor(R.color.textBackground_color_ori));
        }
        return convertView;
    }
    class ViewHolder{
        TextView status,number,time,title,subtile,name,reply;

    }
}
