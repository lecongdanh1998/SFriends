package vn.edu.poly.sfriends.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.poly.sfriends.Component.BaseActivity;
import vn.edu.poly.sfriends.Model.AllConverSationContructor;
import vn.edu.poly.sfriends.R;

public class AdapterAllConverSation extends BaseAdapter {
    ArrayList<AllConverSationContructor> arrayList;
    Context context;
    LayoutInflater inflater;

    public AdapterAllConverSation(ArrayList<AllConverSationContructor> arrayList, Context context) {
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
            convertView = inflater.inflate(R.layout.custom_listview_all_conversation,null);
            viewHolder.imageView = convertView.findViewById(R.id.img_avatar);
            viewHolder.name = convertView.findViewById(R.id.txt_name);
            viewHolder.stt = convertView.findViewById(R.id.txt_stt);
            viewHolder.time = convertView.findViewById(R.id.txt_time);
            viewHolder.title = convertView.findViewById(R.id.txt_title);
            viewHolder.voteSenior = convertView.findViewById(R.id.txt_votessenior);
            viewHolder.voteMiniter = convertView.findViewById(R.id.txt_votes);
            viewHolder.Subtitle = convertView.findViewById(R.id.txt_subtile);
            viewHolder.Replies = convertView.findViewById(R.id.txt_numberReplies);
            viewHolder.Like = convertView.findViewById(R.id.txt_numberLike);
            viewHolder.LNL_votes = convertView.findViewById(R.id.LNL_votes);
            viewHolder.RLTL_cmt = convertView.findViewById(R.id.RLTL_cmt);
            viewHolder.seekBarvoteSenior = convertView.findViewById(R.id.votes_senior);
            viewHolder.seekBarvoteMiniter = convertView.findViewById(R.id.votes_Minister);
            viewHolder.timenotices1 = convertView.findViewById(R.id.txt_time_notices);
            viewHolder.timenotices2 = convertView.findViewById(R.id.txt_time_notices2);
            viewHolder.notices1 = convertView.findViewById(R.id.txt_notices1);
            viewHolder.notices2 = convertView.findViewById(R.id.txt_notices2);
            viewHolder.RLTL_notices = convertView.findViewById(R.id.CardView2);
            convertView.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder) convertView.getTag();
        }
        final AllConverSationContructor contructor = arrayList.get(position);
        Picasso.get()
                .load(contructor.getImages())
                .into(viewHolder.imageView);
//        viewHolder.seekBarvoteSenior.getThumb().mutate().setAlpha(0);
//        viewHolder.seekBarvoteMiniter.getThumb().mutate().setAlpha(0);
        viewHolder.name.setText(contructor.getName());
        viewHolder.stt.setText(contructor.getStt());
        viewHolder.time.setText(contructor.getTime());
        viewHolder.title.setText(contructor.getTitle());
        viewHolder.voteSenior.setText(contructor.getVoteSenior());
        viewHolder.voteMiniter.setText(contructor.getVoteMiniter());
        viewHolder.Subtitle.setText(contructor.getSubtitle());
        viewHolder.Replies.setText(contructor.getReplies());
        viewHolder.Like.setText(contructor.getLike());
        viewHolder.notices2.setText(contructor.getNotices2());
        viewHolder.notices1.setText(contructor.getNotices1());
        viewHolder.timenotices2.setText(contructor.getTimenotices2());
        viewHolder.timenotices1.setText(contructor.getTimenotices1());
        viewHolder.seekBarvoteSenior.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setProgress(Integer.parseInt(contructor.getVoteSenior()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        viewHolder.seekBarvoteMiniter.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setProgress(Integer.parseInt(contructor.getVoteMiniter()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        if(!viewHolder.voteSenior.getText().toString().equals("") || !viewHolder.voteMiniter.getText().toString().equals("")){
            viewHolder.RLTL_cmt.setVisibility(View.GONE);
            viewHolder.LNL_votes.setVisibility(View.VISIBLE);
            viewHolder.seekBarvoteSenior.setProgress(Integer.parseInt(contructor.getVoteSenior()));
            viewHolder.seekBarvoteMiniter.setProgress(Integer.parseInt(contructor.getVoteMiniter()));
            if(!viewHolder.notices2.getText().toString().equals("") || !viewHolder.notices1.getText().toString().equals("") ||
                    !viewHolder.timenotices2.getText().toString().equals("") || !viewHolder.timenotices1.getText().toString().equals("") ){
                viewHolder.RLTL_notices.setVisibility(View.VISIBLE);
            }else {
                viewHolder.RLTL_notices.setVisibility(View.GONE);

            }
        }
        if(!viewHolder.Subtitle.getText().toString().equals("") || !viewHolder.Replies.getText().toString().equals("")| !viewHolder.Like.getText().toString().equals("")){
            viewHolder.RLTL_cmt.setVisibility(View.VISIBLE);
            viewHolder.LNL_votes.setVisibility(View.GONE);
            if(!viewHolder.notices2.getText().toString().equals("") || !viewHolder.notices1.getText().toString().equals("") ||
                    !viewHolder.timenotices2.getText().toString().equals("") || !viewHolder.timenotices1.getText().toString().equals("") ){
                viewHolder.RLTL_notices.setVisibility(View.VISIBLE);
            }else {
                viewHolder.RLTL_notices.setVisibility(View.GONE);

            }
        }
        return convertView;
    }

    class ViewHolder{
        CircleImageView imageView;
        TextView name,stt,time,title,voteSenior,voteMiniter,Subtitle,Replies,Like;
        RelativeLayout RLTL_cmt,RLTL_notices;
        LinearLayout LNL_votes;
        SeekBar seekBarvoteSenior,seekBarvoteMiniter;
        TextView timenotices1,timenotices2,notices1,notices2;



    }
}
