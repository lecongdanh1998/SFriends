package vn.edu.poly.sfriends.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Model.UserIssuedByContructor;
import vn.edu.poly.sfriends.R;

public class AdapterUserIssuedBy extends BaseAdapter {
    LayoutInflater inflater ;
    Context context;
    ArrayList<UserIssuedByContructor> arrayList;

    public AdapterUserIssuedBy(Context context, ArrayList<UserIssuedByContructor> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
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
            convertView = inflater.inflate(R.layout.custom_spinner,null);
            viewHolder.txttitle = convertView.findViewById(R.id.txt_title_issueby);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        UserIssuedByContructor contructor = arrayList.get(position);
        viewHolder.txttitle.setText(contructor.getTitle());


        return convertView;
    }
    class ViewHolder{
        TextView txttitle;
    }
}