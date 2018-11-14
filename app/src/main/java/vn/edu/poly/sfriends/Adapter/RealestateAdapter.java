package vn.edu.poly.sfriends.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Component.ItemClick;
import vn.edu.poly.sfriends.Model.LoginContrucstor;
import vn.edu.poly.sfriends.Model.RealestateContructor;
import vn.edu.poly.sfriends.R;

public class RealestateAdapter extends RecyclerView.Adapter<RecyclerViewHolderReales> {
    ArrayList<RealestateContructor> arrayList;
    Context context;
    LayoutInflater inflater;
    public RealestateAdapter(ArrayList<RealestateContructor> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @NonNull
    @Override
    public RecyclerViewHolderReales onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_rcyview_batdongsan,viewGroup,false);
        RecyclerViewHolderReales vh = new RecyclerViewHolderReales(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderReales viewHolder, int i) {
        final RealestateContructor playList = arrayList.get(i);
        viewHolder.catogary.setText(playList.getCatogary());
        viewHolder.title.setText(playList.getTitle());
        Picasso.get()
                .load(playList.getImages())
                .placeholder(R.drawable.email)
                .error(R.drawable.email)
                .into(viewHolder.images);
        viewHolder.setItemClickListener(new ItemClick() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick)
                    Toast.makeText(context, "Không có chức năng", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(context, "Không có chức năng \n Vị Trí : "+position, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
class RecyclerViewHolderReales extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener // Implement 2 sự kiện onClick và onLongClick
{
    ImageView images;
    TextView catogary,title;
    private ItemClick itemClickListener; // Khai báo interface
    public RecyclerViewHolderReales(View itemView) {
        super(itemView);
        images = (ImageView) itemView.findViewById(R.id.img_batdongsan);
        catogary = (TextView) itemView.findViewById(R.id.txt_category);
        title = (TextView) itemView.findViewById(R.id.txt_title);

        itemView.setOnClickListener(this); // Mấu chốt ở đây , set sự kiên onClick cho View
        itemView.setOnLongClickListener(this); // Mấu chốt ở đây , set sự kiên onLongClick cho View
    }

    //Tạo setter cho biến itemClickListenenr
    public void setItemClickListener(ItemClick itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false); // Gọi interface , false là vì đây là onClick
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true); // Gọi interface , true là vì đây là onLongClick
        return true;
    }
}
