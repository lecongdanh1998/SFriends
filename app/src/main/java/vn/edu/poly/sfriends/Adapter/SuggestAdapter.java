package vn.edu.poly.sfriends.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import vn.edu.poly.sfriends.Component.ItemClick;
import vn.edu.poly.sfriends.Model.SeveralModel;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.View.MainActivity;

public class SuggestAdapter extends RecyclerView.Adapter<SuggestAdapter.ViewHolder> {

    List<SeveralModel> listSeveral;
    Context context;

    public SuggestAdapter(List<SeveralModel> listSeveral, Context context) {
        this.listSeveral = listSeveral;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_suggest_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Picasso.get().load(R.drawable.restaurant).into(viewHolder.img_suggest);
        viewHolder.txt_name_suggest.setText(listSeveral.get(i).getName());
        viewHolder.txt_price_suggest.setText(listSeveral.get(i).getPrice());
        viewHolder.setClickListener(new ItemClick() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(context, "#" + position + " - " + listSeveral.get(position) +
                            " " +
                            "(Long click)", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, MainActivity.class));
                } else {
                    Toast.makeText(context, "#" + position + " - " + listSeveral.get(position),
                            Toast
                            .LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSeveral.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener, View.OnLongClickListener {

        public ImageView img_suggest;
        public TextView txt_name_suggest, txt_price_suggest;
        private ItemClick clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            img_suggest = itemView.findViewById(R.id.img_suggest);
            txt_name_suggest = itemView.findViewById(R.id.txt_name_suggest);
            txt_price_suggest = itemView.findViewById(R.id.txt_price_suggest);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClick itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }

}