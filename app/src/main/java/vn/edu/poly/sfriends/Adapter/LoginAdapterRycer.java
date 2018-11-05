package vn.edu.poly.sfriends.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Component.ItemClick;
import vn.edu.poly.sfriends.Model.LoginContrucstor;
import vn.edu.poly.sfriends.R;

public class LoginAdapterRycer extends RecyclerView.Adapter<RecyclerViewHolder>  {
    ArrayList<LoginContrucstor> arrayList;
    Context context;
    LayoutInflater inflater;

    public LoginAdapterRycer(ArrayList<LoginContrucstor> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycelview_login,viewGroup,false);
        RecyclerViewHolder vh = new RecyclerViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder viewHolder, int i) {
        final LoginContrucstor playList = arrayList.get(i);
        Picasso.get()
                .load(playList.getImage())
                .placeholder(R.drawable.email)
                .error(R.drawable.email)
                .into(viewHolder.imagesChanel);
        viewHolder.setItemClickListener(new ItemClick() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick)
                    Toast.makeText(context, "Không có chức năng", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(context, "Không có chức năng \n Vị Trí : "+position, Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(context,MainActivity.class);
//                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener // Implement 2 sự kiện onClick và onLongClick
{
    ImageView imagesChanel;

    private ItemClick itemClickListener; // Khai báo interface
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        imagesChanel = (ImageView) itemView.findViewById(R.id.imagesLoginRecucelview);
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
